import * as dotenv from 'dotenv';
dotenv.config();
import './instrument';

import { NestFactory } from '@nestjs/core';
import * as cookieParser from 'cookie-parser';
import { Logger, ValidationPipe } from '@nestjs/common';
import * as bodyParser from 'body-parser';
import * as basicAuth from 'express-basic-auth';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';

import { AppModule } from './app.module';
import { AppConfigService } from './config/app/service';
import { ResponseInterceptor } from './interceptors/response.interceptor';
import { HttpExceptionFilter } from './interceptors/exception.interceptor';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  const configService = app.get(AppConfigService);
  const port = configService.env.port || 3002;

  app.useGlobalInterceptors(new ResponseInterceptor());
  app.useGlobalFilters(new HttpExceptionFilter());

  app.useGlobalPipes(
    new ValidationPipe({
      forbidNonWhitelisted: true,
      whitelist: true,
    }),
  );

  app.use(
    ['/docs', '/docs-json'],
    basicAuth({
      challenge: true,
      users: {
        [configService.env.swagger.user]: configService.env.swagger.password,
      },
    }),
  );
  app.use(cookieParser());
  app.use(bodyParser.json({ limit: '50mb' }));
  app.use(bodyParser.urlencoded({ limit: '50mb', extended: true }));
  const config = new DocumentBuilder()
    .setTitle('Spencer User Backend')
    .setDescription('Spencer User Backend')
    .setVersion('0.0.1')
    .addBearerAuth()
    .addTag('Spencer')
    .build();
  const document = SwaggerModule.createDocument(app, config);

  SwaggerModule.setup('docs', app, document);
  await app.listen(port);
  Logger.log(`Spencer backend server is up and running 🚀🎉 on port: ${port}`);
}
bootstrap();
