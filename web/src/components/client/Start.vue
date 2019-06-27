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
        <div v-if="showTask">
          <el-tag style="margin-bottom:20px">{{taskVo.taskName}}</el-tag>
          <el-form label-position="top">
            <div v-for="(item, idx) of taskVo.formData" :key="idx">
              <el-row type="flex" align="middle">
                <el-col :span="22">
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
import FormItem from '@/components/form/FormItem.tsx'
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

  taskVo: any = {
    formData: []
  }

  showTask = false

  mounted() {
    this.user = JSON.parse(localStorage.user)
    this.handleSearch()
  }

  // 搜索流程
  async handleSearch() {
    this.showTask = false
    this.flowList = await new FlowApi().getByUser(this.user.id)
  }

  // 选择流程
  async handleFlowChange(flow: any) {
    if (!flow) return

    let node = flow.nodes[1]
    let form = await new FormApi().getFormById(node.formId)

    this.taskVo.userId = this.user.id
    this.taskVo.flowId = flow.id
    this.taskVo.formId = node.formId
    this.taskVo.taskName = node.nodeName
    this.taskVo.formData = form.items

    this.showTask = true
  }

  // 提交任务
  async commit() {
    let res = await new TaskApi().start(this.taskVo)
    this.$message.success(res)
    this.handleSearch()
  }
}
</script>
