import axios, { AxiosInstance } from "axios"

export default class Api {
  // axios 实例
  http: AxiosInstance

  constructor() {
    this.http = axios.create({
      baseURL: "https://some-domain.com/api/",
      timeout: 1000
    })
  }

  // 保存表单结构数据
  saveForm(form: any) {
    return this.http.post("url", form)
  }
}
