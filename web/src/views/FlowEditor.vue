<template>
  <div class="flow-editor">
    <el-container>
      <!-- 左侧类型节点面板 -->
      <el-aside>
        <flow-item @addNode="addNode"></flow-item>
      </el-aside>
      <!-- 中间画板 -->
      <el-main>
        <div ref="page" class="flow-page"></div>
      </el-main>
      <!-- 右侧配置面板 -->
      <el-aside>
        <div class="flow-config">
          <flow-config
            :configData="configData"
            @addLine="addLine"
            @updateNodeModel="updateNodeModel"
          ></flow-config>
        </div>
      </el-aside>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import FlowItem from '@/components/flow/FlowItem.vue'
import FlowConfig from '@/components/flow/FlowConfig.vue'
import G6 from '@antv/g6'
import G6Register from '@/components/flow/G6Register'

@Component({
  components: { FlowItem, FlowConfig }
})
export default class FlowEditor extends Vue {
  //== 图数据 =====================================
  data = {}
  // 图实例
  graph: any = {}
  // 当前节点
  curNode: FlowNode = {}
  // id - g6Node map
  idG6Map: any = {}
  // id - Vue map
  idVueMap: any = {}
  // 当前节点位置
  dx: any = 0
  dy: any = 0
  // 绘制连线位置
  lx: any = 0
  ly: any = 0

  //== 其他数据 =====================================

  get configData() {
    return { curNode: this.curNode, idG6Map: this.idG6Map }
  }

  //== vue 狗子 =====================================

  mounted() {
    this.createEditor()
  }

  //== 绘图方法 =====================================

  // 获取节点
  getNode(ev: any) {
    console.log('点击节点')
  }

  // 增加节点
  addNode(event: any, nodeType: string) {
    console.log('增加新节点')

    let nodeName = ''
    let nodeIcon = ''
    if (nodeType == 'success') {
      nodeName = '开始'
      nodeIcon = 'el-icon-circle-plus'
    }
    if (nodeType == 'primary') {
      nodeName = '任务节点'
      nodeIcon = 'el-icon-tickets'
    }
    if (nodeType == 'warning') {
      nodeName = '审核节点'
      nodeIcon = 'el-icon-success'
    }
    if (nodeType == 'danger') {
      nodeName = '结束'
      nodeIcon = 'el-icon-error'
    }

    let id: string = 'node-' + new Date().getTime().toString()
    let model = {
      id,
      shape: id,
      x: event.clientX - 320,
      y: event.clientY - 56,
      nodeName,
      nodeType,
      nodeIcon
    }

    // 向图内创建新节点
    new G6Register().elButton(id)
    let node = this.graph.add('node', model)

    // 挂载节点组件
    this.createVueNode(model)

    // 同步数据
    this.curNode = node
    this.idG6Map[id] = node
  }

  // 修该节点属性
  updateNodeModel(id: string, nodeModel: any) {
    console.log('update node id = ', id)
    console.log('update node model = ', nodeModel)
    // 更新图
    this.graph.update(id, nodeModel)
    // 更新 vue 节点
    this.createVueNode(nodeModel)
  }

  // 增加连线
  addLine(newLine: any) {
    this.graph.add('edge', {
      shape: 'line',
      source: newLine.source,
      target: newLine.target,
      endArrow: true
    })
  }

  // 创建编辑器
  createEditor() {
    console.log('创建编辑器')
    // 注册节点
    new G6Register().register()
    // 创建图实例
    const graph = new G6.Graph({
      container: this.$refs.page,
      height: 500,
      renderer: 'svg'
    })
    this.graph = graph
    // 点击事件
    graph.on('node:click', (ev: any) => {
      console.log('点击节点，id = ' + ev.item.id)
      this.curNode = this.idG6Map[ev.item.id]
    })
    // 拖动开始时
    graph.on('node:dragstart', (ev: any) => {
      const { item } = ev
      this.curNode = item
      const model = item.getModel()
      this.dx = model.x - ev.x
      this.dy = model.y - ev.y
    })
    // 拖动时
    graph.on('node:drag', (ev: any) => {
      this.curNode &&
        graph.update(this.curNode, {
          x: ev.x + this.dx,
          y: ev.y + this.dy
        })
    })
    // 渲染数据
    graph.read(this.data)
  }

  // 在节点上挂载 vue 组件
  createVueNode(nodeModel: any) {
    console.log('mount vue node, id = ' + nodeModel.id)
    let vueNode = Vue.extend({
      render: h =>
        h(
          'el-button',
          {
            attrs: {
              id: nodeModel.id
            },
            props: {
              autofocus: true,
              type: nodeModel.nodeType,
              icon: nodeModel.nodeIcon
            }
          },
          [nodeModel.nodeName]
        )
    })
    new vueNode().$mount('#' + nodeModel.id)
  }

  //== 其他方法 =====================================
}

interface FlowNode {}
</script>
