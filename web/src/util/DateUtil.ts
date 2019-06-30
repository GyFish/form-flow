import dayjs from 'dayjs'

export default class DateUtil {
  static format(row: any, column: any, cellValue: any, index: any) {
    return dayjs(cellValue).format('YYYY-MM-DD HH:mm:ss')
  }
  static formatTime(time: string) {
    return dayjs(time).format('YYYY.MM.DD HH:mm')
  }
  static formatDay(time: string) {
    return dayjs(time).format('MM.DD')
  }
}
