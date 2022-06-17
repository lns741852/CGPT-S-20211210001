<template>
  <div id="printContent2" style="width: 100%">
    <h3>補輸通知單-列印 </h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <template #default>
        <el-descriptions
          class="margin-top"
          title="補輸通知單-列印 "
          :column="3"
          border
        >
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">手術室代碼</div>
            </template>
            {{ allocatePrint.roomno }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">配送單位</div>
            </template>
            {{ allocatePrint.depnoask }}
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <div class="cell-item">補包日期</div>
            </template>
            {{ allocatePrint.datatime }}
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <div class="cell-item">補包單位</div>
            </template>
            {{ allocatePrint.depno }}
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <div class="cell-item">補輸人員</div>
            </template>
            {{ usercname }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">交易序號</div>
            </template>
            {{ allocatePrint.reqno }}
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <div class="cell-item">病歷號碼</div>
            </template>
            {{ allocatePrint.patientno }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">申領類別</div>
            </template>
            <span v-if="allocatePrint.allocatetype == 'G'"> 緊急</span>
            <span v-else-if="allocatePrint.allocatetype == 'H'"> 常規</span>
            <span v-else-if="allocatePrint.allocatetype == 'I'"> 瑕疵包補輸</span>
            <span v-else-if="allocatePrint.allocatetype == 'B'"> 補輸</span>
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <div class="cell-item">備註</div>
            </template>
            {{ allocatePrint.note2 }}
          </el-descriptions-item>


        </el-descriptions>
      </template>
    </el-card>

    <el-card style="margin-top: 10px">
      <el-table class="tablePrint" :data="allocatePrint.reqdetails" >
        <el-table-column prop="setno" label="盤包代號" width="100">
        </el-table-column>
        <el-table-column prop="setname" label="盤包名稱" width="180" align="center">
        </el-table-column>
        <el-table-column prop="setcount" label="申領數量" width="100" align="center">
        </el-table-column>
        <el-table-column prop="realcount" label="配送數量" width="100" align="center">
        </el-table-column>
        <el-table-column label="條碼編號" >
          <template #default="scope">
            <li
              class="tableBarcode"
              v-for="item in scope.row.barcodeName"
              :key="item"
            >
              {{ item.barcode }}
            </li>
          </template>
        </el-table-column>
      </el-table>
      <el-button class="edit_button" v-print="Print2">列印</el-button>
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
      setnoDatas: [],
      allocatePrint: {},
      Print2: {
        id: "printContent2",
        popTitle: "頂部文字",
        standard: "html5",
        closeCallback(vue) {
          vue.submitFrom();
        },
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
        this.allocatePrint = res.data.data;
        console.log(this.allocatePrint);
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

        this.allocatePrint.datatime = formatDate(date);
      });
    },
    submitFrom() {
      this.$router.push({ path: "/relocate" });
    },
  },
};
</script>

<style lang="less" scope>
.tableBarcode {
  list-style: none;
}

@page {
  //控制是使用a4還是使用其它紙張規格
  size: auto;
}
</style>