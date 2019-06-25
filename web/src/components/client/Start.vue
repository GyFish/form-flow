<template>
  <el-container>
    <!-- 顶部搜索、按钮栏 header -->
    <div class="tool-box">
      <el-aside width="100%">
        <!-- 搜索框 -->
        <div class="search-box">
          <el-input placeholder="Start"></el-input>
          <el-button type="primary" icon="el-icon-search" @click="handleSearch"></el-button>
        </div>
        <!-- 任务列表 -->
        <div class="task-list">
          <el-table
            @current-change="handleFlowChange"
            :data="flowList"
            :show-header="false"
            highlight-current-row
          >
            <el-table-column prop="title"></el-table-column>
          </el-table>
        </div>
      </el-aside>
    </div>
    <!-- 中间 main -->
    <div class="main-box">
      <el-main>
        <el-tag v-if="taskVo.taskName" style="margin-bottom:20px">{{taskVo.taskName}}</el-tag>
        <el-form label-position="top">
          <div v-for="(item, idx) of taskVo.formData" :key="idx">
            <el-row type="flex" align="middle">
              <el-col :span="22">
                <form-item :item="item"></form-item>
              </el-col>
            </el-row>
          </div>
          <el-form-item>
            <el-button v-if="taskVo.taskName" type="success" @click="commit">提交</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </div>
  </el-container>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import FormItem from '@/components/form/FormItem.tsx'
import { Mutation } from 'vuex-class'
import FlowApi from '@/apis/FlowApi'
import FormApi from '../../apis/FormApi'
import TaskApi from '../../apis/TaskApi'

@Component({
  components: { FormItem }
})
export default class AppStart extends Vue {
  // 流程列表
  flowList: any = []

  user: any = {}
  appInfo: any = {}

  taskVo = {
    userId: '',
    flowId: '',
    flowTitle: '',
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
    this.flowList = await new FlowApi().getByUser(this.user.id)
  }

  async handleFlowChange(row: any) {
    if (!row) return

    let { id, formId, nodeName } = row.nodes[1]

    this.taskVo.flowId = row.id
    this.taskVo.flowTitle = row.title
    this.taskVo.formId = formId
    this.taskVo.nodeId = id
    this.taskVo.taskName = nodeName

    let form = await new FormApi().getFormById(formId)
    this.taskVo.formData = form.items
  }

  // 提交表单
  async commit() {
    let res = await new TaskApi().start(this.taskVo)
    this.$message.success(res)
    this.handleSearch()
    this.taskVo.formData = []
  }
}
</script>
