import {
  ArgumentsHost,
  Catch,
  ExceptionFilter,
  HttpException,
  Logger,
} from '@nestjs/common';
import { Response } from 'express';

@Catch(HttpException)
export class HttpExceptionFilter implements ExceptionFilter {
  private logger = new Logger();

  catch(exception: HttpException, host: ArgumentsHost) {
    let err = exception['response'];
    err = err.response ? err.response : JSON.stringify(err);
    const context = host.switchToHttp();
    const request = context.getRequest();
    const response = context.getResponse<Response>();
    const status = exception.getStatus();
    const userDetail = request?.user || {};

    this.logger.error(
      `Error on API [${context.getRequest().method} :${
        context.getRequest().url
      }] || Error Message [${JSON.stringify(
        err,
      )}] || User Details [${JSON.stringify(userDetail)}]`,
    );
    const error =
      typeof exception.getResponse() === 'string'
        ? exception.getResponse()
        : exception.getResponse() &&
            typeof exception.getResponse()['message'] === 'object'
          ? exception.getResponse()['message'].join(', ')
          : exception.getResponse()['message'];

    response.status(status).json({
      isSuccess: false,
      statusCode: response.statusCode,
      error: error,
      message: '',
      data: {},
    });
  }
}
