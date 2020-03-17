<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>

<style lang="scss">
  // normalize.css 样式格式化
  @import '~normalize.css/normalize.css';
  @import './styles/index.scss'; // 全局自定义的css样式
</style>
