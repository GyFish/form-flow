import Vue, { CreateElement, VNode } from 'vue'

export default class NodeCard {
  render(h: CreateElement, commandHandler: Function): VNode {
    return (
      <div>
        <el-card shadow="never">
          <div slot="header">
            <span>节点名称</span>
            <el-dropdown
              onCommand={commandHandler}
              trigger="click"
              style="float: right;"
            >
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
            />
            <el-button
              style="float: right; padding: 3px 0; margin-right: 10px;"
              type="text"
              icon="el-icon-edit"
            />
          </div>
        </el-card>
      </div>
    )
  }
}
