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
          <el-table :show-header="true" :data="flowInfoList">
            <el-table-column label="title" fit prop="title"></el-table-column>
            <el-table-column label="createTime" fit prop="createTime"></el-table-column>
            <el-table-column fit align="right">
              <template slot-scope="scope">
                <el-button
                  circle
                  type="text"
                  icon="el-icon-view"
                  @click="handleView(scope.row)"
                />
                <el-button
                  circle
                  type="text"
                  icon="el-icon-edit"
                  @click="handleEdit(scope.row)"
                />
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
      <el-dialog fullscreen title="预览" :visible.sync="showViewFlag">
      </el-dialog>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Mutation } from 'vuex-class'
import FlowApi from '@/apis/FlowApi'

@Component
export default class AppInfo extends Vue {
  //~ ------------------------------------------------------
  // 表单列表
  flowInfoList: any = []

  nodes: any = []

  // 是否显示预览
  showViewFlag = false

  mounted() {
    this.getFlowInfoList()
  }

  // 查询表单列表
  async getFlowInfoList() {
    this.flowInfoList = await new FlowApi().getFlowInfoList()
  }

  // 查询表单元素列表
  async getFlowNodeList(uuid: string) {
    this.nodes = await new FlowApi().getFlowNodeList(uuid)
  }



  // 搜索
  search() {
    alert('search')
  }

  // 新建，跳转流程图编辑器
  toFlowEditor() {
    this.$router.push('/flowEditor')
  }

  // 编辑
  handleEdit() {
    this.$router.push('/flowEditor')
  }

  // 预览
  handleView(row: any) {
    this.showViewFlag = true
  }

  // 删除
  handleDelete() {
    alert('delete')
  }
}
</script>