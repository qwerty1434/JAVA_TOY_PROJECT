<template>
  <div class="about">
    <h1>HelloWorld</h1>

    <button @click="searchAll">데이터 모두조회</button>
    {{ user }}
    <br />
    <br />
    <br />
    <input type="text" v-model="keyword" />

    <button @click="disclosure">전송</button>
    {{ res }}
  </div>
</template>
<script>
import url from "@/url/http";

export default {
  data() {
    return {
      keyword: null,
      user: null,
      res: null,
    };
  },
  methods: {
    searchAll() {
      url.get("/User/searchAll", {}).then((response) => {
        console.log(response);
        this.user = response.data;
      });
    },
    disclosure() {
      url.get("/Api/disclosure", this.keyword).then((response) => {
        console.log(this.keyword);
        this.res = response.data;
      });
    },
  },
};
</script>
