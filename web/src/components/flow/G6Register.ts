import G6 from '@antv/g6'

export default class G6Register {
  // ~--------------------------------
  register() {
    G6.registerNode('start', {
      draw(item: any) {
        const group = item.getGraphicGroup()
        const html = G6.Util.createDOM(`<div id="start"></div>`)
        return group.addShape('dom', {
          attrs: {
            x: 0,
            y: 0,
            width: 100,
            height: 28,
            html
          }
        })
      },
      anchor: {
        type: 'circle',
        points: [[0.5, 0], [1, 0.5], [0.5, 1], [0, 0.5]]
      }
    })
  }

  node() {
    G6.registerNode(
      'startNode',
      {
        label: '开始',
        color_type: '#1890FF',
        type_icon_url: 'https://gw.alipayobjects.com/zos/rmsportal/czNEJAmyDpclFaSucYWB.svg',
        state_icon_url: 'https://gw.alipayobjects.com/zos/rmsportal/MXXetJAxlqrbisIuZxDO.svg',
        // 设置锚点
        anchor: [
          [
            0.5,
            0,
            {
              type: 'input'
            }
          ], // 上面边的中点
          [
            0.5,
            1,
            {
              type: 'output'
            }
          ] // 下边边的中点
        ]
      },
      'model'
    )
  }

  model() {
    // 注册模型卡片基类
    G6.registerNode('model', {
      draw(item: any) {
        const group = item.getGraphicGroup()
        const model = item.getModel()
        const width = 238
        const height = 26
        const x = -width / 2
        const y = -height / 2
        const borderRadius = 3
        const keyShape = group.addShape('rect', {
          attrs: {
            x,
            y,
            width,
            height,
            radius: borderRadius,
            fill: '#ecf5ff',
            stroke: '#c6e2ff',
            lineWidth: 0.8
          }
        })
        // 左侧色条
        group.addShape('path', {
          attrs: {
            path: [
              ['M', x, y + borderRadius],
              ['L', x, y + height - borderRadius],
              ['A', borderRadius, borderRadius, 0, 0, 0, x + borderRadius, y + height],
              ['L', x + borderRadius, y],
              ['A', borderRadius, borderRadius, 0, 0, 0, x, y + borderRadius]
            ],
            fill: this.color_type
          }
        })
        // 类型 logo
        group.addShape('image', {
          attrs: {
            img: this.type_icon_url,
            x: x + 10,
            y: y + 5,
            width: 20,
            height: 16
          }
        })
        // 名称文本
        const label = model.label ? model.label : this.label
        group.addShape('text', {
          attrs: {
            text: label,
            x: x + 50,
            y: y + 9,
            textAlign: 'start',
            textBaseline: 'top',
            fill: 'rgba(0,0,0,0.65)'
          }
        })
        return keyShape
      },
      // 设置锚点
      anchor: [
        [0.5, 0], // 上面边的中点
        [0.5, 1] // 下边边的中点
      ]
    })
  }

  line() {
    G6.registerEdge('line', {
      draw(item: any) {
        const group = item.getGraphicGroup()
        const path = this.getPath(item)
        return group.addShape('path', {
          attrs: {
            path,
            stroke: 'red'
          }
        })
      },
      getPath(item: any) {
        const points = item.getPoints()
        return G6.Util.pointsToPolygon(points)
      },
      endArrow: {
        path(item: any) {
          const keyShape = item.getKeyShape()
          let lineWidth = keyShape.attr('lineWidth')
          const width = 15
          const halfHeight = 4
          const radius = lineWidth * 10
          return [
            ['M', -width, halfHeight],
            ['L', 0, 0],
            ['L', -width, -halfHeight],
            ['A', radius, radius, 0, 0, 1, -width, halfHeight],
            ['Z']
          ]
        },
        shorten(item: any) {
          const keyShape = item.getKeyShape()
          return keyShape.attr('lineWidth') * 3.1
        },
        style(item: any) {
          const keyShape = item.getKeyShape()
          const { strokeOpacity, stroke } = keyShape.attr()
          return {
            fillOpacity: strokeOpacity,
            fill: stroke
          }
        }
      }
    })
  }

  elButton(id: string) {
    G6.registerNode(id, {
      draw(item: any) {
        const group = item.getGraphicGroup()
        const html = G6.Util.createDOM(`<div id="${id}"></div>`)
        return group.addShape('dom', {
          attrs: {
            x: -50,
            y: -25,
            width: 100,
            height: 28,
            html
          }
        })
      },
      // 设置锚点
      anchor: {
        type: 'circle',
        points: [[0.5, 0], [1, 0.5], [0.5, 1], [0, 0.5]]
      }
    })
  }
}
