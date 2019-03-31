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
                  <form-item :data="{...item, idx}"></form-item>
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
            <el-tabs stretch :value="activeIdx < 0 ? 'formConfig' : 'itemConfig'">
              <el-tab-pane label="字段属性" name="itemConfig">
                <form-config :item="{...FormItem[activeIdx], idx: activeIdx}"></form-config>
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

console.log(metas)
console.log(FormItem)
console.log(FormConfig)

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

  save() {
    console.log('save...')
    console.log(this.data)
    new FormApi().save({
      form: this.data.form,
      formItems: this.computedFormItems
    })
  }
}
</script>
