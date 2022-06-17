<template>
  <div style="width: 100%">
    <h3>物品歸還</h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="inputDataRef"
          :model="inputData"
          label-width="120px"
          :rules="inputDataRules"
        >
          <el-form-item label="歸還部門：" prop="depno">
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

          <el-form-item label="去汙鍋別：" prop="pot">
            <el-select v-model="pot" placeholder="請選擇">
              <el-option
                v-for="item in potList"
                :key="item"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="去汙鍋次：" prop="potnum">
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

          <el-form-item label="回收人員：">
            <el-row gutter="24">
              <el-col :span="9">
                代號 <el-input v-model="userno" style="width: 60%" />
              </el-col>
              <el-col :span="9">
                姓名
                <el-input v-model="usercname" style="width: 60%" />
              </el-col>
            </el-row>
          </el-form-item>

          <el-form-item label="清洗人員：">
            <el-row gutter="24">
              <el-col :span="9">
                代號 <el-input v-model="userno2" @blur="getUsername" style="width: 60%" />
              </el-col>
              <el-col :span="9">
                姓名
                <el-input v-model="usercname2" style="width: 60%" />
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="交易序號：">
            <el-input style="width: 20%" v-model="inputData.reqno"></el-input>
          </el-form-item>
          <el-form-item label="條碼代號：">
            <el-input style="width: 20%" v-model="inputData.barcode"></el-input>
          </el-form-item>
        </el-form>

        <el-button class="edit_button" @click="inputReceive()">查詢</el-button>
      </template>
    </el-card>
  </div>

  <el-card style="margin-top: 10px">
    <el-table :data="dpotList">
      <el-table-column prop="setno" label="盤包代號"> </el-table-column>
      <el-table-column prop="barcode" label="條碼編號"> </el-table-column>
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
      userno: "",
      userno2: "",
      usercname: "",
      usercname2: "",
      pot: "",
      potnum: "",
      inputData: {
        depno: "",
        reqno: "",
        barcode: "",
      },
      depnoList: [],
      dpotList: [],
      potList: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "B"],
      potnumList: [
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
      ],
    };
  },
  created() {
    this.getDepnoList();
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
    /**盤包輸入 => 列表 */
    async inputReceive() {
      if (this.inputData.depno === "") {
        ElMessage.error("請輸入歸還部門");
        return true;
      }

      if (this.inputData.barcode === "" && this.inputData.reqno === "") {
        ElMessage.error("請輸入條碼編號或交易序號");
        return true;
      }

      if (this.inputData.barcode != "") {
        await this.$axios.get("/reprint", this.inputData).then((res) => {
          let count = 0;
          if (res.data.code === 200) {
            if (res.data.data.list.length != 0) {
              res.data.data.list.forEach((obj) => {
                if (this.dpotList.length != 0) {
                  this.dpotList.forEach((item) => {
                    if (item.barcode === obj.barcode) {
                      ElMessage.error("條碼代號重複");
                      count = 1;
                      return;
                    }
                  });

                  if (count === 1) {
                    return;
                  }
                  this.dpotList.push(obj);
                } else {
                  this.dpotList.push(obj);
                }
              });
            }
          }
        });
      } else {
        await this.$axios.post("/receive/req", this.inputData).then((res) => {
          let count = 0;
          if (res.data.code === 200) {
            res.data.data.forEach((obj) => {
              if (this.dpotList.length != 0) {
                this.dpotList.forEach((obj) => {
                  if (obj.barcode === obj.barcode) {
                    count = 1;
                    return;
                  }
                });
                if (count === 1) {
                  return;
                }
                this.dpotList.push(obj);
              } else {
                this.dpotList = res.data.data;
              }
            });
          }
        });
      }
    },
    /**刪除標籤 */
    deleteTag(id) {
      this.dpotList.splice(id, 1);
    },

    //列印標籤
    submitFrom() {
      if (this.dpotList.length > 0) {
        this.dpotList.forEach((item) => {
          item.location = this.depno;
          item.dpotsn = this.potnum;
          item.dpotname = this.pot;
          item.receive ='Y'
          item.receiveuserno = this.userno;
          item.receiveusername = this.usercname;
          item.clearuserno = this.userno2;
          item.clearusername = this.usercname2;
        });

        this.$axios.put("/receive", this.dpotList).then(() => {
          this.dpotList = [];
          this.inputData = {
            depno: "",
            reqno: "",
            barcode:"",
          };
        });
      }
    },
    getUsername(){
        this.$axios.get("/info/"+ this.userno2).then((res) => {
            if(res.data.data ===null){
                this.usercname2=""
                return
            }
             this.usercname2=res.data.data.usercname;     
      });
    }
  },
};
</script>

<style lang="less" scope>
</style>