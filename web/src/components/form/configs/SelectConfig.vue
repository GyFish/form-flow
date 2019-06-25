<template>
  <div>
    <el-form>
      <el-form-item label="key">
        <el-input v-model="input.prop"></el-input>
      </el-form-item>
      <el-form-item label="名称">
        <el-input v-model="input.label"></el-input>
      </el-form-item>
      <el-form-item v-for="(option, i) of input.options" :key="i" :label="'选项' + (i + 1)">
        <el-button
          type="text"
          icon="el-icon-plus"
          style="float:right;padding-left:5px"
          @click="handleAddOption(i)"
        ></el-button>
        <el-button
          type="text"
          icon="el-icon-minus"
          style="float:right;padding-right:5px"
          @click="handleRemoveOption(i)"
        ></el-button>
        <el-input placeholder="label" v-model="option.label"></el-input>
        <el-input placeholder="value" v-model="option.value"></el-input>
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

  get input(): any {
    return this.item
  }

  @Watch('input', { deep: true })
  onInputChange(value: any) {
    this.$emit('updateItem', value)
  }

  handleAddOption(index: any) {
    this.input.options.splice(index + 1, 0, {})
  }

  handleRemoveOption(index: any) {
    this.input.options.splice(index, 1)
  }
}
</script>
