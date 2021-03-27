<template>
  <div class="card mb-4 shadow-sm course">
    <img class="img-fluid" v-bind:src="course.image">
    <div class="card-body">
      <h4 class="">{{course.name}}</h4>
      <p class="card-text">{{course.summary}}</p>
      <div class="d-flex justify-content-between align-items-center">
        <div class="btn-group">
          <button type="button" class="btn btn-sm btn-outline-secondary">课程详情</button>
        </div>
        <div class="text-muted">
          <span class="badge badge-info"><i class="fa fa-yen" aria-hidden="true"></i>&nbsp;{{course.price}}</span>&nbsp;
          <span class="badge badge-info"><i class="fa fa-yen" aria-hidden="true"></i>&nbsp;{{course.enroll}}</span>&nbsp;
        </div>
      </div>
    </div>
  </div>
</template>
<style></style>
<script>
  export default {
    name: "the-course",
    props:{
      course: {}
    },
    data: function () {
      return {}
    },
  }
</script>

<style>
  .course h4 {
    font-size: 1.25rem;
    margin: 15px;
  }

  .course .text-muted .badge {
    font-size: 1rem;
  }
</style>
