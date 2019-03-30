<template>
  <div class="app-card">
    <el-col :span="7">
      <el-card shadow="hover">
        <div slot="header">
          <span>Demo App</span>
          <el-button type="text" icon="el-icon-d-arrow-right" @click="toApp">进入</el-button>
          <el-button type="primary" icon="el-icon-tickets" @click="toFormEditor">表单</el-button>
          <el-button disabled type="success" icon="el-icon-check" @click="toFlowEditor">流程</el-button>
        </div>
        <div>使用设计好的表单收集数据，点击进入开始吧！</div>
      </el-card>
    </el-col>
    <el-col :span="7">
      <el-card shadow="hover">
        <div class="new-button">
          <el-button type="text" icon="el-icon-plus" size="large" @click="newApp"></el-button>
        </div>
      </el-card>
    </el-col>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'

@Component
export default class AppCard extends Vue {
  toApp() {
    this.$emit('toApp')
  }

  toFormEditor() {
    this.$emit('toFormEditor')
  }

  toFlowEditor() {
    this.$emit('toFlowEditor')
  }

  newApp() {
    this.$emit('newApp')
  }
}
</script>

