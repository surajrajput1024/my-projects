import { Controller, Get } from '@nestjs/common';

@Controller('')
export class AppController {
  @Get('/health')
  checkHealth() {
    return { result: 'Health check successful' };
  }
}
