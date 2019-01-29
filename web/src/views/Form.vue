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
        <el-form label-position="left" label-width="100px">
          <draggable class="pad" v-model="items" :options="{group:'people'}">
            <div v-for="(item, idx) of items">
              <item :data="{...item, idx}" v-on:flushByIdx="onFlushByIdx"/>
            </div>
          </draggable>
        </el-form>
      </el-main>
      <!-- 右侧属性栏 -->
      <el-aside>
        <el-tabs stretch :value="activeIdx < 0 ? 'formConfig' : 'itemConfig'">
          <el-tab-pane label="字段属性" name="itemConfig">
            <pre>{{JSON.stringify(items[activeIdx], null, 4)}}</pre>
            <config :item="{...items[activeIdx], idx: activeIdx}"></config>
          </el-tab-pane>
          <el-tab-pane label="表单属性" name="formConfig" style="text-align:center">
            <img src="@/assets/logo.png">
          </el-tab-pane>
        </el-tabs>
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
  name: 'Design',
  components: {
    draggable,
    metas,
    item,
    config
  }
})
export default class Design extends Vue {
  // store
  @State meta: any
  @State data: any
  @State activeIdx: any
  @Mutation update: any
  @Mutation updateByIdx: any

  tempItem: any

  get items() {
    return this.data.items
  }

  set items(items) {
    this.update(items)
  }

  clone(el: any) {
    // 避免 copy 时引用同一个对象
    return Object.assign({}, el)
  }

  onFlushByIdx(prop: any) {
    console.log('父组件 design 监听到事件：onFlushByIdx')
    this.updateByIdx({idx: prop.idx, item: prop})
    // 清空
    this.tempItem = null
  }

  mounted() {
    // console.log('design page mounted...')
    // console.log(this.items)
  }

  updated() {
    // console.log('design page updated...')
    // console.log(this.items)
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