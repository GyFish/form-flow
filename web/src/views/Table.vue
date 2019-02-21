<template>
  <div>
    <el-table border fit stripe :data="tableData">
      <el-table-column v-for="item of items" :prop="item.prop" :label="item.label"></el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import { State } from 'vuex-class'

@Component
export default class TableView extends Vue {
  // state
  @State data: any

  get tableData() {
    return this.data.table
  }

  get items() {
    return this.data.items
  }
}
</script>
