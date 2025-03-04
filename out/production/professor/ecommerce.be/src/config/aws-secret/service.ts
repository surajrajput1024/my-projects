import { Injectable, Logger } from '@nestjs/common';
import {
  SecretsManagerClient,
  GetSecretValueCommand,
} from '@aws-sdk/client-secrets-manager';

@Injectable()
export class AwsSecretsService {
  private readonly logger = new Logger(AwsSecretsService.name);
  private secretsManagerClient: SecretsManagerClient;

  constructor() {
    this.secretsManagerClient = new SecretsManagerClient({
      region: process.env.AWS_REGION,
    });
  }

  /**
   * Fetch secret value from AWS Secrets Manager using AWS SDK v3
   * @param secretName Name of the secret in Secrets Manager
   */
  async getSecretValue(secretName: string): Promise<any> {
    try {
      const command = new GetSecretValueCommand({ SecretId: secretName });
      const secret = await this.secretsManagerClient.send(command);

      if (secret.SecretString) {
        return JSON.parse(secret.SecretString);
      }

      throw new Error('SecretString is not present in secret');
    } catch (error) {
      this.logger.error(
        `Failed to retrieve secret ${secretName}: ${error.message}`,
      );
      throw error;
    }
  }
}
