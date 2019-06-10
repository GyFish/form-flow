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
    const res: any = await this.http.post('/api/saveFlow', flowData)
    return super.extractData(res)
  }

  // 查询流程列表
  async getFlowInfoList() {
    const res: any = await this.http.get('/manager/flow/getFlowInfoList')
    return super.extractData(res)
  }

  // 查询流程节点列表
  async getFlowNodeList(uuid: string) {
    const res: any = await this.http.get('/manager/flow/getFlowNodeList')
    return super.extractData(res)
  }

  // 查询流程节点列表
  async getFlowMeta(uuid: string) {
    const res: any = await this.http.get('/service/flow/getFlowMeta', {
      params: {
        uuid
      }
    })
    return super.extractData(res)
  }
}
