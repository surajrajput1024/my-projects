"""
Main application entry point for the FastAPI service.

This module sets up the FastAPI application, including middleware, exception handlers,
logging configuration, and routers for different service endpoints.
"""
from __future__ import annotations

import logging.config
from contextlib import asynccontextmanager

from fastapi import Depends, FastAPI
from fastapi.exceptions import RequestValidationError
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import JSONResponse

from src.auth.auth import get_current_user
from src.auth.router import router as auth_router
from src.shared.config.logging import LOGGING_CONFIG
from src.shared.initializers import initializer
from src.shared.middleware import global_exception_handler
from src.shared.models.response import (  # Adjust the import path as necessary
    ResponseModel,
)

# Apply the logging configuration
logging.config.dictConfig(LOGGING_CONFIG)

# Initialize the logger
logger = logging.getLogger(__name__)


@asynccontextmanager
async def lifespan(app: FastAPI):
    """
    Context manager for the application lifespan.

    This function handles startup and shutdown logic for the FastAPI application.

    Args:
        app (FastAPI): The FastAPI application instance.
    """
    # Startup logic
    initializer.connect_to_db()

    yield

    # Shutdown logic
    initializer.shutdown()


# Initialize the FastAPI application with the lifespan context manager
app = FastAPI(lifespan=lifespan)

# Include middlewares
app.middleware("http")(global_exception_handler)


# Add the validation exception handler
@app.exception_handler(RequestValidationError)
async def validation_exception_handler(request, exc):
    logger.exception(f"Validation error: {exc}")

    # Create the standardized error response
    error_response = ResponseModel(
        success=False, message="Validation error.", data=None, errors=exc.errors()
    )

    return JSONResponse(
        status_code=422,
        content=error_response.dict(),
    )  #


# Define allowed origins for CORS
origins = ["http://localhost:3000"]

# Add CORS middleware to the application
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Include routers for different service endpoints
app.include_router(auth_router, prefix="/auth")


@app.get("/")
async def read_root(current_user: dict = Depends(get_current_user)):
    """
    Root endpoint of the application.

    Returns:
        dict: A simple greeting message.
    """
    return {"Hello": "World"}


@app.get("/ping")
async def ping():
    return {"message": "alive"}
