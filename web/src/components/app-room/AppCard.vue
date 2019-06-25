<template>
  <div class="app-card">
    <el-col :span="8">
      <el-card shadow="hover">
        <div slot="header">
          <span>{{appInfo.title}}</span>
          <el-button type="text" icon="el-icon-d-arrow-right" @click="userSelect">进入</el-button>
          <el-button type="success" icon="el-icon-tickets" @click="toAppReport">报表</el-button>
          <el-button type="primary" icon="el-icon-setting" @click="toAppAdmin(appInfo)">管理</el-button>
        </div>
        <div>{{appInfo.description}}</div>
      </el-card>
    </el-col>
    <el-dialog title="请选择用户" :visible.sync="dialogVisible" width="30%">
      <el-link
        v-for="(user, i) of userList"
        :key="i"
        @click="toAppClient(user)"
        style="margin:12px"
      >{{user.userName}}</el-link>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator'
import UserApi from '@/apis/UserApi'

@Component
export default class AppCard extends Vue {
  // app 基础信息
  @Prop()
  appInfo: any

  // 用户选择弹窗是否可见
  dialogVisible = false

  // 用户列表
  userList = []

  // 选择用户
  async userSelect() {
    this.userList = await new UserApi().userList({
      appId: this.appInfo.id
    })
    this.dialogVisible = true
  }

  // 跳转应用
  toAppClient(user: any) {
    localStorage.user = JSON.stringify(user)
    localStorage.appInfo = JSON.stringify(this.appInfo)
    this.$router.push('/appClient')
  }

  // 跳转报表
  toAppReport() {
    this.$router.push('/appReport')
  }

  // 跳转后台管理
  toAppAdmin() {
    localStorage.appInfo = JSON.stringify(this.appInfo)
    this.$router.push('/appAdmin')
  }
}
</script>

