import { Module } from '@nestjs/common';
import { CloudinaryService } from './service';

@Module({
  providers: [CloudinaryService],
  exports: [CloudinaryService],
})
export class CloudinaryModule {}
