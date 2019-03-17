<template>
  <div id="app">
    <el-menu mode="horizontal" router>
      <el-menu-item index="/form">表单</el-menu-item>
      <el-menu-item index="/table">表格</el-menu-item>
      <el-menu-item index="/flowEditor">流程</el-menu-item>
      <el-menu-item index="/app">应用</el-menu-item>
    </el-menu>
    <router-view/>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Provide, Vue } from 'vue-property-decorator'

@Component
export default class App extends Vue {}
</script>

<style lang="scss">
html,
body {
  height: 100%;
  margin: 0%;
}
#app {
  height: 100%;
  min-height: 100%;
  .el-menu-item {
    height: 35px;
    line-height: 35px;
  }
}
</style>
