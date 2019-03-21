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
}
