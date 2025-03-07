"""added access_token ffield and relevant indexes

Revision ID: b93d9651f86c
Revises: 95702bec778c
Create Date: 2024-11-13 15:01:10.205453

"""
from typing import Sequence, Union

import sqlalchemy as sa

from alembic import op

# revision identifiers, used by Alembic.
revision: str = "b93d9651f86c"
down_revision: Union[str, None] = "95702bec778c"
branch_labels: Union[str, Sequence[str], None] = None
depends_on: Union[str, Sequence[str], None] = None


def upgrade() -> None:
    # ### commands auto generated by Alembic - please adjust! ###
    op.add_column("users", sa.Column("access_token", sa.String(), nullable=True))
    op.create_index(
        op.f("ix_users_access_token"), "users", ["access_token"], unique=False
    )
    # ### end Alembic commands ###


def downgrade() -> None:
    # ### commands auto generated by Alembic - please adjust! ###
    op.drop_index(op.f("ix_users_access_token"), table_name="users")
    op.drop_column("users", "access_token")
    # ### end Alembic commands ###
