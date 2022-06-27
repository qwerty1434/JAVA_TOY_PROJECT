<template>
  <div class="about">
    <h1>HelloWorld</h1>

    <button @click="searchAll">DB 모든유저 불러오기</button>
    {{ user }}
    <br />
    <br />
    <br />
    <div>
      <h3>공시검색</h3>
      <input type="text" v-model="Disclosure_corp_name" />

      <button @click="disclosure">전송</button>
    </div>
    {{ DisclosureRes }}

    <div>
      <h3>단일회사 전체 재무제표</h3>
      <input type="text" v-model="Balance_corp_name" />

      <button @click="BalanceSheet">전송</button>
    </div>
    {{ BalanceRes }}
  </div>
</template>
<script>
import url from "@/url/http";

export default {
  data() {
    return {
      Disclosure_corp_name: "삼성전자",
      Balance_corp_name: "카카오",
      user: null,
      DisclosureRes: "검색어를 입력해 주세요",
      BalanceRes: "검색어를 입력해 주세요",
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
      url
        .get("/Api/disclosure", {
          params: { corp_name: this.Disclosure_corp_name },
        })
        .then((response) => {
          this.DisclosureRes = response.data;
        });
    },
    BalanceSheet() {
      url
        .get("/Api/BalanceSheet", {
          params: { corp_name: this.Balance_corp_name },
        })
        .then((response) => {
          this.BalanceRes = response.data;
        });
    },
  },
};
</script>
