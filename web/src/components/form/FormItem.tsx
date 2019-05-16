import Vue, { CreateElement, VNode } from "vue"
import { Prop, Component } from "vue-property-decorator"
import { State, Mutation } from "vuex-class"
import ItemFactory from "./items/ItemFactory"

@Component
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
    return this.renderByFactory(this.$createElement)
  }

  renderByFactory(h: CreateElement): VNode {
    console.log(this.data)
    return (
      <div class="dynamic-item">
        <el-form-item
          // 字段的名称
          label={this.data.label}
          // 是否选中的样式
          class={{ active: this.data.idx == this.activeIdx }}
          // 点击时设置选中
          nativeOn={{
            click: () => {
              this.active(this.data.idx)
            }
          }}
        >
          {ItemFactory.getItem({
            h,
            item: this.data,
            mutations: {
              updateResult: this.updateResult,
              updateByIdx: this.updateByIdx,
            },
            mode: this.mode,
            emit: this.$emit
          })}
        </el-form-item>
      </div>
    )
  }
}
