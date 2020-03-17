<template>
  <div class="app-wrapper">
    <sidebar class="sidebar-container"></sidebar>
    <div class="main-container">
      <navbar/>
      <app-main/>
    </div>
  </div>
</template>
<script>
  import {Navbar, Sidebar, AppMain} from "@/views/layout/components"

  export default {
    name: "layout",
    components: {
      Navbar,
      Sidebar,
      AppMain
    }, computed: {
      sidebar() {
        return this.$store.state.app.sidebar;
      }
    }
  }
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
  @import "src/styles/mixn.scss";

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
  }
</style>
