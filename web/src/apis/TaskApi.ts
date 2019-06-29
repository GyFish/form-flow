import Api from './Api'

export default class TaskApi extends Api {
  //~ ------------------------------------------------------
  // 构造器
  constructor() {
    super()
  }

  //~ ------------------------------------------------------
  // 新建
  async start(taskVo: any) {
    const res: any = await this.http.post('/api/task/start', taskVo)
    return super.extractData(res)
  }

  // 提交
  async commit(taskVo: any) {
    const res: any = await this.http.post('/api/task/commit', taskVo)
    return super.extractData(res)
  }

  // 查询
  async query(taskQueryVo: any) {
    const res: any = await this.http.post('/api/task/query', taskQueryVo)
    return super.extractData(res)
  }

  // 需要展示的任务
  async preTask(taskId: string) {
    const res: any = await this.http.get('/api/task/preTask?taskId=' + taskId)
    return super.extractData(res)
  }

  // 所有任务
  async getByProcess(processId: string) {
    const res: any = await this.http.get('/api/task/getByProcess?processId=' + processId)
    return super.extractData(res)
  }
}
