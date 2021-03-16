<template>
  <div>
    <p>
      <button v-on:click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      &nbsp;
      <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <!--组件的位置并进行渲染:第一个list组件内部定义的回调方法，第二个是course组件的方法list-->
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <div class="row">
      <div v-for="course in courses" class="col-md-4">
        <div class="thumbnail search-thumbnail">
          <img v-show="!course.image" class="media-object" src="/static/image/demo-course.jpg" />
          <img v-show="course.image" class="media-object" v-bind:src="course.image" />
          <div class="caption">
            <div class="clearfix">
              <span class="pull-right label label-primary info-label">
                {{COURSE_LEVEL | optionKV(course.level)}}
              </span>
              <span class="pull-right label label-primary info-label">
                {{COURSE_CHARGE | optionKV(course.charge)}}
              </span>
              <span class="pull-right label label-primary info-label">
                {{COURSE_STATUS | optionKV(course.status)}}
              </span>
            </div>

            <h3 class="search-title">
              <a href="#" class="blue">{{course.name}}</a>
            </h3>
            <p>
              <span class="blue bolder bigger-150">{{course.price}}&nbsp;<i class="fa fa-rmb"></i></span>&nbsp;
            </p>
            <p>{{course.summary}}</p>
            <p>
              <span class="badge badge-info">{{course.id}}</span>
              <span class="badge badge-info">排序：{{course.sort}}</span>
              <span class="badge badge-info">时长：{{course.time | formatSecond}}</span>
            </p>
            <p>
              <button v-on:click="toChapter(course)" class="btn btn-white btn-xs btn-info btn-round">
                大章
              </button>&nbsp;
              <button v-on:click="edit(course)" class="btn btn-white btn-xs btn-info btn-round">
                编辑
              </button>&nbsp;
              <button v-on:click="del(course.id)" class="btn btn-white btn-xs btn-warning btn-round">
                删除
              </button>
            </p>
          </div>
        </div>
      </div>
    </div>

    <!--<table id="simple-table" class="table  table-bordered table-hover">-->
      <!--<thead>-->
      <!--<tr>-->
        <!--<th>id</th>-->
        <!--<th>名称</th>-->
        <!--<th>概述</th>-->
        <!--<th>时长</th>-->
        <!--<th>价格（元）</th>-->
        <!--<th>封面</th>-->
        <!--<th>级别</th>-->
        <!--<th>收费</th>-->
        <!--<th>状态</th>-->
        <!--<th>报名数</th>-->
        <!--<th>顺序</th>-->
        <!--<th>操作</th>-->
      <!--</tr>-->
      <!--</thead>-->

      <!--<tbody>-->
      <!--<tr v-for="course in courses">-->
        <!--<td>{{course.id}}</td>-->
        <!--<td>{{course.name}}</td>-->
        <!--<td>{{course.summary}}</td>-->
        <!--<td>{{course.time}}</td>-->
        <!--<td>{{course.price}}</td>-->
        <!--<td>{{course.image}}</td>-->
        <!--<td>{{COURSE_LEVEL | optionKV(course.level)}}</td>-->
        <!--<td>{{COURSE_CHARGE | optionKV(course.charge)}}</td>-->
        <!--<td>{{COURSE_STATUS | optionKV(course.status)}}</td>-->
        <!--<td>{{course.enroll}}</td>-->
        <!--<td>{{course.sort}}</td>-->
      <!--<td>-->
        <!--<div class="hidden-sm hidden-xs btn-group">-->
          <!--<button v-on:click="edit(course)" class="btn btn-xs btn-info">-->
            <!--<i class="ace-icon fa fa-pencil bigger-120"></i>-->
          <!--</button>-->
          <!--<button v-on:click="del(course.id)" class="btn btn-xs btn-danger">-->
            <!--<i class="ace-icon fa fa-trash-o bigger-120"></i>-->
          <!--</button>-->
        <!--</div>-->

        <!--<div class="hidden-md hidden-lg">-->
          <!--<div class="inline pos-rel">-->
            <!--<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">-->
              <!--<i class="ace-icon fa fa-cog icon-only bigger-110"></i>-->
            <!--</button>-->

            <!--<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">-->
              <!--<li>-->
                <!--<a href="#" class="tooltip-info" data-rel="tooltip" title="View">-->
                  <!--<span class="blue">-->
                    <!--<i class="ace-icon fa fa-search-plus bigger-120"></i>-->
                  <!--</span>-->
                <!--</a>-->
              <!--</li>-->

              <!--<li>-->
                <!--<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">-->
                  <!--<span class="green">-->
                    <!--<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>-->
                  <!--</span>-->
                <!--</a>-->
              <!--</li>-->

              <!--<li>-->
                <!--<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">-->
                  <!--<span class="red">-->
                    <!--<i class="ace-icon fa fa-trash-o bigger-120"></i>-->
                  <!--</span>-->
                <!--</a>-->
              <!--</li>-->
            <!--</ul>-->
          <!--</div>-->
        <!--</div>-->
      <!--</td>-->
      <!--</tr>-->
      <!--</tbody>-->
    <!--</table>-->

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">表单</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="course.name" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">概述</label>
                <div class="col-sm-10">
                  <input v-model="course.summary" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">时长</label>
                <div class="col-sm-10">
                  <input v-model="course.time" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">价格（元）</label>
                <div class="col-sm-10">
                  <input v-model="course.price" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">封面</label>
                <div class="col-sm-10">
                  <input v-model="course.image" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">级别</label>
                <div class="col-sm-10">
                  <select v-model="course.level" class="form-control">
                    <option v-for="o in COURSE_LEVEL" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">收费</label>
                <div class="col-sm-10">
                  <select v-model="course.charge" class="form-control">
                    <option v-for="o in COURSE_CHARGE" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">状态</label>
                <div class="col-sm-10">
                  <select v-model="course.status" class="form-control">
                    <option v-for="o in COURSE_STATUS" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">报名数</label>
                <div class="col-sm-10">
                  <input v-model="course.enroll" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">顺序</label>
                <div class="col-sm-10">
                  <input v-model="course.sort" class="form-control">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button v-on:click="save()" type="button"  class="btn btn-primary">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  export default {
    components: {Pagination},
    name: "business-course",
    data: function() {
      return {
        course: {},//接收单个course
        courses: [], //接收course的数组
        COURSE_LEVEL: COURSE_LEVEL,
        COURSE_CHARGE: COURSE_CHARGE,
        COURSE_STATUS: COURSE_STATUS,
      }
    },
    mounted: function() {
      // 子组件调用父组件的方法 sidebar激活样式方法一
      // this.$parent().activeSidebar("business-course-sidebar")
      let _this = this;
      _this.$refs.pagination.size = 5;
      _this.list(1);
      // 前后端数据交互
    },
    methods: {

      /**
       * 添加模态框
       */
      add() {
        let _this = this;
        _this.course = {}; // 解决新增数据时模态框保留上次数据问题
        // 第一个modal是css选择器，模态框代码里有class="modal",是modal()方法里的是内置方法，弹出关闭模态框hide，show
        $("#form-modal").modal({backdrop:"static"}, "show"); // 点空白不会关闭
        // $("#form-modal").modal("show"); // 点空白会关闭
      },

      edit(course) {
        let _this = this;
        // vue中将表格行数据显示到表单，反过来也会的问题：$.extend({},course)解决(course,复制给空对象)
        _this.course = $.extend({}, course); //vue中的_tis.course会通过v-modal属性和form表单做数据绑定
        $("#form-modal").modal({backdrop:"static"}, "show"); // 点空白不会关闭

      },

      /**删除**/
      del(id) {
        let _this = this;
        Confirm.show("删除课程后不可恢复！确认删除？",function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/course/delete/' + id).then((response)=>{
            Loading.hide();
            let resp = response.data;
            if (resp.success) {
              _this.list(1);
              Toast.success("删除成功！");
            }
          });
        });
      },

      /**
       * 列表查询展示分页
       * @param page
       */
      list(page) {
        let _this = this;
        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/list', {
          page: page,
          size: _this.$refs.pagination.size, // $refs获取子组件（根绝pagination这个名字）
        }).then((response)=>{
          Loading.hide();
          // console.log("查询课程列表结果:", response);
          let resp = response.data;
          _this.courses = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);//重现渲染组件
        })
      },

      /**点击保存**/
      save() {
        let _this = this;

        // 保存校验
        if (1 != 1
          || !Validator.require(_this.course.name, "名称")
          || !Validator.length(_this.course.name, "名称", 1, 50)
          || !Validator.length(_this.course.summary, "概述", 1, 2000)
          || !Validator.length(_this.course.image, "封面", 1, 100)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/course/save',
          _this.course).then((response)=>{
          Loading.hide();
          let resp = response.data;
          if (resp.success) {
            $("#form-modal").modal("hide");
            _this.list(1);
            Toast.success("保存成功！");
          } else {
            Toast.warning(resp.message)
          }
        })
      },

      /**
       * 点击【大章】
       */
      toChapter(course) {
        let _this = this;
        SessionStorage.set("course", course);
        _this.$router.push("/business/chapter");
      },
    }
  }
</script>

<style scoped>
  .caption h3 {
    font-size: 20px;
  }
</style>