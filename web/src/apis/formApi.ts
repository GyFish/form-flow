import Api from "./Api"

export default class FormApi extends Api {
  constructor() {
    super()
  }

  // 保存表单结构数据
  async saveFormEditor(form: any) {
    const res: any = await this.http.post("/form/saveFormEditor", form)
    return super.extractData(res)
  }
}
