import AbstractConfig from "./AbstractConfig"
import { ConfigOption } from "./ConfigFactory"
import { CreateElement, VNode } from "vue"

export default class InputConfig extends AbstractConfig {
  constructor(option: ConfigOption) {
    super(option)
    this.configs.push(this.renderType(this.h))
  }

  renderType(h: CreateElement): VNode {
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
