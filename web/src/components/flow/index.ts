// G6 node，前两个是 g6 属性
export interface ConfigModel {
  // 当前节点 g6 model
  model: any
  // 节点流程属性
  node: FlowNode
  // 当前配置栏标签页
  activeTab: string
  // 流程名称
  title: any
}

// 节点
export class FlowNode {
  // 节点 id
  id: any
  // 所属流程 id
  processId?: number
  // 节点名称
  nodeName?: string
  // 节点类型
  nodeType?: string
  // 节点状态
  status?: FlowStatus
  // g6 model
  model?: any
  // 处理人 id
  handlerId?: number
  // 处理人 name
  handlerName?: string
  // 处理人所在组 id
  handlerGroupId?: number
  // 处理人所在组 name
  handlerGroupName?: string
  // 关联表单
  formId?: number
  // 下一节点
  nextNodeId?: number
  // 是否可流回
  canFlowBack?: Boolean
  //
}

// 流程动作，即流程图中的连线
export interface FlowAction {}

// 流程节点的状态
export interface FlowStatus {}
