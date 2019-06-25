<template>
  <div class="app-client">
    <el-container>
      <!-- 左侧 aside -->
      <div class="left-aside">
        <el-aside width="100%">
          <!-- 头像 -->
          <div class="avatar-box">
            <img :src="`/${user.userName}.jpg`" alt="avatar">
          </div>
          <div class="name-box">
            <span>{{user.userName}}</span>
          </div>
          <!-- 导航 -->
          <el-menu collapse width="100%" @select="handleSelect">
            <el-menu-item index="Start">
              <i class="el-icon-edit"></i>
              <span slot="title">新建一个任务，Go！</span>
            </el-menu-item>
            <!-- <el-menu-item index="Created">
              <i class="el-icon-time"></i>
              <span slot="title">我发起的，看看结束了没有？</span>
            </el-menu-item> -->
            <el-menu-item index="Todo">
              <i class="el-icon-document"></i>
              <span slot="title">待处理的，Todo!</span>
            </el-menu-item>
            <el-menu-item index="Done">
              <i class="el-icon-circle-check"></i>
              <span slot="title">处理过的，Done！</span>
            </el-menu-item>
          </el-menu>
          <!-- 底部设置 -->
          <div class="setting-box">
            <el-button circle type="info" icon="el-icon-menu"></el-button>
          </div>
        </el-aside>
      </div>
      <div class="app-box">
        <router-view></router-view>
      </div>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'

@Component
export default class ClientLayout extends Vue {
  avatar = 'Tom'

  user = {}
  appInfo = {}

  mounted() {
    this.user = JSON.parse(localStorage.user)
    console.log(this.user)
  }

  handleSelect(index: any) {
    this.$router.push({
      name: index
    })
  }
}
</script>
