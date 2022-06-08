<template>
  <div style="width: 100%">
    <h3>申領作業</h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="inputDataRef"
          :model="inputData"
          label-width="120px"
          :rules="inputDataRules"
        >
          <el-form-item label="來源入庫庫房：">
            <el-select v-model="inputData.depno" placeholder="請選擇">
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
            <el-select v-model="inputData.centerno" placeholder="請選擇">
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
              v-model="inputData.depnoask"
              placeholder="請選擇"
              @change="getRoomList(inputData.depnoask)"
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
            <el-select v-model="inputData.roomno" placeholder="請選擇">
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
            <el-input v-model="inputData.patientno" style="width: 30%" />
          </el-form-item>
          <el-form-item label="負責人員：">
            <el-row gutter="24">
              <el-col :span="9">
                代號 <el-input v-model="inputData.empno" style="width: 60%" />
              </el-col>
              <el-col :span="9">
                姓名
                <el-input v-model="inputData.usercname" style="width: 60%" />
              </el-col>
            </el-row>
          </el-form-item>
          <el-divider></el-divider>

          <el-row :gutter="20">
            <el-col :span="3"> </el-col>
            <el-col :span="6"> 代號 </el-col>
            <el-col :span="6"> 名稱 </el-col>
            <el-col :span="6"> 數量 </el-col>
          </el-row>

          <el-row :gutter="20" style="margin-top: 10px">
            <el-col :span="3">個案車/套組：</el-col>
            <el-col :span="6"
              ><el-input v-model="inputData.casecarno"
            /></el-col>
            <el-col :span="6"><el-input /></el-col>
            <el-col :span="6"
              ><el-input v-model="inputData.casecarnum"
            /></el-col>
          </el-row>

          <el-row :gutter="20" style="margin-top: 10px">
            <el-col :span="3">&nbsp;&nbsp;&nbsp;&nbsp;盤包代號：</el-col>
            <el-col :span="6"><el-input v-model="inputData.setno" /></el-col>
            <el-col :span="6"
              ><el-input v-model="inputData.setnamech"
            /></el-col>
            <el-col :span="6"><el-input v-model="inputData.num" /></el-col>
          </el-row>
        </el-form>
        <el-divider></el-divider>
        <el-button class="edit_button" @click="inputSetno()">輸入</el-button>
      </template>
    </el-card>
  </div>

  <el-card style="margin-top: 10px">
    <el-table :data="setnoDatas">
      <el-table-column prop="setno" label="盤包代號">
      </el-table-column>
      <el-table-column prop="setnamech" label="盤包名稱">
      </el-table-column>
      <el-table-column prop="setcount" label="申領數量">
      </el-table-column>
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
      inputData: {
        casecarno: "",
        roomno:"",
        depno:"",
        centerno:"",
        depnoask:"",
        patientno:"",
        casecarlist:""
      },
      depnoList: [],
      roomList: [],
      costcenterList: [],
      setnoDatas: [],
    };
  },
  created() {
    this.getDepnoList();
    this.getCostcenterList();
    this.inputData.empno = localStorage.getItem("userno");
    this.inputData.usercname = localStorage.getItem("usercname");
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
      this.inputData.roomno =""
      this.$axios.get("/depno/room/" + depno).then((res) => {
        this.roomList = res.data.data;
      });
    },

    /**盤包輸入 => 列表 */
    async inputSetno() {
      if (this.inputData.casecarno != "") {
        await this.$axios
          .get("/apply/casecar/" + this.inputData.casecarno)
          .then((res) => {
              if(res.data.data === null){
                  return
              }
              this.inputData.casecarlist += this.inputData.casecarno 
            res.data.data.casecarSetdatas.forEach((item) => {
              if (item != null) {
                  let i = 0;
                this.setnoCheckList = localStorage.getItem("setnoAll").split(",");
                //盤包重複判斷
                this.setnoDatas.some((e) => {
                  if (e.setno === item.csrSetdata3m.setno) {
                    e.setcount =
                      Number(e.setcount) +
                      Number(item.num);
                    i = i + 1;
                  }
                });
                if (i !== 0) {
                  return ;
                }

                //盤包是否正確
                this.setnoCheckList.some((e) => {
                    if (e === item.csrSetdata3m.setno) {
                    this.setnoDatas.push({
                        setno: item.csrSetdata3m.setno,
                        setcount: item.num,
                        setnamech: item.csrSetdata3m.setnamech,
                    });
                    return true;
                    }
                });



              }
            });
          });
      }
        this.inputData.casecarno=""
        this.inputData.casecarnum=""

      //數字是否正確
      if (isNaN(this.inputData.num)) {
        return true;
      }
      let i = 0;
      this.setnoCheckList = localStorage.getItem("setnoAll").split(",");
      //盤包重複判斷
      this.setnoDatas.some((e) => {
        if (e.setno === this.inputData.setno) {
          e.setcount =
            Number(e.setcount) + Number(this.inputData.num);
          i = i + 1;
        }
      });
      if (i !== 0) {
        this.inputData.setno=""
        this.inputData.setnamech=""       
        this.inputData.num=""
        return true;
      }
      //盤包是否正確
      this.setnoCheckList.some((e) => {
        if (e === this.inputData.setno) {
          this.setnoDatas.push({
              setno: this.inputData.setno,
              setcount: this.inputData.num,
              setnamech: this.inputData.setnamech,
          });
          return true;
        }
      });
        this.inputData.setno=""
        this.inputData.setnamech=""       
        this.inputData.num=""
    },

    /**刪除標籤 */
    deleteTag(id) {
      this.setnoDatas.splice(id, 1);
    },

    //申領確認
    submitFrom(){

      if (this.inputData.depno === "") {
        ElMessage.error("請輸入來源庫房");
        return true;
      }

      if (this.inputData.centerno === "") {
        ElMessage.error("請輸入成本中心");
        return true;
      }

      if (this.inputData.depnoask === "") {
        ElMessage.error("請輸入申領單位");
        return true;
      }

      if(this.setnoDatas.length <1){
          ElMessage.error("請輸入申領盤包")
          return;
      }

      this.inputData.reqdetails = this.setnoDatas;
      this.$axios.post("/apply", this.inputData).then((res) => {
        if(res.data.code != 200){
            return
        }
        const id  = res.data.data
        this.$router.push({path: `/apply/print/${id}`});
      });
    },




  },
  watch: {
    "inputData.setno": function () {
      if (this.inputData.setno != undefined) {
        if (this.inputData.setno.length === 6) {
          this.inputData.setno = this.inputData.setno.toUpperCase();
          this.$axios.get("/setdata/" + this.inputData.setno).then((res) => {
            if (res.data.data !== null) {
              this.inputData.setnamech = res.data.data.setnamech;
              this.inputData.num = 1;
            } else {
              this.inputData.setnamech = "";
            }
          });
        }
      }
    },
    "inputData.casecarno": function () {
      if (this.inputData.casecarno != undefined) {
        if (this.inputData.casecarno.length === 6) {
          this.inputData.casecarno = this.inputData.casecarno.toUpperCase();
            this.inputData.casecarnum = 1;
        }
      }
    },

  },
};
</script>

<style lang="less" scope>



</style>