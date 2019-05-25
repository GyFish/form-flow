import Vue, { CreateElement, VNode } from 'vue'
import G6 from '@antv/g6'
import NodeCard from '@/components/flow/NodeCard'

export default class Maker {
  html: any

  shape = 'shape'

  register(option: any) {
    const html = G6.Util.createDOM(`<div>`)
    G6.registerNode(this.shape, {
      draw(item: any) {
        const group = item.getGraphicGroup()
        return group.addShape('dom', {
          attrs: {
            x: 0,
            y: 0,
            width: option.width,
            height: option.height,
            html
          }
        })
      },
      anchor: [[0.5, 0], [0.5, 1]]
    })
    this.html = html
  }

  createDom(model: any, graph: any) {
    graph.add('node', {
      id: model.id,
      x: model.x,
      y: model.y,
      shape: this.shape
    })
  }

  mountNode(h: CreateElement, commandHandler: any) {
    let vueNode = Vue.extend({
      render: () => {
        return new NodeCard().render(h, commandHandler)
      }
    })
    new vueNode().$mount(this.html)
  }
}
