<template>
  <div class="app-admin">
    <el-container>
      <!-- 菜单 -->
      <div class="menu-bar">
        <el-aside width="100%">
          <el-header height="50px" class="logo-box">
            <img src="@/assets/form-flow.png" height="100%">
          </el-header>
          <el-menu @select="handleSelect">
            <el-menu-item index="AppInfo">
              <i class="el-icon-edit"></i>
              <span slot="title">基础设置</span>
            </el-menu-item>
            <el-menu-item index="UserAdmin">
              <i class="el-icon-edit"></i>
              <span slot="title">用户管理</span>
            </el-menu-item>
            <el-menu-item index="FormAdmin">
              <i class="el-icon-edit"></i>
              <span slot="title">表单管理</span>
            </el-menu-item>
            <el-menu-item index="FlowAdmin">
              <i class="el-icon-edit"></i>
              <span slot="title">流程管理</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
      </div>
      <router-view></router-view>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator'

@Component
export default class AdminLayout extends Vue {

  appId = ''

  mounted() {
    this.appId = this.$route.params.appId
  }
  
  handleSelect(index: string) {
    this.$router.push({
      name: index,
      params: {
        appId: this.appId
      }
    })
  }
}
</script>