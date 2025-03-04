import { ApiProperty } from '@nestjs/swagger';
import {
  IsNotEmpty,
  IsNumberString,
  IsOptional,
  Length,
} from 'class-validator';

export class SendOtpDto {
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
}
