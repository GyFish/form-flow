import AbstractItem from "./AbstractItem"
import { VNode } from "vue"
import { ItemOption } from "./ItemFactory"

export default class DateItem extends AbstractItem {
  constructor(option: ItemOption) {
    super(option)
  }

  render(): VNode {
    
    return this.h("el-date-picker", this.attributes, this.subVNodes)
  }
}
