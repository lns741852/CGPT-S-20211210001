<template>
  <div style="width: 100%">
    <h3>申領作業</h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="來源入庫庫房：">
            <el-select v-model="addForm.depno" placeholder="請選擇">
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
            <el-select v-model="addForm.centerno" placeholder="請選擇">
              <el-option
                v-for="item in costcenterList"
                :key="item.centerno"
                :label="item.centername"
                :value="item.centerno"
              >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="申領單位：">
            <el-select
              v-model="addForm.depnoask"
              placeholder="請選擇"
              @change="getRoomList(addForm.depnoask)"
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

          <el-form-item label="室別：">
            <el-select v-model="addForm.roomno" placeholder="請選擇">
              <el-option
                v-for="item in roomList"
                :key="item.roomno"
                :label="item.roomname"
                :value="item.roomno"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="病歷號：">
            <el-input v-model="addForm.patientno" style="width: 30%" />
          </el-form-item>
          <el-form-item label="負責人員：">
            <el-row gutter="24">
              <el-col :span="9">
                代號 <el-input v-model="addForm.userno" style="width: 60%" />
              </el-col>
              <el-col :span="9">
                姓名 <el-input v-model="addForm.usercname" style="width: 60%" />
              </el-col>
            </el-row>
          </el-form-item>
          
          <el-form-item label="盤包代號：">             
            <el-row gutter="10">
              <el-col :span="8"
                >代號<el-input v-model="addForm.setno"
              /></el-col>
              <el-col :span="8"
                >名稱<el-input v-model="addForm.setnamech"
              /></el-col>
              <el-col :span="8">數量<el-input v-model="addForm.num" /></el-col>
              <el-col class="Edit_setno_font" span="2"></el-col>
            </el-row>
          </el-form-item>
        </el-form>
        <el-button class="edit_button" @click="inputPot()">輸入</el-button>
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
      addForm: {},
      depnoList: [],
      roomList: [],
      costcenterList: [],
    };
  },
  created() {
    this.getDepnoList();
    this.getCostcenterList();
    this.addForm.userno = localStorage.getItem("userno");
    this.addForm.usercname = localStorage.getItem("usercname");
  },
  methods: {
    /**部門查詢 */
    getDepnoList() {
      this.$axios.get("/depno").then((res) => {
        this.total = res.data.data.total;
        this.depnoList = res.data.data.list;
      });
    },
    /**成本中心查詢 */
    getCostcenterList() {
      this.$axios.get("/costcenter").then((res) => {
        this.costcenterList = res.data.data;
      });
    },

    /**病房查詢 */
    getRoomList(depno) {
      this.$axios.get("/depno/room/" + depno).then((res) => {
        this.roomList = res.data.data;
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
      this.barcodeDatas = [];

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
      if (this.depno == "") {
        ElMessage.error("請輸入庫房");
        return;
      }
      if (this.barcodeDatas.length > 0) {
        let ids = "?usercname=" + this.usercname + "&depno=" + this.depno;
        this.barcodeDatas.forEach((item) => {
          ids += "&ids=" + item.barcodeid;
        });
        this.$axios.put("/warehousing" + ids).then(() => {
          this.barcodeDatas = [];
          this.depno = "";
          this.disinfectionDepno = "";
          this.pot = "";
          this.potnum = "";
          this.disinfection = "";
        });
      }
    },
  },
    watch: {
    "addForm.setno": function () {
      if (this.addForm.setno != undefined) {
        if (this.addForm.setno.length === 6) {
          this.addForm.setno = this.addForm.setno.toUpperCase();
          this.$axios.get("/setdata/" + this.addForm.setno).then((res) => {
            if(res.data.data !== null){
              this.addForm.setnamech = res.data.data.setnamech;
            }else{
              this.addForm.setnamech=""              
            }
          });
        }
      }
    },
    }
};
</script>

<style lang="less" scope>
</style>