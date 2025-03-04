import { Module } from '@nestjs/common';
import { User } from './entities/user.entity';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UserService } from './users.service';
import { Device } from './entities/device.entity';
import { UserController } from './users.controller';
import { JwtModule } from '@nestjs/jwt';
import { Env } from 'src/common/constants/env.constants';
import { CloudinaryModule } from 'src/config/cloudinary/module';

@Module({
  imports: [
    TypeOrmModule.forFeature([User, Device]),
    JwtModule.register({
      secret: Env.JWT.AUTH_TOKEN,
      signOptions: { expiresIn: Env.JWT.EXPIRES_IN },
    }),
    CloudinaryModule,
  ],
  providers: [UserService],
  exports: [UserService],
  controllers: [UserController],
})
export class UserModule {}
