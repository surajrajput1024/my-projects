from __future__ import annotations

import argparse
import code
import os
import readline
import rlcompleter
import subprocess

import uvicorn
from dotenv import load_dotenv

dotenv_path = os.path.join(os.path.dirname(__file__), "src", "shared", "config", ".env")
load_dotenv(dotenv_path=dotenv_path)


from src.shared.config.app_config import ENV
from src.shared.initializers import Initializers


def initialise_command_line_args():
    """
    Initializes and returns the command line argument parser.

    Returns:
        argparse.ArgumentParser: The argument parser with defined arguments.
    """
    parent_parser = argparse.ArgumentParser()
    parent_parser.add_argument(
        "--console",
        nargs="?",
        default=False,
        const=True,
        help="Use this to open interactive console",
    )
    parent_parser.add_argument(
        "--server", nargs="?", default=False, const=True, help="Use this to run server"
    )
    return parent_parser


def open_console():
    """
    Opens an interactive Python console with tab completion enabled.
    """
    vars = globals()
    vars.update(locals())
    readline.set_completer(rlcompleter.Completer(vars).complete)
    readline.parse_and_bind("tab: complete")
    shell = code.InteractiveConsole(vars)
    shell.interact()


def run_server():
    """
    Runs the server using Uvicorn in development/local
    environments or Gunicorn in other environments.
    """
    if ENV == "dev" or ENV == "local":
        uvicorn.run("src.main:app", host="0.0.0.0", port=9001, reload=True)
    else:
        command = [
            "gunicorn",
            "-k",
            "uvicorn.workers.UvicornWorker",
            "-c",
            "gunicorn_conf.py",
            "src.main:app",
        ]
        subprocess.run(command)


if __name__ == "__main__":
    parser = initialise_command_line_args()
    args = vars(parser.parse_args())
    initializer = Initializers()
    initializer.connect_to_db()
    if args.get("console", None):
        open_console()
    elif args.get("server", None):
        run_server()
