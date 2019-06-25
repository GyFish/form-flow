import Vue from 'vue'
import { Prop, Component } from 'vue-property-decorator'
import Input from './items/Input.vue'
import Select from './items/Select.vue'
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
          class={this.isActive()}
          // 点击时设置选中
          nativeOnClick={() => this.$emit('setActiveItem', this.item.uuid)}
        >
          <component data={this.item} />
        </el-form-item>
      </div>
    )
  }

  isActive() {
    let active = this.curItem ? this.item.uuid == this.curItem.uuid : false
    return { active }
  }

  getItem() {
    switch (this.item.itemType) {
      case 'el-input':
        return Input
      case 'el-select':
        return Select
      default:
        break
    }
  }
}
