<template>
  <div class="flow-item-container">
    <div id="success" class="flow-item" draggable="true" @dragstart="dragstart" @dragend="dragend">
      <el-button autofocus type="success" icon="el-icon-circle-plus">开始</el-button>
    </div>
    <div id="primary" class="flow-item" draggable="true" @dragstart="dragstart" @dragend="dragend">
      <el-button autofocus type="primary" icon="el-icon-tickets">任务节点</el-button>
    </div>
    <div id="warning" class="flow-item" draggable="true" @dragstart="dragstart" @dragend="dragend">
      <el-button autofocus type="warning" icon="el-icon-success">审核节点</el-button>
    </div>
    <div id="danger" class="flow-item" draggable="true" @dragstart="dragstart" @dragend="dragend">
      <el-button autofocus type="danger" icon="el-icon-error">结束</el-button>
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
