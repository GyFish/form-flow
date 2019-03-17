<template>
  <div class="design">
    <el-container>
      <!-- 左侧组件栏 -->
      <el-aside>
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
          <draggable class="pad" v-model="items" :options="{group:'people'}">
            <div v-for="(item, idx) of items">
              <el-row type="flex" align="middle">
                <el-col :span="22">
                  <item :data="{...item, idx}"/>
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
            <el-form-item v-if="items.length > 0">
              <el-button type="success" @click="commit">提交</el-button>
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
                <config :item="{...items[activeIdx], idx: activeIdx}"></config>
                <pre v-if="showdata%2==0">{{JSON.stringify(items[activeIdx], null, 4)}}</pre>
              </el-tab-pane>
              <el-tab-pane label="表单属性" name="formConfig" style="text-align:center">
                <img src="@/assets/logo.png">
              </el-tab-pane>
            </el-tabs>
          </el-main>
          <el-footer height="50px">
            <el-button @click="showdata++">查看数据</el-button>
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
import metas from '@/components/Metas.vue'
import item from '@/components/Item.vue'
import config from '@/components/Config.vue'
import draggable from 'vuedraggable'

@Component({
  name: 'Form',
  components: {
    draggable,
    metas,
    item,
    config
  }
})
export default class Form extends Vue {
  // store
  @State meta: any
  @State data: any
  @State activeIdx: any
  @Mutation update: any
  @Mutation updateByIdx: any
  @Mutation removeByIdx: any
  @Mutation commitTable: any

  // 是否显示配置栏数据
  showdata: number = 0

  get items() {
    return this.data.items
  }

  set items(items) {
    this.update(items)
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
    console.log(this.data.items)
  }
  
}
</script>


<style lang="scss">
.el-aside {
  width: 20%;
  height: 100%;
}

.el-main {
  border-left: 1px solid #e6e6e6;
  border-right: 1px solid #e6e6e6;
}

.design {
  height: calc(100% - 36px);
  .el-container {
    height: 100%;
  }
}

.el-form {
  height: 100%;
  min-height: 100%;
}

.pad {
  min-height: 100%;
}
</style>