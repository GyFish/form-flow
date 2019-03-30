<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/yayi.jpg" @click="toAppStore">
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { State, Mutation } from 'vuex-class'

@Component
export default class Home extends Vue {
  @Mutation setActiveMenu: any

  toAppStore() {
    this.$router.push('/appStore')
    this.setActiveMenu('/appStore')
  }
}
</script>
