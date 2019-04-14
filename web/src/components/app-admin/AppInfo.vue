<template>
  <div class="admin-box">
    <el-container>
      <el-header class="tool-bar">
        <div class="tool-bar-title">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item>基础设置</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div class="tool-bar-button">
          <el-button type="primary" icon="el-icon-edit">编辑</el-button>
        </div>
      </el-header>
      <el-main class="main-area"></el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component
export default class AppInfo extends Vue {}
</script>