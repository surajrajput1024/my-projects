from src.auth.models.user import User as UserModel
from src.auth.repositories.exceptions import (
    DatabaseSaveException,
    UserIdMissingFromToken,
)


class User:
    def __init__(self, db_session) -> None:
        """
        Initializes the User repository with a database session.

        Args:
            db_session: The database session used for querying and committing changes.
        """
        self.db_session = db_session

    def find_user_by_user_id(self, user_id):
        """
        Finds a user in the database by their user ID.

        Args:
            user_id (str): The ID of the user to find.

        Returns:
            UserModel: The user object if found, otherwise None.
        """
        return self.db_session.query(UserModel).filter_by(user_id=user_id).first()

    def create_or_update_user(self, user_dict):
        """
        Creates a new user or updates an existing user in the database.

        This method checks if a user with the given ID exists in the database. If the user exists,
        it updates the user's attributes with the non-null values from the provided dictionary.
        If the user does not exist, it creates a new user with the data from the dictionary.

        Args:
            user_dict (dict): A dictionary containing user information. Must include the "sub" key
                              representing the user ID.

        Returns:
            UserModel: The created or updated user object.

        Raises:
            ValueError: If the "sub" key is not present in the user_dict.
        """
        user_id = user_dict.get("sub")
        if not user_id:
            raise UserIdMissingFromToken("User ID (sub) is required in user_dict")

        # Check if the user already exists
        user = self.db_session.query(UserModel).filter_by(user_id=user_id).first()

        if user:
            # Update existing user with non-null values from user_dict
            for key, value in user_dict.items():
                if value is not None and hasattr(user, key):
                    setattr(user, key, value)
        else:
            # Create a new user
            user_data = {
                key: user_dict.get(key)
                for key in UserModel.__table__.columns.keys()
                if key in user_dict
            }
            user_data["user_id"] = user_id
            user = UserModel(**user_data)
            self.db_session.add(user)

        # Commit the transaction
        self.db_session.commit()
        self.db_session.refresh(user)
        return user

    def save(self, user):
        """
        Saves the user model instance to the database.

        This method attempts to commit the user instance to the database session.
        If an exception occurs during the commit, it will rollback the session
        and raise an HTTPException.

        Args:
            user (UserModel): The user model instance to be saved.

        Returns:
            UserModel: The saved user model instance.

        Raises:
            HTTPException: If there is an error during the database commit.
        """
        try:
            self.db_session.add(user)
            self.db_session.commit()
            self.db_session.refresh(user)
            return user
        except Exception as e:
            self.db_session.rollback()
            raise DatabaseSaveException(f"Failed to save user: {str(e)}")
