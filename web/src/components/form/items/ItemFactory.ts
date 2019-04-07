import InputItem from "./InputItem"
import SelectItem from "./SelectItem"
import DateItem from "./DateItem"
import { CreateElement, VNode } from "vue"

export default class ItemFactory {
  static getItem(option: ItemOption): VNode {
    let {
      item: { itemType }
    } = option
    if (itemType == "el-input") return new InputItem(option).render()
    if (itemType == "el-select") return new SelectItem(option).render()
    if (itemType == "el-date-picker") return new DateItem(option).render()
    return option.h("div")
  }
}

export class ItemOption {
  item!: any
  h!: CreateElement
  mutations?: any
}
