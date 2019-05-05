<template>
  <el-container>
    <el-main>
      <el-tabs stretch value="flowConfig">
        <el-tab-pane label="节点属性" name="nodeConfig">
          <el-form label-position="top">
            <el-form-item label="节点名称">
              <el-input v-model="flow.nodeName" @change="$emit('updateVueModel')"></el-input>
            </el-form-item>
            <el-form-item label="处理人">
              <el-select v-model="flow.handlerId">
                <el-option
                  v-for="user in userList"
                  :key="user.id"
                  :label="user.userName"
                  :value="user.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="处理组">
              <el-select v-model="flow.handlerGroupId"></el-select>
            </el-form-item>
            <el-form-item label="关联表单">
              <el-select v-model="flow.formId">
                <el-option
                  v-for="form in formList"
                  :key="form.id"
                  :label="form.title"
                  :value="form.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="节点类型">
              <el-select v-model="flow.nodeType">
                <el-option label="任务节点" value="task"></el-option>
                <el-option label="审核节点" value="check"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="下一节点">
              <el-select v-model="flow.nextNode" @change="handleNextNode">
                <el-option
                  v-for="g6Node of allFlowNodes"
                  :key="g6Node.id"
                  :label="g6Node.nodeName"
                  :value="g6Node.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <el-form label-position="left">
            <el-form-item label="允许流回">
              <el-switch v-model="flow.canFlowBack"></el-switch>
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
              <el-switch v-model="flow.canFlowBack" active-text="分配模式" inactive-text="领取模式"></el-switch>
            </el-form-item>
            <el-form-item>
              <el-button @click="$emit('saveDefinition')" type="primary" icon="el-icon-check">保存</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-main>
    <el-footer height="50px"></el-footer>
  </el-container>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'
import UserApi from '@/apis/UserApi'
import { nodeModel, FlowNode } from '@/components/flow/index'
import FormApi from '../../apis/FormApi'

@Component
export default class FlowConfig extends Vue {
  // 属性
  @Prop()
  nodeData!: nodeModel

  //== data =====================================
  // 节点绘图属性
  get flow() {
    return this.nodeData.flow
  }
  set flow(nodeData: any) {
    this.nodeData = nodeData
  }

  // 所有节点
  get allFlowNodes() {
    return Object.values(this.nodeData.dataMap)
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


