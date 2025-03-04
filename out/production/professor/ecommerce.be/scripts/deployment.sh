#!/bin/bash

# Exit immediately if any command fails
set -e

# Target machine details
TARGET_IP="3.108.194.230"
TARGET_USER="ubuntu"
TARGET_KEY="~/.ssh/spencer.pem.pem"

# Get the current branch name and replace slashes with hyphens
BRANCH_NAME=$(git rev-parse --abbrev-ref HEAD | tr '/' '-')

# Get today's date in the desired format (e.g., 02-sep-24)
DATE=$(date +"%d-%b-%y" | tr '[:upper:]' '[:lower:]')

# Get a unique identifier (e.g., the short commit hash)
UNIQUE_ID=$(git rev-parse --short HEAD)

# Combine them to create the version tag
version="${BRANCH_NAME}-${DATE}-${UNIQUE_ID}"

echo "Running commands with version: $version"

# ECR login
aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 418295681420.dkr.ecr.ap-south-1.amazonaws.com
echo "Docker login successful."

# Build Docker image
echo "Building Docker image. This may take some time."
docker build --platform linux/amd64 . -t spencers_user_module:$version
echo "Docker build completed."

# Tag the image
docker tag "spencers_user_module:$version" "418295681420.dkr.ecr.ap-south-1.amazonaws.com/spencers_user_module:$version"
echo "Docker tag completed."

# Push the image to ECR
docker push 418295681420.dkr.ecr.ap-south-1.amazonaws.com/spencers_user_module:$version
echo "Docker push completed."

# Create and push Git tag
git tag $version
git push origin $version
echo "Git tag push completed."

# Execute the AWS command locally and use the result in the remote SSH command for Docker login on EC2
login_password=$(aws ecr get-login-password --region ap-south-1)
ssh -i "$TARGET_KEY" "$TARGET_USER@$TARGET_IP" "echo '$login_password' | sudo docker login --username AWS --password-stdin 418295681420.dkr.ecr.ap-south-1.amazonaws.com"
echo "Docker login on remote machine successful."

# Stop the existing container (if any)
echo "Stopping existing container if already running."
ssh -i "$TARGET_KEY" "$TARGET_USER@$TARGET_IP" "sudo docker stop \$(sudo docker ps --format '{{.Image}} {{.ID}}' | grep '418295681420.dkr.ecr.ap-south-1.amazonaws.com/spencers_user_module' | awk '{print \$2}') || true"

# Pull the new Docker image on the target machine
echo "Pulling the Docker image on the target machine."
ssh -i "$TARGET_KEY" "$TARGET_USER@$TARGET_IP" "sudo docker pull 418295681420.dkr.ecr.ap-south-1.amazonaws.com/spencers_user_module:$version"

# Run the Docker container
echo "Running the Docker container on the target machine."
ssh -i "$TARGET_KEY" "$TARGET_USER@$TARGET_IP" "sudo docker run -p 3000:3000 -p 3002:3002 -d --env-file ~/.env -e env=stg 418295681420.dkr.ecr.ap-south-1.amazonaws.com/spencers_user_module:$version"

echo "All commands executed successfully."
