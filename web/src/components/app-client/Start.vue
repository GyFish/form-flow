<template>
  <el-container>
    <!-- 顶部搜索、按钮栏 header -->
    <div class="tool-box">
      <el-aside width="100%">
        <!-- 搜索框 -->
        <div class="search-box">
          <el-input></el-input>
          <el-button type="primary" icon="el-icon-search"></el-button>
        </div>
        <!-- 任务列表 -->
        <div class="task-list">
          <el-table highlight-current-row :data="flowList" :show-header="false" height="100%">
            <el-table-column prop="flowName" label="名称"></el-table-column>
          </el-table>
        </div>
      </el-aside>
    </div>
    <!-- 中间 main -->
    <div class="main-box">
      <el-main>
        <el-form label-position="top">
          <div v-for="(item, idx) of formItems">
            <el-row type="flex" align="middle">
              <el-col :span="22">
                <form-item :data="{...item, idx}" :mode="`VIEW`" @onViewInput="onViewInput"></form-item>
              </el-col>
            </el-row>
          </div>
          <el-form-item>
            <el-button type="success" @click="commit">提交</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </div>
  </el-container>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import FormItem from '@/components/form/FormItem.tsx'
import { Mutation } from 'vuex-class'

@Component({
  components: { FormItem }
})
export default class AppStart extends Vue {
  // 更新 state 中的表单数据
  @Mutation updateFormItems: any

  // 流程列表
  flowList: any = [
    {
      id: 123,
      flowName: '日常请假流程'
    },
    {
      id: 123,
      flowName: '报销交通费流程'
    },
    {
      id: 123,
      flowName: '投诉流程'
    },
    {
      id: 123,
      flowName: '客户反馈流程'
    }
  ]
  formItems: any = [
    {
      id: null,
      itemType: 'el-input',
      prop: 'input',
      value: '',
      label: '输入框',
      placeholder: '请输入...',
      icon: 'el-icon-edit',
      prefixIcon: '',
      inputType: 'text'
    },
    {
      id: null,
      itemType: 'el-input',
      prop: 'text',
      value: '',
      label: '文本域',
      icon: 'el-icon-tickets',
      inputType: 'textarea'
    },
    {
      id: null,
      itemType: 'el-select',
      prop: 'select',
      value: '',
      label: '下拉框',
      icon: 'el-icon-arrow-down',
      options: [
        { value: 'k1', label: '选项一' },
        { value: 'k2', label: '选项二' }
      ]
    }
  ]

  // mounted
  mounted() {
    this.updateFormItems(this.formItems)
  }

  // 提交表单
  commit() {
    console.log('commit...')
  }

  // 响应输入值
  onViewInput(value: any) {
    console.log(value)
  }
}
</script>
