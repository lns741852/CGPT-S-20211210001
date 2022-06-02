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
          <el-form-item label="鍋別：" prop="pot" >
            <el-select v-model="pot" placeholder="請選擇" @change="selectPotsn(pot)">
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

          <el-form-item label="滅菌鍋狀態：" prop="ispotopen">
            <el-radio-group v-model="ispotopen">
              <el-radio label="Y">開機</el-radio>
              <el-radio label="N">未開機</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="滅菌物品類別：" prop="type">
            <el-radio-group v-model="type">
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
            <el-radio-group v-model="efficiency">
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
          <el-form-item label="效能監測控制 ：" prop="type">
            <el-checkbox-group v-model="checkList">
              <el-checkbox label="A">測漏</el-checkbox>
              <el-checkbox label="B">抽真空</el-checkbox>
              <el-checkbox label="C">包外化學指示</el-checkbox>
              <el-checkbox label="D">包內化學指示劑</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="鍋次控制：">
            <el-row>
              <el-col :span="24">
                <el-checkbox v-model="cl" label="2"
                  >過程挑戰包 No.2</el-checkbox
                ></el-col
              >
               <el-col :span="24"
              >BI生物指示劑 批號：<el-input v-model="rbiBatch"   style="width:20%;margin-top:10px"/>
            </el-col>
            <el-col :span="9">
              培菌溫度(℃)：<el-input v-model="rbitemperature"  style="width:48%;margin-top:10px"
            /></el-col>
            <el-col :span="12">
              培菌時間(小時)：
              <el-select v-model="rbitime" style="margin-top:10px" placeholder="請選擇...">
                <el-option value="1500" label="0.25"/>
                <el-option value="2400" label="0.4" selected/>
              </el-select>
            </el-col>
            <el-col :span="24"
              >對照組 批號：<el-input v-model="rbiComparisonBatch"  style="width:20%;margin-top:10px" />
            </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="讀取條碼編號：">
            <el-input style="width: 20%" v-model="barcode"></el-input>
          </el-form-item>
        </el-form>

        <el-button class="edit_button" @click="inputTag()">輸入</el-button>
      </template>
    </el-card>
  </div>

  <el-card style="margin-top: 10px">
    <el-table :data="potDatas">
      <el-table-column prop="barcode" label="條碼編號" width="300px">
      </el-table-column>
      <el-table-column prop="potname" label="消毒方式" width="300px">
      </el-table-column>
      <el-table-column prop="potno" label="鍋別" width="300px">
      </el-table-column>
      <el-table-column prop="potsn" label="鍋次" width="300px">
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
      process: "",
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
      this.disinfection =""
      if (name != "") {
        this.$axios.get("/depno_name/" + name).then((res) => {
          this.potDepnoList = res.data.data.csrPotDepno;
        });
      }
    },
    //選擇消毒方式
    selectPot(name) {
      this.pot ="";
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
    selectPotsn(potname){
          this.$axios.get("/pot/potsn/" + potname).then((res) => {
            if(this.disinfection ==="Steam")
            {
              this.potnum = res.data.data+1
            }else{
               this.potnum = res.data.data +2
            }
            
        });
    },
    /**盤包輸入 => 列表 */
    async inputTag() {
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

      if (this.type === "") {
        ElMessage.error("請選擇滅菌物品類別");
        return true;
      }

      //barcode判斷
      if (this.barcode != "") {
        await this.$axios.get("/pot/barcode/" + this.barcode).then((res) => {
          if (res.data.code === 200) {
            return;
          }
          this.barcode = "";
        });
      } else {
        ElMessage.error("請輸入條碼");
      }

      this.potDatas.forEach((item) => {
        if (item.barcode === this.barcode) {
          this.barcode = "";
          ElMessage.error("條碼重複輸入");
        }
      });

      if (this.barcode === "") {
        return;
      }

      //監測種類
      if (this.efficiency === "A") {
        this.firste = "Y";
      } else if (this.efficiency === "B") {
        this.firstf = "Y";
      } else if (this.efficiency === "C") {
        this.implant = "Y";
      } else if (this.efficiency === "D") {
        this.nonimplant = "Y";
      } else if (this.efficiency === "E") {
        this.gc = "GC1";
      } else if (this.efficiency === "F") {
        this.gc = "GC3";
      } else if (this.efficiency === "G") {
        this.quality = "R";
      } else if (this.efficiency === "H") {
        this.quality = "N";
      }

      //滅菌效能控制
      this.checkList.forEach((item) => {
        if (item === "A") {
          this.leak = "Y";
        } else if (item === "B") {
          this.vacuum = "Y";
        } else if (item === "C") {
          this.externalIndicator = "Y";
        } else if (item === "D") {
          this.internalInicator = "Y";
        }
      });

      if (this.rbiBatch != "") {
        this.rbi = "Y";
      }

      if (this.rbiComparisonBatch != "") {
        this.rbiComparison = "Y";
      }

      if (this.cl === true) {
        this.process = "2";
        this.class5 = "Y";
      }
      

      this.potDatas.push({
        leak: this.leak,
        vacuum: this.vacuum,
        externalIndicator: this.externalIndicator,
        internalInicator: this.internalInicator,
        firste: this.firste,
        firstf: this.firstf,
        implant: this.implant,
        nonimplant: this.nonimplant,
        gc: this.gc,
        quality: this.quality,
        rbi: this.rbi,
        rbiComparison: this.rbiComparison,
        depno: this.depno,
        potname: this.disinfection,
        potno: this.pot,
        potsn: this.potnum,
        ispotopen: this.ispotopen,
        datauserno: this.userno,
        datausername: this.usercname,
        barcode: this.barcode,
        potType: this.type,
        process: this.process,
        class5: this.class5,
        rbiBatch: this.rbiBatch,
        rbitemperature: this.rbitemperature,
        rbitime: this.rbitime,
        rbiComparisonBatch: this.rbiComparisonBatch,
      });

       this.barcode =""
    },
    /**刪除標籤 */
    deleteTag(id) {
      this.potDatas.splice(id, 1);
    },


    //列印標籤
    submitFrom() {
      if (this.potDatas.length > 0) {
        this.$axios.post("/pot", this.potDatas).then(() => {
          this.potDatas = [];
          this.depno = "";
          this.disinfection = "";
          this.pot = "";
          this.potnum = "";
          this.ispotopen = "Y";
          this.type = "";
          this.efficiency = "";
          this.checkList = [];
          this.cl = false;
          this.rbiBatch = "";
          this.rbitemperature = "";
          this.rbitime = "";
          this.rbiComparisonBatch = "";
          this.barcode = "";
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