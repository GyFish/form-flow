import { VNode, CreateElement, VNodeData } from "vue"
import { ItemOption } from "./ItemFactory"

export default abstract class AbstractItem {
  //~ 属性 -------------------------
  type: string
  item: any
  h: CreateElement

  attributes: VNodeData
  subVNodes: Array<VNode> = []

  //~ 构造器 -------------------------
  constructor(option: ItemOption) {
    this.type = option.item.type
    this.item = option.item
    this.h = option.h
    this.attributes = {
      attrs: {
        placeholder: this.item.placeholder
      },
      props: { value: this.item.value }
    }
  }

  //~ 方法 -------------------------
  abstract render(): VNode
}
