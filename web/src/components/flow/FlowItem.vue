<template>
  <div>
    <div id="success" class="flow-item" draggable="true" @dragstart="dragstart" @dragend="dragend">
      <el-button type="success" icon="el-icon-circle-plus" plain>开始</el-button>
    </div>
    <div id="primary" class="flow-item" draggable="true" @dragstart="dragstart" @dragend="dragend">
      <el-button type="primary" icon="el-icon-tickets" plain>任务节点</el-button>
    </div>
    <div id="warning" class="flow-item" draggable="true" @dragstart="dragstart" @dragend="dragend">
      <el-button type="warning" icon="el-icon-success" plain>审核节点</el-button>
    </div>
    <div id="danger" class="flow-item" draggable="true" @dragstart="dragstart" @dragend="dragend">
      <el-button type="danger" icon="el-icon-error" plain>结束</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import G6Editor, { Flow } from '@antv/g6-editor'

@Component
export default class FlowItem extends Vue {
  // 临时变量，类型
  tempType: any = ''

  mounted() {}

  dragstart(event: any) {
    this.tempType = event.target.id
  }

  dragend(event: any) {
    this.$emit('addNode', event, this.tempType)
  }
}
</script>

<style lang="scss">
.el-button {
  display: block;
  // color: #409eff;
  border-color: #c6e2ff;
  // background-color: #ecf5ff;
  width: 80%;
  margin: 15px 10%;
  text-align: left;
}
</style>
