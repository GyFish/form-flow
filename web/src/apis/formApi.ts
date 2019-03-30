import Api from './Api';

export default class FormApi extends Api{

  // 保存表单结构数据
  async save(form: any) {
    return await this.http.post("/form/save", form)
  }
  
}
