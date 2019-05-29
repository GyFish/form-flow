import { CreateElement, RenderContext, VNode } from 'vue'
import { FlowNode } from '.'

export default class NodeCard {
  render(h: CreateElement, node: FlowNode, handler: any): VNode {
    return (
      <el-card shadow="never" class="card-container">
        <div slot="header">
          <span>{node.nodeName}</span>
          <el-dropdown onCommand={() => handler.add(node)} trigger="click" style="float: right;">
            <el-button type="primary" icon="el-icon-plus" />
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="task">任务节点</el-dropdown-item>
              <el-dropdown-item command="check">审核节点</el-dropdown-item>
              <el-dropdown-item command="branch">分支节点</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-button
            style="float: right; padding: 3px 0; margin-right: 10px;"
            type="text"
            icon="el-icon-delete"
            onClick={() => handler.delete(node)}
          />
          <el-button
            style="float: right; padding: 3px 0; margin-right: 10px;"
            type="text"
            icon="el-icon-edit"
            onClick={() => handler.edit(node)}
          />
        </div>
        <div>
          <span>处理人：{node.handlerName}</span>
        </div>
      </el-card>
    )
  }
}
