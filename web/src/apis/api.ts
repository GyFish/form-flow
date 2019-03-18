import axios, { AxiosInstance } from "axios"

export default class Api {
  // axios 实例
  http: AxiosInstance

  constructor() {
    this.http = axios.create({
      baseURL: "http://localhost:7001",
      timeout: 3000
    })
  }

  // 保存表单结构数据
  async saveForm(form: any) {
    return await this.http.post("/form/saveForm", form)
  }
}
