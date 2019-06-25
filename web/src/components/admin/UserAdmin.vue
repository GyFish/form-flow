<template>
  <div class="admin-box">
    <el-container>
      <el-main class="main-box">
        <div class="search-box">
          <el-input width="100px" placeholder="code / name"></el-input>
          <el-button @click="search" type="success" icon="el-icon-search">查询</el-button>
          <el-button type="warning" icon="el-icon-plus">导入</el-button>
          <el-button @click="handleAddUser" type="primary" icon="el-icon-plus">新建</el-button>
        </div>
        <div class="list-box">
          <el-table :data="userList">
            <el-table-column label="工号" fit prop="userNo"></el-table-column>
            <el-table-column label="姓名" fit prop="userName"></el-table-column>
            <el-table-column label="权限" fit>
              <template slot-scope="scope">
                <el-button type="text" @click="handleAuth(scope.row)">流程</el-button>
                <el-button
                  circle
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                />
              </template>
            </el-table-column>
          </el-table>
        </div>

        <!-- 新建用户弹窗 -->
        <el-dialog width="30%" title="新建用户" :visible.sync="addUserFlag">
          <el-form>
            <el-form-item label="工号">
              <el-input v-model="userVo.userNo"/>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="userVo.userName"/>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="addUserFlag = false">取 消</el-button>
            <el-button type="primary" @click="saveUser">确 定</el-button>
          </span>
        </el-dialog>

        <!-- 流程权限配置 -->
        <el-dialog title="流程权限" :visible.sync="flowAuthFlag">
          <div style="height:250px">
            <!-- <el-checkbox v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox> -->
            <div style="margin: 15px 0;"></div>
            <el-checkbox-group v-model="checkedFlowList" @change="handleCheckedChange">
              <el-checkbox v-for="flow in flowList" :key="flow.id" :label="flow.id">{{flow.title}}</el-checkbox>
            </el-checkbox-group>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="flowAuthFlag = false">取 消</el-button>
            <el-button type="primary" @click="saveAuth">确 定</el-button>
          </span>
        </el-dialog>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator'
import UserApi from '@/apis/UserApi'
import FlowApi from '@/apis/FlowApi'

@Component
export default class UserAdmin extends Vue {
  // 是否显示新建用户对话框
  addUserFlag = false

  // 是否显示流程权限配置对话框
  flowAuthFlag = false

  // 是否全选
  // checkAll = false

  // 用户列表
  userList = []

  // 用户流程关联
  flowList = []
  checkedFlowList = []

  // 用户详情
  userVo: any = {}

  appId: any = ''

  // mounted
  mounted() {
    this.appId = JSON.parse(localStorage.appInfo).id
    this.search()
    this.getFlowList()
  }

  // 用户列表
  async search() {
    this.userList = await new UserApi().userList({
      appId: this.appId
    })
  }

  // 流程列表
  async getFlowList() {
    this.flowList = await new FlowApi().getFlowList({
      appId: this.appId
    })
  }

  // 打开流程权限弹窗
  handleAuth(user: any) {
    this.checkedFlowList = user.flowList || []
    this.flowAuthFlag = true
    this.userVo = user
  }

  // 更新权限
  async saveAuth() {
    this.userVo.flowList = this.checkedFlowList
    this.saveUser()
    this.flowAuthFlag = false
  }

  // 打开添加用户弹窗
  handleAddUser() {
    this.userVo = {}
    this.addUserFlag = true
  }

  // 新建用户
  async saveUser() {
    this.userVo.appId = this.appId
    let res = await new UserApi().saveUser(this.userVo)
    this.$message.success(res)
    this.addUserFlag = false
    this.search()
  }

  // 删除用户
  async handleDelete(user: any) {
    let res = await new UserApi().deleteUser(user.id)
    this.$message.success(res)
    this.search()
  }

  // 全选
  handleCheckAllChange(processId: any) {}

  // 选中
  handleCheckedChange(value: any) {
    console.log(this.checkedFlowList)
  }
}
</script>