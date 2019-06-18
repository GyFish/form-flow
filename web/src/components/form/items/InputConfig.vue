<template>
  <div>
    <el-form>
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
  data!: Item

  input = {}

  @Watch('data')
  onDataChange(value: any) {
    console.log('  on data change, value =', value)
    this.input = value
  }

  @Watch('input', { deep: true })
  onInputChange(value: any) {
    console.log('  on input change, value =', value)
    this.$emit('updateItem', value)
  }
}
</script>
