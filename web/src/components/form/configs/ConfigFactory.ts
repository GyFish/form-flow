import InputConfig from "./InputConfig"
import SelectConfig from "./SelectConfig"
import DateConfig from "./DateConfig"
import { CreateElement, VNode } from "vue"

export default class ConfigFactory {
  static getConfigs(option: ConfigOption): Array<VNode> {
    console.log('form item config is ' + option.item.type)
    switch (option.item.itemType) {
      case "el-input":
        return new InputConfig(option).configs
      case "el-select":
        return new SelectConfig(option).configs
      case "el-date-picker":
        return new DateConfig(option).configs
      default:
        return new Array<VNode>()
    }
  }
}

export class ConfigOption {
  item!: any
  h!: CreateElement
  mutations?: any
}
