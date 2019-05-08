var data = {
    nodes: [{
        id: 'node1',
        x: 100,
        y: 200,
        shape: 'circle',
        style: {
            fill: 'red'
        }
        //img: 'https://gw.alipayobjects.com/os/s/prod/antv/assets/image/logo-with-text-73b8a.svg'
    }, {
        id: 'node2',
        x: 300,
        y: 200,
        label: '萧庆',
        labelCfg: {
            position: 'bottom'
        },
        shape: 'image',
        img: 'https://img2.bosszhipin.com/boss/avatar/avatar_13.png'
    }, {
        id: 'node3',
        x: 400,
        y: 100,
        shape: 'image',
        label: '语雀',
        labelCfg: {
            position: 'bottom'
        },
        img: 'https://gw.alipayobjects.com/zos/rmsportal/XuVpGqBFxXplzvLjJBZB.svg'
    }, {
        id: 'node4',
        x: 400,
        y: 400,
        shape: 'image',
        img: '//img.alicdn.com/tfs/TB1_uT8a5ERMeJjSspiXXbZLFXa-143-59.png'
    }],
    edges: [{
        id: 'edge1',
        target: 'node2',
        source: 'node1',
        style: {
            endArrow: true
        },
        label: '你好,我好',
        labelCfg: {
            style: {stroke: 'white', lineWidth: 5} // 加白边框
        }
    }, {
        source: 'node2',
        target: 'node3',
        style: {
            endArrow: true
        },
        shape: 'quadratic',
        label: '过去的线',
        labelCfg: {
            refY: -10,
            refX: 0,
            autoRotate: true,
            style: {
                fill: 'red'
            }
        }
    },
        {
            source: 'node3',
            target: 'node2',
            style: {
                endArrow: true,
                stroke: 'red'
            },
            size: 2,
            shape: 'quadratic',
            label: '回来的线',
            labelCfg: {
                refY: -10,
                refX: 0,
                autoRotate: true,
                style: {
                    fill: 'red'
                }
            }
        }, {
            source: 'node3',
            target: 'node4',
            style: {
                endArrow: true,
                stroke: 'blue',
                lineDash: [2, 2]
            },
            shape: 'my-edge',
            label: '随便连连\n换行',
            curveLevel: 4,
            labelCfg: {
                refY: -20,
                refX: 0,
                autoRotate: true,
                style: {
                    fill: 'red'
                }
            }
        }]
};