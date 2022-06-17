<template>
  <div style="width: 100%">
    <h3>申領確認列表</h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="inputDataRef"
          :model="inputData"
          label-width="120px"
          :rules="inputDataRules"
        >
          <el-form-item label="交易序號：">
            <el-input readonly v-model="inputData.reqno" style="width: 30%" />
          </el-form-item>

          <el-form-item label="申領單位：">
            <el-input
              readonly
              v-model="inputData.depnoName.depname"
              style="width: 30%"
            />
          </el-form-item>

          <el-form-item label="室別：">
            <el-input
              readonly
              v-model="inputData.roomName.roomname"
              style="width: 30%"
            />
          </el-form-item>

          <el-form-item label="病歷號：">
            <el-input
              readonly
              v-model="inputData.patientno"
              style="width: 30%"
            />
          </el-form-item>
          <el-form-item label="手術名稱：">
            <el-input
              readonly
              v-model="inputData.surgicalname"
              style="width: 30%"
            />
          </el-form-item>
          <el-form-item label="申領人員：">
            <el-row gutter="24">
              <el-col :span="9">
                代號
                <el-input
                  readonly
                  v-model="inputData.empno"
                  style="width: 60%"
                />
              </el-col>
              <el-col :span="9">
                姓名
                <el-input
                  readonly
                  v-model="inputData.usercname"
                  style="width: 60%"
                />
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="配備人員：">
            <el-row gutter="24">
              <el-col :span="9">
                代號
                <el-input
                  v-model="inputData.datauserno"
                  @blur="getUsername"
                  style="width: 60%"
                />
              </el-col>
              <el-col :span="9">
                姓名
                <el-input v-model="inputData.datausername" style="width: 60%" />
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="申領類別：">
            <span>緊急</span>
          </el-form-item>

          <el-divider></el-divider>
          <el-row :gutter="20">
            <el-col :span="1"> </el-col>
            <el-col :span="5"> 盤包代號 </el-col>
            <el-col :span="5"> 名稱 </el-col>
            <el-col :span="5"> 請領數量 </el-col>
            <el-col :span="5"> 配送數量 </el-col>
          </el-row>

          <div v-for="item in inputData.reqdetails" :key="item">
            <el-row :gutter="20" style="margin-top: 10px">
              <el-col :span="1"></el-col>
              <el-col :span="5"
                ><el-input readonly v-model="item.setno"
              /></el-col>
              <el-col :span="5"
                ><el-input readonly v-model="item.setnoName.setnamech"
              /></el-col>
              <el-col :span="5"
                ><el-input readonly v-model="item.setcount"
              /></el-col>
              <el-col :span="5"><el-input v-model="item.realcount" /></el-col>
            </el-row>
          </div>
          <el-divider></el-divider>
          <el-form-item label="讀取條碼編號：">
            <el-input v-model="barcode" style="width: 30%" />
          </el-form-item>
        </el-form>

        <el-button class="edit_button" @click="inputBarcode()">輸入</el-button>
      </template>
    </el-card>
  </div>

  <el-card style="margin-top: 10px">
    <el-table :data="setnoDatas">
      <el-table-column prop="setno" label="盤包代號"> </el-table-column>
      <el-table-column prop="barcode" label="條碼編號"> </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button class="delete_button" @click="deleteBarcode(scope.$index)"
            >刪除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-button class="edit_button" @click="clickConfirm()">確認</el-button>
  </el-card>
</template>

<script>
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      reqId: this.$route.params.id,
      inputData: {
        depnoName: {},
        roomName: {},
        reqdetails: [],
      },

      barcode: "",
      depnoList: [],
      roomList: [],
      setnoDatas: [],
    };
  },
  created() {
    this.selectReqById();
  },
  methods: {
    selectReqById() {
      this.$axios.get("/allocate2/" + this.reqId).then((res) => {
        this.inputData = res.data.data;
        if (this.inputData.roomName == null) {
          this.inputData.roomName = {
            roomName: "",
          };
        }
        this.inputData.datauserno = this.inputData.empno;
        this.inputData.datausername = this.inputData.usercname;
      });
    },

    /**條碼輸入 => 列表 */
    async inputBarcode() {
      let count = 0;
      let barcode = {};
      //barcode判斷
      if (this.barcode != "") {
        await this.$axios
          .get("/allocate/barcode/" + this.barcode)
          .then((res) => {
            if (res.data.code === 200) {
              barcode = res.data.data;
              return;
            }
            this.barcode = "";
          });
      } else {
        ElMessage.error("請輸入條碼");
      }

      this.setnoDatas.forEach((item) => {
        if (item.barcode === this.barcode) {
          this.barcode = "";
          ElMessage.error("條碼重複輸入");
        }
      });

      if (this.barcode === "") {
        return;
      }

      await this.inputData.reqdetails.forEach((item) => {
        if (item.setno === barcode.setno) {
          item.realcount += 1;
          count += 1;
        }
      });

      if (count === 0) {
        ElMessage.error("條碼錯誤");
        this.barcode = "";
        return;
      }

      this.setnoDatas.push({
        barcodeid: barcode.barcodeid,
        barcode: barcode.barcode,
        setno: barcode.setno,
      });

      this.barcode = "";
    },

    /**刪除標籤 */
    deleteBarcode(id) {
      this.setnoDatas.splice(id, 1);
    },

    //申領確認
    async clickConfirm() {
      let count = 0;
      this.inputData.reqdetails.forEach((item) => {
        if (item.setcount != item.realcount) {
          count += 1;
        }
      });
      if (count != 0) {
        await this.$msgbox
          .confirm("盤包申領數量與配送數量不符，確定要繼續?", {
            cancelButtonText: "取消",
            confirmButtonText: "確定",
            type: "warning",
          })
          .then(() => {
            this.submitForm();
          })
          .catch(() => {
            return;
          });
      } else {
        this.submitForm();
      }
    },

    //申領單確認
    submitForm() {
      this.inputData.allocatetime = null;
      this.inputData.datatime = null;
      this.inputData.deltime = null;

      this.$axios.put("/allocate", this.inputData).then((res) => {
        if (res.data.code === 200) {
          this.submitForm2();
        }
      });
    },
    //Barcode修改
    submitForm2() {
      this.$axios
        .put("/allocate/" + this.inputData.reqId, this.setnoDatas)
        .then((res) => {
          if (res.data.code === 200) {
            this.$router.push({ path: `/allocate3/${this.reqId}` });
          }
        });
    },
    //人員名稱
    getUsername() {
      this.$axios.get("/info/" + this.inputData.datauserno).then((res) => {
        if (res.data.data === null) {
          this.inputData.datausername = "";
          return;
        }
        this.inputData.datausername = res.data.data.usercname;
      });
    },
  },
  watch: {},
};
</script>

<style lang="less" scope>
</style>