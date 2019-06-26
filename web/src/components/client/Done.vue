<template>
  <el-container>
    <!-- 顶部搜索、按钮栏 header -->
    <div class="tool-box">
      <el-aside width="100%">
        <!-- 搜索框 -->
        <div class="search-box">
          <el-input placeholder="Done"></el-input>
          <el-button type="primary" icon="el-icon-search" @click="handleSearch"></el-button>
        </div>
        <!-- 任务列表 -->
        <div class="task-list">
          <el-table
            @current-change="handleFlowChange"
            :data="processList"
            :show-header="false"
            highlight-current-row
          >
            <el-table-column prop="processName"></el-table-column>
            <el-table-column prop="taskName"></el-table-column>
          </el-table>
        </div>
      </el-aside>
    </div>
    <!-- 中间 main -->
    <div class="todo-box">
      <el-main class="content">
        <div v-for="(task, i) of taskList" :key="i">
          <task-card :task="task"/>
        </div>
      </el-main>
    </div>
  </el-container>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import FormApi from '../../apis/FormApi'
import TaskApi from '../../apis/TaskApi'
import TaskCard from '@/components/client/TaskCard.vue'

@Component({
  components: { TaskCard }
})
export default class AppTodo extends Vue {
  // 流程列表
  processList: any = []
  taskList: any = []

  user: any = {}
  appInfo: any = {}

  mounted() {
    this.user = JSON.parse(localStorage.user)
    this.appInfo = JSON.parse(localStorage.appInfo)
    this.handleSearch()
  }

  // 搜索已处理流程
  async handleSearch() {
    this.processList = await new TaskApi().query({
      userId: this.user.id,
      status: 'DONE'
    })
  }

  // 选择流程
  async handleFlowChange(task: any) {
    if (!task) return

    this.taskList = await new TaskApi().query({
      processId: task.processId
    })
  }
}
</script>
