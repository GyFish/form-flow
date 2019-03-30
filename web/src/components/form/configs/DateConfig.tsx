import AbstractConfig from "./AbstractConfig"
import { ConfigOption } from "./ConfigFactory"
import { CreateElement, VNode } from "vue"

export default class InputConfig extends AbstractConfig {
  constructor(option: ConfigOption) {
    super(option)
    if (this.item.inputType == "text") {
    }
  }

  //
  
}
