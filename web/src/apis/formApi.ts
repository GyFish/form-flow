import Api from './Api'

export default class FormApi extends Api {
  //~ ------------------------------------------------------
  // 构造器
  constructor() {
    super()
  }

  //~ ------------------------------------------------------
  // 保存表单结构数据
  async saveForm(formDefinition: any) {
    const res: any = await this.http.post('/api/form/saveForm', formDefinition)
    return super.extractData(res)
  }

  // 保存表单结构数据
  async deleteForm(id: any) {
    const res: any = await this.http.delete('/api/form/deleteForm?id=' + id)
    return super.extractData(res)
  }

  // 查询表单列表
  async getFormList() {
    const res: any = await this.http.get('/api/form/getFormList')
    return super.extractData(res)
  }

  // 根据 id 查询
  async getFormById(id: string) {
    const res: any = await this.http.delete('/api/form/getFormById?id=' + id)
    return super.extractData(res)
  }
}
