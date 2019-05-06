<template>
  <el-container>
    <el-main style="border:0">
      <el-tabs stretch value="flowConfig">
        <el-tab-pane label="节点属性" name="nodeConfig">
          <el-form label-position="top">
            <el-form-item label="节点名称">
              <el-input v-model="node.nodeName" @change="$emit('updateVueModel')"></el-input>
            </el-form-item>
            <el-form-item label="处理人">
              <el-select v-model="node.handlerId">
                <el-option
                  v-for="user in userList"
                  :key="user.id"
                  :label="user.userName"
                  :value="user.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="处理组">
              <el-select v-model="node.handlerGroupId"></el-select>
            </el-form-item>
            <el-form-item label="关联表单">
              <el-select v-model="node.formId">
                <el-option
                  v-for="form in formList"
                  :key="form.id"
                  :label="form.title"
                  :value="form.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="节点类型">
              <el-select v-model="node.nodeType">
                <el-option label="任务节点" value="task"></el-option>
                <el-option label="审核节点" value="check"></el-option>
              </el-select>
            </el-form-item>
            <!-- 列举所有节点 -->
            <el-form-item label="下一节点">
              <el-select v-model="node.nextNode" @change="handleNextNode">
                <el-option
                  v-for="n of Object.values(nodeData.nodeMap)"
                  :key="n.id"
                  :label="n.nodeName"
                  :value="n.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <el-form label-position="left">
            <el-form-item label="允许流回">
              <el-switch v-model="node.canFlowBack"></el-switch>
            </el-form-item>
            <el-form-item>
              <el-button type="danger" @click="deleteNode">删除</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="流程属性" name="flowConfig">
          <el-form>
            <el-form-item label="流程名称">
              <el-input></el-input>
            </el-form-item>
            <el-form-item>
              <el-switch v-model="node.canFlowBack" active-text="分配模式" inactive-text="领取模式"></el-switch>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-main>
    <el-footer height="50px">
      <el-button @click="$router.push('/appAdmin/flowAdmin')" type="warning" icon="el-icon-back">返回</el-button>
      <el-button @click="$emit('saveDefinition')" type="primary" icon="el-icon-check">保存</el-button>
    </el-footer>
  </el-container>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import UserApi from '@/apis/UserApi'
import { NodeModel, FlowNode } from '@/components/flow/index'
import FormApi from '../../apis/FormApi'

@Component
export default class FlowConfig extends Vue {
  // 属性
  @Prop()
  nodeData!: NodeModel

  //== data =====================================
  // 节点绘图属性
  get node() {
    return this.nodeData.node
  }
  set node(nodeData: any) {
    this.nodeData = nodeData
  }

  // 处理人列表
  userList = []

  // 表单列表
  formList = []

  // 处理人
  handlerId = ''

  //== methods =====================================

  async mounted() {
    await this.getUserList()
  }

  // 与下一节点连线
  handleNextNode(nextNodeId: any) {
    this.$emit('addLine', {
      source: this.nodeData.model.id,
      target: nextNodeId
    })
  }

  // 删除节点
  deleteNode() {}

  //== api =====================================
  // 获取处理人列表
  async getUserList() {
    this.userList = await new UserApi().userList({})
  }

  // 获取表单列表
  async getFormList() {
    this.formList = await new FormApi().getFormList()
  }
}
</script>


