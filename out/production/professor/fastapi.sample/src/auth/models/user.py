from sqlalchemy import JSON, Boolean, Column, String

from src.shared.initializers import Base


class User(Base):
    __tablename__ = "users"

    user_id = Column(String, primary_key=True, unique=True, index=True)
    email_verified = Column(Boolean, default=False)
    name = Column(String, nullable=True)
    preferred_username = Column(String, unique=True, nullable=True)
    given_name = Column(String, nullable=True)
    family_name = Column(String, nullable=True)
    email = Column(String, unique=True, nullable=True)
    roles = Column(JSON, nullable=True)
    attributes = Column(JSON, nullable=True)
    refresh_token = Column(String, nullable=True)
    token_expiry = Column(String, nullable=True)
