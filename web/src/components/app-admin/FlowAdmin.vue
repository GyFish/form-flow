<template>
  <div class="admin-box">
    <el-container>
      <el-main class="main-box">
        <div class="search-box">
          <el-input width="100px" placeholder="code / name"></el-input>
          <el-button @click="search" type="success" icon="el-icon-search">查询</el-button>
          <el-button @click="toFlowEditor" type="primary" icon="el-icon-plus">新建</el-button>
        </div>
        <div class="list-box">
          <el-table fit :show-header="true" :data="flowInfoList">
            <el-table-column label="标题" fit prop="title"></el-table-column>
            <el-table-column label="创建时间" fit prop="createTime" :formatter="timeFormater"></el-table-column>
            <el-table-column fit align="right">
              <template slot-scope="scope">
                <el-button circle type="text" icon="el-icon-view" @click="handleView(scope.row)"/>
                <el-button circle type="text" icon="el-icon-edit" @click="handleEdit(scope.row)"/>
                <el-button
                  circle
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                />
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
      <!-- 预览弹出框 -->
      <el-dialog fullscreen title="预览" :visible.sync="showViewFlag"></el-dialog>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Mutation } from 'vuex-class'
import FlowApi from '@/apis/FlowApi'
import DateUtil from '@/util/DateUtil'

@Component
export default class AppInfo extends Vue {
  //~ ------------------------------------------------------
  // 表单列表
  flowInfoList: any = []

  nodeList: any = []

  // 是否显示预览
  showViewFlag = false

  timeFormater = DateUtil.format

  mounted() {
    this.getFlowList()
  }

  // 查询流程列表
  async getFlowList() {
    this.flowInfoList = await new FlowApi().getFlowList()
  }

  // 搜索
  search() {
    this.getFlowList()
  }

  // 新建，跳转流程图编辑器
  toFlowEditor() {
    this.$router.push('/flowEditor')
  }

  // 编辑
  async handleEdit(row: any) {
    console.log('编辑流程，row =', row)
    this.$router.push({
      name: 'flowEditor',
      params: {
        metaProp: JSON.stringify(row)
      }
    })
  }

  // 预览
  handleView(row: any) {
    // this.showViewFlag = true
    this.$notify.warning('这个功能暂未开放')
  }

  // 删除
  async handleDelete(row: any) {
    let msg = await new FlowApi().deleteFlow(row.id)
    this.$notify.success(msg)
    this.getFlowList()
  }
}
</script>