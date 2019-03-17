<template>
  <div class="design">
    <el-container>
      <el-main class="app">
        <el-col :span="6">
          <el-card shadow="hover">
            <div slot="header">
              <span>Demo1</span>
              <el-button type="text">进入</el-button>
            </div>
            <div>使用设计好的表单收集数据</div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card shadow="hover">
            <el-button
              type="text"
              icon="el-icon-plus"
              class="new-button"
              size="large"
              @click="newApp"
            ></el-button>
          </el-card>
        </el-col>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
@Component({
  components: {}
})
export default class App extends Vue {
  newApp() {
    console.log('new app...')
  }
}
</script>

<style lang="scss" scoped>
.app {
  .el-button {
    float: right;
    padding: 3px 0;
  }
  .new-button {
    font-size: 200%;
    padding: 23px 45%;
  }
  .el-card {
    height: 120px;
    margin: 10px 10px;
  }
}
</style>
