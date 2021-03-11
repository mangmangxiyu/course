const Toast = Swal.mixin({
  toast: true,
  position: 'top-end',
  showConfirmButton: false,
  timer: 3000,
  timerProgressBar: true,
  didOpen: (toast) => {
    toast.addEventListener('mouseenter', Swal.stopTimer)
    toast.addEventListener('mouseleave', Swal.resumeTimer)
  }
})

// 原有样式Toast.fire({
//   icon: 'success',
//   title: 'Signed in successfully'
// })

// 全局变量，有三个方法，传入message变量（显示信息）
toast = {
  success: function (message) {
    Toast.fire({
      icon: 'success',
      title: message
    });
  },
  error: function (message) {
    Toast.fire({
      icon: 'error',
      title: message
    });
  },
  warning: function (message) {
    Toast.fire({
      icon: 'warning',
      title: message
    });
  },
};