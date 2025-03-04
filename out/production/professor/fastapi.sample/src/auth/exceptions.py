class NoAccessTokenProvided(Exception):
    """Exception raised when no access token is provided in the request."""

    def __init__(self, message="No access token provided"):
        self.message = message
        super().__init__(self.message)


class AccessTokenExpired(Exception):
    """Exception raised when the access token has expired."""

    def __init__(self, message="Access token has expired"):
        self.message = message
        super().__init__(self.message)


class InvalidAccessToken(Exception):
    """Exception raised when the access token is invalid."""

    def __init__(self, message="Invalid access token"):
        self.message = message
        super().__init__(self.message)


class AuthenticationError(Exception):
    """Exception raised for authentication errors."""

    def __init__(self, message="Authentication failed"):
        self.message = message
        super().__init__(self.message)
