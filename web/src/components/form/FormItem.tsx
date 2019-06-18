import Vue, { CreateElement, VNode } from 'vue'
import { Prop, Component } from 'vue-property-decorator'
import { State, Mutation } from 'vuex-class'
import ItemFactory from './items/ItemFactory'
import Input from './items/Input'

@Component({ components: { Input } })
export default class FormItem extends Vue {
  // prop
  @Prop() data!: any
  @Prop() mode!: any

  // store
  @State(state => state.data.items) items: any
  @State activeIdx: any
  @Mutation active: any
  @Mutation updateFormItems: any
  @Mutation updateByIdx: any
  @Mutation updateResult: any

  render() {
    console.log('=== 渲染表单元素，item =', this.data)
    return (
      <div class="dynamic-item">
        <el-form-item
          // 字段的名称
          label={this.data.label}
          // 是否选中的样式
          class={{ active: this.data.idx == this.activeIdx }}
          // 点击时设置选中
          nativeOnClick={() => this.active(this.data.idx)}
        >
          {/* {ItemFactory.getItem({
            h,
            item: this.data,
            mutations: {
              updateResult: this.updateResult,
              updateByIdx: this.updateByIdx
            },
            mode: this.mode,
            emit: this.$emit
          })} */}
          {this.getItem()}
        </el-form-item>
      </div>
    )
  }

  getItem() {
    if (this.data.itemType == 'el-input') {
      return <Input data={this.data} />
    }
  }
}
