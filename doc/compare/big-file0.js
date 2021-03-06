<script>
export default {
  name: 'big-file',
  props: {
    text: {
      default: "上传大文件"
    },
    inputId: {
      default: "file-upload"
    },
    suffixs: {
      default: []
    },
    use: {
      default: ""
    },
    afterUpload: {
      type: Function,
      default: null
    },
  },
  data: function () {
    return {
    }
  },
methods: {
  uploadFile() {
    let _this = this;
    let formData = new window.FormData();
    let file = _this.$refs.file.files[0];

    console.log(file);

    // 生成文件标识，标识多次上传的是否为同一个文件
    let key = hex_md5(file);
    let key10 = parseInt(key, 16);
    let key62 = Tool._10to62(key10);

    console.log(key, key10, key62);

    // 判断文件格式
    let suffixs = _this.suffixs;
    let fileName = file.name;
    let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
    let validateSuffix = false;
    for (let i = 0; i < suffixs.length; i++) {
      if (suffixs[i].toLowerCase() === suffix) {
        validateSuffix = true;
        break;
      }
    }
    if (!validateSuffix) {
      Toast.warning("文件格式不正确！只支持上传：" + suffixs.join(","));
      $("#" + _this.inputId + "-input").val("");
      return;
    }

    // 文件分片
    let shardSize = 20 * 1024 * 1024;// 分片大小
    let shardIndex = 2;// 分片索引1表示入库为第一个分片
    let start = (shardIndex - 1) * shardSize;// 当前分片起始位置
    let end = Math.min(file.size, start + shardSize);// 当前分片结束为止
    let fileShard = file.slice(start, end);// 从文件中截取当前的分片数据
    let size = file.size;
    let shardTotal = Math.ceil(size / shardSize);

    let fileReader = new FileReader();
    fileReader.onload = function (e) {// 数据onload监听
      let base64 = e.target.result;
      // console.log("base64", base64);

      let param = {
        "shard": base64,
        "shardIndex": shardIndex,
        "shardSize": shardSize,
        "shardTotal": shardTotal,
        "use": _this.use,
        "name": file.name,
        "suffix": suffix,
        "size": file.size,
        "key": key62
      };

      Loading.show();
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/file/admin/upload', param).then((response) => {
        Loading.hide();
        let resp = response.data;
        console.log("上传文件成功：", resp);
        _this.afterUpload(resp);
        $("#" + _this.inputId + "-input").val("");
      });
    };
    fileReader.readAsDataURL(fileShard)
  },

  selectFile () {
    let _this = this;
    $("#" + _this.inputId + "-input").trigger("click");
  }
}
}
</script>
