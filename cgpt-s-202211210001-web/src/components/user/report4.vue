<template>
  <div class="board-center">
    <h3>人員操作盤包統計表</h3>
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
        <el-form-item label="查詢報表">
          <el-select v-model="queryInfo.status" placeholder="請選擇">
            <el-option
              v-for="item in statusList"
              :key="item"
              :label="statusObj[item]"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="人員代號">
          <el-input maxlength="6" v-model="queryInfo.userno" />
        </el-form-item>

        <el-button class="edit_button" @click="getReportList">查詢</el-button>
        <el-button class="edit_button" @click="exportReport"
          >匯出報表</el-button
        >
      </el-form>

      <!--列表-->
      <el-table :data="reportList" style="width: 100%">
        <el-table-column prop="setno" label="盤包代號" align="center" />
        <el-table-column prop="setnamech" label="物品名稱" align="center" />
        <el-table-column prop="total" label="總數" align="center" />
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
import { ElMessage } from "element-plus"
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
        status:"1"
      },
      reportList: [],
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {},
      statusList: ["1","2","3","4","5","6","7"],
      statusObj: {
        1: "製造盤包人員",
        2: "申領盤包人員",
        3: "歸還盤包人員",
        4: "清洗盤包人員",
        5: "配送盤包人員",
        6: "滅菌監測人員",
        7: "滅菌操作人員",
      },
    };
  },
  created() {},
  methods: {
    /**列表查詢 */

    getReportList() {
        if(this.queryInfo.userno == ""){
            ElMessage.error("請輸入員工代號")
            return
        }


      //去除空資料
      for (let i in this.queryInfo) {
        if (this.queryInfo[i] === "" || this.queryInfo[i] === null) {
          this.queryInfo[i] = null; //速度比delete快
        }
      }

      this.$axios.get("/report/04", this.queryInfo).then((res) => {
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
      this.$downloadRequest("/report/04/export", this.queryInfo);
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