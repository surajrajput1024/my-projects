import { Module } from '@nestjs/common';
import { PassportModule } from '@nestjs/passport';
import { UserJwtStrategy } from './user.jwt.strategy';
import { UserJwtGuard } from './user.jwt.guard';
import { UserModule } from 'src/modules/user/user.module';

@Module({
  imports: [PassportModule, UserModule],
  providers: [UserJwtStrategy, UserJwtGuard],
  exports: [UserJwtStrategy, UserJwtGuard],
})
export class AuthGuardModule {}
