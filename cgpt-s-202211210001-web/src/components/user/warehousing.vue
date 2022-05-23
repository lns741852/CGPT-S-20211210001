<template>
  <div style="width: 100%">
    <h3>入庫作業</h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="入庫庫房：">
            <el-select v-model="depno" placeholder="請選擇">
              <el-option
                v-for="item in depnoList"
                :key="item.depno"
                :label="item.depname"
                :value="item.depno"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="消毒庫房：" prop="depno">
            <el-select
              v-model="disinfectionDepno"
              placeholder="請選擇"
              @change="selectDisinfection(this.disinfectionDepno)"
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
              v-model="disinfection"
              placeholder="請選擇"
              @change="selectPot(disinfection)"
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
            <el-select
              v-model="pot"
              placeholder="請選擇"
              @change="selectPotsn(pot)"
            >
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
            <el-select v-model="potnum" placeholder="請選擇">
              <el-option
                v-for="item in potnumList"
                :key="item"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="消毒時間：">
            <el-date-picker
              type="date"
              placeholder="選擇日期"
              v-model="date"
            ></el-date-picker>
          </el-form-item>

          <el-form-item label="負責人員：">
            <el-row gutter="24">
              <el-col :span="9">
                代號 <el-input v-model="userno" style="width: 60%" />
              </el-col>
              <el-col :span="9">
                姓名 <el-input v-model="usercname" style="width: 60%" />
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <el-button class="edit_button" @click="inputPot()">搜尋</el-button>
      </template>
    </el-card>
  </div>

  <el-card style="margin-top: 10px">
    <el-table :data="barcodeDatas">
      <el-table-column prop="barcode" label="條碼編號" width="300px">
      </el-table-column>
      <el-table-column prop="setno" label="盤包代號"> </el-table-column>
      <el-table-column prop="setsn" label="序號"> </el-table-column>
      <el-table-column prop="status" label="狀態">
        <template #default="scope">
          <el-tag v-if="scope.row.status > 2">已入庫</el-tag>
          <el-tag v-else type="info">未入庫 </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="location" label="位置"> </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button class="delete_button" @click="deleteTag(scope.$index)"
            >刪除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-button class="edit_button" @click="submitFrom()">確認</el-button>
  </el-card>
</template>

<script>
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      inputPotParam: {},
      disinfectionDepno: "",
      userno: "",
      usercname: "",
      depnoList: [],
      depno: "",
      disinfection: "",
      pot: "",
      potnum: "",
      potList: [],
      barcodeDatas: [],
      potDepnoList: [],
      disinfectionList: [],
      potnumList: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"],
      date: new Date(),
    };
  },
  created() {
    this.getDepnoList();
    this.getDisinfection();
    this.userno = localStorage.getItem("userno");
    this.usercname = localStorage.getItem("usercname");
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

    /**消毒鍋 */
    getDisinfection() {
      this.$axios.get("/disinfection").then((res) => {
        this.disinfectionList = res.data.data.list;
      });
    },
    //部門對應消毒鍋
    selectDisinfection(name) {
      this.disinfection = "";
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
      //鍋次
      if (name === "Steam") {
        this.potnumList.splice(0, 0, 0);
        this.cl = true;
        this.rbitemperature = "55";
        this.rbitime = "2400";
      } else {
        if (this.potnumList.indexOf(0) != -1) {
          this.potnumList.splice(0, 1);
          this.rbitemperature = "58";
          this.rbitime = "1500";
        }
      }
      this.checkList.push("C");
      this.checkList.push("D");
    },
    //鍋次查詢
    selectPotsn(potname) {
      this.$axios.get("/pot/potsn/" + potname).then((res) => {
        if (this.disinfection === "Steam") {
          this.potnum = res.data.data;
          if (this.potnum === -1) {
            this.potnum += 1;
          }
        } else {
          this.potnum = res.data.data + 1;
        }
      });
    },
    /**盤包輸入 => 列表 */
    async inputPot() {
      // if (this.disinfectionDepno === "") {
      //   ElMessage.error("請輸入消毒庫房");
      //   return true;
      // }

      // if (this.disinfection === "") {
      //   ElMessage.error("請輸入消毒鍋");
      //   return true;
      // }

      // if (this.pot === "") {
      //   ElMessage.error("請輸入鍋別");
      //   return true;
      // }

      // if (this.potnum === "") {
      //   ElMessage.error("請輸入鍋次");
      //   return true;
      // }
      this.barcodeDatas =[]

      this.inputPotParam.depno = this.disinfectionDepno;
      this.inputPotParam.potname = this.disinfection;
      this.inputPotParam.potno = this.pot;
      this.inputPotParam.potsn = this.potnum;
      this.inputPotParam.potscantime = this.date;
      await this.$axios
        .post("/warehousing/pot", this.inputPotParam)
        .then((res) => {
          if (res.data.data.length > 0) {
            this.barcodeDatas = res.data.data;
          }
        });
    },
    /**刪除標籤 */
    deleteTag(id) {
      this.barcodeDatas.splice(id, 1);
    },

    //入庫
    submitFrom() { 
      if(this.depno ==""){
        ElMessage.error("請輸入庫房");
        return
      }    
      if (this.barcodeDatas.length > 0) {
        let ids ="?usercname="+this.usercname+"&depno=" + this.depno
        this.barcodeDatas.forEach(item =>{
          ids += "&ids=" + item.barcodeid;
        });
        this.$axios.put("/warehousing" + ids).then(() => {
          this.barcodeDatas = [];
          this.depno=""
          this.disinfectionDepno=""
          this.pot=""
          this.potnum=""
          this.disinfection=""
        });
      }
    },
  },
  watch: {
    rbiBatch: function () {
      this.rbiComparisonBatch = this.rbiBatch;
    },
  },
};
</script>

<style lang="less" scope>
</style>