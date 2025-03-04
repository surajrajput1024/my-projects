import time

from fastapi import Depends, Request
from jwt import ExpiredSignatureError, InvalidTokenError, PyJWKClient, decode

from src.auth.exceptions import (
    AccessTokenExpired,
    AuthenticationError,
    InvalidAccessToken,
    NoAccessTokenProvided,
)
from src.auth.oauth import get_user_info
from src.auth.oauth import refresh_token as refresh_access_token
from src.auth.repositories.user import User as UserRepository
from src.shared.config.app_config import KEYCLOAK_CLIENT_ID, KEYCLOAK_REALM_URL
from src.shared.initializers import get_db_session

JWKS_URL = f"{KEYCLOAK_REALM_URL}/certs"
jwks_client = PyJWKClient(JWKS_URL)


def get_repo(db_session=Depends(get_db_session)):
    return UserRepository(db_session)


def extract_access_token(request: Request) -> str:
    """Extract access token from Authorization header or cookies."""
    auth_header = request.headers.get("Authorization")
    if auth_header and auth_header.startswith("Bearer "):
        return auth_header.split(" ")[1]
    token = request.cookies.get("access_token")
    if token:
        return token
    raise NoAccessTokenProvided()


def decode_token(token: str, ignore_expiration: bool = False) -> dict:
    """Decode the given access token to extract claims."""
    try:
        signing_key = jwks_client.get_signing_key_from_jwt(token)
        return decode(
            token,
            signing_key.key,
            algorithms=["RS256"],
            audience=KEYCLOAK_CLIENT_ID,
            options={"verify_exp": not ignore_expiration, "verify_aud": False},
        )
    except ExpiredSignatureError:
        if ignore_expiration:
            return decode(
                token,
                signing_key.key,
                algorithms=["RS256"],
                audience=KEYCLOAK_CLIENT_ID,
                options={"verify_exp": False, "verify_aud": False},
            )
        raise AccessTokenExpired()
    except InvalidTokenError:
        raise InvalidAccessToken()


def retrieve_refresh_token(repo: UserRepository, user_id: str) -> str:
    """Retrieve refresh token from the database based on user ID."""
    db_user = repo.find_user_by_user_id(user_id)
    if db_user and db_user.refresh_token:
        return db_user.refresh_token
    raise ValueError("No refresh token found for the user")


def refresh_access_token_and_update_db(
    repo: UserRepository, refresh_token: str, user_id: str
) -> str:
    """Refresh the access token using the refresh token and update the database."""
    try:
        token_data = refresh_access_token(refresh_token)
        new_access_token = token_data.get("access_token")
        new_refresh_token = token_data.get("refresh_token")
        db_user = repo.find_user_by_user_id(user_id)
        if new_refresh_token and db_user:
            db_user.refresh_token = new_refresh_token
            repo.save(db_user)
        return new_access_token
    except Exception as e:
        raise InvalidAccessToken(f"Failed to refresh access token with error {str(e)}")


def is_token_expiring_soon(decoded_token: dict, buffer_time: int = 300) -> bool:
    """Check if the token is expiring soon within the buffer time provided."""
    exp_time = decoded_token.get("exp")
    if not exp_time:
        return False
    current_time = int(time.time())
    time_remaining = exp_time - current_time
    return time_remaining < buffer_time and time_remaining > 0


async def get_current_user(request: Request, repo=Depends(get_repo)):
    """
    Extracts and validates the access token from the request.

    If the access token is expired, raises 401 Unauthorized.
    If the token is about to expire, refresh it.
    Raises an HTTPException if unable to refresh or validate the token.

    Args:
        request (Request): The incoming HTTP request object.
        repo (UserRepository): The user repository instance.

    Returns:
        dict: The user information if the token is valid.

    Raises:
        HTTPException: Raises an HTTPException if user authentication fails.
    """
    try:
        token = extract_access_token(request)
        decoded_token = decode_token(token, ignore_expiration=True)
    except (NoAccessTokenProvided, AccessTokenExpired, InvalidAccessToken) as e:
        raise AuthenticationError(str(e))

    user_id = decoded_token.get("sub")
    if not user_id:
        raise InvalidAccessToken("User ID not found in token")

    if is_token_expiring_soon(decoded_token):
        try:
            refresh_token = retrieve_refresh_token(repo, user_id)
            new_access_token = refresh_access_token_and_update_db(
                repo, refresh_token, user_id
            )
            user_info = get_user_info(new_access_token)
            user_info["access_token_refreshed"] = True
        except InvalidAccessToken as e:
            raise AuthenticationError(str(e))
    else:
        try:
            user_info = get_user_info(token)
        except InvalidAccessToken as e:
            raise AuthenticationError(str(e))

    return user_info
