import { VNode, CreateElement, VNodeData } from "vue"
import { ItemOption } from "./ItemFactory"

export default abstract class AbstractItem {
  //~ 属性 -------------------------
  type: string
  item: any
  h: CreateElement
  mutations: any

  attributes: VNodeData
  subVNodes: Array<VNode> = []

  //~ 构造器 -------------------------
  constructor(option: ItemOption) {
    this.type = option.item.type
    this.h = option.h
    this.item = option.item
    this.mutations = option.mutations
    this.attributes = {
      attrs: {
        placeholder: this.item.placeholder
      },
      props: {
        value: this.item.value
      },
      on: {
        input: (value: string) => {
          // 更新表单结构值
          this.item.value = value
          this.mutations.updateByIdx({
            idx: this.item.idx,
            item: this.item
          })
          // 更新结果
          this.mutations.updateResult({
            field: this.item.prop,
            value: value
          })
        }
      }
    }
  }

  //~ 方法 -------------------------
  abstract render(): VNode
}
