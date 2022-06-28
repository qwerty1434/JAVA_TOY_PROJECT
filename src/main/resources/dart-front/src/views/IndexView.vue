<template>
  <div class="about">
    <h1>HelloWorld</h1>

    <button @click="SearchAll">DB 모든유저 불러오기</button>
    {{ user }}
    <br />
    <br />
    <br />
    <div>
      <h3>공시검색</h3>
      <input type="text" v-model="Disclosure_corp_name" />

      <button @click="Disclosure">전송</button>
    </div>
    {{ DisclosureRes }}

    <div>
      <h3>단일회사 전체 재무제표</h3>
      기업이름: <input type="text" v-model="Balance_corp_name" />
      <br />
      회계년도:
      <select v-model="BalanceYear">
        <option disabled value="">사업연도</option>
        <option>2015</option>
        <option>2016</option>
        <option>2017</option>
        <option>2018</option>
        <option>2019</option>
        <option>2020</option>
        <option>2021</option>
        <option>2022</option>
      </select>

      <br />
      보고서 코드:
      <select v-model="BalanceType">
        <option disabled value="">보고서 종류</option>
        <option value="11013">1분기 보고서</option>
        <option value="11012">반기 보고서</option>
        <option value="11014">3분기 보고서</option>
        <option value="11011">사업 보고서</option>
      </select>

      <br />
      연결,일반:
      <select v-model="BalanceConn">
        <option disabled value="">연결,일반</option>
        <option value="CFS">연결</option>
        <option value="OFS">일반</option>
      </select>

      <br />
      <button @click="BalanceSheet">전송</button>
    </div>
    {{ BalanceRes }}
    <div>
      <h3>지분공시 종합정보</h3>
      <input type="text" v-model="ShareDisclosure_corp_name" />

      <button @click="ShareDisclosure">전송</button>
    </div>
    {{ ShareDisclosureRes }}
    <div>
      <h3>주요사항 보고서 종합정보</h3>
      <input type="text" v-model="KeyPointsReport_corp_name" />
      <br />
      <date-picker
        inline
        :editable="false"
        valueType="format"
        format="YYYYMMDD"
        v-model="KeyPointsBgnYr"
      />
      <date-picker
        inline
        :editable="false"
        valueType="format"
        format="YYYYMMDD"
        v-model="KeyPointsEndYr"
      />
      <button @click="KeyPointsReport">전송</button>
    </div>
    {{ KeyPointsReportRes }}
  </div>
</template>
<script>
import url from "@/url/http";
import DatePicker from "vue2-datepicker";
import "vue2-datepicker/index.css";

export default {
  components: { DatePicker },
  data() {
    return {
      Disclosure_corp_name: "삼성전자",
      DisclosureRes: "검색어를 입력해 주세요",
      user: null,
      Balance_corp_name: "카카오",
      BalanceRes: "검색어를 입력해 주세요",
      BalanceYear: "2022",
      BalanceType: "11011",
      BalanceConn: "OFS",
      ShareDisclosure_corp_name: "삼성전자",
      ShareDisclosureRes: "",
      KeyPointsReport_corp_name: "삼성전자",
      KeyPointsReportRes: "",
      KeyPointsBgnYr: "20220101",
      KeyPointsEndYr: "20220628",
    };
  },
  methods: {
    SearchAll() {
      url.get("/User/SearchAll", {}).then((response) => {
        console.log(response);
        this.user = response.data;
      });
    },
    Disclosure() {
      url
        .get("/Api/Disclosure", {
          params: { corp_name: this.Disclosure_corp_name },
        })
        .then((response) => {
          this.DisclosureRes = response.data;
        });
    },
    BalanceSheet() {
      url
        .get("/Api/BalanceSheet", {
          params: {
            corp_name: this.Balance_corp_name,
            bsns_year: this.BalanceYear,
            reprt_code: this.BalanceType,
            fs_div: this.BalanceConn,
          },
        })
        .then((response) => {
          this.BalanceRes = response.data;
        });
    },
    ShareDisclosure() {
      url
        .get("/Api/ShareDisclosure", {
          params: { corp_name: this.ShareDisclosure_corp_name },
        })
        .then((response) => {
          this.ShareDisclosureRes = response.data;
        });
    },
    KeyPointsReport() {
      url
        .get("/Api/KeyPointsReport", {
          params: {
            corp_name: this.KeyPointsReport_corp_name,
            bgn_de: this.KeyPointsBgnYr,
            end_de: this.KeyPointsEndYr,
          },
        })
        .then((response) => {
          this.KeyPointsReportRes = response.data;
        });
    },
  },
};
</script>
