from __future__ import annotations

from fastapi.logger import logger
from sqlalchemy import create_engine, text
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import scoped_session, sessionmaker

from src.shared.config.app_config import ENV, POSTGRE_DATABASE

Base = declarative_base()


class Initializers:
    """
    A class to handle the initialization of
    various services such as database, Redis, and Firebase.

    Attributes:
        db_url (str): The database URL for PostgreSQL connection.
        SessionLocal (scoped_session | None):
        SQLAlchemy session maker for database connections.
        redis_client (redis.StrictRedis | None):
        Redis client for caching and other Redis operations.
    """

    def __init__(self):
        """
        Initializes the Initializers
        class with database URL
        and sets up placeholders
        for session and Redis client.
        """
        db_name = f"gonimbus_{ENV}"
        user, password, host, port = (
            POSTGRE_DATABASE[db_name][key]
            for key in ("username", "password", "host", "port")
        )
        self.db_url = f"postgresql://{user}:{password}@{host}:{port}/{db_name}"
        self.SessionLocal = None
        self.redis_client = None

    def connect_to_db(self) -> None:
        """
        Establishes a connection to the PostgreSQL database and verifies the connection.

        Raises:
            Exception: If there is an error connecting to the database.
        """
        if not self.SessionLocal:
            engine = create_engine(self.db_url)
            self.SessionLocal = scoped_session(
                sessionmaker(
                    autocommit=False,
                    autoflush=False,
                    bind=engine,
                    expire_on_commit=False,
                )
            )
            print("Connection established")

        session = self.SessionLocal()
        try:
            session.execute(text("SELECT 1"))
            logger.info("Database connection successful")
        except Exception as e:
            logger.error(f"Error connecting to database: {e}")
            raise
        finally:
            session.close()

    def shutdown(self) -> None:
        """
        Shuts down the database session if it is initialized.
        """
        if self.SessionLocal:
            logger.info("Closing database session")
            session = self.SessionLocal()
            session.close()
        else:
            logger.error("SessionLocal is not initialized")


initializer = Initializers()


def get_db_session():
    """
    Provides a database session for use in FastAPI dependency injection.

    Yields:
        session: The database session.

    Raises:
        Exception: If there is an error during the session.
    """
    session = initializer.SessionLocal()
    try:
        yield session
        session.commit()
    except Exception as e:
        session.rollback()
        raise e
    finally:
        session.close()
