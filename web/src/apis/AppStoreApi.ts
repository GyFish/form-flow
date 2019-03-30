import Api from "./Api"

export default class AppStoreApi extends Api {
  // 保存表单结构数据
  async saveApp(appInfo: any) {
    const res: any = await this.http.post("/app-store/saveApp", appInfo)
    return super.extractData(res)
  }
}
