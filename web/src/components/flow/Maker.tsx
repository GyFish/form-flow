import Vue, { CreateElement, VNode } from 'vue'
import G6 from '@antv/g6'

export default class Maker {

  html: any
  
  render(h: CreateElement, commandHandler: any): VNode {
    return (
      <el-dropdown trigger="click" onCommand={commandHandler}>
        <el-button class="addButton" type="success" icon="el-icon-plus" />
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="task">任务节点</el-dropdown-item>
          <el-dropdown-item command="check">审核节点</el-dropdown-item>
          <el-dropdown-item command="branch">分支节点</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    )
  }

  register(option: {}) {
    const html = G6.Util.createDOM(`<div>`)
    G6.registerNode('starter', {
      draw(item: any) {
        const group = item.getGraphicGroup()
        return group.addShape('dom', {
          attrs: {
            x: 0,
            y: 0,
            width: 25,
            height: 25,
            html
          }
        })
      }
    })
    this.html = html
  }

  createDom(callBack: Function) {
    callBack.call(this)
  }

  mountVNode(h: CreateElement, commandHandler: any, option: {}) {
    let vueNode = Vue.extend({
      render: () => {
        return this.render(h, commandHandler)
      }
    })
    new vueNode().$mount(this.html)
  }
}
