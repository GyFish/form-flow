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
          <flow-config
            :nodeData="nodeData"
            @addLine="addLine"
            @updateVueModel="updateVueModel"
            @saveDefinition="saveDefinition"
          ></flow-config>
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

  //== vue 狗子 =====================================

  mounted() {
    this.createEditor()
    this.handleRead()

    this.drawStartNode()
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

  // 绘制开始节点
  drawStartNode() {

    G6.registerNode('startNode', {
      draw(item: any){
        const group = item.getGraphicGroup()
        const model = item.getModel()
        let keyShape = group.addShape('circle', {
          attrs: {
            x: 0,
            y: 0,
            r: 30,
            fill: '#ECF5FF',
            stroke: '#C6E2FF'
          }
        })
        group.addShape('text', {
          attrs: {
            x: 0,
            y: 0,
            fill: '#333',
            text: '点击开始',
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
      y: 50,
    })

    this.graph.on('node:click', (ev: any) => {
      let nodeId = ev.item.id
      if (nodeId == 'startNode') {
        this.drawAddButton()
        this.mountStart()
        this.startLine()
      }
    })

  }

  // 绘制增加节点
  drawAddButton() {

    G6.registerNode('addButton', {
      draw(item: any){
        const group = item.getGraphicGroup();
        const html = G6.Util.createDOM(`<div id="add">`);
        return group.addShape('dom', {
          attrs: {
            x: 0,
            y: 0,
            width: 28,
            height: 28,
            html
          }
        })
      }
    })

    let addButton = {
      id: 'addButton',
      shape: 'addButton',
      x: 400 - 28 / 2,
      y: 100,
    }
    this.graph.add('node', addButton)
  
  }

  startLine() {
    this.graph.add('edge', {
      id: 'edge1',
      target: 'addButton',
      source: 'startNode'
    })
  }

  mountStart() {
    let vueNode = Vue.extend({
      render: () => { return new Maker().render(this.$createElement) }
      // render: h => h('el-button', {
      //   props: {
      //     type: 'success',
      //     icon: 'el-icon-plus',
      //     circle: true,
      //   }
      // })
    })
    new vueNode().$mount('#add')
  }

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
    let g6model = {
      id,
      shape: id,
      x: event.clientX - 320,
      y: event.clientY - 56
    }
    let nodeModel = {
      id,
      nodeName,
      nodeType,
      nodeIcon
    }

    // 向图内创建新节点
    new G6Register().elButton(id)
    let node = this.graph.add('node', g6model)

    // 挂载节点组件
    this.renderVueNode(nodeModel)

    // 同步数据
    this.nodeData.model = node
    this.nodeData.dataMap = node.dataMap
    this.nodeData.node = nodeModel
    this.nodeData.nodeMap[id] = nodeModel

    console.log('>> 更新 nodeData = ', this.nodeData)
  }

  // 修该节点属性
  updateVueModel() {
    console.log('updateVueModel')
    // 更新 vue 节点
    this.renderVueNode(this.nodeData.node)
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

  // 渲染数据
  handleRead(graphData: any = {}) {
    this.graph.read(graphData)
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

  // 在节点上挂载 vue 组件
  renderVueNode(flowModel: any) {
    console.log('>> renderVueNode = ', flowModel)
    let vueNode = Vue.extend({
      render: h =>
        h(
          'el-button',
          {
            attrs: {
              id: flowModel.id
            },
            props: {
              autofocus: true,
              type: flowModel.nodeType,
              icon: flowModel.nodeIcon
            }
          },
          [flowModel.nodeName]
        )
    })
    new vueNode().$mount('#' + flowModel.id)
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
