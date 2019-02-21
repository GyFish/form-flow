
<script lang="tsx">
import Vue, { CreateElement, VNode, VNodeData } from 'vue'
import { Prop, Component } from 'vue-property-decorator'
import { State, Mutation } from 'vuex-class'
import ItemFactory from './items/ItemFactory'

@Component
export default class Item extends Vue {
  // prop
  @Prop() data!: any

  // store
  @State(state => state.data.items) items: any
  @State activeIdx: any
  @Mutation active: any

  render(h: CreateElement): VNode {
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
        {ItemFactory.getItem({ h, item: this.data })}
      </el-form-item>
    )
  }
}
</script>

<style lang="scss">
.el-form-item {
  padding: 1%;
  margin: 0px 5px 0px 0px;
}
.active {
  border: 1px solid #409eff;
}
</style>
