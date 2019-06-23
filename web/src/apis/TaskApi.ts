import Api from './Api'

export default class TaskApi extends Api {
  //~ ------------------------------------------------------
  // 构造器
  constructor() {
    super()
  }

  //~ ------------------------------------------------------
  // 保存流程结构数据
  async start(taskStartVo: any) {
    const res: any = await this.http.post('/api/task/start', taskStartVo)
    return super.extractData(res)
  }

  // 查询
  async query(taskQueryVo: any) {
    const res: any = await this.http.post('/api/task/query', taskQueryVo)
    return super.extractData(res)
  }

  // 前面的节点
  async previous(taskId: string) {
    const res: any = await this.http.get('/api/task/previous?taskId=' + taskId)
    return super.extractData(res)
  }
}
