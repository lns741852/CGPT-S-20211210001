<template>
  <div style="width: 100%">
    <h3>補輸作業</h3>
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

          <el-form-item label="補輸單位：">
            <el-row gutter="24">
              <el-col :span="9">
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
              </el-col>
              <el-col :span="10">
                <el-form-item label="轉入室別：" prop="depno">
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
              </el-col>
            </el-row>
          </el-form-item>

          <el-form-item>
            <el-radio-group v-model="inputData.allocatetype">
              <el-radio label="B">一般補輸</el-radio>
              <el-radio label="I">瑕疵補輸</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="病歷號：">
            <el-input v-model="inputData.patientno" style="width: 20%" />
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

          <el-form-item label="備註：">
            <el-row gutter="24">
              <el-col :span="18">
                補輸原因：
                <el-select
                  @change="inputCauce(inputData.cause)"
                  v-model="inputData.cause"
                  placeholder="請選擇"
                >
                  <el-option
                    v-for="item in causeList"
                    :key="item"
                    :label="item"
                    :value="item"
                  >
                  </el-option>
                </el-select>
              </el-col>

              <el-col :span="18" style="margin-top: 10px">
                <el-input
                  type="textarea"
                  :rows="4"
                  placeholder="請輸入補輸原因.."
                  v-model="inputData.note2"
                  style="width: 60%"
                >
                </el-input>
              </el-col>
            </el-row>
          </el-form-item>

          <el-form-item label="讀取條碼編號：">
            <el-input style="width: 20%" v-model="inputData.barcode"></el-input>
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
    <el-button class="edit_button" @click="submitFrom()">確認</el-button>
  </el-card>
</template>

<script>
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      inputData: {
        roomno: "",
        depno: "",
        depnoask: "",
        patientno: "",
        barcode: "",
        note2: "",
        allocatetype: "B",
      },
      causeList: [
        "瑕疵-配",
        "保養-配",
        "過期-配",
        "異物-配",
        "功能不良-配",
        "內容不符-配",
        "發出數量不符",
        "發錯包-配",
        "潮濕-滅",
        "指示帶變色不全-滅",
        "配送標籤未回-汙",
        "業務調帳",
      ],
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
      this.inputData.roomno = "";
      this.$axios.get("/depno/room/" + depno).then((res) => {
        this.roomList = res.data.data;
      });
    },

    /**條碼輸入 => 列表 */
    async inputBarcode() {
      let barcode = {};
      //barcode判斷
      if (this.inputData.barcode != "") {
        await this.$axios
          .get("/allocate/barcode/" + this.inputData.barcode)
          .then((res) => {
            if (res.data.code === 200) {
              barcode = res.data.data;
              return;
            }
            this.inputData.barcode = "";
          });
      } else {
        ElMessage.error("請輸入條碼");
      }

      this.setnoDatas.forEach((item) => {
        if (item.barcode === this.inputData.barcode) {
          this.inputData.barcode = "";
          ElMessage.error("條碼重複輸入");
        }
      });

      if (this.inputData.barcode === "") {
        return;
      }

      this.setnoDatas.push({
        barcodeid: barcode.barcodeid,
        barcode: barcode.barcode,
        setno: barcode.setno,
      });

      this.inputData.barcode = "";
    },

    /**刪除標籤 */
    deleteTag(id) {
      this.setnoDatas.splice(id, 1);
    },

    //補輸確認
    submitFrom() {
      if (this.inputData.depno === "") {
        ElMessage.error("請輸入來源庫房");
        return true;
      }

      if (this.inputData.depnoask === "") {
        ElMessage.error("請輸入補輸單位");
        return true;
      }

      if (this.setnoDatas.length < 1) {
        ElMessage.error("請輸入補輸盤包");
        return;
      }

      this.inputData.reqdetails = this.setnoDatas;
      this.$axios.post("/apply/relocate", this.inputData).then((res) => {
        if (res.data.code === 200) {
           this.submitForm2(res.data.data);
        }
      });
    },
    //Barcode修改
    submitForm2(reqId) {
      this.$axios
        .put("/allocate/" + reqId, this.setnoDatas)
        .then((res) => {
          if (res.data.code === 200) {
            this.$router.push({ path: `/relocate2/${reqId}` });
          }
        });
    },

    //補輸原因
    inputCauce(item) {
      if (this.inputData.note2.length > 0) {
        this.inputData.note2 += "," + item;
      } else {
        this.inputData.note2 += item;
      }
    },
  },
};
</script>

<style lang="less" scope>
</style>