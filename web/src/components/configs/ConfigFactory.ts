import InputConfig from "./InputConfig"
import SelectConfig from "./SelectConfig"
import { CreateElement, VNode } from "vue"

export default class ConfigFactory {
  static getConfigs(option: ConfigOption): Array<VNode> {
    if (option.item.type == "el-input") return new InputConfig(option).configs
    if (option.item.type == "el-select") return new SelectConfig(option).configs
    return new Array<VNode>()
  }
}

export class ConfigOption {
  item!: any
  h!: CreateElement
  mutations?: any
}
