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
      <el-form-item label="类型">
        <el-radio-group v-model={this.item.type}>
          <el-radio-button label="上海" />
          <el-radio-button label="北京" />
          <el-radio-button label="广州" />
          <el-radio-button label="深圳" />
        </el-radio-group>
      </el-form-item>
    )
  }
}
