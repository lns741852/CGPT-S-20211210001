<template>
  <div class="board-center">
    <h3>每月過期包一覽表(CSR)</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="開始時間">
          <el-date-picker v-model="queryInfo.start" type="date" placeholder="選擇日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="結束時間">
          <el-date-picker v-model="queryInfo.end" aria-hidden="true" type="date" placeholder="選擇日期">
          </el-date-picker>
        </el-form-item>
        <el-button class="edit_button" @click="getReportList">查詢</el-button>
        <el-button class="edit_button" @click="exportReport">匯出報表</el-button>
      </el-form>

      <!--列表-->
      <el-table :data="reportList" style="width: 100%">
        <el-table-column type="index" label="編號" width="80" />
        <el-table-column prop="setno" label="盤包代號" />
        <el-table-column prop="setnamech" label="物品名稱"  width="300"/>
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
        start: new Date(),
        end: new Date(),
        pageno: "",
        pagesize: "",
      },
      reportList: [],
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {},
      fix: "",
    };
  },
  created() {
  },
  methods: {
    /**列表查詢 */
    getReportList() {
      this.$axios.get("/report/02", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.reportList = res.data.data;
      });
    },
    /**匯出 */
    exportReport() {
        this.$downloadRequest('/report/02/export', this.queryInfo);
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