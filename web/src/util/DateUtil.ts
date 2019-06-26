import dayjs from 'dayjs'

export default class DateUtil {
  static format(row: any, column: any, cellValue: any, index: any) {
    return dayjs(cellValue).format('YYYY-MM-DD HH:mm:ss')
  }
  static formatStr(time: string) {
    return dayjs(time).format('YYYY.MM.DD HH:mm')
  }
}
