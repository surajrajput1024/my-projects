interface TUserProperties {
  userId: string;
  name: string;
  phone: string;
  countryCode: string;
}

interface TRequestUser {
  user: TUserProperties;
}

export interface TokenInfo {
  userId: string;
  phone: string;
  countryCode: string;
}

export type TRequest = Request & TRequestUser;
