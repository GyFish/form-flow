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
import Vue from 'vue'
import Component from 'vue-class-component'

import FlowConfig from '@/components/flow/FlowConfig.vue'
import Maker from '@/components/flow/Maker'
import { FlowNode, NodeModel } from '@/components/flow/index'
import FlowApi from '@/apis/FlowApi'

import G6 from '@antv/g6'
import G6Register from '@/components/flow/G6Register'

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

  // starter 索引
  starterIndex = 0
  starterY = 50

  // 节点 索引
  nodeIndex = 0

  //== vue 狗子 =====================================

  mounted() {
    this.createEditor()
    this.handleRead()
    this.handleNodeClick()

    this.drawStartNode()

    // 延迟加载 vnode，需要等到 G6 渲染完毕
    setTimeout(this.handleAddNode, 100)
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
  }

  // 绘制节点卡片
  drawNodeCard(x = this.axisX - 200 / 2, y = 150): string {
    let maker = new Maker()
    let width = 200
    let height = 69
    let id = 'node-' + this.nodeIndex++

    console.log(`=== draw NodeCard, id = ${id}, x = ${x}, y = ${y}`)

    // 注册 shape
    maker.register({
      width,
      height
    })

    // 创建 dom
    maker.createDom(
      {
        id,
        x,
        y
      },
      this.graph
    )

    maker.mountNode(this.$createElement, this.handleAddNode)

    this.starterY += 80
    return id
  }

  handleAddNode(type: string) {
    this.graphData = this.graph.save()
    console.log(this.graphData)

    let { nodes } = this.graphData
    let last = nodes[nodes.length - 1]

    let source = last.id
    let y = this.axisY + (2 * nodes.length - 1) * this.axisYStep

    console.log(
      `=== handle add node, type = ${type}, source = ${source}, y = ${y}`
    )

    let nodeId = this.drawNodeCard(this.axisX - 200 / 2, y)
    console.log(nodeId)

    this.drawEdge(last.id, nodeId)
  }

  // 点击事件
  handleClick() {
    this.graph.on('node:click', (ev: any) => {
      let nodeId = ev.item.id
      console.log('>> 点击节点', nodeId, ev.item)
      this.nodeData.node = this.nodeData.nodeMap[ev.item.id]
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
