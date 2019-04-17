<template>
  <div class="admin-box">
    <el-container>
      <el-main class="main-box">
        <div class="search-box">
          <el-input width="100px" placeholder="code / name"></el-input>
          <el-button @click="search" type="success" icon="el-icon-search">查询</el-button>
          <el-button @click="toFormEditor" type="primary" icon="el-icon-plus">新建</el-button>
        </div>
        <div class="list-box">
          <el-table :show-header="false" :data="formList">
            <el-table-column fit prop="businessCode"></el-table-column>
            <el-table-column fit prop="title"></el-table-column>
            <el-table-column fit align="right">
              <template slot-scope="scope">
                <el-button
                  circle
                  type="text"
                  icon="el-icon-view"
                  @click="handleEdit(scope.$index, scope.row)"
                />
                <el-button
                  circle
                  type="text"
                  icon="el-icon-edit"
                  @click="handleEdit(scope.$index, scope.row)"
                />
                <el-button
                  circle
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.$index, scope.row)"
                />
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import FormApi from '@/apis/FormApi'

@Component
export default class AppInfo extends Vue {
  //~ ------------------------------------------------------
  // 表单列表
  formList: any = []

  mounted() {
    this.getFormList()
  }

  // 查询表单列表
  async getFormList() {
    this.formList = await new FormApi().getFormList()
  }

  search() {
    this.getFormList()
  }

  // 跳转表单编辑器
  toFormEditor() {
    this.$router.push('/formEditor')
  }
}
</script>