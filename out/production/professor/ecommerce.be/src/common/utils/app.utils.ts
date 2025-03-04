export function generateOTP() {
  const digits = '0123456789';
  let otp = '';
  for (let i = 0; i < 4; i++) {
    otp += digits[Math.floor(Math.random() * 10)];
  }

  return otp;
}

export function toBoolean(value: unknown): boolean {
  if (typeof value === 'boolean') {
    return value;
  }

  if (typeof value === 'string') {
    const lowercased = value.toLowerCase().trim();
    return lowercased === 'true' || lowercased === '1';
  }

  if (typeof value === 'number') {
    return value !== 0;
  }

  return false;
}
