import os

# Define the directory paths for logging
current_dir = os.path.dirname(os.path.abspath(__file__))
root_dir = os.path.join(current_dir, "..")
log_directory = os.path.join(root_dir, "log")

# Create the log directory if it does not exist
if not os.path.exists(log_directory):
    os.makedirs(log_directory)
LOGGING_CONFIG = {
    "version": 1,
    "disable_existing_loggers": False,
    "formatters": {
        "default": {
            "format": "%(asctime)s - %(name)s - %(levelname)s - %(message)s",
        },
    },
    "handlers": {
        "console": {
            "class": "logging.StreamHandler",
            "formatter": "default",
            "level": "DEBUG",  # Set this to DEBUG to show all levels on console
        },
        "info_file_handler": {
            "class": "logging.FileHandler",
            "formatter": "default",
            "level": "INFO",
            "filename": os.path.join(log_directory, "info.log"),
        },
        "error_file_handler": {
            "class": "logging.FileHandler",
            "formatter": "default",
            "level": "ERROR",
            "filename": os.path.join(log_directory, "error.log"),
        },
        "debug_file_handler": {
            "class": "logging.FileHandler",
            "formatter": "default",
            "level": "DEBUG",
            "filename": os.path.join(log_directory, "debug.log"),
        },
    },
    "root": {
        "handlers": ["debug_file_handler", "info_file_handler", "error_file_handler"],
        "level": "DEBUG",
    },
}
