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
    <!-- 弹窗 -->
    <el-dialog modal width="30%" :visible.sync="showDialog">
      <el-form :model="app">
        <el-form-item label="应用名称">
          <el-input v-model="app.title"></el-input>
        </el-form-item>
        <el-form-item label="应用描述">
          <el-input v-model="app.desc"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-select v-model="app.type">
            <el-option label="表单" value="form"></el-option>
            <el-option label="流程" value="flow"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="表单" v-if="app.type == `form`">
          <el-select v-model="app.form">
            <el-option label="表单1" value="form"></el-option>
            <el-option label="表单2" value="form"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showDialog = false">取 消</el-button>
        <el-button type="primary" @click="showDialog = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
@Component({
  components: {}
})
export default class App extends Vue {
  // 是否显示弹窗
  showDialog: Boolean = false
  // app详情
  app: any = {}

  newApp() {
    console.log('new app...')
    this.showDialog = true
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
