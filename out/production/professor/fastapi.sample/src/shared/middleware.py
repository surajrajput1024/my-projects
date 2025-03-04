import json
import logging
import uuid

from fastapi import Request, Response
from fastapi.responses import JSONResponse

from src.shared.exception_mapping import (
    DEFAULT_DETAIL,
    DEFAULT_STATUS_CODE,
    EXCEPTION_STATUS_CODE_MAPPING,
)
from src.shared.models.response import ResponseModel

logger = logging.getLogger(__name__)

excluded_paths = ["/auth/login", "/docs", "/redoc", "/openapi.json"]


async def global_exception_handler(request: Request, call_next):
    request_id = request.headers.get("X-Request-ID", str(uuid.uuid4()))
    logger.info(f"Request ID: {request_id} - Request: {request.method} {request.url}")

    try:
        # Check if the request path should be excluded from the middleware
        if _is_excluded_path(request.url.path):
            response = await call_next(request)
            logger.info(
                f"Request ID: {request_id} - Excluded path response status code: {response.status_code}"
            )
            return response

        response = await call_next(request)
        logger.info(
            f"Request ID: {request_id} - Response status code: {response.status_code}"
        )
        response.headers["X-Request-ID"] = request_id

        if _is_successful_response(response.status_code):
            return await _create_successful_response(response)

        return response

    except Exception as exc:
        logger.exception(f"Request ID: {request_id} - Exception occurred: {exc}")
        return _create_error_response(exc)


def _is_excluded_path(path: str) -> bool:
    # Define paths that should be excluded from this middleware
    return any(path.startswith(excluded_path) for excluded_path in excluded_paths)


def _is_successful_response(status_code: int) -> bool:
    return 200 <= status_code < 300


async def _create_successful_response(response: Response) -> JSONResponse:
    body = b"".join([chunk async for chunk in response.body_iterator])
    data = json.loads(body.decode("utf-8"))

    response_data = ResponseModel(
        success=True,
        message="Operation successful.",
        data=data,
        errors=None,
    )

    new_response = JSONResponse(
        status_code=response.status_code,
        content=response_data.model_dump(),
        headers=_get_updated_headers(response),
    )
    return new_response


def _create_error_response(exc: Exception) -> JSONResponse:
    status_code, detail = _map_exception_to_status_and_detail(exc)

    error_response = ResponseModel(
        success=False,
        message="An error occurred.",
        data=None,
        errors={"detail": detail},
    )

    final_response = JSONResponse(
        status_code=status_code, content=error_response.model_dump()
    )
    return final_response


def _map_exception_to_status_and_detail(exc: Exception) -> tuple:
    status_code = DEFAULT_STATUS_CODE
    detail = DEFAULT_DETAIL

    for exception_class, (
        status_code_map,
        default_detail,
    ) in EXCEPTION_STATUS_CODE_MAPPING.items():
        if isinstance(exc, exception_class):
            return status_code_map, str(exc) if str(exc) else default_detail

    return status_code, detail


def _get_updated_headers(response: Response) -> dict:
    headers = dict(response.headers)
    headers.pop("content-length", None)
    return headers
