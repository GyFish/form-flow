// This file is created by egg-ts-helper
// Do not modify this file!!!!!!!!!

import 'egg';
import ExportFormService from '../../../app/service/formService';
import ExportTest from '../../../app/service/Test';

declare module 'egg' {
  interface IService {
    formService: ExportFormService;
    test: ExportTest;
  }
}
