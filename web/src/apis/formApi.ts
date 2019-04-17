import Api from "./Api"

export default class FormApi extends Api {
  //~ ------------------------------------------------------
  // 构造器
  constructor() {
    super()
  }

  //~ ------------------------------------------------------
  // 保存表单结构数据
  async saveDefinition(formDefinition: any) {
    const res: any = await this.http.post("/form/saveDefinition", formDefinition)
    return super.extractData(res)
  }

  // 查询表单列表
  async getFormList() {
    const res: any = await this.http.get("/form/getFormList")
    return super.extractData(res)
  }
}
