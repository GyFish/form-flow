import axios, { AxiosInstance } from "axios"

export default class Api {
  // axios 实例
  http: AxiosInstance

  constructor() {
    this.http = axios.create({
      baseURL: "http://47.94.198.39:7000",
      // baseURL: "http://localhost:7000",
      // timeout: 3000
    })
    // this.http.
  }

  extractData(res: any) {
    if (res.data.code == 0) return res.data.data
  }

  extractMsg(res: any) {
    if (res.data.code == 0) return res.data.msg
  }
}
