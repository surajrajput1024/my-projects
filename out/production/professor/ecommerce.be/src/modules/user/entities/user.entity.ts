import { Column, Entity, Index, OneToMany } from 'typeorm';
import { Device } from './device.entity';
import { IsEmail } from 'class-validator';
import { Gender } from 'src/common/constants/app.constants';
import { Base } from 'src/common/entities/base.entities';

@Entity()
@Index('unique_user', ['phone', 'countryCode'], {
  unique: true,
})
export class User extends Base {
  @Column({ type: 'varchar', nullable: true, length: 100 })
  name: string;

  @Column({ type: 'varchar', nullable: false, length: 12 })
  phone: string;

  @Column({ type: 'varchar', nullable: false, length: 4 })
  countryCode: string;

  @Column({ type: 'varchar', nullable: true, length: 100 })
  imageUrl: string;

  @Column({ type: 'boolean', default: false })
  isRegistered: boolean;

  @Column({ type: 'varchar', nullable: true, unique: false })
  @IsEmail()
  email: string;

  @Column({ type: 'enum', enum: Gender, default: null })
  gender: Gender;

  @Column({ type: Date, nullable: true })
  dob: Date;

  @Column({ type: 'json', nullable: true })
  detail?: Record<string, string>;

  @OneToMany(() => Device, (devices) => devices.user)
  devices: Device[];
}
