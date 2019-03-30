import AbstractConfig from "./AbstractConfig"
import { ConfigOption } from "./ConfigFactory"
import { CreateElement, VNode } from "vue"

export default class InputConfig extends AbstractConfig {
  constructor(option: ConfigOption) {
    super(option)
    if (this.item.inputType == "text") {
      this.configs.push(this.renderPlaceHolder(this.h))
      this.configs.push(this.renderPrefixIcon(this.h))
    }
  }

  renderPlaceHolder(h: CreateElement) {
    return (
      <el-form-item label="占位文本">
        {h("el-input", {
          on: {
            input: (value: string) => {
              this.item.placeholder = value
              this.mutations.update()
            }
          }
        })}
      </el-form-item>
    )
  }

  renderPrefixIcon(h: CreateElement): VNode {
    return (
      <el-form-item label="首部图标">
        {h("el-input", {
          on: {
            input: (value: string) => {
              this.item.prefixIcon = value
              this.mutations.update()
            }
          }
        })}
      </el-form-item>
    )
  }
}
