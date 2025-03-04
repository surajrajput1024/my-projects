import { Logger, Module } from '@nestjs/common';
import { JwtModule, JwtService } from '@nestjs/jwt';
import { AuthService } from './auth.service';
import { AuthController } from './auth.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Otp } from './entities/otp.entity';
import { Env } from 'src/common/constants/env.constants';
import { User } from '../user/entities/user.entity';
import { Device } from '../user/entities/device.entity';
import { UserModule } from '../user/user.module';

@Module({
  imports: [
    JwtModule.register({
      secret: Env.JWT.AUTH_TOKEN,
      signOptions: { expiresIn: Env.JWT.EXPIRES_IN },
    }),
    UserModule,
    TypeOrmModule.forFeature([User, Device, Otp]),
  ],
  controllers: [AuthController],
  providers: [AuthService, Logger, JwtService],
})
export class AuthModule {}
