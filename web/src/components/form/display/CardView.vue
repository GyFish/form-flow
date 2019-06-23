<template>
  <div class="card-view">
    <el-card>
      <el-col :span="8" v-for="(item, i) of list" :key="i" class="item">
        <el-col :span="8">{{item.label}}：</el-col>
        <el-col :span="16">{{item.value}}</el-col>
      </el-col>
    </el-card>
  </div>
</template>

<script lang="ts">
import { Vue, Prop, Component } from 'vue-property-decorator'

@Component
export default class CardView extends Vue {
  @Prop()
  list: any
}
</script>

<style lang="scss" scoped>
.card-view {
  .item {
    padding: 10px;
  }
}
</style>
