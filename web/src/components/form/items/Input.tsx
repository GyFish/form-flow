
import Vue from 'vue'
import { Prop, Component } from 'vue-property-decorator'

@Component
export default class Input extends Vue {
  @Prop() data!: any

  render() {
    return <el-input></el-input>
  }
}