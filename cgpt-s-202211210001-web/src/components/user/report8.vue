<template>
  <div class="board-center">
    <h3>滅菌鍋操作及測試紀錄報表</h3>
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
          <el-select
            v-model="queryInfo.depno"
            clearable
            placeholder="請選擇"
            @change="selectDisinfection(queryInfo.depno)"
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
        <el-form-item label="消毒鍋：" prop="disinfection">
          <el-select
            v-model="queryInfo.disinfection"
            placeholder="請選擇"
            clearable
            @change="selectPot(queryInfo.disinfection)"
          >
            <el-option
              v-for="item in disinfectionList"
              :key="item.disinfection"
              :label="item.potname"
              :value="item.disinfection"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="鍋別：" prop="pot">
          <el-select v-model="queryInfo.pot" placeholder="請選擇" clearable>
            <el-option
              v-for="item in potList"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="鍋次：" prop="potnum">
          <el-select v-model="queryInfo.potnum" clearable placeholder="請選擇">
            <el-option
              v-for="item in potnumList"
              :key="item"
              :label="item"
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
        <el-table-column prop="potno" label="鍋別" align="center" />
        <el-table-column prop="potsn" label="鍋次" align="center" />
        <el-table-column prop="num" label="物品數量" align="center" />
        <el-table-column
          prop="machanicDurationStart"
          label="進鍋時間"
          align="center"
        />
        <el-table-column
          prop="machanicDurationEnd"
          label="出鍋時間"
          align="center"
        />
        <el-table-column label="滅菌溫度" align="center">
          <template #default="scope">
            {{ scope.row.machanicTemperature1 }}~{{ scope.row.machanicTemperature }}
          </template>
        </el-table-column>
        <el-table-column label="滅菌壓力" align="center">
          <template #default="scope">
            {{ scope.row.machanicPressure1 }}~{{scope.row.machanicPressure}}
          </template>
        </el-table-column>
        <el-table-column prop="leakvalue" label="測漏指數" align="center" />
        <el-table-column prop="vacuumResult" label="抽中空" align="center" />
        <el-table-column prop="indicatornote" label="失敗原因" align="center" />
        <el-table-column
          prop="externalIndicatorResult"
          label="包外化學指示劑"
          align="center"
        />
        <el-table-column
          prop="internalInicatorResult"
          label="包內化學指示劑"
          align="center"
        />
        <el-table-column
          prop="class5Result"
          label="第四第五化學指示劑"
          align="center"
        />
        <el-table-column prop="rbiResult" label="生物指示劑" align="center" />
        <el-table-column prop="implant" label="植入物" align="center" />
        <el-table-column prop="potType" label="滅菌類別" align="center" />
        <el-table-column prop="indiusername" label="記錄人員" align="center" />
        <el-table-column prop="rbitime" label="滅菌時間(小時)" align="center" />
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
        disinfection: "",
        pot: "",
        potnum: "",
      },
      reportList: [],
      depnoList: [],
      potList: [],
      potDepnoList: [],
      disinfectionList: [],
      potnumList: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"],
    };
  },
  created() {
    this.getDepnoList();
    this.getDisinfection();
    this.selectDisinfection(this.queryInfo.depno);
  },
  methods: {
    /**列表查詢 */
    getDepnoList() {
      this.$axios.get("/depno").then((res) => {
        this.depnoList = res.data.data.list;
      });
    },

    /**消毒鍋 */
    getDisinfection() {
      this.$axios.get("/disinfection").then((res) => {
        this.disinfectionList = res.data.data.list;
      });
    },

    //部門對應消毒鍋
    selectDisinfection(name) {
      this.queryInfo.disinfection = "";
      if (name != "") {
        this.$axios.get("/depno_name/" + name).then((res) => {
          this.potDepnoList = res.data.data.csrPotDepno;
        });
      }
    },
    //選擇消毒方式
    selectPot(name) {
      this.pot = "";
      this.potList = [];
      this.checkList = [];
      this.potDepnoList.forEach((item) => {
        //鍋別修改
        if (item.disinfection === name) {
          for (let i = item.beginnum; i <= item.potnum; i++) {
            this.potList.push(item.head + i);
          }
        }
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

      this.$axios.get("/report/08", this.queryInfo).then((res) => {
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
      this.$downloadRequest("/report/08/export", this.queryInfo);
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