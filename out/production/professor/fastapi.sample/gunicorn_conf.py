from __future__ import annotations

import os

command = "gunicorn"
pythonpath = "src"
bind = f"0.0.0.0:{os.getenv('PORT', 9001)}"
workers = int(os.getenv("GUNICORN_WORKERS", 2))
worker_class = "uvicorn.workers.UvicornWorker"
timeout = 300
