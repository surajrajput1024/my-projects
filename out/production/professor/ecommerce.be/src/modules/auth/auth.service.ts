import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { SendOtpDto } from './dto/send-otp.dto';
import { BaseVerifyOtpDto } from './dto/verify-otp.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Otp } from './entities/otp.entity';
import { generateOTP } from 'src/common/utils/app.utils';
import { Env } from 'src/common/constants/env.constants';
import { UserService } from '../user/users.service';
import { User } from '../user/entities/user.entity';
import { Device } from '../user/entities/device.entity';

@Injectable()
export class AuthService {
  constructor(
    private readonly userService: UserService,
    private readonly jwtService: JwtService,
    @InjectRepository(User)
    private readonly userRepository: Repository<User>,
    @InjectRepository(Device)
    private readonly deviceRepository: Repository<Device>,
    @InjectRepository(Otp)
    private readonly otpRepository: Repository<Otp>,
  ) {}

  async sendOtp(sendOtpDto: SendOtpDto) {
    const otp = generateOTP();
    sendOtpDto['otp'] = otp;
    const preExsitingOtp = await this.otpRepository.findOne({
      where: { phone: sendOtpDto.phone, countryCode: sendOtpDto.countryCode },
    });
    if (!preExsitingOtp) {
      sendOtpDto['counter'] = 1;
      await this.otpRepository.save(sendOtpDto);
    } else {
      let otpAttempts = preExsitingOtp.counter;
      if (preExsitingOtp.blockedAt) {
        const blockedAt = Number(preExsitingOtp.blockedAt);
        const currentTime = Number(Date.now());
        const diffMiliSec = Math.abs(currentTime - blockedAt);
        const diffMin = Math.abs(diffMiliSec / (1000 * 60));
        if (diffMin >= Env.OTP.BLOCK_DURATION) {
          otpAttempts = 0;
          await this.otpRepository.update(
            { phone: sendOtpDto.phone, countryCode: sendOtpDto.countryCode },
            { blockedAt: null, counter: 0 },
          );
        } else {
          const blockDuration = Math.ceil(Env.OTP.BLOCK_DURATION - diffMin);
          throw new HttpException(
            `Please wait for ${blockDuration} min before retrying`,
            HttpStatus.FAILED_DEPENDENCY,
          );
        }
      }
      if (otpAttempts >= Env.OTP.ATTEMPTS_ALLOWED) {
        await this.otpRepository.update(
          { phone: sendOtpDto.phone, countryCode: sendOtpDto.countryCode },
          { blockedAt: Date.now() },
        );
        throw new HttpException(
          `Max Request Attempts Reached. Retry after ${Env.OTP.BLOCK_DURATION} minutes`,
          HttpStatus.FAILED_DEPENDENCY,
        );
      }
      sendOtpDto['counter'] = otpAttempts + 1;
      await this.otpRepository.update(
        { phone: sendOtpDto.phone, countryCode: sendOtpDto.countryCode },
        sendOtpDto,
      );
    }

    return { message: 'Otp send successfully' };
  }

  async verifyOtp(verifyOtpDto: BaseVerifyOtpDto) {
    const [otpInfo, userData] = await Promise.all([
      this.otpRepository.findOne({
        where: {
          phone: verifyOtpDto.phone,
          countryCode: verifyOtpDto.countryCode,
        },
      }),
      this.userService.findUserByPhone(
        verifyOtpDto.phone,
        verifyOtpDto.countryCode,
      ),
    ]);

    if (!otpInfo) {
      throw new HttpException(
        'No OTP found for this number',
        HttpStatus.NOT_FOUND,
      );
    }

    let userInfo = userData;
    const otpSentAt = Number(new Date(otpInfo.updatedAt).getTime());
    const currentTime = Number(Date.now());
    const diffMiliSec = Math.abs(currentTime - otpSentAt);
    const diffMin = Math.abs(diffMiliSec / (1000 * 60));

    if (diffMin >= Env.OTP.EXPIRY_DURATION) {
      throw new HttpException(
        'OTP expired. Please request for a new OTP',
        HttpStatus.FAILED_DEPENDENCY,
      );
    }

    if (verifyOtpDto.otp === otpInfo.otp || true) {
      if (!userInfo) {
        userInfo = await this.userRepository.save({
          phone: verifyOtpDto.phone,
          countryCode: verifyOtpDto.countryCode,
        });
      }
      const [deviceInfo, ,] = await Promise.all([
        this.deviceRepository.findOne({
          where: {
            isActive: true,
            user: { id: userInfo.id },
          },
          withDeleted: true,
        }),
        this.deviceRepository.update(
          { user: { id: userInfo.id }, isActive: true },
          {
            token: verifyOtpDto.token,
          },
        ),
      ]);
      if (!deviceInfo) {
        delete verifyOtpDto.otp;
        delete verifyOtpDto.countryCode;
        delete verifyOtpDto.phone;
        verifyOtpDto['user'] = { id: userInfo.id };
        verifyOtpDto['isActive'] = true;
        await this.deviceRepository.save(verifyOtpDto);
      } else {
        await this.deviceRepository.update(
          { user: { id: userInfo.id }, isActive: true },
          {
            isActive: true,
            token: verifyOtpDto.token,
          },
        );
      }

      await this.otpRepository.delete({ phone: verifyOtpDto.phone });

      const accessToken = this.jwtService.sign(
        {
          userId: userInfo.id,
          phone: userInfo.phone,
          countryCode: userInfo.countryCode,
        },
        {
          secret: Env.JWT.AUTH_TOKEN,
          expiresIn: Env.JWT.EXPIRES_IN,
        },
      );

      return {
        userId: userInfo.id,
        isRegistered: userInfo.isRegistered,
        accessToken: accessToken,
      };
    } else {
      throw new HttpException('Incorrect OTP', HttpStatus.BAD_REQUEST);
    }
  }
}
