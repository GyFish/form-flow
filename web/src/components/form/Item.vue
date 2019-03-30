
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
  @Mutation update: any
  @Mutation updateByIdx: any
  @Mutation updateResult: any

  render(h: CreateElement): VNode {
    return (
      <div class="dynamic-item">
        <el-form-item
          label={this.data.label}
          class={{ active: this.data.idx == this.activeIdx }}
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
              updateByIdx: this.updateByIdx
            }
          })}
        </el-form-item>
      </div>
    )
  }
}
</script>
