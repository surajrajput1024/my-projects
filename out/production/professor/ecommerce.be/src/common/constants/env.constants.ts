import { config } from 'dotenv';

// Init env variables into process.env
config();

const getVar = (name: string) => {
  const _var = process.env[name];
  if (!_var) throw new Error(`Unable to locate env. variable: ${name}`);

  return _var;
};

export const Env = {
  SWAGGER: {
    SWAGGER_USER: getVar('SWAGGER_USER'),
    SWAGGER_PASSWORD: getVar('SWAGGER_PASSWORD'),
  },
  JWT: {
    AUTH_TOKEN: getVar('JWT_AUTH_TOKEN'),
    REFRESH_TOKEN: getVar('JWT_REFRESH_TOKEN'),
    EXPIRES_IN: getVar('EXPIRES_IN'),
  },
  OTP: {
    ATTEMPTS_ALLOWED: parseInt(getVar('OTP_ATTEMPTS_ALLOWED')),
    EXPIRY_DURATION: parseInt(getVar('OTP_EXPIRY_DURATION')),
    BLOCK_DURATION: parseInt(getVar('OTP_BLOCK_DURATION')),
  },
  LOGGER: {
    LOGGING_ENABLED: getVar('LOGGING_ENABLED'),
  },
  CLOUDINARY: {
    NAME: getVar('CLOUDINARY_CLOUD_NAME'),
    KEY: getVar('CLOUDINARY_API_KEY'),
    SECRET: getVar('CLOUDINARY_API_SECRET'),
  },
};
