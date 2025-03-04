from fastapi import HTTPException

from src.auth.oauth import get_token, get_user_info


class Service:
    def __init__(self, user_repository) -> None:
        """
        Initializes the Service class with a user repository.

        Args:
            user_repository: An instance of a user repository for managing user data.
        """
        self.user_repository = user_repository

    def process_callback(self, code):
        """
        Processes the OAuth2 callback by exchanging the authorization code for tokens
        and retrieving user information.

        Args:
            code (str): The authorization code received from the OAuth2 provider.

        Returns:
            dict: A dictionary containing a success message, user information, and the access token.

        Raises:
            HTTPException: If the authorization code is not provided or token exchange fails.
        """
        if not code:
            raise HTTPException(status_code=400, detail="Authorization code not found")

        # Exchange the code for a token
        token_data = get_token(code)
        access_token = token_data.get("access_token")
        refresh_token = token_data.get("refresh_token")

        # Get user information using the access token
        user_info = get_user_info(access_token)
        user_info["refresh_token"] = refresh_token
        # Create or update the user in the repository
        saved_user = self.user_repository.create_or_update_user(user_info)

        return {
            "user": saved_user,
            "access_token": access_token,
        }
