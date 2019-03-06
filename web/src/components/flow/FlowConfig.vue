<template>
  <el-container>
    <el-main>
      <el-tabs stretch value="flowConfig">
        <el-tab-pane label="流程属性" name="flowConfig">
          <el-form label-position="top">
            <el-form-item>
              <el-form-item label="节点id">
                <el-input :value="curNode.id"></el-input>
              </el-form-item>
            </el-form-item>
            <el-form-item>
              <el-form-item label="节点名称">
                <el-input v-model="nodeName"></el-input>
              </el-form-item>
            </el-form-item>
            <el-form-item label="类型">
              <el-select v-model="nodeType">
                <el-option label="开始节点" value="shanghai"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="下一节点">
              <el-select v-model="nextNode" @change="addLine">
                <el-option v-for="node of allNodes" :label="node.model.nodeName" :value="node.id"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="节点属性" name="nodeConfig">
          <el-form label-position="top">
            <el-form-item>
              <el-form-item label="节点id">
                <el-input :value="curNode.id"></el-input>
              </el-form-item>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-main>
    <el-footer height="50px">
      <el-button type="danger" @click="deleteNode">删除</el-button>
    </el-footer>
  </el-container>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'

@Component
export default class FlowConfig extends Vue {
  // 属性
  @Prop()
  data: any
  // 节点类型
  nodeType: any = ''
  // 下一节点
  nextNode: any = ''
  // 节点绘图属性
  get curNode() {
    return this.data.curNode
  }
  // 节点名称
  get nodeName() {
    let { model } = this.data.curNode
    return model ? model.nodeName : ''
  }
  set nodeName(value: string) {
    let { model } = this.data.curNode
    model.nodeName = value
    this.$emit('updateNodeModel', this.curNode.id, model)
  }
  // 所有节点
  get allNodes() {
    return Object.values(this.data.idNodeMap)
  }

  // 与下一节点连线
  addLine(nextNodeId: any) {
    this.$emit('addLine', { source: this.curNode.id, target: nextNodeId })
  }

  // 删除节点
  deleteNode() {}
}
</script>

<style lang="scss">
</style>
