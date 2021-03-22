<template>
  <!--1此次提交应为url="'oss-append'",shard-size="shardSize"-->
  <!--2此次提交使用vod.vue所以size写死不可配置-->
  <big-file v-bind:input-id="inputId"
            v-bind:text="text"
            v-bind:suffixs="suffixs"
            v-bind:use="use"
            v-bind:after-upload="afterUpload"
            v-bind:shard-size="1000 * 1024 * 1024"
            v-bind:url="'vod'"></big-file>
</template>

<script>
  import BigFile from "./big-file";
  export default {
    components: {BigFile},
    name: 'vod',
    props: {
      text: {
        default: "上传VOD"
      },
      inputId: {
        default: "vod-upload"
      },
      suffixs: {
        default: []
      },
      use: {
        default: ""
      },
      // 2上次有配置 ->1
      // shardSize: {
      //   default: 50 * 1024
      // },
      afterUpload: {
        type: Function,
        default: null
      },
    },
    methods: {
    }
  }
</script>
