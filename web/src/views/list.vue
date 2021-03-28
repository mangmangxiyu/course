<template>
  <main role="main">
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <pagination ref="pagination" v-bind:list="listCourse"></pagination>
          </div>
        </div>
        <br>
        <div class="row">
          <div v-for="o in courses" class="col-md-4">
            <the-course v-bind:course="o"></the-course>
          </div>
          <h3 v-show="courses.length === 0">课程还未上架</h3>
        </div>
      </div>
    </div>
  </main>
</template>

<script>

  import TheCourse from "../components/the-course"
  import Pagination from "../components/pagination";

  export default {
    components: {Pagination, TheCourse},
    name: "list",
    data: function () {
      return {
        courses: [],
        level1: [],
        level2: [],
      }
    },
    mounted() {
      let _this = this;
      _this.$refs.pagination.size = 1;
      _this.listCourse();
      _this.allCategory();
    },
    methods: {
      /**
       * 查询课程列表
       */
      listCourse(page) {
        let _this = this;
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/web/course/list', {
          page: page,
          size: _this.$refs.pagination.size,
        }).then((response) => {
          let resp = response.data;
          if (resp.success) {
            _this.courses = resp.content.list;
            _this.$refs.pagination.render(page, resp.content.total);
          }
        }).catch((response) => {
          console.log("error：", response);
        })
      },
      /**
       * 所有分类查询
       *
       */
      allCategory() {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/web/category/all').then((response)=>{
          Loading.hide();
          let resp = response.data;
          _this.categorys = resp.content;

          // 将所有记录格式化成属性结构的数据
          _this.level1 = [];
          for (let i = 0; i < _this.categorys.length; i++) {
            let c = _this.categorys[i];
            if (c.parent === "00000000") {
              _this.level1.push(c);
              for (let j = 0; j < _this.categorys.length; j++) {
                let child = _this.categorys[j];
                if (child.parent === c.id) {
                  if (Tool.isEmpty(c.children)) {
                    c.children = [];
                  }
                  c.children.push(child);
                }
              }
            }
          }

          _this.level2 = [];
        })
      },


    }
  }
</script>

<style>

</style>


