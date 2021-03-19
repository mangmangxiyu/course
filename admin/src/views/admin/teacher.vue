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

    <!--组件的位置并进行渲染:第一个list组件内部定义的回调方法，第二个是teacher组件的方法list-->
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <div class="row">
      <div v-for="teacher in teachers" class="col-md-3">
        <div>
					<span class="profile-picture">
            <img v-show="!teacher.image" class="editable img-responsive editable-click editable-empty" src="/static/image/讲师头像/头像1.jpg" v-bind:title="teacher.intro"/>
            <img v-show="teacher.image" class="editable img-responsive editable-click editable-empty" v-bind:src="teacher.image" v-bind:title="teacher.intro"/>
					</span>

          <div class="space-4"></div>

          <div class="width-85 label label-info label-xlg arrowed-in arrowed-in-right">
            <div class="inline position-relative">
              <a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
                <i class="ace-icon fa fa-circle light-green"></i>
                &nbsp;
                <span class="white">{{teacher.position}}</span>
              </a>
            </div>
          </div>
        </div>

        <div class="space-6"></div>
        <a href="javascript:;" class="text-info bigger-110" v-bind:title="teacher.motto">
          <i class="ace-icon fa fa-user"></i>
          {{teacher.name}}【{{teacher.nickname}}】
        </a>

        <div class="hr hr16 dotted"></div>

        <div class="profile-social-links align-center">
          <button v-on:click="edit(teacher)" class="btn btn-xs btn-info">
            <i class="ace-icon fa fa-pencil bigger-120"></i>
          </button>
          &nbsp;
          <button v-on:click="del(teacher.id)" class="btn btn-xs btn-danger">
            <i class="ace-icon fa fa-trash-o bigger-120"></i>
          </button>
        </div>

      <div class="hr hr16 dotted"></div>


      </div>
    </div>

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
                <label class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-10">
                  <input v-model="teacher.name" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">昵称</label>
                <div class="col-sm-10">
                  <input v-model="teacher.nickname" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">头像</label>
                <div class="col-sm-10">
                  <file v-bind:id="'image-upload'"
                        v-bind:text="'上传头像'"
                        v-bind:suffixs="['jpg', 'jpeg', 'png']"
                        v-bind:use="FILE_USE.TEACHER.key"
                        v-bind:after-upload="afterUpload"></file>
                  <div v-show="teacher.image" class="row">
                    <div class="col-md-4">
                      <img v-bind:src="teacher.image" class="img-responsive">
                    </div>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">职位</label>
                <div class="col-sm-10">
                  <input v-model="teacher.position" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">座右铭</label>
                <div class="col-sm-10">
                  <input v-model="teacher.motto" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">简介</label>
                <div class="col-sm-10">
                  <textarea v-model="teacher.intro" class="form-control" rows="5"></textarea>
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
  import File from "../../components/file";
  export default {
    components: {Pagination, File},
    name: "business-teacher",
    data: function() {
      return {
        teacher: {},//接收单个teacher
        teachers: [], //接收teacher的数组
        FILE_USE: FILE_USE// 不写这句直接用上面的FILE_USE会报错，xx:xx左边是file组件内的变量，右是js的全局变量，在html中用的是组件内的变量（直接读不到全局的变量）
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 5;
      _this.list(1);
      // 前后端数据交互
      // 子组件调用父组件的方法 sidebar激活样式方法一
      // this.$parent().activeSidebar("business-teacher-sidebar")
    },
    methods: {

      /**
       * 添加模态框
       */
      add() {
        let _this = this;
        _this.teacher = {}; // 解决新增数据时模态框保留上次数据问题
        // 第一个modal是css选择器，模态框代码里有class="modal",是modal()方法里的是内置方法，弹出关闭模态框hide，show
        $("#form-modal").modal({backdrop:"static"}, "show"); // 点空白不会关闭
        // $("#form-modal").modal("show"); // 点空白会关闭
      },

      /**
       * 点击【编辑】
       * */
      edit(teacher) {
        let _this = this;
        // vue中将表格行数据显示到表单，反过来也会的问题：$.extend({},teacher)解决(teacher,复制给空对象)
        _this.teacher = $.extend({}, teacher); //vue中的_tis.teacher会通过v-modal属性和form表单做数据绑定
        $("#form-modal").modal({backdrop:"static"}, "show"); // 点空白不会关闭

      },

      /**删除**/
      del(id) {
        let _this = this;
        Confirm.show("删除讲师后不可恢复！确认删除？",function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/teacher/delete/' + id).then((response)=>{
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
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/list', {
          page: page,
          size: _this.$refs.pagination.size, // $refs获取子组件（根绝pagination这个名字）
        }).then((response)=>{
          Loading.hide();
          // console.log("查询讲师列表结果:", response);
          let resp = response.data;
          _this.teachers = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);//重现渲染组件
        })
      },

      /**点击保存**/
      save() {
        let _this = this;

        // 保存校验
        if (1 != 1
          || !Validator.require(_this.teacher.name, "姓名")
          || !Validator.length(_this.teacher.name, "姓名", 1, 50)
          || !Validator.length(_this.teacher.nickname, "昵称", 1, 50)
          || !Validator.length(_this.teacher.image, "头像", 1, 100)
          || !Validator.length(_this.teacher.position, "职位", 1, 50)
          || !Validator.length(_this.teacher.motto, "座右铭", 1, 50)
          || !Validator.length(_this.teacher.intro, "简介", 1, 500)
        ) {
          return;
        }

        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/teacher/save',
          _this.teacher).then((response)=>{
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

      afterUpload(resp) {
        let _this = this;
        let image = resp.content.path;
        _this.teacher.image = image;
      }
    }
  }
</script>