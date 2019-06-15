import Api from './Api'

export default class FlowApi extends Api {
  //~ ------------------------------------------------------
  // 构造器
  constructor() {
    super()
  }

  //~ ------------------------------------------------------
  // 保存流程结构数据
  async saveFlow(flowData: any) {
    const res: any = await this.http.post('/api/flow/saveFlow', flowData)
    return super.extractData(res)
  }

  // 删除流程结构数据
  async deleteFlow(id: string) {
    const res: any = await this.http.delete('/api/flow/deleteFlow?id=' + id)
    return super.extractMsg(res)
  }

  // 查询流程列表
  async getFlowList() {
    const res: any = await this.http.get('/api/flow/getFlowList')
    return super.extractData(res)
  }
}
