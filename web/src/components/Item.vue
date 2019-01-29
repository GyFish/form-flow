
<script lang="tsx">
import Vue, { CreateElement, VNode, VNodeData } from 'vue'
import { Prop, Component } from 'vue-property-decorator'
import { State, Mutation } from 'vuex-class'

@Component
export default class Item extends Vue {
  // prop
  @Prop() data!: any

  // store
  @State(state => state.data.items) items: any
  @State activeIdx: any
  @Mutation active: any

  mounted() {
    // 渲染完成之后，prop可能已经改变，通知一下父组件刷新 state.data，只需要通知一次，渲染完成之后
    console.log('item page mounted...')
    this.$emit('flushByIdx', this.data)
  }

  render(h: CreateElement): VNode {
    // 基本的属性
    let attributes: VNodeData = {
      attrs: {
        placeholder: this.data.placeholder
      },
      props: { value: this.data.value }
    }

    let subElements = new Array<VNode>()

    if (this.data.type == 'el-input') {
      // add or init prefix-icon 解决一开始未申明prop，后添加时不能实时渲染的问题
      let { prefixIcon } = this.data
      let { props } = attributes
      if (!prefixIcon) {
        prefixIcon = ''
        this.data.prefixIcon = prefixIcon
      }
      props = Object.assign(attributes.props, {
        'prefix-icon': prefixIcon
      })
      // 类型
      if (this.data.inputType == 'textarea')

      attributes.props = props
    }

    if (this.data.type == 'el-select') {
      let { options } = this.data
      for (const option of options)
        subElements.push(
          h('el-option', {
            props: { value: option.value, label: option.label }
          })
        )
      let o = (<h1></h1>)
    }

    return (
      <el-form-item
        label={this.data.label}
        class={{ active: this.data.idx == this.activeIdx }}
        nativeOn={{
          click: () => {
            this.active(this.data.idx)
          }
        }}
      >
        {h(this.data.type, attributes, subElements)}
      </el-form-item>
    )
  }
}
</script>

<style lang="scss">
.el-form-item {
  padding: 1%;
  margin: 0;
}
.active {
  border: 1px solid #409eff;
}
</style>
