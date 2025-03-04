import { Column, Entity, JoinColumn, ManyToOne } from 'typeorm';
import { User } from './user.entity';
import { DeviceType } from 'src/common/constants/app.constants';
import { Base } from 'src/common/entities/base.entities';

@Entity()
export class Device extends Base {
  @Column({ type: 'varchar', nullable: true, length: 30 })
  manifacturerName: string;

  @Column({ type: 'varchar', nullable: true, length: 30 })
  deviceId: string;

  @Column({ type: 'varchar', nullable: true, length: 255 })
  token: string;

  @Column({ type: 'varchar', nullable: true, length: 30 })
  IMEINumber: string;

  @Column({
    type: 'enum',
    enum: DeviceType,
    default: DeviceType.ANDROID,
  })
  platform: DeviceType;

  @Column({ type: 'boolean' })
  isActive: boolean;

  @Column({ type: Date, nullable: true })
  lastActiveTime: boolean;

  @ManyToOne(() => User, (user) => user.devices)
  @JoinColumn({ name: 'userId' })
  user: User;
}
