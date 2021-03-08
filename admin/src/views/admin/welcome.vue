<template>
  <div>
    <h1>你好欢迎来到控制台</h1>
  </div>
</template>

<script>
  export default {
    name: "welcome",
    mounted: function() {
      // 子组件调用父组件的方法
      this.$parent.activeSidebar("welcome-sidebar")
    },
    methods: {
    }
  }
</script>

