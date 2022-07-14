<template>
  <div class="board-center">
    <h3>每月製作盤包種類及數目分佈圖</h3>
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
        <el-form-item label="盤包代號">
          <el-input maxlength="6" v-model="queryInfo.setno" />
        </el-form-item>

        <el-button class="edit_button" @click="getReportList">查詢</el-button>
        <el-button class="edit_button" @click="exportReport"
          >匯出報表</el-button
        >
      </el-form>

      <!--列表-->
      <el-table :data="reportList" style="width: 100%">
        <el-table-column prop="setno" label="盤包代號" />
        <el-table-column prop="setnamech" label="物品名稱" width="300" />
        <el-table-column prop="numbaseCsr" label="CSR" />
        <el-table-column prop="numbaseCsr2" label="CSR2" />
        <el-table-column prop="numbaseOr1" label="AOR" />
        <el-table-column prop="numbaseOr2" label="BOR" />
        <el-table-column prop="numbaseOr3" label="GOR" />
        <el-table-column prop="numbaseWr1" label="WR" />
        <el-table-column prop="total" label="總數" />
      </el-table>
      <!--分頁-->
      <el-pagination
        :current-page="queryInfo.pageno"
        :page-size="queryInfo.pagesize"
        layout="total, prev, pager, next, jumper"
        :total="total"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </el-card>
  </div>
</template>


<script>
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
        setno: "",
      },
      reportList: [],
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {},
      fix: "",
    };
  },
  created() {},
  methods: {
    /**列表查詢 */

    getReportList() {
      //去除空資料
      for (let i in this.queryInfo) {
        if (this.queryInfo[i] === "" || this.queryInfo[i] === null) {
          this.queryInfo[i] = null; //速度比delete快
        }
      }

      this.$axios.get("/report/03", this.queryInfo).then((res) => {
        this.reportList = res.data.data;
      });
    },
    /**匯出 */
    exportReport() {
      //去除空資料
      for (let i in this.queryInfo) {
        if (this.queryInfo[i] === "" || this.queryInfo[i] === null) {
          this.queryInfo[i] = null; //速度比delete快
        }
      }
      this.$downloadRequest("/report/03/export", this.queryInfo);
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