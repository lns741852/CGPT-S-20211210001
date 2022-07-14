<template>
  <div style="width: 100%">
    <h3>交易查詢</h3>
    <el-card class="box-card">
      <template #default>
        <el-form
          ref="inputData"
          :model="inputData"
          label-width="120px"
          :rules="inputDataRules"
        >
          <el-form-item label="庫房：">
            <el-select v-model="inputData.depno" clearable placeholder="請選擇">
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
              v-model="inputData.depnoask"
              placeholder="請選擇"
              @change="getRoomList(inputData.depnoask)"
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
          <el-form-item label="室別：" prop="depno">
            <el-select
              v-model="inputData.roomno"
              clearable
              placeholder="請選擇"
            >
              <el-option
                v-for="item in roomList"
                :key="item.roomno"
                :label="item.roomname"
                :value="item.roomno"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="成本中心：">
            <el-select
              v-model="inputData.centerno"
              clearable
              placeholder="請選擇"
            >
              <el-option
                v-for="item in costcenterList"
                :key="item.centerno"
                :label="item.centername"
                :value="item.centerno"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="盤包代號：" prop="setno">
            <el-input style="width: 20%" v-model="inputData.setno" />
          </el-form-item>
          <el-form-item label="交易序號：">
            <el-input style="width: 20%" v-model="inputData.reqno" />
          </el-form-item>
          <el-form-item label="查詢日期：">
            <el-date-picker
              type="date"
              placeholder="選擇日期"
              v-model="inputData.datatime"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="狀態：">
            <el-select
              v-model="inputData.status"
              clearable
              placeholder="請選擇"
            >
              <el-option
                v-for="(item, index) in status"
                :key="index"
                :label="item"
                :value="index"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="類型：">
            <el-select
              v-model="inputData.allocatetype"
              clearable
              placeholder="請選擇"
            >
              <el-option
                v-for="(item, index) in types"
                :key="index"
                :label="item"
                :value="index"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-button class="edit_button" @click="searchReq()">搜尋</el-button>
        </el-form>
      </template>
    </el-card>
  </div>

  <el-card style="margin-top: 10px">
    <el-table :data="reqList">
      <el-table-column prop="allocatetype" label="類別" >
      </el-table-column>
      <el-table-column prop="datatime" label="申領日期"> </el-table-column>
      <el-table-column prop="allocatetime" label="配送日期"> </el-table-column>
      <el-table-column prop="reqno" label="交易序號"> 
       <template #default="scope">
          <el-link type="primary" :href="'/#/search2_3/' + scope.row.reqId">{{
            scope.row.reqno
          }}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="depno" label="庫房"> </el-table-column>
      <el-table-column prop="roomno" label="申請單位"></el-table-column>
    </el-table>
    <el-button class="edit_button" @click="submitFrom()">確認</el-button>
  </el-card>
</template>

<script>
// import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      inputData: {
        depno: null,
        roomno: null,
        depnoask: null,
        status: null,
        allocatetype: null,
        centerno: null,
        datatime: new Date(),
        setno: null,
        reqno: null,
        checking: null,
        useUp: null,
      },
      roomList: [],
      costcenterList: [],
      depnoList: [],
      status: {
        0: "未配送",
        1: "已配送",
        2: "已使用",
      },
      types: {
        0: "臨時申領",
        1: "補輸",
        2: "瑕疵補輸",
      },
      allocatetype:{
        G:"臨時申領",
        B:"補輸",
        I:"瑕疵補輸"
      },


      reqList:[]
    };
  },
  created() {
    this.getDepnoList();
    this.getCostcenterList();
  },
  mounted() {},
  methods: {
    /**列表查詢 */
    getDepnoList() {
      this.$axios.get("/depno").then((res) => {
        this.total = res.data.data.total;
        this.depnoList = res.data.data.list;
      });
    },

    /**病房查詢 */
    getRoomList(depno) {
      this.inputData.roomno = "";
      this.$axios.get("/depno/room/" + depno).then((res) => {
        this.roomList = res.data.data;
      });
    },

    /**成本中心查詢 */
    getCostcenterList() {
      this.$axios.get("/costcenter").then((res) => {
        this.costcenterList = res.data.data;
      });
    },

    /**盤包輸入 => 列表 */
    async searchReq() {
      if (this.inputData.status != null) {
        switch (this.inputData.status) {
          case "0":
            this.inputData.checking = "N";
            break;
          case "1":
            this.inputData.checking = "Y";
            break;
          case "2":
            this.inputData.useUp = "Y";
        }
      }
      await this.$axios.post("/search/req", this.inputData).then((res) => {
        this.reqList =res.data.data
        this.reqList.forEach(i =>{
        if (i.datatime != "" && i.datatime != null){
          i.datatime =i.datatime.substring(0, 19).replace("T", " ");
        }
        if(i.allocatetime.substring(0,1) ==="+"){
          i.allocatetime=""
        }
        if (i.allocatetime != "" && i.allocatetime != null){
          i.allocatetime =i.allocatetime.substring(0, 19).replace("T", " ");
        }
        if(i.roomno === "All"){
          i.roomno =""
        }
        i.allocatetype = this.allocatetype[i.allocatetype]
        })       
      });
    },
    /**刪除標籤 */
    deleteTag(id) {
      this.barcodeDatas.splice(id, 1);
    },
  },
  watch: {
    "inputData.setno": function () {
      if (this.inputData.setno != undefined) {
        if (this.inputData.setno.length === 6) {
          this.inputData.setno = this.inputData.setno.toUpperCase();
        }
      }
    },
  },
};
</script>

<style lang="less" scope>
</style>