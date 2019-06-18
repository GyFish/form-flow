import Vue, { CreateElement, VNode } from 'vue'
import { Prop, Component } from 'vue-property-decorator'
// import { Mutation, State } from 'vuex-class'
// import ConfigFactory from './configs/ConfigFactory'
import InputConfig from './items/InputConfig.vue'

@Component({ components: { InputConfig } })
export default class FormConfig extends Vue {
  // store
  @Prop() item!: any
  // @Mutation updateByIdx: any

  // update() {
  //   this.updateByIdx({
  //     idx: this.item.idx,
  //     item: this.item
  //   })
  // }

  render() {
    console.log('=== 渲染表单配置，item =', this.item)
    if (this.item.itemType == 'el-input') {
      return <InputConfig data={this.item} {...{ on: { updateItem: this.updateItem } }} />
    }
  }

  updateItem(value: any) {
    console.log('value =', value)
    this.$emit('updateItem', value)
  }
}
