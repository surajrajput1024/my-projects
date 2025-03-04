import { Injectable } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';

@Injectable()
export class AppConfigService {
  public env: Record<string, any>;

  constructor(private configService: ConfigService) {
    this.env = this.buildConfig();
  }

  /**
   * Builds the structured configuration object with nested properties.
   * Fetches environment variables for non-sensitive data.
   */
  private buildConfig() {
    return {
      port: this.get<number>('PORT', 3001),
      db: {
        type: this.get<string>('DB_TYPE', 'postgres'),
        name: this.get<string>('DB_NAME', 'postgres'),
        host: this.get<string>('DB_HOST', 'localhost'),
        username: this.get<string>('DB_USERNAME'),
        password: this.get<string>('DB_PASSWORD'),
        port: this.get<number>('DB_PORT', 5432),
        synchronize: this.get<boolean>('DB_SYNCHRONIZE', false),
      },
      ssl: {
        rejectUnauthorised: this.get<boolean>('SSL_REJECT_UNAUTHORISED', false),
      },
      swagger: {
        user: this.get<string>('SWAGGER_USER'),
        password: this.get<string>('SWAGGER_PASSWORD'),
      },
    };
  }

  /**
   * Get an environment variable, throw an error if it doesn't exist.
   * Optionally, a default value can be provided.
   * @param key - The environment variable key
   * @param defaultValue - Optional default value if the key is not found
   */
  private get<T = string>(key: string, defaultValue?: T): T {
    const value = this.configService.get<T>(key);
    if (!value && defaultValue === undefined) {
      throw new Error(
        `Configuration error: Missing environment variable "${key}"`,
      );
    }
    return value || defaultValue!;
  }
}
