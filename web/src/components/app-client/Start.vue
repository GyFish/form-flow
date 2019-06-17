<template>
  <el-container>
    <!-- 顶部搜索、按钮栏 header -->
    <div class="tool-box">
      <el-aside width="100%">
        <!-- 搜索框 -->
        <div class="search-box">
          <el-input></el-input>
          <el-button type="primary" icon="el-icon-search"></el-button>
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
          <div v-for="(item, idx) of formItems" :key="idx">
            <el-row type="flex" align="middle">
              <el-col :span="22">
                <form-item :data="{...item, idx}" :mode="`VIEW`" @onViewInput="onViewInput"></form-item>
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
import { Component, Vue } from 'vue-property-decorator'
import FormItem from '@/components/form/FormItem.tsx'
import { Mutation } from 'vuex-class'
import FlowApi from '@/apis/FlowApi'
import FormApi from '../../apis/FormApi'

@Component({
  components: { FormItem }
})
export default class AppStart extends Vue {
  // 更新 state 中的表单数据
  @Mutation updateFormItems: any

  // 流程列表
  flowList: any = []
  formItems: any = []

  // mounted
  mounted() {
    this.updateFormItems(this.formItems)
    this.getFlowList()
  }

  // 查询流程列表
  async getFlowList() {
    this.flowList = await new FlowApi().getFlowList()
  }

  // 获取表单元素
  async getFormById(id: string) {
    let form = await new FormApi().getFormById(id)
    this.formItems = form.items
  }

  // 选中流程
  handleFlowChange(row: any) {
    console.log(row)
    let id = row.nodes[1].formId
    this.getFormById(id)
  }

  // 提交表单
  commit() {
    console.log(this.formItems)
  }

  // 响应输入值
  onViewInput(value: any) {
    console.log(value)
  }
}
</script>
