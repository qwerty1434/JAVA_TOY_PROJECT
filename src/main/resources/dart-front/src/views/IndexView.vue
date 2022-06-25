<template>
  <div class="about">
    <h1>HelloWorld</h1>

    <button @click="searchAll">데이터 모두조회</button>
    {{ user }}
  </div>
</template>
<script>
import url from "@/url/http";

export default {
  data() {
    return {
      user: null,
    };
  },
  methods: {
    searchAll() {
      url.get("/User/searchAll", {}).then((response) => {
        console.log(response);
        this.user = response.data;
      });
    },
  },
};
</script>
