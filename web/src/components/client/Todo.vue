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
            <el-table-column prop="processName"></el-table-column>
            <!-- <el-table-column prop="taskName"></el-table-column> -->
            <el-table-column prop="updateTime" :formatter="timeFormatter"></el-table-column>
          </el-table>
        </div>
      </el-aside>
    </div>
    <!-- 中间 main -->
    <div class="todo-box">
      <el-main class="content">
        <div v-if="showTask">
          <!-- 卡片 -->
          <task-card :task="preTask"/>
          <!-- 历史 -->
          <el-collapse @change="showTaskLog">
            <el-collapse-item>
              <template slot="title">
                <el-button type="text" icon="el-icon-receiving">Task Log</el-button>
              </template>
              <task-card v-for="(task, i) of allTasks" :key="i" :task="task"/>
            </el-collapse-item>
          </el-collapse>
          <!-- 表单 -->
          <el-form label-position="top">
            <div v-for="(item, idx) of taskVo.formData" :key="idx">
              <el-row type="flex" align="middle">
                <el-col :span="24">
                  <form-item :item="item"></form-item>
                </el-col>
              </el-row>
            </div>
            <el-form-item>
              <el-button type="success" @click="commit">提交</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-main>
    </div>
  </el-container>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import FormApi from '../../apis/FormApi'
import TaskApi from '../../apis/TaskApi'
import FormItem from '@/components/form/FormItem.tsx'
import TaskCard from '@/components/client/TaskCard.vue'
import DateUtil from '@/util/DateUtil'

@Component({
  components: { TaskCard, FormItem }
})
export default class AppTodo extends Vue {
  // 流程列表
  taskList: any = []

  user: any = {}
  appInfo: any = {}

  taskVo: any = {
    formData: []
  }

  // 首部 task
  preTask: any = {}

  // 历史 所有 task
  allTasks: any = {}

  showTask = false

  mounted() {
    this.user = JSON.parse(localStorage.user)
    this.handleSearch()
  }

  async handleSearch() {
    this.showTask = false
    this.taskList = await new TaskApi().query({
      userId: this.user.id,
      status: 'TODO'
    })
  }

  async handleFlowChange(task: any) {
    if (!task) return

    this.preTask = await new TaskApi().preTask(task.id)

    let form = await new FormApi().getFormById(task.formId)
    this.taskVo.formData = form.items

    this.showTask = true

    this.taskVo.id = task.id
    this.allTasks = await new TaskApi().getByProcess(this.preTask.processId)
  }

  async showTaskLog() {}

  // 提交表单
  async commit() {
    let res = await new TaskApi().commit(this.taskVo)
    this.$message.success(res)
    this.handleSearch()
  }

  timeFormatter(row: any) {
    return DateUtil.formatStr(row.updateTime)
  }
}
</script>
