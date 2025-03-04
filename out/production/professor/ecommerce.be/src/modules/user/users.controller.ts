import {
  Controller,
  Post,
  Body,
  UseGuards,
  Req,
  HttpStatus,
  HttpException,
  Get,
  UseInterceptors,
  UploadedFile,
} from '@nestjs/common';
import { FileInterceptor } from '@nestjs/platform-express';
import { UserService } from './users.service';
import { ApiBearerAuth, ApiTags } from '@nestjs/swagger';
import { UserJwtGuard } from '../auth/authGuards/user.jwt.guard';
import { TRequest } from 'src/common/constants/app.types';
import { CreateUserDto } from './dto/create-user.dto';

@Controller('user')
@ApiTags('User Registration')
@ApiBearerAuth()
export class UserController {
  constructor(private readonly userService: UserService) {}

  @UseGuards(UserJwtGuard)
  @Post('')
  async registerUser(
    @Req() req: TRequest,
    @Body() createUserDto: CreateUserDto,
  ) {
    try {
      return await this.userService.registerUser(req.user, createUserDto);
    } catch (error) {
      if (error.status)
        throw new HttpException(error.message, error.getStatus());
      else throw new HttpException(error.message, HttpStatus.BAD_REQUEST);
    }
  }

  @UseGuards(UserJwtGuard)
  @Get('')
  async getUserProfile(@Req() req: TRequest) {
    try {
      const userData = await this.userService.getUser(req.user);

      return userData;
    } catch (error) {
      if (error.status)
        throw new HttpException(error.message, error.getStatus());
      else throw new HttpException(error.message, HttpStatus.BAD_REQUEST);
    }
  }

  @UseGuards(UserJwtGuard)
  @Post('upload/profile')
  @UseInterceptors(FileInterceptor('file'))
  async uploadProfile(
    @Req() req: TRequest,
    @UploadedFile() file: Express.Multer.File,
  ) {
    try {
      return await this.userService.updateProfileImage(req.user, file);
    } catch (error) {
      if (error.status)
        throw new HttpException(error.message, error.getStatus());
      else throw new HttpException(error.message, HttpStatus.BAD_REQUEST);
    }
  }
}
