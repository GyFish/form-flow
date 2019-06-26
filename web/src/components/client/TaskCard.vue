<template>
  <div class="task-card">
    <el-card>
      <el-row>
        <i :class="status"></i>
        <el-tag type="info">{{createTime}}</el-tag>
        <el-tag>{{task.taskName}}</el-tag>
      </el-row>
      <el-col :span="8" v-for="(item, i) of task.formData" :key="i" class="item">
        <el-col :span="8">{{item.label}}：</el-col>
        <el-col :span="16">{{item.value}}</el-col>
      </el-col>
    </el-card>
  </div>
</template>

<script lang="ts">
import { Vue, Prop, Component } from 'vue-property-decorator'
import DateUtil from '@/util/DateUtil'

@Component
export default class TaskCard extends Vue {
  @Prop()
  task: any

  get status() {
    switch (this.task.status) {
      case 'TODO':
        return 'el-icon-question'
        break
      case 'DONE':
        return 'el-icon-success'
        break
    }
  }

  get createTime() {
    return DateUtil.formatStr(this.task.createTime)
  }
}
</script>
