<template>
  <div class="admin-box">
    <el-container>
      <el-header class="tool-bar">
        <div class="tool-bar-title">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item>基础设置</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div class="tool-bar-button">
          <el-button
            v-if="mode == 'show'"
            @click="handleDelete"
            type="danger"
            icon="el-icon-delete"
          >删除</el-button>
          <el-button v-if="mode == 'show'" @click="edit" type="primary" icon="el-icon-edit">编辑</el-button>
          <el-button v-if="mode == 'edit'" @click="cancle" type="warning" icon="el-icon-close">取消</el-button>
          <el-button v-if="mode == 'edit'" @click="save" type="success" icon="el-icon-check">保存</el-button>
        </div>
      </el-header>
      <el-main class="main-box">
        <el-form ref="form" :model="form" label-width="100px">
          <el-form-item label="应用名称：">
            <div v-if="mode == `show`">{{form.title}}</div>
            <el-input v-if="mode == `edit`" v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="应用描述：">
            <div v-if="mode == `show`">{{form.description}}</div>
            <el-input v-if="mode == `edit`" v-model="form.description"></el-input>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import AppStoreApi from '@/apis/AppStoreApi'

@Component
export default class AppInfo extends Vue {
  // 表单显示模式，show | edit
  mode: any = 'show'

  appId = ''

  form: any = {
    title: '这是名字',
    description: '这是一段描述'
  }

  mounted() {
    this.appId = this.$route.params.appId
  }

  // 删除
  async handleDelete() {
    if (this.appId == 'demo') return
    // 确认是否删除
    try {
      let confirmDelete = await this.$confirm('是否删除该应用?', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
    } catch (error) {
      // 如果有异常代表点了取消
      return
    }
    let res = await new AppStoreApi().deleteApp(this.appId)
    this.$message.success(res)
    this.$router.push("/appStore")
  }

  // 进入编辑状态
  edit() {
    this.mode = 'edit'
  }

  // 退出编辑状态
  cancle() {
    this.mode = 'show'
  }

  // 保存，同时进入显示状态
  save() {
    this.mode = 'show'
  }
}
</script>