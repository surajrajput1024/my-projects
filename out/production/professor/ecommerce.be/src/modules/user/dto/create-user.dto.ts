import { ApiProperty } from '@nestjs/swagger';
import {
  IsEmail,
  IsEnum,
  IsNotEmpty,
  IsOptional,
  IsString,
  MaxLength,
} from 'class-validator';
import { Gender } from 'src/common/constants/app.constants';

export class CreateUserDto {
  @ApiProperty({ example: 'John' })
  @IsNotEmpty()
  @IsString()
  @MaxLength(100)
  name: string;

  @ApiProperty({ example: 'charlie@gmail.com' })
  @IsNotEmpty()
  @IsString()
  @IsEmail()
  @MaxLength(100)
  email: string;

  @ApiProperty({
    example: Gender.MALE,
    enum: Gender,
  })
  @IsOptional()
  @IsEnum(Gender)
  gender: Gender;

  @ApiProperty({ example: 'EW798FHK' })
  @IsOptional()
  @IsString()
  referralCode: Date;
}
