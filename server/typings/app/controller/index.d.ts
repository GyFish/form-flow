// This file is created by egg-ts-helper
// Do not modify this file!!!!!!!!!

import 'egg';
import ExportFormController from '../../../app/controller/formController';
import ExportHome from '../../../app/controller/home';

declare module 'egg' {
  interface IController {
    formController: ExportFormController;
    home: ExportHome;
  }
}
