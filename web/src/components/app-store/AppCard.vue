<template>
  <div class="app-card">
    <el-col :span="8">
      <el-card shadow="hover">
        <div slot="header">
          <span>{{appInfo.title}}</span>
          <el-button type="text" icon="el-icon-d-arrow-right" @click="toAppClient">进入</el-button>
          <el-button type="success" icon="el-icon-tickets" @click="toAppReport">报表</el-button>
          <el-button type="primary" icon="el-icon-setting" @click="toAppAdmin">管理</el-button>
        </div>
        <div>{{appInfo.description}}</div>
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

  toAppClient() {
    this.$router.push('/appClient')
  }

  toAppReport() {
    this.$router.push('/appReport')
  }

  toAppAdmin() {
    console.log('push appId =', this.appInfo)
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

