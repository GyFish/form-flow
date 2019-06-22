<template>
  <div class="app-card">
    <el-col :span="8">
      <el-card shadow="hover">
        <div slot="header">
          <span>{{appInfo.title}}</span>
          <el-button type="text" icon="el-icon-d-arrow-right" @click="userSelect">进入</el-button>
          <el-button type="success" icon="el-icon-tickets" @click="toAppReport">报表</el-button>
          <el-button type="primary" icon="el-icon-setting" @click="toAppAdmin">管理</el-button>
        </div>
        <div>{{appInfo.description}}</div>
      </el-card>
    </el-col>
    <el-dialog title="选择用户" :visible.sync="dialogVisible" width="30%">
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

  dialogVisible = false

  // 用户列表
  userList = [
    {
      userName: 'Tom',
      userId: 1
    }
  ]

  async userSelect() {
    this.userList = await new UserApi().userList({})
    this.dialogVisible = true
  }

  toAppClient(user: any) {
    this.$router.push({
      name: 'AppClient',
      params: {
        user
      }
    })
  }

  toAppReport() {
    this.$router.push('/appReport')
  }

  toAppAdmin() {
    this.$router.push({
      name: 'AppAdmin',
      params: {
        appId: this.appInfo.id
      }
    })
  }

  newApp() {
    this.$emit('newApp')
  }
}
</script>

