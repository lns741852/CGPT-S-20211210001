<template>
  <div id="printContent">
    <div style="width: 100%">
      <h3>申領作業通知單</h3>
      <el-card class="box-card">
        <!--表單驗證-->
        <template #default>
          <el-descriptions
            class="margin-top"
            title="申領作業通知單"
            :column="3"
            border
          >
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">申領單位</div>
              </template>
              {{ applyPrint.depnoask }}
            </el-descriptions-item>

            <el-descriptions-item>
              <template #label>
                <div class="cell-item">申領日期</div>
              </template>
              {{ applyPrint.datatime }}
            </el-descriptions-item>

            <el-descriptions-item>
              <template #label>
                <div class="cell-item">申領人員</div>
              </template>
              {{ usercname }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">交易序號</div>
              </template>
              {{ applyPrint.reqno }}
            </el-descriptions-item>

            <el-descriptions-item>
              <template #label>
                <div class="cell-item">物品來源庫房</div>
              </template>
              {{ applyPrint.depno }}
            </el-descriptions-item>

            <el-descriptions-item>
              <template #label>
                <div class="cell-item">病歷號碼</div>
              </template>
              {{ applyPrint.patientno }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">手術個案車代碼</div>
              </template>
              {{ applyPrint.casecarlist }}
            </el-descriptions-item>

            <el-descriptions-item>
              <template #label>
                <div class="cell-item">適用手術名稱</div>
              </template>
              {{ applyPrint.surgicalname }}
            </el-descriptions-item>

            <el-descriptions-item>
              <template #label>
                <div class="cell-item">申領類別</div>
              </template>
              <span v-if="applyPrint.allocatetype == 'G'"> 緊急</span>
              <span v-else-if="applyPrint.allocatetype == 'H'"> 常規</span>
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div class="cell-item">成本中心</div>
              </template>
              {{ applyPrint.centerno }}
            </el-descriptions-item>
          </el-descriptions>
        </template>
      </el-card>
    </div>

    <el-card style="margin-top: 10px">
      <el-table :data="applyPrint.reqdetails">
        <el-table-column prop="setno" label="盤包代號"  width="200"> </el-table-column>
        <el-table-column prop="setname" label="盤包名稱"  width="300"> </el-table-column>
        <el-table-column prop="setcount" label="申領數量"  width="200"> </el-table-column>
      </el-table>
      <el-button
        class="edit_button"
        v-print="Print"
        >確認</el-button
      >
    </el-card>
  </div>
</template>

<script>
// import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      reqId: this.$route.params.id,
      depnoList: [],
      roomList: [],
      costcenterList: [],
      setnoDatas: [],
      applyPrint: {},
      Print: {
        id: "printContent",
        popTitle: "頂部文字",
        standard: "html5",
        closeCallback (vue) { vue.submitFrom() },
      },
    };
  },
  created() {
    this.getReq();
    this.userno = localStorage.getItem("userno");
    this.usercname = localStorage.getItem("usercname");
  },
  methods: {
    getReq() {
      this.$axios.get("/apply/print/" + this.reqId).then((res) => {
        this.applyPrint = res.data.data;

        let date = new Date(res.data.data.datatime);

        const formatDate = (date) => {
          let formatted_date =
            date.getFullYear() +
            "-" +
            (date.getMonth() + 1) +
            "-" +
            date.getDate() +
            " " +
            date.getHours() +
            ":" +
            date.getMinutes() +
            ":" +
            date.getSeconds();
          return formatted_date;
        };

        this.applyPrint.datatime = formatDate(date);
      });
    },
    submitFrom() {
      this.$router.push({ path: "/apply" });
    },
  },
};
</script>

<style lang="less" scope>
</style>