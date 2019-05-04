// 配置组件用到的定义
export interface FlowConfigData {
  // 当前节点
  curNode: FlowNode
  // 所有节点的 map
  idG6Map: any
}

// 节点
export interface FlowNode {
  // 节点 id
  id: number
  // 所属流程 id
  processId: number
  // 节点名称
  nodeName: string
  // 节点类型
  nodeType: string
  // 节点状态
  status: FlowStatus
  // g6 model
  model: any
  // 处理人 id
  handlerId: number
  // 处理人 name
  handlerName: string
  // 处理人所在组 id
  handlerGroupId: number
  // 处理人所在组 name
  handlerGroupName: string
  // 关联表单
  formId: number
  // 下一节点
  nextNodeId: number
}

// 流程动作，即流程图中的连线
export interface FlowAction {}

// 流程节点的状态
export interface FlowStatus {}
