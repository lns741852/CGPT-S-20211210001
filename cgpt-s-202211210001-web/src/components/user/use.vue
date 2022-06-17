<template>
  <div style="width: 100%">
    <h3>病患使用確認作業</h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="inputDataRef"
          :model="inputData"
          label-width="120px"
          :rules="inputDataRules"
        >
          <el-form-item label="使用單位：" prop="depno">
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

          <el-form-item label="負責人員：">
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
          <el-form-item label="使用病患代號：">
            <el-input
              style="width: 20%"
              v-model="inputData.patientno"
            ></el-input>
          </el-form-item>
          <el-form-item label="交易序號：">
            <el-input style="width: 20%" v-model="inputData.reqno"></el-input>
          </el-form-item>
          <el-form-item label="條碼代號：">
            <el-input style="width: 20%" v-model="inputData.barcode"></el-input>
          </el-form-item>
        </el-form>

        <el-button class="edit_button" @click="inputReq()">查詢</el-button>
      </template>
    </el-card>
  </div>

  <el-card style="margin-top: 10px">
    <el-table :data="dpotList">
      <el-table-column prop="setno" label="盤包代號" width="300px">
      </el-table-column>
      <el-table-column prop="barcode" label="條碼編號" width="300px">
      </el-table-column>
      <el-table-column label="病歷號" width="300px">
        <template #default="scope">
          <el-input v-model="scope.row.patientno" style="width: 50%"></el-input>
        </template>
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
      userno: "",
      usercname: "",
      depnoList: [],
      inputData: {
        depno: "",
        patientno: "",
        reqno: "",
        barcode: "",
      },
      dpotList: [],
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
    async inputReq() {
      if (this.inputData.barcode != "") {
        await this.$axios
          .post("/use/barcode", this.inputData)
          .then((res) => {
            if (res.data.code === 200) {
              this.dpotList = res.data.data;
              return;
            }
          });
      } else {
        if (this.inputData.depno === "") {
          ElMessage.error("請輸入使用單位");
          return true;
        }

        if (this.inputData.patientno === "" && this.inputData.reqno === "") {
          ElMessage.error("請輸入病歷號或交易序號");
          return true;
        }

        await this.$axios.post("/use", this.inputData).then((res) => {
          if (res.data.code === 200) {
            this.dpotList = res.data.data;
            return;
          }
        });
      }
    },
    /**刪除標籤 */
    deleteTag(id) {
      this.dpotList.splice(id, 1);
    },

    //修改
    submitFrom() {
      if (this.dpotList.length > 0) {
        this.$axios.put("/use", this.dpotList).then(() => {
          this.dpotList = [];
          this.inputData = {
            depno: "",
            patientno: "",
            reqno: "",
          };
        });
      }
    },
  },
};
</script>

<style lang="less" scope>
</style>