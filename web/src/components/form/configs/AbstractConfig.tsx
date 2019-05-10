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
    this.renderValue(this.h)
    this.renderLabel(this.h)
  }
  //~ 方法 -------------------------
  renderLabel(h: CreateElement) {
    let label = (
      <el-form-item label="名称">
        {h("el-input", {
          props: {
            value: this.item.label
          },
          on: {
            input: (value: string) => {
              this.item.label = value
              this.mutations.update()
            }
          }
        })}
      </el-form-item>
    )
    this.configs.push(label)
  }
  renderValue(h: CreateElement) {
    let value = (
      <el-form-item label="key">
        {this.h("el-input", {
          props: {
            value: this.item.prop
          },
          on: {
            input: (value: string) => {
              this.item.prop = value
              this.mutations.update()
            }
          }
        })}
      </el-form-item>
    )
    this.configs.push(value)
  }
}
