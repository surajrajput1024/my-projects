import { Injectable } from '@nestjs/common';
import { PassportStrategy } from '@nestjs/passport';
import { ExtractJwt, Strategy } from 'passport-jwt';
import { Env } from 'src/common/constants/env.constants';

@Injectable()
export class UserJwtStrategy extends PassportStrategy(Strategy, 'USER') {
  constructor() {
    super({
      secretOrKey: Env.JWT.AUTH_TOKEN,
      ignoreExpiration: false,
      jwtFromRequest: ExtractJwt.fromExtractors([
        (req) => {
          if (req && req.cookies) {
            return req.cookies['accessToken'];
          }
          return null;
        },
      ]),
    });
  }

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  async validate(payload: any) {
    return payload;
  }
}
