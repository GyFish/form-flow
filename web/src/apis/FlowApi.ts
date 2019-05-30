import Api from "./Api"

export default class FlowApi extends Api {
  //~ ------------------------------------------------------
  // 构造器
  constructor() {
    super()
  }

  //~ ------------------------------------------------------
  // 保存流程结构数据
  async saveFlow(flowData: any) {
    const res: any = await this.http.post(
      "/api/saveFlow",
      flowData
    )
    return super.extractData(res)
  }

  // 查询表单列表
  async getFlowList() {
    const res: any = await this.http.get("/manager/flow/getFlowList")
    return super.extractData(res)
  }
}
