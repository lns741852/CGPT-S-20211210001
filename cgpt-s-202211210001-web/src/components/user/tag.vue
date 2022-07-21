<template>
  <div style="width: 100%">
    <h3>盤包製作</h3>
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
              v-model="inputData.depno"
              placeholder="請選擇"
              @change="selectDisinfection(this.inputData.depno)"
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
          <el-form-item label="盤包代號：">
            <el-form :rules="addFormRules">
              <el-row gutter="24">
                <el-col :span="5">
                  <el-input v-model="inputData.setno" placeholder="盤包代號" />
                </el-col>
                <el-col :span="5">
                  <el-input
                    v-model="inputData.setnamech"
                    placeholder="盤包名稱"
                  />
                </el-col>
                <el-col :span="3"
                  ><el-input v-model="inputData.sn" placeholder="序號" disabled
                /></el-col>
                <el-col :span="3"
                  ><el-input v-model="inputData.num" placeholder="數量"
                /></el-col>
                <el-col class="Edit_setno_font" :span="3">
                  <router-link to="/setno_search" target="_blank"
                    >查詢盤包代號</router-link
                  >
                </el-col>
                <el-col class="Edit_setno_font" :span="3">
                  <el-button
                    class="edit_button"
                    @click="addDialogVisible = true"
                    >查詢已建立盤包</el-button
                  >
                </el-col>
              </el-row>
            </el-form>
          </el-form-item>
          <el-form-item label="消毒鍋：" prop="disinfection">
            <el-select v-model="inputData.disinfection" placeholder="請選擇">
              <el-option
                v-for="item in disinfectionList"
                :key="item.disinfection"
                :label="item.potname"
                :value="item.disinfection"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="包裝方式：" prop="package">
            <el-select
              v-model="inputData.package"
              placeholder="請選擇"
              :disabled="!udiDisabled"
            >
              <el-option
                v-for="item in packageList"
                :key="item.processno"
                :label="item.processname"
                :value="item.processno"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="處理方式：" prop="type" @click="selectType">
            <el-radio-group v-model="type">
              <el-radio label="A">器械盒</el-radio>
              <el-radio label="B">器械包</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="器械盒UDI：" prop="box">
            <el-input
              style="width: 20%"
              v-model="inputData.box"
              :disabled="udiDisabled"
            />
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

          <el-form-item label="隔日消毒：" prop="tomorrow">
            <el-radio-group v-model="tomorrow">
              <el-radio label="Y">是</el-radio>
              <el-radio label="N">否</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="有效期限：" prop="sday">
            <el-select v-model="inputData.sday" placeholder="請選擇">
              <el-option
                v-for="item in dateList"
                :key="item"
                :label="processDate[item]"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="選擇印表機：" prop="printer">
            <el-select v-model="inputData.printer" placeholder="請選擇">
              <el-option
                v-for="item in printerList"
                :key="item.address"
                :label="item.printername"
                :value="item.address"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-button class="edit_button" @click="inputTag()">輸入</el-button>
      </template>
    </el-card>
  </div>

  <el-card style="margin-top: 10px">
    <el-table :data="tagDatas">
      <el-table-column prop="barcode" label="條碼名稱" width="300px"/>
      <el-table-column prop="setno" label="盤包名稱" />
      <el-table-column prop="setsn" label="序號" />
      <el-table-column label="包裝方式">
        <template #default="scope">
          <el-tag v-if="scope.row.box != ''">器械盒</el-tag>
          <el-tag v-else type="info">標籤 </el-tag>
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
    <el-button class="edit_button" @click="submitFrom()">列印</el-button>
  </el-card>
  <!--新增-->
  <el-dialog
    v-model="addDialogVisible"
    @close="addDialogClosed"
    width="40%"
    title="添加器械盒"
  >
    <!--列表-->
    <el-table :data="seqList" style="width: 100%">
      <el-table-column prop="setno" label="盤包代號" />
      <el-table-column prop="seq" label="序號" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button
            class="edit_button"
            @click="addSetno(scope.row.setno, scope.row.seq, scope.row.id)"
            >使用</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
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
      addDialogVisible: false,
      day: new Date(),
      tomorrow: "N",
      userno: "",
      usercname: "",
      depnoList: [],
      seqList: [],
      inputData: {
        depno: "",
        setno: "",
        num: 1,
        disinfection: "",
        package: "",
        box: "",
        printer: "",
        sn: "",
        seqId: "",
      },
      checkBoxitme: false,
      printerList: [],
      tagDatas: [],
      type: "A",
      udiDisabled: false,
      printer: "",
      packageList: [],
      disinfectionList: [],
      printerData: [],
      dateList: [1, 7, 14, 30, 60, 90, 180, 365],
      processDate: {
        1: "一天",
        7: "一周",
        14: "兩周",
        30: "一個月",
        60: "兩個月",
        90: "三個月",
        180: "半年",
        365: "一年",
      },
    };
  },
  created() {
    this.getDepnoList();
    this.getProcess();
    this.getDisinfection();
    this.getSeqList();
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
    getSetnoByName(name) {
      this.$axios.get("/setno_name/" + name).then((res) => {
        this.inputData.disinfection = res.data.data.spotno;
        this.inputData.package = res.data.data.sprocess;
        this.inputData.sday = res.data.data.sday;
      });
    },
    /** 消毒包查詢 */
    getProcess() {
      this.$axios.get("/disinfection/process").then((res) => {
        this.packageList = res.data.data;
      });
    },
    /**列表查詢 */
    getDisinfection() {
      this.$axios.get("/disinfection", this.queryInfo).then((res) => {
        this.disinfectionList = res.data.data.list;
      });
    },
    selectType() {
      if (this.type === "A") {
        this.inputData.box = "";
        this.udiDisabled = true;
      } else {
        this.udiDisabled = false;
      }
    },
    selectDisinfection(name) {
      if (name != "") {
        this.$axios.get("/depno_name/" + name).then((res) => {
          this.printerList = res.data.data.csrPrinter;
        });
      }
    },
    /**盤包輸入 => 列表 */
   async inputTag() {
      //時間格式化
      const formatDate = (date) => {
        let formatted_date;
        if (date.getMonth() + 1 > 9) {
          formatted_date = date.getFullYear() + "" + (date.getMonth() + 1);
        } else {
          formatted_date = date.getFullYear() + "0" + (date.getMonth() + 1);
        }

        if (date.getDate() > 9) {
          formatted_date = formatted_date + "" + date.getDate();
        } else {
          formatted_date = formatted_date + "0" + date.getDate();
        }

        return formatted_date;
      };
      //有效日期計算
      const day = (day) => {
        if (this.inputData.sday < 30) {
          day.setDate(day.getDate() + this.inputData.sday);
        } else {
          day.setMonth(day.getMonth() + this.inputData.sday / 30);
        }
        return day;
      };

      if (this.inputData.depno === "") {
        ElMessage.error("請輸入部門");
        return true;
      }

      if (this.inputData.disinfection === "") {
        ElMessage.error("請輸入消毒鍋");
        return true;
      }

      if (this.inputData.package === "") {
        ElMessage.error("請輸入包裝方式");
        return true;
      }

      if (this.type === "A") {
        if (this.inputData.box === "") {
          ElMessage.error("請輸入器械盒UDI");
          return true;
        }
      }

      if (this.inputData.sday === "") {
        ElMessage.error("請輸入有效期限");
        return true;
      }

      if (this.inputData.printer === "" && this.inputData.box === "") {
        ElMessage.error("請輸選擇印表機");
        return true;
      }

      //數字是否正確
      if (isNaN(this.inputData.num)) {
        ElMessage.error("請輸入正確數量");
        return true;
      }
      //器械盒判斷
      if (this.inputData.box != "") {
        if (this.inputData.num != 1) {
          ElMessage.error("數量大於1");
          return true;
        }
      await this.checkBox(this.inputData.box);
        if (! this.checkBoxitme) {
          return true;
        }
      }
      this.setnoCheckList = localStorage.getItem("setnoAll").split(",");

      for (let i = 1; i <= this.inputData.num; i++) {
        //盤包是否正確
        this.setnoCheckList.some((e) => {
          if (e === this.inputData.setno) {
            if (this.inputData.box != "") {
              this.barcode = this.inputData.box;
            } else {
              this.barcode =
                this.inputData.setno + formatDate(this.day).substring(2);
            }

            this.tagDatas.push({
              depno: this.inputData.depno,
              setno: this.inputData.setno,
              setnamech: this.inputData.setnamech,
              disinfection: this.inputData.disinfection,
              box: this.inputData.box,
              datauserno: this.userno,
              datausername: this.usercname,
              barcode: this.barcode,
              setsn: this.inputData.sn,
              effectivedate: day(this.day),
              eod: this.tomorrow,
              seqId: this.inputData.seqId,
            });

            this.inputData.sn =""
            this.day = new Date();
            return true;
          }
        });
      }
    },
    /**刪除標籤 */
    deleteTag(id) {
      this.tagDatas.splice(id, 1);
    },
    //列印標籤
    submitFrom() {     
      if (this.tagDatas.length > 0) {
        this.$axios.post("/tag/make", this.tagDatas).then(() => {
          (this.inputData = {
            depno: "",
            setno: "",
            num: 1,
            disinfection: "",
            package: "",
            box: "",
            printer: "",
            sn: "",
            seqId: "",
          }),
          this.tagDatas = [];
          this.getSeqList()
        });
      }
    },
    //器械盒查詢
     async checkBox(name) {
         await this.$axios.get("/box/name/" + name).then((res) => {
        if (res.data.code === 500) {
          this.checkBoxitme = false;
          return;
        }
        this.checkBoxitme = true;
        this.tagDatas.forEach(i=>{
          if(i.barcode === name){
              this.checkBoxitme = false;
          }
        })      
      });
    },
    getSeqList() {
      this.$axios.get("/tag/seq").then((res) => {
        this.seqList = res.data.data;
      });
    },

    addSetno(setno, sn, id) {
      let count = 0;
      this.tagDatas.forEach((e) => {
        if (e.seqId === id && e.setno === setno) {
          count += 1;
          return;
        }
      });
      if (count != 0) {
        ElMessage.error("序號重複輸入");
        return;
      }
      this.inputData.setno = setno;
      this.inputData.sn = sn;
      this.inputData.seqId = id;
      this.addDialogVisible = false;
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
              this.getSetnoByName(res.data.data.setno);
            } else {
              this.inputData.setnamech = "";
            }
          });
        }
      }
    },
  },
};
</script>

<style lang="less" scope>
</style>