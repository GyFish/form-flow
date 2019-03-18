// This file is created by egg-ts-helper
// Do not modify this file!!!!!!!!!

import 'egg';
import ExportForm from '../../../app/model/form';
import ExportFormItem from '../../../app/model/formItem';
import ExportUser from '../../../app/model/user';

declare module 'sequelize' {
  interface Sequelize {
    Form: ReturnType<typeof ExportForm>;
    FormItem: ReturnType<typeof ExportFormItem>;
    User: ReturnType<typeof ExportUser>;
  }
}
