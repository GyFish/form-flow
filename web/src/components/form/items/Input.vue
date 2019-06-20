<template>
  <el-input :placeholder="data.placeholder"/>
</template>

<script lang="ts">
import Vue from 'vue'
import { Prop, Component } from 'vue-property-decorator'

@Component
export default class Input extends Vue {
  @Prop() data!: any
}
</script>
