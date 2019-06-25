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
            <el-table-column prop="taskName"></el-table-column>
          </el-table>
        </div>
      </el-aside>
    </div>
    <!-- 中间 main -->
    <div class="todo-box">
      <el-main class="content">
        <div v-if="cardName">
          <!-- 卡片 -->
          <card-view :list="itemList" :cardName="cardName"/>
          <!-- 历史 -->
          <el-collapse>
            <el-collapse-item>
              <template slot="title">
                <el-button type="text" icon="el-icon-receiving">Task Log</el-button>
              </template>
              <card-view :list="itemList" :cardName="cardName"/>
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
import CardView from '@/components/form/display/CardView.vue'
import FormItem from '@/components/form/FormItem.tsx'
import '@/styles/app-todo.scss'

@Component({
  components: { CardView, FormItem }
})
export default class AppTodo extends Vue {
  // 流程列表
  taskList: any = []

  cardName: any = ''
  itemList: any = []

  user: any = {}
  appInfo: any = {}

  taskVo = {
    userId: '',
    flowId: '',
    nodeId: '',
    taskName: '',
    formId: '',
    formData: []
  }

  mounted() {
    this.user = JSON.parse(localStorage.user)
    this.taskVo.userId = this.user.id
    this.handleSearch()
  }

  async handleSearch() {
    this.cardName = ''
    this.taskList = await new TaskApi().query({
      userId: this.user.id,
      status: 'TODO'
    })
  }

  async handleFlowChange(task: any) {
    if (!task) return

    let previousTask = await new TaskApi().previous(task.id)

    this.cardName = previousTask.taskName
    this.itemList = previousTask.formData

    let form = await new FormApi().getFormById(task.formId)
    this.taskVo.formData = form.items
  }

  // 提交表单
  async commit() {}
}
</script>
