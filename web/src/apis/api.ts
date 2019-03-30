import axios, { AxiosInstance } from "axios"

export default class Api {
  // axios 实例
  http: AxiosInstance

  constructor() {
    this.http = axios.create({
      baseURL: "http://localhost:7007",
      // timeout: 3000
    })
  }

  extractData(res: any) {
    if (res.data.code == 0) return res.data.data
  }
}
