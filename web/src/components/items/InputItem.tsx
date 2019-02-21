import AbstractItem from "./AbstractItem"
import { VNode } from "vue"
import { ItemOption } from "./ItemFactory"

export default class InputItem extends AbstractItem {
  constructor(option: ItemOption) {
    super(option)
  }

  render(): VNode {

    // 解决一开始未申明prop，后添加时不能实时渲染的问题
    let { prefixIcon, inputType } = this.item
    let { props } = this.attributes

    // 类型
    if (!inputType) {
      inputType = "text"
    }
    props = Object.assign(props, {
      type: inputType
    })

    // 输入框
    if (inputType == "text") {
      // 首部图标
      this.item.prefixIcon = prefixIcon ? prefixIcon : ""
      props = Object.assign(props, {
        "prefix-icon": prefixIcon
      })
    }

    
    this.attributes.props = props
    return this.h("el-input", this.attributes, this.subVNodes)
  }
}
