import { Base } from 'src/common/entities/base.entities';
import { Column, Entity, Index } from 'typeorm';

@Entity()
@Index('unique_contact', ['phone', 'countryCode'], { unique: true })
export class Otp extends Base {
  @Column({ type: 'varchar', nullable: false, length: 12 })
  phone: string;

  @Column({ type: 'varchar', nullable: false, length: 4 })
  countryCode: string;

  @Column({ type: 'varchar', nullable: false, length: 4 })
  otp: string;

  @Column({ type: 'integer', nullable: false })
  counter: number;

  @Column({ type: 'bigint', nullable: true })
  blockedAt: Date;

  @Column({ type: 'varchar', length: 4, nullable: true })
  regionCode: string;
}
