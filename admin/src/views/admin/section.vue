<template>
  <div>
    <h4 class="lighter">
      <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
      <router-link to="/business/course" class="pink"> {{course.name}} </router-link>：
      <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
      <router-link to="/business/chapter" class="pink"> {{chapter.name}} </router-link>
    </h4>
    <hr>
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

    <!--组件的位置并进行渲染:第一个list组件内部定义的回调方法，第二个是section组件的方法list-->
    <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>标题</th>
        <!--<th>视频</th>-->
        <th>VOD</th>
        <th>时长</th>
        <th>收费</th>
        <th>顺序</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="section in sections">
        <td>{{section.id}}</td>
        <td>{{section.title}}</td>
        <!--<td>{{section.video}}</td>此次3-->
        <td>{{section.vod}}</td>
        <td>{{section.time | formatSecond}}</td>
        <td>{{SECTION_CHARGE | optionKV(section.charge)}}</td>
        <td>{{section.sort}}</td>
      <td>
        <div class="hidden-sm hidden-xs btn-group">
          <!--此次3增加的一个按钮-->
          <button v-on:click="play(section)" class="btn btn-xs btn-info">
            <i class="ace-icon fa fa-video-camera bigger-120"></i>
          </button>
          <button v-on:click="edit(section)" class="btn btn-xs btn-info">
            <i class="ace-icon fa fa-pencil bigger-120"></i>
          </button>
          <button v-on:click="del(section.id)" class="btn btn-xs btn-danger">
            <i class="ace-icon fa fa-trash-o bigger-120"></i>
          </button>
        </div>
      </td>
      </tr>
      </tbody>
    </table>

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
                <label class="col-sm-2 control-label">标题</label>
                <div class="col-sm-10">
                  <input v-model="section.title" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">课程</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{course.name}}</p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">大章</label>
                <div class="col-sm-10">
                  <p class="form-control-static">{{chapter.name}}</p>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">视频</label>
                <div class="col-sm-10">
                  <vod v-bind:input-id="'video-upload'"
                        v-bind:text="'上传大视频'"
                        v-bind:suffixs="['mp4']"
                        v-bind:use="FILE_USE.COURSE.key"
                        v-bind:after-upload="afterUpload"></vod>
                  <div v-show="section.video" class="row">
                    <div class="col-md-9">
                      <!--此次2+class="hidden"-->
                      <!--<player ref="player" ref="player"></player>下面player此次3-->
                      <player v-bind:player-id="'form-player-div'"
                              ref="player"></player>
                      <video id="video" v-bind:src="section.video" controls="controls" class="hidden"></video>
                    </div>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">时长</label>
                <div class="col-sm-10">
                  <input v-model="section.time" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">视频</label>
                <div class="col-sm-10">
                  <input v-model="section.video" class="form-control" disabled>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">VOD</label>
                <div class="col-sm-10">
                  <input v-model="section.vod" class="form-control" disabled>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">收费</label>
                <div class="col-sm-9">
                  <select v-model="section.charge" class="form-control">
                    <option v-for="o in SECTION_CHARGE" v-bind:value="o.key">{{o.value}}</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">顺序</label>
                <div class="col-sm-10">
                  <input v-model="section.sort" class="form-control">
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

    <!--此次3带有弹出框的播放器-->
    <modal-player ref="modalPlayer"></modal-player>
  </div>
</template>

<script>
  import Pagination from "../../components/pagination";
  import BigFile from "../../components/big-file";
  import Vod from "../../components/vod";
  import Player from "../../components/player";
  import ModalPlayer from "../../components/modal-player";
  export default {
    components: {ModalPlayer, Player, Pagination, BigFile, Vod},
    name: "business-section",
    data: function() {
      return {
        section: {},//接收单个section
        sections: [], //接收section的数组
        SECTION_CHARGE: SECTION_CHARGE,
        FILE_USE: FILE_USE,
        course: {},
        chapter: {}
      }
    },
    mounted: function() {
      let _this = this;
      _this.$refs.pagination.size = 5;
      let course = SessionStorage.get(SESSION_KEY_COURSE) || {};
      let chapter = SessionStorage.get(SESSION_KEY_CHAPTER) || {};
      if (Tool.isEmpty(course) || Tool.isEmpty(chapter)) {
        _this.$router.push("/welcome");
      }
      _this.course = course;
      _this.chapter = chapter;
      _this.list(1);
      // 子组件调用父组件的方法 sidebar激活样式方法一
      this.$parent.activeSidebar("business-course-sidebar")
    },
    methods: {

      /**
       * 添加模态框
       */
      add() {
        let _this = this;
        _this.section = {}; // 解决新增数据时模态框保留上次数据问题
        // 第一个modal是css选择器，模态框代码里有class="modal",是modal()方法里的是内置方法，弹出关闭模态框hide，show
        $("#form-modal").modal({backdrop:"static"}, "show"); // 点空白不会关闭
        // $("#form-modal").modal("show"); // 点空白会关闭
      },

      edit(section) {
        let _this = this;
        // vue中将表格行数据显示到表单，反过来也会的问题：$.extend({},section)解决(section,复制给空对象)
        _this.section = $.extend({}, section); //vue中的_tis.section会通过v-modal属性和form表单做数据绑定
        $("#form-modal").modal({backdrop:"static"}, "show"); // 点空白不会关闭

      },

      /**删除**/
      del(id) {
        let _this = this;
        Confirm.show("删除小节后不可恢复！确认删除？",function () {
          Loading.show();
          _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/section/delete/' + id).then((response)=>{
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
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/list', {
          page: page,
          size: _this.$refs.pagination.size, // $refs获取子组件（根绝pagination这个名字）
          courseId: _this.course.id,
          chapterId: _this.chapter.id
        }).then((response)=>{
          Loading.hide();
          // console.log("查询小节列表结果:", response);
          let resp = response.data;
          _this.sections = resp.content.list;
          _this.$refs.pagination.render(page, resp.content.total);//重新渲染组件
        })
      },

      /**点击保存**/
      save() {
        let _this = this;
        _this.section.video = "";// 此次1：获取到的可播放地址是有效的，所以就算保存到数据库也会过期，没用。所以根据vod播放,我们的校验逻辑中，空值是不会校验长度的。
        // 保存校验
        if (1 != 1
          || !Validator.require(_this.section.title, "标题")
          || !Validator.length(_this.section.title, "标题", 1, 50)
          || !Validator.length(_this.section.video, "视频", 1, 200)
        ) {
          return;
        }
        _this.section.courseId = _this.course.id;
        _this.section.chapterId = _this.chapter.id;


        Loading.show();
        _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/section/save',
          _this.section).then((response)=>{
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
        let video = resp.content.path;
        let vod = resp.content.vod;//此次1
        _this.section.video = video;
        _this.section.vod = vod;//此次1
        _this.getTime();
        _this.$refs.player.playUrl(video);// 此次2
      },
      // 获取时长，延时操作解决获取时长NAN的bug
      getTime() {
        let _this = this;
        setTimeout(function () {
        let ele = document.getElementById("video");
        console.info(ele);
        _this.section.time = parseInt(ele.duration, 10);
        }, 1000);
      },

      // 此次3方法
      /**
       * 播放视频
       * @param section
       */
      play(section) {
        let _this = this;
        _this.$refs.modalPlayer.playVod(section.vod);
      }
    }
  }
</script>

<style>
  video {
    width: 100%;
    height: auto;
    margin-top: 10px;
  }
</style>