import axios, { AxiosInstance } from 'axios'

export default class Api {
  // axios 实例
  http: AxiosInstance

  constructor() {
    this.http = axios.create({
      baseURL: this.getBaseURL()
      // timeout: 3000
    })
  }

  getBaseURL() {
    switch (process.env.NODE_ENV) {
      case 'development':
        return 'http://localhost:7000/'
      case 'production':
        return 'http://47.94.198.39:7000/'
    }
  }

  extractData(res: any) {
    if (res.data.code == 0) return res.data.data
  }

  extractMsg(res: any) {
    if (res.data.code == 0) return res.data.msg
  }
}
