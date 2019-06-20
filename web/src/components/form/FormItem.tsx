import Vue from 'vue'
import { Prop, Component } from 'vue-property-decorator'
import Input from './items/Input.vue'
import { Item } from '.'

@Component({ components: { Input } })
export default class FormItem extends Vue {
  // prop
  @Prop() item!: Item
  @Prop() curItem!: any

  render() {
    console.log('=== 渲染表单元素，item =', this.item)

    let component = this.getItem()
    console.debug('  get item =', component)

    return (
      <div class="dynamic-item">
        <el-form-item
          // 字段的名称
          label={this.item.label}
          // 是否选中的样式
          class={{ active: this.item.uuid == this.curItem.uuid }}
          // 点击时设置选中
          nativeOnClick={() => this.$emit('setActiveItem', this.item.uuid)}
        >
          {this.getItem()}
        </el-form-item>
      </div>
    )
  }

  getItem() {
    if (this.item.itemType == 'el-input') {
      return <Input data={this.item} />
    }
  }
}
