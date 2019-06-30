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
import { Prop } from 'vue-property-decorator'

@Component({
  components: { FlowConfig }
})
export default class FlowEditor extends Vue {
  //== 图数据 =====================================

  @Prop()
  metaProp: any

  // 图实例
  graph: any = {}

  graphData: any = {
    nodes: [],
    edges: []
  }

  // 中轴线 x 坐标
  axisX = 450
  // 中轴线上节点位置
  axisY = 50
  // 节点间距
  axisYStep = 50

  // 当前节点
  configModel: ConfigModel = {
    activeTab: 'flowConfig',
    model: {},
    title: '',
    todoShow: '',
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
      handlerGroupName: '',
      active: false
    }
  }
  // 节点列表
  nodeList: Array<FlowNode> = []
  // id - g6Node map
  idG6Map: any = {}
  // id - vueNode map
  idNodeMap: any = {}
  // 应用id
  appId: any = ''

  //== vue 狗子 =====================================

  mounted() {
    console.log('=== mounted ===')

    this.appId = JSON.parse(localStorage.appInfo).id

    this.createEditor()
    this.handleRead()
    this.handleNodeClick()

    this.drawStartNode()

    // 延迟加载 vnode，需要等到 G6 渲染完毕
    let startNode = this.idNodeMap['startNode']
    console.log('  this.idNodeMap =', this.idNodeMap)
    console.log('  this.idG6Map   =', this.idG6Map)
    console.log('  startNode      =', startNode)

    // 判断路由参数，有的话，从参数创建
    if (this.metaProp) {
      this.replay(startNode)
    } else {
      setTimeout(() => this.handleAddNode(startNode), 200)
    }
  }

  replay(startNode: any) {
    console.log('  开始恢复流程图，metaProp =', this.metaProp)

    let { title, todoShow, nodes } = this.metaProp

    this.configModel.title = title
    this.configModel.todoShow = todoShow

    for (let i = 0; i < nodes.length - 1; i++) {
      let curNode = i == 0 ? startNode : nodes[i]
      setTimeout(() => this.handleAddNode(curNode, nodes[i + 1]), 200 * (i + 1))
    }
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
      console.debug('=== 点击节点', nodeId, ev.item)

      if (nodeId == 'startNode') return

      this.configModel.activeTab = 'nodeConfig'

      let curNode: any = this.idNodeMap[nodeId]

      this.configModel.node = curNode

      this.nodeList.forEach(n => (n.active = false))
      curNode.active = true
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
    let startNode = new FlowNode()
    startNode.id = 'startNode'
    startNode.nodeName = 'startNode'

    this.nodeList.push({
      id: 'startNode',
      nodeName: 'startNode'
    })

    this.idNodeMap['startNode'] = startNode

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

    let startG6Node = this.graph.add('node', {
      id: 'startNode',
      shape: 'startNode',
      x: this.axisX,
      y: this.axisY
    })

    this.idG6Map['startNode'] = startG6Node
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

    // 挂载 vue 组件
    maker.mountNode(this.$createElement, html, node, {
      add: this.handleAddNode,
      delete: this.handleDeleteNode,
      edit: this.handleEditNode
    })

    this.idG6Map[node.id] = g6Node
    this.idNodeMap[node.id] = node
  }

  // 增加节点
  handleAddNode(curNode?: FlowNode, newNode?: FlowNode) {
    console.log(`=== handle add node`)

    console.log('  this.nodeList =', this.nodeList)
    console.log('  curNode       =', curNode)

    if (!curNode) {
      curNode = new FlowNode()
      curNode.id = 'startNode'
    }

    if (!newNode) {
      newNode = new FlowNode()
      newNode.id = 'node-' + new Date().getTime()
      newNode.nodeName = 'task'
      ;(newNode.handlerName = ''), (newNode.active = false)
    }

    // 插入点的索引
    let insertPoint = this.getIndex(this.nodeList, curNode)
    console.log('  插入点的索引值 =', insertPoint)

    // 插入点纵坐标，如果是第一个节点，间距小一些
    let step = insertPoint == 0 ? this.axisYStep : 2 * this.axisYStep
    let axisY = this.idG6Map[curNode.id].model.y + step

    // 追加节点
    this.drawNodeCard(newNode, this.axisX - 200 / 2, axisY)

    // 绘制连线
    this.drawEdge(curNode.id, newNode.id)

    // 如果是中间节点，则需要移动后面的位置
    if (!this.isLast(this.nodeList, curNode)) {
      this.slideDown(this.nodeList, curNode)
    }

    // 插入元素，维护列表
    this.nodeList.splice(insertPoint + 1, 0, newNode)
  }

  // 编辑节点
  handleEditNode(node: FlowNode) {
    console.log('=== 编辑节点，node =', node)
    this.configModel.node = node
  }

  // 删除节点
  handleDeleteNode(node: FlowNode) {
    console.log('=== 删除节点，node =', node)

    // 删除节点的索引
    let deletePoint = this.getIndex(this.nodeList, node)
    console.log('  删除节点的索引值 =', deletePoint)

    // 初始节点不允许删除
    if (deletePoint <= 1) {
      return
    }

    if (!this.isLast(this.nodeList, node)) {
      // 上移节点
      this.slideUp(this.nodeList, node)
      // 在断点连线
      this.drawEdge(this.nodeList[deletePoint - 1].id, this.nodeList[deletePoint + 1].id)
    }

    // 从 map 中移除
    delete this.idNodeMap[node.id]
    delete this.idG6Map[node.id]

    // 删除 vue 节点
    this.nodeList.splice(deletePoint, 1)

    // 删除 g6 节点
    this.graph.remove(node.id)
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

  // 插入节点时，数组向后移动
  slideDown(nodeList: any, curNode: FlowNode) {
    let slideFlag = false

    for (const node of nodeList) {
      if (slideFlag) {
        console.log(`  下推节点, nodeName = ${curNode.nodeName}`)
        this.graph.update(node.id, { y: this.idG6Map[node.id].model.y += 2 * this.axisYStep })
      }
      if (slideFlag || node.id == curNode.id) {
        slideFlag = true
      }
    }
  }

  // 删除节点时，数组向前移动
  slideUp(nodeList: any, curNode: FlowNode) {
    let slideFlag = false

    for (const node of nodeList) {
      if (slideFlag) {
        console.log(`  上移节点, nodeName = ${curNode.nodeName}`)
        this.graph.update(node.id, { y: this.idG6Map[node.id].model.y -= 2 * this.axisYStep })
      }
      if (slideFlag || node.id == curNode.id) {
        slideFlag = true
      }
    }
  }

  // 获取索引
  getIndex(nodeList: any, curNode: FlowNode) {
    for (let i = 0; i < nodeList.length; i++) {
      let node = nodeList[i]
      if (node.id == curNode.id) {
        return i
      }
    }
    return -1
  }

  //== api =====================================

  // 保存流程定义
  async saveDefinition() {
    console.log('=== 保存流程定义')

    // check
    if (!this.saveCheck()) return

    let data: any = {
      title: this.configModel.title,
      todoShow: this.configModel.todoShow,
      nodes: this.nodeList,
      graph: this.graph.save()
    }

    data.appId = this.appId

    if (this.metaProp) {
      data.id = this.metaProp.id
    }

    let res = await new FlowApi().saveFlow(data)

    this.$message.success(res)

    this.$router.push('/appAdmin/flowAdmin')
  }

  // 保存表单时的各种检查
  saveCheck() {
    // 提醒内容
    let alertMsg = ''

    // 表单标题
    if (this.configModel.title == '') {
      this.configModel.activeTab = 'flowConfig'
      alertMsg = '请给流程取个名字~'
    }

    // 消息提醒
    if (alertMsg) this.$message.warning(alertMsg)

    return alertMsg == ''
  }
}
</script>
