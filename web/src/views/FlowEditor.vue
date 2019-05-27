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
          <flow-config :configModel="configModel" @saveDefinition="saveDefinition"></flow-config>
        </div>
      </el-aside>
    </el-container>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'

import G6 from '@antv/g6'

import FlowConfig from '@/components/flow/FlowConfig.vue'
import Maker from '@/components/flow/Maker'
import { FlowNode, ConfigModel } from '@/components/flow/index'
import FlowApi from '@/apis/FlowApi'

@Component({
  components: { FlowConfig }
})
export default class FlowEditor extends Vue {
  //== 图数据 =====================================
  // 图实例
  graph: any = {}
  graphData: any = {
    nodes: [],
    edges: []
  }
  // 中轴线 x 坐标
  axisX = 400
  // 中轴线上节点位置
  axisY = 50
  // 节点间距
  axisYStep = 50

  // 当前节点
  configModel: ConfigModel = {
    activeTab: 'flowConfig',
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
    }
  }
  // 节点列表
  nodeList: Array<FlowNode> = []
  // id - g6Node map
  idG6Map: any = {}

  idNodeMap: any = {}

  // starter 索引
  starterIndex = 0

  // 节点 索引
  nodeIndex = 0

  //== vue 狗子 =====================================

  mounted() {
    this.createEditor()
    this.handleRead()
    this.handleNodeClick()

    this.drawStartNode()

    // 延迟加载 vnode，需要等到 G6 渲染完毕
    setTimeout(this.handleAddNode, 200)
  }

  //== 绘图方法 =====================================

  // 创建编辑器
  createEditor() {
    this.graph = new G6.Graph({
      container: this.$refs.page,
      height: 1000,
      // height: window.innerHeight,
      renderer: 'svg'
    })
  }

  // 渲染数据
  handleRead(graphData: any = {}) {
    this.graph.read(graphData)
  }

  // 点击事件
  handleNodeClick() {
    this.graph.on('node:click', (ev: any) => {
      let nodeId = ev.item.id
      console.log('=== 点击节点', nodeId, ev.item)
      this.configModel.activeTab = 'nodeConfig'
    })
  }

  // 绘制连线
  drawEdge(source: any, target: any) {
    this.graph.add('edge', {
      source,
      target
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
      x: this.axisX,
      y: this.axisY
    })

    this.nodeList.push({
      id: 'startNode',
      nodeName: 'startNode'
    })
  }

  // 绘制节点卡片
  drawNodeCard(node: FlowNode, x: any, y: any) {
    let maker = new Maker()
    let width = 200
    let height = 69

    console.log(`=== draw NodeCard, id = ${node.id}, x = ${x}, y = ${y}`)

    // 注册 shape
    let html = maker.register({
      id: node.id,
      width,
      height
    })

    // 创建 dom
    let g6Node = maker.createDom(
      {
        id: node.id,
        x,
        y
      },
      this.graph
    )

    maker.mountNode(this.$createElement, html, node, {
      add: this.handleAddNode,
      delete: this.handleDeleteNode,
      edit: this.handleEditNode
    })

    this.nodeList.push(node)
    this.idG6Map[node.id] = g6Node
    this.idNodeMap[node.id] = node
  }

  // 增加节点
  handleAddNode(curNode: FlowNode) {
    console.log(`=== handle add node`)

    let { nodes } = this.graph.save()

    console.log('nodes', nodes)
    console.log('curNode', curNode)

    let last = nodes[nodes.length - 1]
    let lastAxixY = this.axisY + (2 * nodes.length - 1) * this.axisYStep

    let node = new FlowNode()
    node.id = 'node-' + new Date().getTime()
    node.nodeName = 'task'

    // 是否中间节点
    if (this.isLast(nodes, curNode)) {
      this.drawNodeCard(node, this.axisX - 200 / 2, lastAxixY)
      this.drawEdge(last.id, node.id)
    } else {
      this.drawNodeCard(node, this.axisX - 200 / 2, this.idG6Map[curNode.id].model.y + 2 * this.axisYStep)
      this.slide(nodes, curNode)
    }
  }

  // 编辑节点
  handleEditNode(node: FlowNode) {
    console.log('=== 编辑节点，node =', node)
    this.configModel.node = node
  }

  // 删除节点
  handleDeleteNode() {
    alert('handleDeleteNode')
  }

  //== node =====================================

  isLast(nodes: any, curNode: FlowNode) {
    if (!curNode) {
      return true
    }
    for (let i = 0; i < nodes.length; i++) {
      const node = nodes[i]
      if (node.id == curNode.id) {
        return i == nodes.length - 1
      }
    }
  }

  slide(nodes: any, curNode: FlowNode) {
    console.log('向后滑动节点')

    let slideFlag = false

    for (const node of nodes) {
      if (slideFlag) {
        this.graph.update(node.id, { y: node.y += 2 * this.axisYStep })
      }
      if (slideFlag || node.id == curNode.id) {
        slideFlag = true
      }
    }
  }

  //== api =====================================

  // 保存流程定义
  async saveDefinition() {
    await new FlowApi().saveDefinition(this.idG6Map)
  }
}
</script>

<style>
</style>
