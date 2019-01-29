import { CreateElement, VNode } from "vue"
import { ConfigOption } from "./ConfigFactory"

export default abstract class AbstractConfig {
  //~ 属性 -------------------------
  configs: Array<VNode> = [] // 属性列表
  h: CreateElement // 渲染函数
  item: any // 数据对象
  mutations!: any
  //~ 构造器 -------------------------
  constructor(option: ConfigOption) {
    this.h = option.h
    this.item = option.item
    this.mutations = option.mutations
    this.renderLabel()
    this.renderPlaceHolder()
  }
  //~ 方法 -------------------------
  renderLabel() {
    this.configs.push(
      this.h(
        "el-form-item",
        {
          props: {
            label: "名称"
          }
        },
        [
          this.h("el-input", {
            props: {
              value: this.item.label
            },
            on: {
              input: (value: string) => {
                this.item.label = value
                this.mutations.update()
              }
            }
          })
        ]
      )
    )
  }
  renderPlaceHolder() {
    this.configs.push(
      this.h(
        "el-form-item",
        {
          props: {
            label: "占位文本"
          }
        },
        [
          this.h("el-input", {
            props: {
              value: this.item.placeholder
            },
            on: {
              input: (value: string) => {
                this.item.placeholder = value
                this.mutations.update()
              }
            }
          })
        ]
      )
    )
  }
}
