#!/bin/bash

# Prompt the user for the module name
read -p "Enter the module name: " MODULE_NAME

# Check if the module name is provided
if [ -z "$MODULE_NAME" ]; then
  echo "Error: No module name provided."
  exit 1
fi

# Run the NestJS CLI command to generate the module
echo "Generating NestJS module: $MODULE_NAME..."
npx nest g module $MODULE_NAME

# Check if the generation was successful
if [ $? -ne 0 ]; then
  echo "Error: Failed to generate the module."
  exit 1
fi

# Create the target directory if it doesn't exist
TARGET_DIR="src/modules"
if [ ! -d "$TARGET_DIR" ]; then
  echo "Creating target directory: $TARGET_DIR..."
  mkdir -p $TARGET_DIR
fi

# Move the generated module to the target directory
echo "Moving $MODULE_NAME module to $TARGET_DIR..."
mv src/$MODULE_NAME $TARGET_DIR/

# Check if the move was successful
if [ $? -ne 0 ]; then
  echo "Error: Failed to move the module."
  exit 1
fi

echo "Module $MODULE_NAME successfully created and moved to $TARGET_DIR."
