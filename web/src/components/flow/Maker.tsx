import { CreateElement, VNode } from 'vue'

export default class Maker {
  render(h?: CreateElement): VNode {
    return (
      <div>
        <el-dropdown trigger="click">
          <el-button class="addButton" type="success" icon="el-icon-plus" />
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>任务节点</el-dropdown-item>
            <el-dropdown-item>审核节点</el-dropdown-item>
            <el-dropdown-item>分支节点</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    )
  }
}
