"""
This module loads and parses the configuration settings from the environment variables.

The configuration settings include:
- PostgreSQL database settings
- Environment settings
"""
from __future__ import annotations

import json
import os

# Extract configuration settings from environment variables
POSTGRE_DATABASE = json.loads(os.getenv("PG_DATABASES"))
ENV = os.getenv("ENV")
KEYCLOAK_SERVER_URL = os.getenv("KEYCLOAK_SERVER_URL")
KEYCLOAK_REALM = os.getenv("KEYCLOAK_REALM")
KEYCLOAK_CLIENT_ID = os.getenv("KEYCLOAK_CLIENT_ID")
KEYCLOAK_CLIENT_SECRET = os.getenv("KEYCLOAK_CLIENT_SECRET")
REDIRECT_URI = os.getenv("REDIRECT_URI")
BASE_URL = f"{KEYCLOAK_SERVER_URL}/realms/{KEYCLOAK_REALM}/protocol/openid-connect"
AUTH_URL = f"{BASE_URL}/auth"
TOKEN_URL = f"{BASE_URL}/token"
USER_INFO_URL = f"{BASE_URL}/userinfo"
KEYCLOAK_REALM_URL = BASE_URL
