<template>
  <div class="form-editor">
    <el-container>
      <!-- 左侧组件栏 -->
      <el-aside class="metas-panel">
        <draggable
          v-model="meta.items"
          :clone="clone"
          :options="{group:{name:'people',pull:'clone',put:false},sort:false}"
        >
          <div v-for="metas in meta.items">
            <metas :icon="metas.icon" :label="metas.label"></metas>
          </div>
        </draggable>
      </el-aside>

      <!-- 主面板 -->
      <el-main>
        <el-form v-model="result" label-position="left" label-width="100px">
          <draggable class="pad" v-model="computedFormItems" :options="{group:'people'}">
            <div v-for="(item, idx) of computedFormItems">
              <el-row type="flex" align="middle">
                <el-col :span="22">
                  <form-item :data="{...item, idx}" :mode="`EDIT`"></form-item>
                </el-col>
                <el-col :span="2">
                  <el-button
                    circle
                    icon="el-icon-minus"
                    type="danger"
                    size="mini"
                    @click="removeItem(idx)"
                  />
                </el-col>
              </el-row>
            </div>
            <el-form-item v-if="computedFormItems.length > 0">
              <!-- <el-button type="success" @click="commit">提交</el-button> -->
            </el-form-item>
          </draggable>
        </el-form>
      </el-main>

      <!-- 右侧属性栏 -->
      <el-aside>
        <el-container>
          <el-main style="border:0">
            <el-tabs stretch :value="activeConfigTab">
              <el-tab-pane label="字段属性" name="itemConfig">
                <form-config :item="{...computedFormItems[activeIdx], idx: activeIdx}"></form-config>
              </el-tab-pane>
              <el-tab-pane label="表单属性" name="formConfig" style="text-align:center">
                <el-form>
                  <el-form-item label="标题">
                    <el-input v-model="data.form.title"></el-input>
                  </el-form-item>
                </el-form>
                <img src="@/assets/yayi2.jpg" width="95%">
              </el-tab-pane>
            </el-tabs>
          </el-main>
          <el-footer height="50px">
            <el-button @click="backToFormAdmin" type="warning" icon="el-icon-back">返回</el-button>
            <el-button @click="save" type="primary" icon="el-icon-check">保存</el-button>
          </el-footer>
        </el-container>
      </el-aside>
    </el-container>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import { State, Mutation } from 'vuex-class'
import { Provide, Component } from 'vue-property-decorator'
import metas from '@/components/form/Metas.vue'
import FormItem from '@/components/form/FormItem.tsx'
import FormConfig from '@/components/form/FormConfig.tsx'
import draggable from 'vuedraggable'
import FormApi from '@/apis/FormApi'

@Component({
  components: {
    draggable,
    metas,
    FormItem,
    FormConfig
  }
})
export default class FormEditor extends Vue {
  // store
  @State meta: any
  @State data: any
  @State activeIdx: any
  @Mutation active: any
  @Mutation updateFormItems: any
  @Mutation updateByIdx: any
  @Mutation removeByIdx: any
  @Mutation commitTable: any

  // 表单结构

  // 是否显示配置栏数据
  // showdata: number = 0

  get computedFormItems() {
    return this.data.formItems
  }

  set computedFormItems(updatedFormItems) {
    this.updateFormItems(updatedFormItems)
  }

  get activeConfigTab() {
    return this.activeIdx < 0 ? 'formConfig' : 'itemConfig'
  }

  set activeConfigTab(tab) {
    this.active(tab)
  }

  get result() {
    return this.data.result
  }

  clone(el: any) {
    return JSON.parse(JSON.stringify(el))
  }

  removeItem(idx: string) {
    this.removeByIdx(idx)
  }

  commit() {
    this.commitTable()
  }

  // 保存表单
  async save() {
    console.log('save...')
    // check
    if (!this.saveCheck()) return
    // data
    let formEditorVo = {
      form: this.data.form,
      items: this.data.formItems
    }
    console.log('formEditorVo = ', formEditorVo)
    let res = await new FormApi().saveForm(formEditorVo)
    this.$notify.success(res)
    this.backToFormAdmin()
  }

  // 保存表单时的各种检查
  saveCheck() {
    // 提醒内容
    let alertMsg = ''

    // 表单标题
    if (!this.data.form.title) {
      alertMsg = '请给表单取个名字~'
      // 右侧标签页跳到表单配置
      this.active(-1)
    }

    // 消息提醒
    if (alertMsg) this.$notify.warning(alertMsg)

    return alertMsg == ''
  }

  // 返回管理页面
  backToFormAdmin() {
    this.$router.push('/appAdmin/formAdmin')
  }
}
</script>
