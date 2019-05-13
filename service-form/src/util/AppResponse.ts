export class AppResponse {
  //
  code: number
  msg: string
  data: any

  constructor(c: number, m: string, d: any) {
    this.code = c
    this.msg = m
    this.data = d
  }

  static ok(data: any): AppResponse {
    return new AppResponse(0, 'ok', data)
  }
}
