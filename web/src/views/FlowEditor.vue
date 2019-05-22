<template>
  <div class="flow-editor">
    <el-container>
      <!-- 画板 -->
      <el-main>
        <div ref="page" class="flow-page"></div>
      </el-main>

      <!-- 右侧配置面板 -->
      <el-aside>
        <div class="flow-config">
          <flow-config :nodeData="nodeData" @saveDefinition="saveDefinition"></flow-config>
        </div>
      </el-aside>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import FlowConfig from '@/components/flow/FlowConfig.vue'
import G6 from '@antv/g6'
import G6Register from '@/components/flow/G6Register'
import { FlowNode, NodeModel } from '@/components/flow/index'
import FlowApi from '@/apis/FlowApi'
import Maker from '@/components/flow/Maker'

@Component({
  components: { FlowConfig }
})
export default class FlowEditor extends Vue {
  //== 图数据 =====================================
  // 图实例
  graph: any = {}
  // 当前节点
  nodeData: NodeModel = {
    model: {},
    node: {
      id: 0,
      processId: 0,
      formId: 0,
      nextNodeId: 0,
      nodeName: '',
      nodeType: '',
      status: {},
      model: {},
      handlerId: 0,
      handlerName: '',
      handlerGroupId: 0,
      handlerGroupName: ''
    },
    dataMap: {},
    nodeMap: {}
  }
  // id - g6Node map
  idG6Map: any = {}
  // 当前节点位置
  dx: any = 0
  dy: any = 0
  // 绘制连线位置
  lx: any = 0
  ly: any = 0

  // starter 索引
  starterIndex: any = 0

  //== vue 狗子 =====================================

  mounted() {
    this.createEditor()
    this.handleRead()
    this.handleNodeClick()

    this.drawStartNode()

    // 延迟加载 vnode，需要等到 G6 渲染完毕
    setTimeout(this.drawStarterButton, 10)
  }

  //== 绘图方法 =====================================

  // 创建编辑器
  createEditor() {
    this.graph = new G6.Graph({
      container: this.$refs.page,
      height: window.innerHeight,
      renderer: 'svg'
    })
  }

  // 渲染数据
  handleRead(graphData: any = {}) {
    this.graph.read(graphData)
  }

  handleNodeClick() {
    this.graph.on('node:click', (ev: any) => {
      let nodeId = ev.item.id
    })
  }

  // 绘制开始节点
  drawStartNode() {
    G6.registerNode('startNode', {
      draw(item: any) {
        const group = item.getGraphicGroup()
        const model = item.getModel()
        let keyShape = group.addShape('circle', {
          attrs: {
            x: 0,
            y: 0,
            r: 30,
            fill: '#ECF5FF',
            stroke: '#C6E2FF',
            lineWidth: 2
          }
        })
        group.addShape('text', {
          attrs: {
            x: 0,
            y: 0,
            fill: '#333',
            text: '开始',
            textBaseline: 'middle',
            textAlign: 'center'
          }
        })
        return keyShape
      }
    })

    this.graph.add('node', {
      id: 'startNode',
      shape: 'startNode',
      x: 400,
      y: 50
    })
  }

  // 绘制增加节点
  drawStarterButton() {
    console.log('drawStarterButton')

    let maker = new Maker()
    let id = 'starter' + this.starterIndex++

    // 注册
    maker.register()

    // 创建 dom
    maker.createDom(() => {
      this.graph.add('node', {
        id,
        shape: 'starter',
        x: 400 - 25 / 2,
        y: 100
      })
    })

    // 挂载 vnode
    maker.mountVNode(this.$createElement, this.addFlowNode)

    // 绘制连线
    this.graph.add('edge', {
      id: 'start-line',
      source: 'startNode',
      target: id
    })
  }

  addFlowNode(type: string) {
    alert(type)
  }

  // 点击事件
  handleClick() {
    this.graph.on('node:click', (ev: any) => {
      let nodeId = ev.item.id
      console.log('>> 点击节点', nodeId, ev.item)
      this.nodeData.node = this.nodeData.nodeMap[ev.item.id]
    })
  }

  // 拖动开始时
  handleDragStart() {
    this.graph.on('node:dragstart', (ev: any) => {
      let {
        item: { id, model }
      } = ev
      // console.log('拖动开始', id, model)
      this.nodeData.model = model
      this.dx = model.x - ev.x
      this.dy = model.y - ev.y
    })
  }

  // 拖动时
  handleDrag() {
    this.graph.on('node:drag', (ev: any) => {
      let {
        item: { id, model }
      } = ev
      // console.log('拖动时', id, model)
      this.graph.update(id, {
        x: ev.x + this.dx,
        y: ev.y + this.dy
      })
    })
  }

  //== api =====================================

  // 保存流程定义
  async saveDefinition() {
    console.log(this.nodeData.dataMap)
    await new FlowApi().saveDefinition(this.idG6Map)
  }
}
</script>

<style>
</style>
