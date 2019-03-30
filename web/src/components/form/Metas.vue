<template>
  <div>
    <el-button autofocus :icon="icon">{{label}}</el-button>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import Component from 'vue-class-component'
import { Prop } from 'vue-property-decorator'

export class MetaInfo {
  static form = {
    inline: false
  }
  static items = [
    {
      id: 1,
      prop: 'input',
      type: 'el-input',
      label: '文本框',
      icon: 'el-icon-edit'
    },
    {
      id: 2,
      type: 'el-input',
      prop: 'input',
      label: '多行文本',
      icon: 'el-icon-tickets'
    },
    {
      id: 3,
      type: 'el-select',
      prop: 'input',
      label: '下拉框',
      icon: 'el-icon-arrow-down'
    },
    {
      id: 4,
      type: 'el-date-picker',
      prop: 'input',
      label: '日期选择器',
      icon: 'el-icon-time'
    }
  ]
}

@Component
export default class Metas extends Vue {
  @Prop() label!: string
  @Prop() icon!: string
}
</script>

