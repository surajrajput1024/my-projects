import { Module } from '@nestjs/common';
import { AwsSecretsService } from './service';

@Module({
  providers: [AwsSecretsService],
  exports: [AwsSecretsService],
})
export class AwsSecretsModule {}
