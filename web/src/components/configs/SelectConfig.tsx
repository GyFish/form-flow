import AbstractConfig from "./AbstractConfig"
import { ConfigOption } from "./ConfigFactory"
import { CreateElement, VNode } from "vue"

export default class InputConfig extends AbstractConfig {
  constructor(option: ConfigOption) {
    super(option)
    this.configs.push(this.renderType(this.h))
  }

  options(h: CreateElement) {
    let { options } = this.item
    return options.map((option: any, index: any) => {
      return (
        <el-form-item>
          <el-col span={20} style="margin-right:5px">
            {h("el-input", {
              props: {
                value: option.label
              },
              on: {
                input: (value: string) => {
                  option.label = value
                  this.mutations.update()
                }
              }
            })}
          </el-col>
          <el-col span={2}>
            {h("el-button", {
              props: {
                circle: true,
                icon: "el-icon-minus",
                type: "danger",
                size: "mini"
              },
              on: {
                click: () => {
                  options.splice(index, 1)
                  this.mutations.update()
                }
              }
            })}
          </el-col>
        </el-form-item>
      )
    })
  }

  renderType(h: CreateElement): VNode {
    return (
      <div>
        <el-form-item label="选项" />
        {this.options(h)}
        <el-form-item>
          <el-col offset={20} span={2} style="padding-left:5px">
            <el-button
              circle
              icon="el-icon-plus"
              type="primary"
              size="mini"
              on-click={() => {
                this.item.options.push({label: '', value: ''})
              }}
            />
          </el-col>
        </el-form-item>
      </div>
    )
  }
}
