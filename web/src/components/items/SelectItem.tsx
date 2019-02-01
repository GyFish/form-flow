import AbstractItem from "./AbstractItem"
import { ItemOption } from "./ItemFactory"
import { VNode } from "vue"

export default class SelectItem extends AbstractItem {
  constructor(option: ItemOption) {
    super(option)
  }
  render(): VNode {
    let { options } = this.item
    for (const option of options) {
      this.subVNodes.push(
        this.h("el-option", {
          props: { value: option.value, label: option.label }
        })
      )
    }

    return this.h("el-select", this.attributes, this.subVNodes)
  }
}
