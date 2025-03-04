from typing import Any, Optional

from pydantic import BaseModel


class ResponseModel(BaseModel):
    success: bool
    message: Optional[str] = None
    data: Optional[Any] = None
    errors: Optional[Any] = None
