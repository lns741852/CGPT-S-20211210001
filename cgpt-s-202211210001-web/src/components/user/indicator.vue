<template>
  <div style="width: 100%">
    <h3>滅菌鍋監測控制</h3>
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
          <el-radio-group v-model="inputIndicator.ispotopen" disabled>
            <el-radio label="Y">開機</el-radio>
            <el-radio label="N">未開機</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="滅菌物品類別：">
          <el-radio-group v-model="inputIndicator.potType" disabled>
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

        <el-form-item label="儀器控制：">
          <el-row>
            <el-col :span="24">
              <el-radio
                v-model="inputIndicator.machanic"
                label="Y"
                @click="machanicClick"
                >機械性監測</el-radio
              >
              <el-radio
                v-model="inputIndicator.machanic"
                label="N"
                @click="indicatorClick"
                >滅菌失敗</el-radio
              >
            </el-col>

            <el-col :span="24" v-show="indicatorShow">
              滅菌失敗原因：
              <el-input
                type="textarea"
                autosize
                placeholder="請輸入内容"
                v-model="inputIndicator.indicatornote"
                style="width: 40%"
              >
              </el-input>
            </el-col>

            <el-col :span="24" style="margin-top: 10px" v-show="machanicShow"
              >進鍋時間：<el-date-picker
                v-model="inputIndicator.machanicPot"
                type="datetime"
                placeholder="選擇日期時間"
              >
              </el-date-picker>
            </el-col>
            <el-col :span="24" style="margin-top: 10px" v-show="machanicShow"
              >出鍋時間：<el-date-picker
                v-model="inputIndicator.machanicOut"
                type="datetime"
                placeholder="選擇日期時間"
              >
              </el-date-picker>
            </el-col>
            <el-col :span="24" style="margin-top: 10px" v-show="machanicShow"
              >滅菌時間：
              <el-date-picker
                v-model="machanicDuration"
                type="datetimerange"
                range-separator="至"
                start-placeholder="開始日期"
                end-placeholder="结束日期"
              >
              </el-date-picker>
            </el-col>
            <el-col :span="8" v-show="machanicShow">
              溫度(℃/℉)：<el-input
                v-model="inputIndicator.machanicTemperature1"
                style="width: 15%; margin-top: 10px"
              />～
              <el-input
                v-model="inputIndicator.machanicTemperature"
                style="width: 15%; margin-top: 10px"
              />
            </el-col>
            <el-col :span="8" v-show="machanicShow">
              壓力(mbar/psig) ：<el-input
                v-model="inputIndicator.machanicPressure1"
                style="width: 15%; margin-top: 10px"
              />～
              <el-input
                v-model="inputIndicator.machanicPressure"
                style="width: 15%; margin-top: 10px"
              />
            </el-col>
          </el-row>
        </el-form-item>

        <el-form-item label="測漏結果 ：">
          <!-- <el-input   placeholder="測漏指數..." style="width:120px; margin-right:10px" disabled></el-input> -->
          <el-radio-group
            v-model="inputIndicator.leakresult"
            :disabled="leakCheckDisabled"
          >
            <el-radio label="Y">通過</el-radio>
            <el-radio label="N">不通過</el-radio>
          </el-radio-group>
          <span style="margin-left: 10px">測漏指數：</span
          ><el-input
            type="text"
            v-model="inputIndicator.leakvalue"
            :disabled="leakValueDisabled"
            style="width: 10%"
          />
        </el-form-item>

        <el-form-item label="抽真空結果 ：">
          <el-radio-group
            v-model="inputIndicator.vacuumresult"
            :disabled="vacuumCheckDisabled"
          >
            <el-radio label="Y">通過</el-radio>
            <el-radio label="N">不通過</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="包外化學指示劑結果 ：">
          <el-radio-group
            v-model="inputIndicator.externalIndicatorResult"
            :disabled="eCheckDisabled"
          >
            <el-radio label="Y">通過</el-radio>
            <el-radio label="N">不通過</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="包內化學指示劑結果 ：">
          <el-radio-group
            v-model="inputIndicator.internalInicatorReslut"
            :disabled="iCheckDisabled"
          >
            <el-radio label="Y">通過</el-radio>
            <el-radio label="N">不通過</el-radio>
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
              >BI生物指示劑 批號：<el-input
                v-model="inputIndicator.rbiBatch"
                disabled
                style="width: 45%; margin-top: 10px"
              />
            </el-col>
            <el-col :span="8" style="margin-top: 10px">
              <el-form-item label="結果 ：">
                <el-radio-group
                  v-model="inputIndicator.rbiResult"
                  :disabled="rbiDisabled"
                >
                  <el-radio label="Y">通過</el-radio>
                  <el-radio label="N">不通過</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              培菌溫度(℃)：<el-input
                v-model="inputIndicator.rbitemperature"
                disabled
                style="width: 48%; margin-top: 10px"
            /></el-col>
            <el-col :span="12">
              培菌時間(小時)：
              <el-select
                v-model="inputIndicator.rbitime"
                disabled
                style="margin-top: 10px"
                placeholder="請選擇..."
              >
              <el-option
                v-for="item in timeList"
                :key="item"
                :label="timeObj[item]"
                :value="item"
              >
              </el-option>
              </el-select>
            </el-col>
            <el-col :span="8"
              >對照組 批號：<el-input
                v-model="inputIndicator.rbiComparisonBatch"
                disabled
                style="width: 50%; margin-top: 10px"
              />
            </el-col>
            <el-col :span="8" style="margin-top: 10px">
              <el-form-item label="結果 ：">
                <el-radio-group
                  v-model="inputIndicator.rbiComparisonResult"
                  :disabled="rbiDisabled"
                >
                  <el-radio label="Y">通過</el-radio>
                  <el-radio label="N">不通過</el-radio>
                </el-radio-group>
              </el-form-item></el-col
            >
          </el-row>
        </el-form-item>
      </el-form>

      <el-button
        class="edit_button"
        @click="submitFrom()"
        :disabled="submitDisabled"
        >確認</el-button
      >
    </el-card>
  </div>
</template>

<script>
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      submitDisabled: true,
      indicatorShow: false,
      machanicShow: true,
      leakvalue: "",
      machanic: "",
      machanicTemperature: "",
      machanicTemperature1: "",
      machanicPressure1: "",
      machanicPressure: "",
      machanicDuration: [],
      machanicOut: "",
      machanicPot: "",
      indicatornote: "",
      rbiDisabled: true,
      externalIndicatorResult: "",
      internalInicatorReslut: "",
      rbiComparisonResult: "",
      rbiResult: "",
      leakValueDisabled: true,
      leakCheckDisabled: true,
      vacuumCheckDisabled: true,
      eCheckDisabled: true,
      iCheckDisabled: true,
      date: new Date(),
      inputIndicator: {},
      userno: "",
      usercname: "",
      depnoList: [],
      depno: "",
      disinfection: "",
      cl: false,
      pot: "",
      potnum: "",
      potList: [],
      checkList: [],
      potDepnoList: [],
      timeList:[1250,2400],
      timeObj:{1250:0.25,2400:0.4},
      efficiency: "",
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
      } else {
        if (this.potnumList.indexOf(0) != -1) {
          this.potnumList.splice(0, 1);
        }
      }
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

      this.inputIndicator.depno = this.depno;
      this.inputIndicator.potname = this.disinfection;
      this.inputIndicator.potno = this.pot;
      this.inputIndicator.potsn = this.potnum;
      this.inputIndicator.potscantime = this.date;
      await this.$axios.post("/indicator", this.inputIndicator).then((res) => {
        if (res.data.data == "") {
          return;
        }
        console.log(res.data.data)
        if (res.data.data.isright == "Y") {
          alert("該滅菌鍋已完成檢測");
          
        }

        this.inputIndicator = res.data.data;
        //滅菌物品類別
        if (this.inputIndicator.firste === "Y") {
          this.efficiency = "A";
        } else if (this.inputIndicator.firstf === "Y") {
          this.efficiency = "B";
        } else if (this.inputIndicator.implant === "Y") {
          this.efficiency = "C";
        } else if (this.inputIndicator.nonimplant === "Y") {
          this.efficiency = "D";
        } else if (this.inputIndicator.gc === "Y") {
          this.efficiency = "E";
        } else if (this.inputIndicator.gc === "Y") {
          this.efficiency = "F";
        } else if (this.inputIndicator.quality === "Y") {
          this.efficiency = "G";
        } else if (this.inputIndicator.quality === "Y") {
          this.efficiency = "H";
        }

        if (this.inputIndicator.machanicDurationStart != "") {
          this.machanicDuration.push(this.inputIndicator.machanicDurationStart);
          this.machanicDuration.push(this.inputIndicator.machanicDurationEnd);
        }

        //測漏結果
        if (this.inputIndicator.leak === "Y") {
          this.inputIndicator.leakresult = "Y";
          this.leakValueDisabled = false;
          this.leakCheckDisabled = false;
        }
        //抽真空結果
        if (this.inputIndicator.vacuum === "Y") {
          this.inputIndicator.vacuumresult = "Y";
          this.vacuumCheckDisabled = false;
        }

        //包外化學指示劑結果
        if (this.inputIndicator.externalIndicator === "Y") {
          this.inputIndicator.externalIndicatorResult = "Y";
          this.eCheckDisabled = false;
        }
        //包內化學指示劑結果
        if (this.inputIndicator.internalInicator === "Y") {
          this.inputIndicator.internalInicatorReslut = "Y";
          this.iCheckDisabled = false;
        }

        //鍋次控制
        if (this.inputIndicator.class5 === "Y") {
          this.cl = true;
        }

        //BI生物指示劑
        if (this.inputIndicator.rbiBatch != "") {
          this.inputIndicator.rbiComparisonResult = "Y";
          this.inputIndicator.rbiResult = "Y";
          this.rbiDisabled = false;
        }

        this.submitDisabled = false;
      });
    },
    machanicClick() {
      this.machanicShow = true;
      this.indicatorShow = false;
    },
    indicatorClick() {
      this.indicatorShow = true;
      this.machanicShow = false;
    },

    //列印標籤
    submitFrom() {
      if (this.machanicDuration.length > 0) {
        this.inputIndicator.machanicDurationStart = this.machanicDuration[0];
        this.inputIndicator.machanicDurationEnd = this.machanicDuration[1];
      }
      this.$axios.post("/indicator/result", this.inputIndicator).then(() => {
        this.inputIndicator = {};
        this.machanicDuration = [];
        this.depno = "";
        this.disinfection = "";
        this.pot = "";
        this.potnum = "";
      });
    },
  },
};
</script>

<style lang="less" scope>
</style>