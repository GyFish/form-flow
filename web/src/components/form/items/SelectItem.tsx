import AbstractItem from "./AbstractItem"
import { ItemOption } from "./ItemFactory"
import { VNode } from "vue"

export default class SelectItem extends AbstractItem {
  constructor(option: ItemOption) {
    super(option)
  }
  render(): VNode {
    let { options } = this.item
    for (const { value, label } of options) {
      this.subVNodes.push(
        this.h("el-option", {
          props: { key: value, value: value, label: label }
        })
      )
    }

    return this.h("el-select", this.attributes, this.subVNodes)
  }
}
