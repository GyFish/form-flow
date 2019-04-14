<template>
  <div class="app-card">
    <el-col :span="8">
      <el-card shadow="hover">
        <div slot="header">
          <span>{{computedAppInfo.title}}</span>
          <el-button type="text" icon="el-icon-d-arrow-right" @click="toAppClient">进入</el-button>
          <el-button type="success" icon="el-icon-tickets" @click="toAppReport">报表</el-button>
          <el-button type="primary" icon="el-icon-setting" @click="toAppAdmin">管理</el-button>
        </div>
        <div>{{computedAppInfo.description}}</div>
      </el-card>
    </el-col>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator'

@Component
export default class AppCard extends Vue {
  // app 基础信息
  @Prop()
  appInfo: any

  get computedAppInfo() {
    return this.appInfo
  }

  toAppClient() {
    this.$router.push('/appClient')
  }

  toAppReport() {
    this.$router.push('/appReport')
  }

  toAppAdmin() {
    this.$router.push('/appAdmin')
  }

  newApp() {
    this.$emit('newApp')
  }
}
</script>

