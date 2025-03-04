import base64
import hashlib
import os

import requests

from src.auth.exceptions import InvalidAccessToken
from src.shared.config.app_config import (
    AUTH_URL,
    KEYCLOAK_CLIENT_ID,
    KEYCLOAK_CLIENT_SECRET,
    REDIRECT_URI,
    TOKEN_URL,
    USER_INFO_URL,
)


def generate_code_verifier_and_challenge():
    """
    Generates a code verifier and its corresponding code challenge for PKCE (Proof Key for Code Exchange).

    The code verifier is a high-entropy cryptographic random string using the unreserved characters [A-Z], [a-z], [0-9], "-", ".", "_", and "~", with a minimum length of 43 characters and a maximum length of 128 characters.

    The code challenge is a Base64 URL-encoded SHA256 hash of the code verifier.

    Returns:
        tuple: A tuple containing the code verifier and the code challenge.
    """
    code_verifier = base64.urlsafe_b64encode(os.urandom(32)).decode("utf-8").rstrip("=")
    code_challenge = (
        base64.urlsafe_b64encode(hashlib.sha256(code_verifier.encode("utf-8")).digest())
        .decode("utf-8")
        .rstrip("=")
    )
    return code_verifier, code_challenge


def get_auth_url():
    """
    Constructs the authorization URL for initiating the OAuth2 flow with PKCE.

    This function generates a code verifier and its corresponding code challenge,
    then constructs the authorization URL using these values along with the client
    credentials and redirect URI.

    The code verifier is stored globally for later use in the token exchange process.

    Returns:
        str: The complete authorization URL to redirect the user to the Keycloak login page.
    """
    code_verifier, code_challenge = generate_code_verifier_and_challenge()
    global stored_code_verifier
    stored_code_verifier = code_verifier
    return (
        f"{AUTH_URL}?client_id={KEYCLOAK_CLIENT_ID}&redirect_uri={REDIRECT_URI}"
        f"&response_type=code&scope=openid"
        f"&code_challenge={code_challenge}&code_challenge_method=S256"
    )


def refresh_token(refresh_token: str):
    """
    Refreshes the access token using the provided refresh token.

    This function sends a POST request to the token endpoint with the refresh token
    and client credentials to obtain a new access token.

    Args:
        refresh_token (str): The refresh token used to obtain a new access token.

    Returns:
        dict: A dictionary containing the new access token and other related information.

    Raises:
        InvalidAccessToken: If the token refresh process fails, an InvalidAccessToken is raised
                            with a detail message.
    """
    data = {
        "grant_type": "refresh_token",
        "refresh_token": refresh_token,
        "client_id": KEYCLOAK_CLIENT_ID,
    }
    if KEYCLOAK_CLIENT_SECRET:
        data["client_secret"] = KEYCLOAK_CLIENT_SECRET
    response = requests.post(TOKEN_URL, data=data)
    if response.status_code != 200:
        raise InvalidAccessToken("Failed to refresh token")
    return response.json()


def get_token(code: str):
    """
    Exchanges the authorization code for an access token.

    This function sends a POST request to the token endpoint with the authorization
    code, client credentials, and code verifier to obtain an access token.

    Args:
        code (str): The authorization code received from the authorization server.

    Returns:
        dict: A dictionary containing the access token and other related information.

    Raises:
        InvalidAccessToken: If the token exchange process fails, an InvalidAccessToken is raised
                            with a detail message.
    """
    global stored_code_verifier
    data = {
        "grant_type": "authorization_code",
        "code": code,
        "redirect_uri": REDIRECT_URI,
        "client_id": KEYCLOAK_CLIENT_ID,
        "code_verifier": stored_code_verifier,
    }
    if KEYCLOAK_CLIENT_SECRET:
        data["client_secret"] = KEYCLOAK_CLIENT_SECRET
    response = requests.post(TOKEN_URL, data=data)
    print("hello", response.status_code)
    if response.status_code != 200:
        raise InvalidAccessToken("Failed to fetch token")
    return response.json()


def get_user_info(access_token: str):
    """
    Retrieves user information from the authorization server using the provided access token.

    This function sends a GET request to the user info endpoint with the access token
    included in the Authorization header to obtain user details.

    Args:
        access_token (str): The access token used to authenticate the request.

    Returns:
        dict: A dictionary containing the user information retrieved from the server.

    Raises:
        InvalidAccessToken: If the request to fetch user information fails, an InvalidAccessToken
                            is raised with a detail message.
    """
    headers = {"Authorization": f"Bearer {access_token}"}
    response = requests.get(USER_INFO_URL, headers=headers)
    print(response.status_code)
    if response.status_code != 200:
        raise InvalidAccessToken("Failed to fetch user info")
    return response.json()
