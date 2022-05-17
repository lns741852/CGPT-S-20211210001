<template>
  <div style="width: 100%">
    <h3>入鍋作業</h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="庫房：" prop="depno">
            <el-select
              v-model="depno"
              placeholder="請選擇"
              @change="selectDisinfection(this.depno)"
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
              <el-col :span="12"> 代號 <input v-model="userno" /> </el-col>
              <el-col :span="12"> 姓名 <input v-model="usercname" /> </el-col>
            </el-row>
          </el-form-item>
          <el-button class="edit_button" @click="inputPot()">搜尋</el-button>
        </el-form>
      </template>
    </el-card>

    <el-card style="margin-top: 10px">
      <el-form
        ref="addFormRef"
        :model="addForm2"
        label-width="120px"
        :rules="addFormRules"
      >
        <el-form-item label="滅菌鍋狀態：" prop="ispotopen">
          <el-radio-group v-model="ispotopen" disabled>
            <el-radio label="Y">開機</el-radio>
            <el-radio label="N">未開機</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="滅菌物品類別：" prop="type">
          <el-radio-group v-model="type" disabled>
            <el-radio label="A">AOR </el-radio>
            <el-radio label="B">BOR </el-radio>
            <el-radio label="C">GOR </el-radio>
            <el-radio label="D">CSR </el-radio>
            <el-radio label="E">病房</el-radio>
            <el-radio label="F">布類</el-radio>
            <el-radio label="G">臉盆</el-radio>
            <el-radio label="H">皮消</el-radio>
            <el-radio label="I">刷子</el-radio>
            <el-radio label="J">燈把</el-radio>
            <el-radio label="K">其他 </el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="監測種類 ：" prop="efficiency">
          <el-radio-group v-model="efficiency" disabled>
            <el-radio label="A">效能監測(第一空鍋) </el-radio>
            <el-radio label="B">效能監測(第一滿鍋空鍋) </el-radio>
            <el-radio label="C">常規測試與發放(植入物) </el-radio>
            <el-radio label="D">常規測試與發放(非植入物)</el-radio>
            <el-radio label="E">品管測試(須連續執行『1』次)</el-radio>
            <el-radio label="F">品管測試(須連續執行『3』次) </el-radio>
            <el-radio label="G">滅菌品質確效(定期)</el-radio>
            <el-radio label="H">滅菌品質確效(新品)</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="測漏結果 ：" prop="type">
          <!-- <el-input   placeholder="測漏指數..." style="width:120px; margin-right:10px" disabled></el-input> -->
          <el-radio-group v-model="checkList" disabled>
            <el-radio label="A">通過</el-radio>
            <el-radio label="B">不通過</el-radio>
          </el-radio-group>
          <span style="margin-left: 10px">測漏指數：</span
          ><input type="text" disabled style="width: 10%" />
        </el-form-item>

        <el-form-item label="抽真空結果 ：" prop="type">
          <el-radio-group v-model="checkList" disabled>
            <el-radio label="A">通過</el-radio>
            <el-radio label="B">不通過</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="包外化學指示劑結果 ：" prop="type">
          <el-radio-group v-model="checkList" disabled>
            <el-radio label="A">通過</el-radio>
            <el-radio label="B">不通過</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="包內化學指示劑結果 ：" prop="type">
          <el-radio-group v-model="checkList" disabled>
            <el-radio label="A">通過</el-radio>
            <el-radio label="B">不通過</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="鍋次控制：">
          <el-row>
            <el-col :span="24">
              <el-checkbox v-model="cl" label="2" disabled
                >過程挑戰包 No.2</el-checkbox
              ></el-col
            >
            <el-col :span="8"
              >BI生物指示劑 批號：<input v-model="rbiBatch" disabled />
            </el-col>
            <el-col :span="8">
              <el-form-item label="結果 ：" prop="type">
                <el-radio-group v-model="checkList" disabled>
                  <el-radio label="A">通過</el-radio>
                  <el-radio label="B">不通過</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              培菌溫度(℃)：<input v-model="rbitemperature" disabled
            /></el-col>
            <el-col :span="12">
              培菌時間(小時)：
              <select v-model="rbitime" disabled>
                <option value="">請選擇...</option>
                <option value="1500">0.25</option>
                <option value="2400" selected>0.4</option>
              </select>
            </el-col>
            <el-col :span="8"
              >對照組 批號：<input v-model="rbiComparisonBatch" disabled />
            </el-col>
            <el-col :span="8">
              <el-form-item label="結果 ：" prop="type">
                <el-radio-group v-model="checkList">
                  <el-radio label="A">通過</el-radio>
                  <el-radio label="B">不通過</el-radio>
                </el-radio-group>
              </el-form-item></el-col
            >
          </el-row>
        </el-form-item>
      </el-form>

      <el-button class="edit_button" @click="submitFrom()">確認</el-button>
    </el-card>
  </div>
</template>

<script>
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      queryInfo: {
        searchName: "",
        pageno: "",
        pagesize: "",
      },
      date: new Date(),
      rbitime: "",
      rbitemperature: "",
      bi_no: "",
      comparison: "",
      userno: "",
      usercname: "",
      depnoList: [],
      depno: "",
      disinfection: "",
      rbiComparison: "",
      rbi: "",
      internalInicator: "",
      externalIndicator: "",
      vacuum: "",
      leak: "",
      quality: "",
      class5: "",
      cl: false,
      gc: "",
      nonimplant: "",
      implant: "",
      firstf: "",
      firste: "",
      pot: "",
      potnum: "",
      rbiBatch: "",
      rbiComparisonBatch: "",
      barcode: "",
      potList: [],
      checkList: [],
      potDatas: [],
      potDepnoList: [],
      type: "",
      efficiency: "",
      ispotopen: "Y",
      disinfectionList: [],
      potnumList: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"],
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
      this.$axios.get("/depno", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.depnoList = res.data.data.list;
      });
    },

    /**消毒鍋 */
    getDisinfection() {
      this.$axios.get("/disinfection", this.queryInfo).then((res) => {
        this.disinfectionList = res.data.data.list;
      });
    },
    //部門對應消毒鍋
    selectDisinfection(name) {
      if (name != "") {
        this.$axios.get("/depno_name/" + name).then((res) => {
          this.potDepnoList = res.data.data.csrPotDepno;
        });
      }
    },
    //選擇消毒方式
    selectPot(name) {
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
        this.potnumList.splice(0, 0, "BD");
        this.cl = true;
        this.rbitemperature = "55";
        this.rbitime = "2400";
      } else {
        if (this.potnumList.indexOf("BD") != -1) {
          this.potnumList.splice(0, 1);
          this.rbitemperature = "58";
          this.rbitime = "1500";
        }
      }
      this.checkList.push("C");
      this.checkList.push("D");
    },

    /*滅菌鍋查詢*/
    async inputPot() {
      if (this.depno === "") {
        ElMessage.error("請輸入部門");
        return true;
      }

      if (this.disinfection === "") {
        ElMessage.error("請輸入消毒鍋");
        return true;
      }

      if (this.pot === "") {
        ElMessage.error("請輸入鍋別");
        return true;
      }

      if (this.potnum === "") {
        ElMessage.error("請輸入鍋次");
        return true;
      }
    //   await this.$axios.get("/pot/barcode/" + this.barcode).then((res) => {
    //     if (res.data.code === 200) {
    //       return;
    //     }
    //     this.barcode = "";
    //   });
    },

    //列印標籤
    submitFrom() {
      if (this.potDatas.length > 0) {
        this.$axios.post("/pot", this.potDatas).then(() => {});
      }
    },
  },
};
</script>

<style lang="less" scope>
</style>