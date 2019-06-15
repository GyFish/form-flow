import Api from "./Api"

export default class AppStoreApi extends Api {
  constructor() {
    super()
  }

  // 保存表单结构数据
  async saveApp(appInfo: any) {
    const res: any = await this.http.post("/api/app/saveApp", appInfo)
    return super.extractData(res)
  }

  // 查询应用列表
  async appList() {
    const res: any = await this.http.get("/api/app/appList")
    return super.extractData(res)
  }

  // 删除 app
  async deleteApp(appId: any) {
    const res: any = await this.http.delete("/api/app/deleteApp", {
      params: {
        appId
      }
    })
    return super.extractData(res)
  }
}
