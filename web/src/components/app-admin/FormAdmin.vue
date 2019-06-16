<template>
  <div class="admin-box">
    <el-container>
      <el-main class="main-box">
        <div class="search-box">
          <el-input width="100px" placeholder="code / name"></el-input>
          <el-button @click="search" type="success" icon="el-icon-search">查询</el-button>
          <el-button @click="newForm" type="primary" icon="el-icon-plus">新建</el-button>
        </div>
        <div class="list-box">
          <el-table :show-header="true" :data="formList">
            <el-table-column label="title" fit prop="title"></el-table-column>
            <el-table-column label="createTime" fit prop="createTime" :formatter="timeFormater"></el-table-column>
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
      <el-dialog :title="showViewTitle" :visible.sync="showViewFlag">
        <el-form label-position="top">
          <div v-for="(item, idx) of formItems" :key="idx">
            <el-row type="flex" align="middle">
              <el-col :span="22">
                <form-item :data="{...item, idx}" :mode="`VIEW`"></form-item>
              </el-col>
            </el-row>
          </div>
        </el-form>
      </el-dialog>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import FormApi from '@/apis/FormApi'
import FormItem from '@/components/form/FormItem.tsx'
import { Mutation } from 'vuex-class'
import DateUtil from '@/util/DateUtil'

@Component({ components: { FormItem } })
export default class AppInfo extends Vue {
  //~ ------------------------------------------------------
  // 表单列表
  formList: any = []

  timeFormater = DateUtil.format

  // 是否显示预览
  showViewFlag = false
  // 预览弹窗标题
  showViewTitle = '预览'

  // 当前选中表单的元素
  formItems = []

  // 更新 state 中的表单数据
  @Mutation updateFormItems: any

  mounted() {
    // 获取表单列表
    this.getFormList()
    // 设置 state，关联引用
    this.updateFormItems(this.formItems)
  }

  // 查询表单列表
  async getFormList() {
    this.formList = await new FormApi().getFormList()
  }

  // 获取表单列表
  search() {
    this.getFormList()
  }

  // 跳转表单编辑器
  newForm() {
    this.$router.push('/formEditor')
  }

  // 编辑
  async handleEdit(row: any) {
    this.formItems = row.items
    this.updateFormItems(this.formItems)
    this.$router.push('/formEditor')
  }

  // 预览
  handleView(row: any) {
    this.formItems = row.items
    this.showViewFlag = true
    this.showViewTitle = row.title
  }

  // 删除
  async handleDelete(row: any) {
    let msg = await new FormApi().deleteForm(row.id)
    this.$notify.success(msg)
    this.getFormList()
  }
}
</script>