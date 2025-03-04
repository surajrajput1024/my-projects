import {
  Injectable,
  NestInterceptor,
  ExecutionContext,
  CallHandler,
  Logger,
} from '@nestjs/common';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface Response<T> {
  data: T;
}

@Injectable()
export class ResponseInterceptor<T> implements NestInterceptor<T, Response<T>> {
  private logger = new Logger();

  intercept(
    context: ExecutionContext,
    next: CallHandler,
  ): Observable<Response<T>> {
    const statusCode = context.switchToHttp().getResponse().statusCode;
    const response = context.switchToHttp().getResponse();
    const req = context.switchToHttp().getRequest();
    const userDetail = req?.user || {};

    return next.handle().pipe(
      map((data) => {
        const { accessToken, ...filteredData } = data;

        const responsePayload = {
          isSuccess: statusCode >= 200 && statusCode < 300 ? true : false,
          statusCode: statusCode,
          error: '',
          message: data && data.message ? data.message : '',
          data:
            data && data.message
              ? { ...filteredData, message: undefined }
              : filteredData,
        };
        this.logger.debug(
          `Response: [${JSON.stringify(
            responsePayload,
          )}] || User Details: [${JSON.stringify(userDetail)}]`,
        );

        if (data?.accessToken) {
          response.cookie('accessToken', accessToken, {
            httpOnly: true,
            secure: false, // todo must be set to true
            maxAge: 365 * 24 * 60 * 1000,
          });
        }

        return responsePayload;
      }),
    );
  }
}
