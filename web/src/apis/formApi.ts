import Api from "./Api"

export default class FormApi extends Api {
  //~ ------------------------------------------------------
  // 构造器
  constructor() {
    super()
  }

  //~ ------------------------------------------------------
  // 保存表单结构数据
  async saveForm(formDefinition: any) {
    const res: any = await this.http.post(
      "/api/saveForm",
      formDefinition
    )
    return super.extractData(res)
  }

  // 查询表单列表
  async getFormList() {
    const res: any = await this.http.get("/manager/form/getFormList")
    return super.extractData(res)
  }

  // 查询表单元素列表
  async getFormItems(uuid: any) {
    const res: any = await this.http.get("/service/form/getFormMeta", {
      params: {
        uuid
      }
    })
    return super.extractData(res)
  }
}
