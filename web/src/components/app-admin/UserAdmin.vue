<template>
  <div class="admin-box">
    <el-container>
      <el-main class="main-box">
        <div class="search-box">
          <el-input width="100px" placeholder="code / name"></el-input>
          <el-button @click="search" type="success" icon="el-icon-search">查询</el-button>
          <el-button @click="addUserFlag = true" type="primary" icon="el-icon-plus">新建</el-button>
        </div>
        <div class="list-box">
          <el-table :data="userList">
            <el-table-column label="工号" fit prop="userId"></el-table-column>
            <el-table-column label="姓名" fit prop="userName"></el-table-column>
            <el-table-column label="权限" fit>
              <template slot-scope="scope">
                <el-button type="text" @click="flowAuthFlag = true">流程</el-button>
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
              <el-input v-model="userVo.userId"/>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="userVo.userName"/>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="addUserFlag = false">取 消</el-button>
            <el-button type="primary" @click="addUser">确 定</el-button>
          </span>
        </el-dialog>
        <!-- 流程权限配置 -->
        <el-dialog title="流程权限" :visible.sync="flowAuthFlag">
          <div style="height:250px">
            <el-checkbox v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
            <div style="margin: 15px 0;"></div>
            <el-checkbox-group v-model="checkedFlowList" @change="handleCheckedChange">
              <el-checkbox
                v-for="process in userFlowList"
                :key="process.processId"
                :label="process.processName"
              >{{process.processName}}</el-checkbox>
            </el-checkbox-group>
          </div>
        </el-dialog>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator'
import UserApi from '@/apis/UserApi'

@Component
export default class UserAdmin extends Vue {
  // 是否显示新建用户对话框
  addUserFlag = false

  // 是否显示流程权限配置对话框
  flowAuthFlag = false

  // 是否全选
  checkAll = false

  // 用户列表
  userList = [
    {
      userName: 'xiaoming',
      userId: 123
    }
  ]

  // 用户流程关联
  userFlowList = [
    {
      userId: 'Tom',
      processId: 123,
      processName: '流程demo1'
    },
    {
      userId: 'Tom',
      processId: 1223,
      processName: '流程demo3'
    },
    {
      userId: 'Tom',
      processId: 1323,
      processName: '流程demo4'
    }
  ]
  checkedFlowList = ['流程demo1']

  // 用户详情
  userVo: any = {}

  // 用户搜索
  userQuery: any = {}

  // mounted
  mounted() {
    this.search()
  }

  // 搜多用户
  async search() {
    let res = await new UserApi().userList(this.userQuery)
    this.userList = res
  }

  // 新建用户
  async addUser() {
    this.userVo = {}
    let res = await new UserApi().addUser(this.userVo)
    this.$notify.success(res)
    this.addUserFlag = false
    this.search()
  }

  // 全选
  handleCheckAllChange(processId: any) {}

  // 选中
  handleCheckedChange(value: any) {
    console.log(value)
  }
}
</script>