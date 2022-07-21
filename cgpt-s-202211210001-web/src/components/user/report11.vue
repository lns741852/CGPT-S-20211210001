<template>
  <div class="board-center">
    <h3>器械送修紀錄查詢</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="查詢物品">
          <el-select v-model="queryInfo.type" placeholder="請選擇">
            <el-option
              v-for="item in typeList"
              :key="item"
              :label="typeObj[item]"
              :value="item"
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
        <el-table-column prop="udi" label="UDI代號" align="center" />
        <el-table-column prop="username" label="送修人員" align="center" />
        <el-table-column prop="updatetime" label="送修時間" align="center" />
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
        type:"0"
      },
      typeList:["0","1"],
      typeObj:{"0":"器械","1":"器械盒"},
      reportList: [],
    };
  },
  created() {
  },
  methods: {
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

      this.$axios.get("/report/11", this.queryInfo).then((res) => {
        this.reportList = res.data.data;
        console.log( this.reportList)
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
      this.$downloadRequest("/report/11/export", this.queryInfo);
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