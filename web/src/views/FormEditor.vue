<template>
  <div class="form-editor">
    <el-container>
      <!-- 左侧组件栏 -->
      <el-aside class="metas-panel">
        <draggable
          v-model="baseItems"
          :clone="clone"
          :options="{group:{name:'people',pull:'clone',put:false},sort:false}"
        >
          <div v-for="item in baseItems" :key="item.label">
            <el-button autofocus :icon="item.icon">{{item.label}}</el-button>
          </div>
        </draggable>
      </el-aside>

      <!-- 主面板 -->
      <el-main>
        <el-form v-model="result" label-position="left" label-width="100px">
          <draggable class="pad" v-model="formItems" :options="{group:'people'}">
            <div v-for="(item, idx) of formItems" :key="idx">
              <el-row type="flex" align="middle">
                <el-col :span="22">
                  <form-item :item="item" :curItem="curItem" @setActiveItem="setActiveItem"></form-item>
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
          </draggable>
        </el-form>
      </el-main>

      <!-- 右侧属性栏 -->
      <el-aside>
        <el-container>
          <el-main style="border:0">
            <el-tabs stretch :value="activeConfigTab">
              <el-tab-pane label="字段属性" name="itemConfigTab">
                <form-config :item="curItem" @updateItem="updateItem"></form-config>
              </el-tab-pane>
              <el-tab-pane label="表单属性" name="formConfigTab" style="text-align:center">
                <el-form>
                  <el-form-item label="标题">
                    <el-input v-model="formConfigData.title"></el-input>
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
import { Provide, Component } from 'vue-property-decorator'
import FormItem from '@/components/form/FormItem'
import FormConfig from '@/components/form/FormConfig'
import draggable from 'vuedraggable'
import FormApi from '@/apis/FormApi'
import UuidUtil from '@/util/UuidUtil'
import { Item, BaseItem, FormConfigData } from '@/components/form/index'

@Component({
  components: {
    draggable,
    FormItem,
    FormConfig
  }
})
export default class FormEditor extends Vue {
  // 基础元素
  baseItems = BaseItem.items

  // 表单元素列表
  formItems = []

  // 表单全局配置
  formConfigData = new FormConfigData()

  // 当前元素
  curItem = new Item()

  result = {}

  // 当前激活的配置栏
  activeConfigTab = 'formConfigTab'

  clone(el: any) {
    let item = JSON.parse(JSON.stringify(el))
    item.uuid = UuidUtil.uuid()
    return item
  }

  removeItem(idx: any) {
    this.formItems.splice(idx, 1)
    if (this.formItems.length < 1) {
      this.curItem = new Item()
    }
  }

  setActiveItem(uuid: any) {
    console.log('setActiveItem, uuid =', uuid)
    this.formItems.forEach((item: Item) => {
      if (item.uuid == uuid) {
        this.curItem = item
      }
    })
    this.activeConfigTab = 'itemConfigTab'
  }

  updateItem(value: any) {
    console.log('update item =', value)
  }

  // 保存表单
  async save() {
    console.log('save...')
    // check
    if (!this.saveCheck()) return
    // data
    let formEditorVo = {
      ...this.formConfigData,
      items: this.formItems
    }
    let res = await new FormApi().saveForm(formEditorVo)
    this.$notify.success(res)
    this.backToFormAdmin()
  }

  // 保存表单时的各种检查
  saveCheck() {
    // 提醒内容
    let alertMsg = ''

    // 表单标题
    if (!this.formConfigData.title) {
      alertMsg = '请给表单取个名字~'
      // 右侧标签页跳到表单配置
      this.activeConfigTab = 'formConfigTab'
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
