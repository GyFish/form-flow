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
            height="100%"
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
        <el-form label-position="top">
          <div v-for="(item, idx) of startVo.formData" :key="idx">
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

  @Prop()
  user: any

  startVo = {
    userId: this.user.userId,
    flowId: '',
    nodeId: '',
    taskName: '',
    formId: '',
    formData: []
  }

  mounted() {
    this.handleSearch()
  }

  async handleSearch() {
    this.flowList = await new FlowApi().getByUser(this.user.userId)
  }

  async handleFlowChange(row: any) {
    if (!row) return

    let { id, formId, nodeName } = row.nodes[1]

    this.startVo.flowId = row.id
    this.startVo.formId = formId
    this.startVo.nodeId = id
    this.startVo.taskName = nodeName

    let form = await new FormApi().getFormById(formId)
    this.startVo.formData = form.items
  }

  // 提交表单
  async commit() {
    let res = await new TaskApi().start(this.startVo)
    this.$notify.success(res)
    this.handleSearch()
    this.startVo.formData = []
  }
}
</script>
