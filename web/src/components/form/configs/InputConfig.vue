<template>
  <div>
    <el-form>
      <el-form-item label="key">
        <el-input v-model="input.prop"></el-input>
      </el-form-item>
      <el-form-item label="名称">
        <el-input v-model="input.label"></el-input>
      </el-form-item>
      <el-form-item label="占位文本">
        <el-input v-model="input.placeholder"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import { Item } from '../index'
import { Prop, Component, Watch } from 'vue-property-decorator'

@Component
export default class InputConfig extends Vue {
  @Prop()
  item!: Item

  get input() {
    return this.item
  }

  @Watch('input', { deep: true })
  onInputChange(value: any) {
    this.$emit('updateItem', value)
  }
}
</script>
