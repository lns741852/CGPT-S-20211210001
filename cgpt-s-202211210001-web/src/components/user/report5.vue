<template>
  <div class="board-center">
    <h3>申領作業確認統計表</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="開始時間">
          <el-date-picker
            v-model="queryInfo.startTime"
            type="date"
            placeholder="選擇日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="結束時間">
          <el-date-picker
            v-model="queryInfo.endTime"
            aria-hidden="true"
            type="date"
            placeholder="選擇日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="庫房：">
          <el-select v-model="queryInfo.depno" clearable placeholder="請選擇">
            <el-option
              v-for="item in depnoList"
              :key="item.depno"
              :label="item.depname"
              :value="item.depno"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申領庫房：">
          <el-select
            v-model="queryInfo.depnoask"
            placeholder="請選擇"
            clearable
          >
            <el-option
              v-for="item in depnoList"
              :key="item.depno"
              :label="item.depname"
              :value="item.depno"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="成本中心：">
          <el-select v-model="queryInfo.centerno" placeholder="請選擇">
            <el-option
              v-for="item in costcenterList"
              :key="item.centerno"
              :label="item.centername"
              :value="item.centerno"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-button class="edit_button" @click="getReportList">查詢</el-button>
        <el-button class="edit_button" @click="exportReport"
          >匯出報表</el-button
        >
      </el-form>

      <!--列表-->
      <el-table :data="reportList" style="width: 100%">
        <el-table-column prop="reqno" label="交易序號" align="center" />
        <el-table-column prop="centername" label="成本中心" align="center" />
        <el-table-column prop="allocatetype" label="申領類型" align="center" />
        <el-table-column prop="datatime" label="申請日期" align="center" />
        <el-table-column prop="setno" label="包盤代號" align="center" />
        <el-table-column prop="setnamech" label="包盤名稱" align="center" />
        <el-table-column prop="setcount" label="請領數" align="center" />
        <el-table-column prop="realcount" label="配送數" align="center" />
      </el-table>
    </el-card>
  </div>
</template>


<script>
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      queryInfo: {
        startTime: new Date(
          new Date(new Date().toLocaleDateString()).getTime()
        ), //0點
        endTime: new Date(
          new Date(new Date().toLocaleDateString()).getTime() +
            24 * 60 * 60 * 1000 -
            1
        ), //23:59點
        userno: "",
        status: "1",
        depno: "CSR",
        depnoask: "",
        centerno: "",
      },
      reportList: [],
      depnoList: [],
      costcenterList: [],
      addDialogVisible: false,
      editDialogVisible: false,
      allocatetype: {
        G: "臨時申領",
        B: "補輸",
        I: "瑕疵補輸",
      },
    };
  },
  created() {
    this.getDepnoList();
    this.getCostcenterList();
  },
  methods: {
    /**列表查詢 */
    getDepnoList() {
      this.$axios.get("/depno").then((res) => {
        this.depnoList = res.data.data.list;
      });
    },
    /**列表查詢 */
    getReportList() {
      if (this.queryInfo.depno == "") {
        ElMessage.error("請輸庫房");
        return;
      }

      //去除空資料
      for (let i in this.queryInfo) {
        if (this.queryInfo[i] === "" || this.queryInfo[i] === null) {
          this.queryInfo[i] = null; //速度比delete快
        }
      }

      this.$axios.get("/report/05", this.queryInfo).then((res) => {
        this.reportList = res.data.data;
        console.log(this.reportList);
        this.reportList.forEach((i) => {
          i.allocatetype = this.allocatetype[i.allocatetype];
        });
      });
    },
    /**匯出 */
    exportReport() {
      if (this.queryInfo.depno == "") {
        ElMessage.error("請輸庫房");
        return;
      }
      //去除空資料
      for (let i in this.queryInfo) {
        if (this.queryInfo[i] === "" || this.queryInfo[i] === null) {
          this.queryInfo[i] = null; //速度比delete快
        }
      }
      this.$downloadRequest("/report/05/export", this.queryInfo);
    },
    /**成本中心查詢 */
    getCostcenterList() {
      this.$axios.get("/costcenter").then((res) => {
        this.costcenterList = res.data.data;
      });
    },
  },
};
</script>

<style lang="less" scope>
div {
  .board-center {
    margin: 0 auto;
    width: 80%;
  }
  .el-link {
    font-size: 20px;
  }
}
.el-calendar-table .el-calendar-day {
  height: 30px;
}
</style>