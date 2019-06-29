<template>
  <div id="app">
    <el-menu
      class="nav-top"
      :default-active="activeMenu"
      @select="setMenu"
      mode="horizontal"
      router
    >
      <el-menu-item index="/formEditor">表单</el-menu-item>
      <el-menu-item index="/flowEditor">流程</el-menu-item>
      <el-menu-item index="/appRoom">应用</el-menu-item>
    </el-menu>
    <router-view/>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Provide, Vue } from 'vue-property-decorator'
import { State, Mutation } from 'vuex-class'

@Component
export default class App extends Vue {
  @State activeMenu: any
  @Mutation setActiveMenu: any

  setMenu(indexPath: string) {
    this.setActiveMenu(indexPath)
  }
}
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
  .nav-top {
    position: fixed;
    width: 100%;
    top: 0px;
    z-index: 999999999;
  }
  .el-menu-item {
    height: 35px;
    line-height: 35px;
  }
}
</style>
