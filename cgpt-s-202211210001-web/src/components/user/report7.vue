<template>
  <div class="board-center">
    <h3>未使用歸還次數統計報表</h3>
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
        <el-form-item label="部門：">
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
        <el-button class="edit_button" @click="getReportList">查詢</el-button>
        <el-button class="edit_button" @click="exportReport"
          >匯出報表</el-button
        >
      </el-form>

      <!--列表-->
      <el-table :data="reportList" style="width: 100%">
        <el-table-column prop="setno" label="包盤代號" align="center" />
        <el-table-column prop="setnamech" label="包盤名稱" align="center" />
        <el-table-column prop="nrcount" label="未歸還數量" align="center" />
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
        depno: "CSR",
      },
      reportList: [],
      depnoList: [],
    };
  },
  created() {
    this.getDepnoList();
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
        ElMessage.error("請輸部門");
        return;
      }

      //去除空資料
      for (let i in this.queryInfo) {
        if (this.queryInfo[i] === "" || this.queryInfo[i] === null) {
          this.queryInfo[i] = null; //速度比delete快
        }
      }

      this.$axios.get("/report/07", this.queryInfo).then((res) => {
        this.reportList = res.data.data;
      });
    },
    /**匯出 */
    exportReport() {
      if (this.queryInfo.depno == "") {
        ElMessage.error("請輸部門");
        return;
      }
      //去除空資料
      for (let i in this.queryInfo) {
        if (this.queryInfo[i] === "" || this.queryInfo[i] === null) {
          this.queryInfo[i] = null; //速度比delete快
        }
      }
      this.$downloadRequest("/report/07/export", this.queryInfo);
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