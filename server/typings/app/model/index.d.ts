// This file is created by egg-ts-helper
// Do not modify this file!!!!!!!!!

import 'egg';
import ExportUser from '../../../app/model/user';

declare module 'sequelize' {
  interface Sequelize {
    User: ReturnType<typeof ExportUser>;
  }
}
