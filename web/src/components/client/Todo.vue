<template>
  <el-container>
    <!-- 顶部搜索、按钮栏 header -->
    <div class="tool-box">
      <el-aside width="100%">
        <!-- 搜索框 -->
        <div class="search-box">
          <el-input placeholder="Todo"></el-input>
          <el-button type="primary" icon="el-icon-search" @click="handleSearch"></el-button>
        </div>
        <!-- 任务列表 -->
        <div class="task-list">
          <el-table
            @current-change="handleFlowChange"
            :data="taskList"
            :show-header="false"
            highlight-current-row
          >
            <el-table-column prop="taskName"></el-table-column>
          </el-table>
        </div>
      </el-aside>
    </div>
    <!-- 中间 main -->
    <div class="todo-box">
      <el-main class="content">
        <card-view :list="itemList" :cardName="cardName"/>
      </el-main>
    </div>
  </el-container>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import FormApi from '../../apis/FormApi'
import TaskApi from '../../apis/TaskApi'
import CardView from '@/components/form/display/CardView.vue'
import '@/styles/app-todo.scss'

@Component({
  components: { CardView }
})
export default class AppTodo extends Vue {
  // 流程列表
  taskList: any = []

  cardName: any = ''

  itemList: any = []

  user: any = {}
  appInfo: any = {}

  taskVo = {
    userId: this.user.userId,
    flowId: '',
    nodeId: '',
    taskName: '',
    formId: '',
    formData: []
  }

  mounted() {
    this.user = JSON.parse(localStorage.user)
    this.handleSearch()
  }

  async handleSearch() {
    this.taskList = await new TaskApi().query({
      userId: this.user.id,
      status: 'TODO'
    })
  }

  async handleFlowChange(row: any) {
    if (!row) return

    let previousTask = await new TaskApi().previous(row.id)

    this.cardName = previousTask.taskName
    this.itemList = previousTask.formData
  }

  // 提交表单
  async commit() {}
}
</script>
