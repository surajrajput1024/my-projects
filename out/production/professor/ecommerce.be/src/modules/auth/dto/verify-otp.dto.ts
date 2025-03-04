import { ApiProperty } from '@nestjs/swagger';
import {
  IsNotEmpty,
  IsNumberString,
  IsOptional,
  IsString,
  Length,
} from 'class-validator';

export class BaseVerifyOtpDto {
  @ApiProperty({ example: '91', required: false })
  @IsOptional()
  @IsNumberString({ no_symbols: true })
  @Length(1, 4)
  countryCode?: string = '91';

  @ApiProperty({ example: '9999999999' })
  @IsNotEmpty()
  @IsNumberString()
  @Length(7, 12)
  phone: string;

  @ApiProperty({ example: '1111' })
  @IsNotEmpty()
  @IsNumberString()
  @Length(4, 4)
  otp: string;

  @ApiProperty({ example: 'Device Token' })
  @IsString()
  @IsOptional()
  token: string;
}

// export class VerifyOtpDto extends BaseVerifyOtpDto {
//   @ApiProperty({ example: 'Enter Manufacturer Name' })
//   @IsOptional()
//   @IsString()
//   @MaxLength(20)
//   manifacturerName: string;

//   @ApiProperty({ example: 'Enter Device Id' })
//   @IsOptional()
//   @IsString()
//   @MaxLength(30)
//   deviceId: string;

//   @ApiProperty({ example: 'Enter IMEI Number' })
//   @IsString()
//   @IsNotEmpty()
//   @MaxLength(30)
//   @IsOptional()
//   IMEINumber: string;

//   @ApiProperty({
//     example: DeviceType.ANDROID,
//     enum: DeviceType,
//   })
//   @IsOptional()
//   platform: DeviceType;

//   @ApiProperty({ example: 'Device Token' })
//   @IsString()
//   @IsOptional()
//   token: string;
// }

// export class VerifyOtpForLoggedInUser extends BaseVerifyOtpDto {}
