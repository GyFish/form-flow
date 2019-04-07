<template>
  <div class="app-layout">
    <el-container>
      <!-- 左侧 aside -->
      <div class="left-aside">
        <el-aside width="100%">
          <!-- 头像 -->
          <div class="avatar-box">
            <img src="@/assets/avatar1.jpg" alt="avatar">
          </div>
          <!-- 导航 -->
          <el-menu collapse width="100%">
            <el-menu-item index="2">
              <i class="el-icon-edit"></i>
              <span slot="title">新建一个任务，Go！</span>
            </el-menu-item>
            <el-menu-item index="4">
              <i class="el-icon-time"></i>
              <span slot="title">我发起的，看看结束了没有？</span>
            </el-menu-item>
            <el-menu-item index="3">
              <i class="el-icon-document"></i>
              <span slot="title">待处理的，天啊噜，怎么这么多 Todo 啊~</span>
            </el-menu-item>
            <el-menu-item index="1">
              <i class="el-icon-circle-check-outline"></i>
              <span slot="title">处理过的，数数，这周没少干啊，加钱！</span>
            </el-menu-item>
          </el-menu>
          <!-- 底部设置 -->
          <div class="setting-box">
            <el-button circle type="info" icon="el-icon-menu"></el-button>
          </div>
        </el-aside>
      </div>
      <!-- 中间 aside -->
      <div class="mid-aside">
        <el-aside width="100%">
          <!-- 搜索框 -->
          <div class="search-box">
            <el-button type="text" icon="el-icon-arrow-down"></el-button>
            <el-input></el-input>
            <el-button type="primary" icon="el-icon-search"></el-button>
          </div>
          <!-- 任务列表 -->
          <div class="task-list">
            <el-table highlight-current-row :data="taskList" :show-header="false" height="100%">
              <el-table-column prop="date" label="类型"></el-table-column>
              <el-table-column prop="name" label="名称"></el-table-column>
            </el-table>
          </div>
        </el-aside>
      </div>
      <!-- 右侧主页面 -->
      <div class="right-aside">
        <el-main>
          <router-view></router-view>
        </el-main>
      </div>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component
export default class AppLayout extends Vue {
  taskList = [
    {
      date: 1,
      name: 'a'
    },
    {
      date: 1,
      name: 'a'
    },
    {
      date: 1,
      name: 'a'
    }
  ]
}
</script>
