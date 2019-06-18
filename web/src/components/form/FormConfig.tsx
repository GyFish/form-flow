import Vue from 'vue'
import { Prop, Component } from 'vue-property-decorator'
import InputConfig from './items/InputConfig.vue'

@Component({ components: { InputConfig } })
export default class FormConfig extends Vue {
  @Prop()
  item!: any

  render() {
    console.log('=== 渲染表单配置，item =', this.item)

    let config = this.getConfig()
    console.log('  get config =', config)

    if (this.item.itemType == 'el-input') {
      return (
        <config
          // 绑定 prop
          data={this.item}
          // 绑定自定义事件
          {...{ on: { updateItem: this.updateItem } }}
        />
      )
    }
  }

  getConfig() {
    if (this.item.itemType == 'el-input') {
      return InputConfig
    }
  }

  updateItem(value: any) {
    console.log('value =', value)
    this.$emit('updateItem', value)
  }
}
