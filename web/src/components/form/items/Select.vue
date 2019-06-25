<template>
  <el-select v-model="data.value">
    <el-option
      v-for="item in data.options"
      :key="item.value"
      :label="item.label"
      :value="item.value"
    ></el-option>
  </el-select>
</template>

<script lang="ts">
import Vue from 'vue'
import { Prop, Component } from 'vue-property-decorator'

@Component
export default class Select extends Vue {
  @Prop() data!: any
}
</script>
