from http import HTTPStatus

from src.auth.exceptions import AuthenticationError

EXCEPTION_STATUS_CODE_MAPPING = {
    AuthenticationError: (HTTPStatus.UNAUTHORIZED, "User Not Authorised"),
    # Add other exception mappings as needed
}

DEFAULT_STATUS_CODE = HTTPStatus.INTERNAL_SERVER_ERROR
DEFAULT_DETAIL = "An unexpected error occurred."
