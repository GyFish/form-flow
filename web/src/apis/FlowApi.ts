import Api from "./Api"

export default class FlowApi extends Api {
  //~ ------------------------------------------------------
  // 构造器
  constructor() {
    super()
  }

  //~ ------------------------------------------------------
  // 保存流程结构数据
  async saveDefinition(flowDefinition: any) {
    const res: any = await this.http.post(
      "/flow/saveDefinition",
      flowDefinition
    )
    return super.extractData(res)
  }

  // 查询表单列表
  async getFlowList() {
    const res: any = await this.http.get("/flow/getFlowList")
    return super.extractData(res)
  }
}
