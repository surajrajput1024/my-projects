import {
  Body,
  Controller,
  HttpException,
  HttpStatus,
  Post,
} from '@nestjs/common';
import { AuthService } from './auth.service';
import { BaseVerifyOtpDto } from './dto/verify-otp.dto';
import { SendOtpDto } from './dto/send-otp.dto';
import { ApiTags } from '@nestjs/swagger';

@Controller('auth')
@ApiTags('Authentication')
export class AuthController {
  constructor(private readonly authService: AuthService) {}

  @Post('otp/send')
  async sendOtp(@Body() sendOtpDto: SendOtpDto) {
    try {
      return await this.authService.sendOtp(sendOtpDto);
    } catch (error) {
      if (error.status)
        throw new HttpException(error.message, error.getStatus());
      else throw new HttpException(error.message, HttpStatus.BAD_REQUEST);
    }
  }

  @Post('otp/verify')
  async verifyOtp(@Body() verifyOtpDto: BaseVerifyOtpDto) {
    try {
      return await this.authService.verifyOtp(verifyOtpDto);
    } catch (error) {
      if (error.status) {
        throw new HttpException(error.message, error.getStatus());
      } else {
        throw new HttpException(error.message, HttpStatus.BAD_REQUEST);
      }
    }
  }
}
