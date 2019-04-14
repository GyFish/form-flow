<template>
  <div class="app-admin">
    <el-container>
      <!-- 菜单 -->
      <div class="menu-bar">
        <el-aside width="100%">
          <el-header height="50px"></el-header>
          <el-menu router>
            <el-menu-item index="/appAdmin/appInfo">
              <i class="el-icon-edit"></i>
              <span slot="title">基础设置</span>
            </el-menu-item>
            <el-menu-item>
              <i class="el-icon-edit"></i>
              <span slot="title">用户管理</span>
            </el-menu-item>
            <el-menu-item>
              <i class="el-icon-edit"></i>
              <span slot="title">表单管理</span>
            </el-menu-item>
            <el-menu-item>
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
export default class AppAdmin extends Vue {}
</script>