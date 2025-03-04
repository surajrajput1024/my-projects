import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AppConfigModule } from './config/app/module';
import { AppConfigService } from './config/app/service';
import { ConfigModule } from '@nestjs/config';
import { SentryGlobalFilter, SentryModule } from '@sentry/nestjs/setup';
import { APP_FILTER } from '@nestjs/core';

import { toBoolean } from './common/utils/app.utils';
import { UserModule } from './modules/user/user.module';
import { AuthModule } from './modules/auth/auth.module';
import { AppController } from './app.controller';
import { AuthGuardModule } from './modules/auth/authGuards/auth.guard.module';
import { CloudinaryModule } from './config/cloudinary/module';

@Module({
  imports: [
    AuthModule,
    AuthGuardModule,
    ConfigModule.forRoot({
      isGlobal: true,
    }),
    SentryModule.forRoot(),
    AppConfigModule,
    // AwsSecretsModule,

    // TypeORM dynamic configuration using both ConfigService and SecretsManager
    TypeOrmModule.forRootAsync({
      // imports: [AppConfigModule, AwsSecretsModule],
      // inject: [AppConfigService, AwsSecretsService],
      imports: [AppConfigModule],
      inject: [AppConfigService],

      useFactory: async (configService: AppConfigService) => {
        // Non-sensitive data directly from AppConfigService (environment variables)
        const dbConfig = configService.env.db;

        // TODO: Need to remove once will start fetching from ASM
        // Sensitive data (username/password) from AWS Secrets Manager
        // const dbSecret = await secretsService.getSecretValue(
        //   process.env.AWS_SECRET_NAME!,
        // );

        return {
          type: dbConfig.type,
          host: dbConfig.host,
          port: dbConfig.port,
          username: dbConfig.username,
          password: dbConfig.password,
          database: dbConfig.name,
          entities: [__dirname + '/**/*.entity{.ts,.js}'],
          synchronize: dbConfig.synchronize,

          ssl: {
            rejectUnauthorized: toBoolean(
              configService.env.ssl.rejectUnauthorised,
            ),
          },
        };
      },
    }),
    UserModule,
    CloudinaryModule,
  ],

  providers: [
    {
      provide: APP_FILTER,
      useClass: SentryGlobalFilter,
    },
    AppConfigService,
  ],
  controllers: [AppController],
  exports: [AppConfigService],
})
export class AppModule {}
