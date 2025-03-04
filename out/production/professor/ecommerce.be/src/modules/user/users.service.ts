import { HttpException, HttpStatus, Injectable } from '@nestjs/common';
import { User } from './entities/user.entity';
import { Repository } from 'typeorm';
import { InjectRepository } from '@nestjs/typeorm';
import { CreateUserDto } from './dto/create-user.dto';
import { TokenInfo } from 'src/common/constants/app.types';
import { CloudinaryService } from 'src/config/cloudinary/service';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(User)
    private readonly userRepository: Repository<User>,
    private readonly cloudinaryService: CloudinaryService,
  ) {}

  async findUserByPhone(phone: string, countryCode: string) {
    const user = await this.userRepository.findOne({
      where: {
        phone,
        countryCode,
      },
      select: ['id', 'phone', 'countryCode', 'isRegistered'],
    });

    return user;
  }

  async registerUser(tokenInfo: TokenInfo, createUserDto: CreateUserDto) {
    const { phone } = tokenInfo;

    const user = await this.userRepository.findOne({ where: { phone } });

    if (!user) {
      throw new HttpException('User does not exit', HttpStatus.NOT_FOUND);
    }

    await this.userRepository.update(
      { phone },
      { ...createUserDto, isRegistered: true },
    );
    return {
      id: user.id,
      phone: user.phone,
      countryCode: user.countryCode,
      imageUrl: user.imageUrl,
      ...createUserDto,
    };
  }

  async getUser(tokenInfo: TokenInfo) {
    const { userId } = tokenInfo;
    return this.userRepository.findOne({
      where: { id: userId },
      select: [
        'id',
        'countryCode',
        'name',
        'phone',
        'gender',
        'dob',
        'email',
        'imageUrl',
      ],
    });
  }

  async updateProfileImage(tokenInfo: TokenInfo, file: Express.Multer.File) {
    const { userId } = tokenInfo;
    const user = await this.userRepository.findOne({ where: { id: userId } });

    if (!user) {
      throw new HttpException('User does not exit', HttpStatus.NOT_FOUND);
    }
    const image = await this.cloudinaryService.uploadImage(file);
    await this.userRepository.update(
      { id: userId },
      { imageUrl: image.secure_url },
    );
    return {
      imageUrl: image.secure_url,
      format: image.format,
      width: image.height,
      height: image.height,
    };
  }
}
