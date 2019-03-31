<template>
  <div class="app-card">
    <el-col :span="8">
      <el-card shadow="hover">
        <div slot="header">
          <span>{{computedAppInfo.title}}</span>
          <el-button type="text" icon="el-icon-d-arrow-right" @click="toApp">进入</el-button>
          <el-button type="primary" icon="el-icon-tickets" @click="toFormEditor">表单</el-button>
          <el-button disabled type="success" icon="el-icon-check" @click="toFlowEditor">流程</el-button>
          <el-button type="danger" icon="el-icon-delete" @click="deleteApp">删除</el-button>
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

  toApp() {
    this.$emit('toApp')
  }

  toFormEditor() {
    this.$emit('toFormEditor', this.computedAppInfo.formId)
  }

  toFlowEditor() {
    this.$emit('toFlowEditor', this.computedAppInfo.processId)
  }

  deleteApp() {
    this.$emit('deleteApp', this.computedAppInfo.id)
  }

  newApp() {
    this.$emit('newApp')
  }
}
</script>

