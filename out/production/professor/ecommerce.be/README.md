# Spenser Backend

## Description

Backend service for Spenser.

## Project Setup

To set up the project, follow these steps:

1. Install dependencies:

   ```bash
   pnpm install
   ```

2. Set up the database:

   - Ensure you have PostgreSQL installed and running.
   - Create a new database for the project.
   - Update the database configuration in the project (typically in a `.env` file or configuration file).

3. Set up SSH access (for remote server):
   - Ensure you have the `spenser_main.pem` private key file.
   - Set the correct permissions for the key file:
     ```bash
     chmod 400 ~/Downloads/spenser_main.pem
     ```
   - Add the following to your SSH config file (`~/.ssh/config`):
     ```
     Host SpencerTunnel
         HostName <your-server-ip>
         User ec2-user
         IdentityFile spenser_main.pem
         LocalForward localhost:5432 <db-host>:5432
         ServerAliveInterval 60
         ServerAliveCountMax 30
     ```
   - Replace `<your-server-ip>` with the actual IP address of your server.
   - Replace `<db-host>` with the hostname or IP address of your database server.

## Running the Project

### Development Mode

```bash
pnpm run start:dev
```

### Production Mode

```bash
pnpm run start:prod
```

### Watch Mode

```bash
pnpm run start
```

## Running Tests

### Unit Tests

```bash
pnpm run test
```

### End-to-End Tests

```bash
pnpm run test:e2e
```

### Test Coverage

```bash
pnpm run test:cov
```

## Database Connection

To connect to the database through the SSH tunnel:

1. Start the SSH tunnel:

   ```bash
   ssh SpencerTunnel
   ```

   This will forward the remote database port to your local machine.

2. You can now connect to the database using `localhost:5432` as the host.

## Starting the Server

To start the server on the remote machine:

1. SSH into the server:

   ```bash
   ssh -i ~/Downloads/spenser_main.pem ec2-user@<your-server-ip>
   ```

2. Navigate to the project directory:

   ```bash
   cd /path/to/spenser-backend
   ```

3. Install dependencies (if not already done):

   ```bash
   pnpm install
   ```

4. Start the server:

   ```bash
   pnpm run start:prod
   ```

   For development or debugging, you can use `pnpm run start:dev` instead.
