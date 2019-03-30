import InputItem from "./InputItem"
import SelectItem from "./SelectItem"
import DateItem from "./DateItem"
import { CreateElement, VNode } from "vue"

export default class ItemFactory {
  static getItem(option: ItemOption): VNode {
    let {
      item: { type }
    } = option
    if (type == "el-input") return new InputItem(option).render()
    if (type == "el-select") return new SelectItem(option).render()
    if (type == "el-date-picker") return new DateItem(option).render()
    return option.h("div")
  }
}

export class ItemOption {
  item!: any
  h!: CreateElement
  mutations?: any
}
