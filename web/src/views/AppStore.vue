<template>
  <div class="app-store">
    <el-container>
      <el-main class="app">
        <app-card
          @newApp="newApp"
          @toApp="toApp"
          @toFormEditor="toFormEditor"
          @toFlowEditor="toFlowEditor"
        ></app-card>
      </el-main>
    </el-container>
    <!-- 弹窗 -->
    <el-dialog modal title="新建应用" width="50%" :visible.sync="showDialog">
      <el-form :model="appInfo" label-position="left" label-width="68px">
        <el-row>
          <el-col :span="11">
            <el-form-item label="应用名称">
              <el-input v-model="appInfo.title"></el-input>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="5">
            <el-form-item label="使用表单">
              <el-switch v-model="appInfo.useForm"></el-switch>
            </el-form-item>
          </el-col>
          <el-col :offset="1" :span="5">
            <el-form-item label="使用流程">
              <el-switch v-model="appInfo.useFlow"></el-switch>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="应用描述">
              <el-input v-model="appInfo.desc" type="textarea"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showDialog = false">取 消</el-button>
        <el-button type="primary" @click="saveApp">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script lang="ts">
import AppCard from '@/components/appstore/AppCard.vue'
import { Component, Vue } from 'vue-property-decorator'
import { State, Mutation } from 'vuex-class'
import AppStoreApi from '@/apis/AppStoreApi'

@Component({
  components: { AppCard }
})
export default class AppStore extends Vue {
  // 是否显示弹窗
  showDialog: Boolean = false
  // app详情
  appInfo: any = {
    title: '',
    desc: '',
    useForm: true,
    useFlow: false
  }

  @Mutation setActiveMenu: any

  toApp() {
    alert('to app')
  }

  toFormEditor() {
    this.$router.push('/formEditor')
    this.setActiveMenu('/formEditor')
  }

  toFlowEditor() {
    this.$router.push('/flowEditor')
    this.setActiveMenu('/flowEditor')
  }

  // 打开新建应用窗口
  newApp() {
    this.showDialog = true
  }

  // 保存应用
  async saveApp() {
    console.log(this.appInfo)
    let res = await new AppStoreApi().saveApp(this.appInfo)
    console.log(res)
    this.showDialog = false
  }
}
</script>