import { Injectable } from '@nestjs/common';
import { v2 as cloudinary } from 'cloudinary';
import { UploadApiResponse } from 'cloudinary';
import { Env } from 'src/common/constants/env.constants';

@Injectable()
export class CloudinaryService {
  constructor() {
    cloudinary.config({
      cloud_name: Env.CLOUDINARY.NAME,
      api_key: Env.CLOUDINARY.KEY,
      api_secret: Env.CLOUDINARY.SECRET,
    });
  }

  async uploadImage(file: Express.Multer.File): Promise<UploadApiResponse> {
    return new Promise((resolve, reject) => {
      const stream = cloudinary.uploader.upload_stream(
        { resource_type: 'auto' },
        (error, result) => {
          if (error) {
            return reject(error);
          }
          resolve(result);
        },
      );
      stream.end(file.buffer);
    });
  }
}
