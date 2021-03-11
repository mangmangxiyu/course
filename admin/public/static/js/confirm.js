Confirm = {
  show: function (message, callback) {//(①)这个就是外部的方法
    Swal.fire({
      title: '确认?',
      text: message,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '确认!'
    }).then((result) => {
      if (result.value) {//点击确认执行外部的方法①
        if (callback) {
          callback();
        }
      }
    })
  }
}