from fastapi import APIRouter, Depends, Request
from fastapi.responses import RedirectResponse

from src.auth.oauth import get_auth_url
from src.auth.repositories.user import User as UserRepository
from src.auth.service import Service as UserService
from src.shared.initializers import get_db_session

router = APIRouter()


async def get_service(db_session=Depends(get_db_session)):
    """
    Dependency function to provide a UserService instance.

    This function creates a UserRepository using the provided database session
    and returns a UserService instance initialized with this repository.

    Args:
        db_session: The database session dependency provided by FastAPI.

    Returns:
        UserService: An instance of the UserService class.
    """
    user_repo = UserRepository(db_session)
    return UserService(user_repo)


@router.get("/login")
async def login():
    """
    Initiates the login process by redirecting to the Keycloak authorization URL.

    This endpoint constructs the authorization URL using the OAuth2 flow and
    redirects the user to Keycloak for authentication.

    Returns:
        RedirectResponse: A response object that redirects the user to the Keycloak login page.
    """
    auth_url = get_auth_url()
    return RedirectResponse(auth_url)


@router.get("/keycloak_auth_callback")
async def callback(request: Request, service=Depends(get_service)):
    """
    Handles the OAuth2 callback from Keycloak.

    This endpoint processes the authorization code received from Keycloak,
    exchanges it for tokens, and retrieves user information.

    Args:
        request (Request): The incoming HTTP request containing the authorization code.
        service (UserService): The UserService dependency for processing the callback.

    Returns:
        dict: A dictionary containing a success message, user information, and the access token.
    """
    code = request.query_params.get("code")
    print("here again", code)
    return service.process_callback(code)
