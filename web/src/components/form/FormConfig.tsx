import Vue, { CreateElement, VNode } from "vue"
import { Prop, Component } from "vue-property-decorator"
import { Mutation, State } from "vuex-class"
import ConfigFactory from "./configs/ConfigFactory"

@Component
export default class FormConfig extends Vue {
  // store
  @Prop() item!: any
  @Mutation updateByIdx: any

  update() {
    this.updateByIdx({
      idx: this.item.idx,
      item: this.item
    })
  }

  render(h: CreateElement): VNode {
    if (!this.item) return <a>字段属性</a>
    return h("el-form", [
      ConfigFactory.getConfigs({
        h,
        item: this.item,
        mutations: {
          update: this.update
        }
      })
    ])
  }
}
