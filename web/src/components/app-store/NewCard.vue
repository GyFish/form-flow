<template>
  <div class="app-card">
    <el-col :span="8">
      <el-card shadow="hover">
        <div class="new-button">
          <el-button type="text" icon="el-icon-plus" size="large" @click="newApp"></el-button>
        </div>
      </el-card>
    </el-col>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator'

@Component
export default class NewCard extends Vue {
  newApp() {
    this.$emit('newApp')
  }
}
</script>
