<template>
  <div style="width: 100%">
    <h3>盤包列表</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-row :gutter="24">
        <el-col :span="8">
          <!--輸入框-->
          <el-input
            placeholder="盤包代號 / 盤包名稱"
            v-model="queryInfo.searchName"
            clearable
            @clear="getSetnoList"
            @keyup.enter="getSetnoList"
          >
            <template #append>
              <el-button class="search_button" @click="getSetnoList">
                <el-icon><search /></el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="12"> </el-col>
        <el-button class="edit_button" @click="addDialogVisible = true"
          >新增盤包</el-button
        >
      </el-row>
      <!--列表-->
      <el-table :data="setnoList" style="width: 100%" stripe>
        <el-table-column type="index" label="編號" width="60" />
        <el-table-column prop="setno" label="盤包代號" width="160" />
        <el-table-column prop="setname" label="英文名稱" />
        <el-table-column prop="setnamech" label="中文名稱" />
        <!-- <el-table-column prop="positionId" label="儲位" width="180" /> -->
        <!-- <el-table-column prop="numbaseCsr" label="CSR庫存量" width="80" />
        <el-table-column prop="numbaseCsr2" label="CSR2庫存量" width="80" />
        <el-table-column prop="numbaseOr1" label="AOR庫存量" width="80" />
        <el-table-column prop="numbaseOr2" label="BOR庫存量" width="80" />
        <el-table-column prop="numbaseOr3" label="GOR庫存量" width="80" />
        <el-table-column prop="numbaseWr1" label="WR庫存量" width="80" /> -->
        <el-table-column prop="price" label="成本" />
        <el-table-column label="操作" width="360">
          <template #default="scope">
            <el-button  type="primary" @click="addUdi(scope.row.id)"
              >添加器械</el-button
            >
            <el-button class="edit_button" @click="showEditDialon(scope.row.id)"
              >修改</el-button
            >
            <el-button
              class="delete_button"
              @click="deleteSetno(scope.row.id, scope.row.setnamech)"
              >刪除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <!--分頁-->
      <el-pagination
        :current-page="queryInfo.pageno"
        :page-size="queryInfo.pagesize"
        layout="total, prev, pager, next, jumper"
        :total="total"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </el-card>
    <!--新增用戶對話框-->
    <el-dialog
      v-model="addDialogVisible"
      @close="addDialogClosed"
      width="40%"
      title="添加盤包"
    >
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="盤包代號" prop="setno">
            <el-input v-model="addForm.setno"></el-input>
          </el-form-item>
          <el-form-item label="中文名稱" prop="setnamech">
            <el-input v-model="addForm.setnamech"></el-input>
          </el-form-item>
          <el-form-item label="英文名稱" prop="setname">
            <el-input v-model="addForm.setname"></el-input>
          </el-form-item>
          <!--新增_下拉選單-->
          <el-form-item label="標籤製作預設值">
            <el-form-item label="消毒方式" prop="spotno">
              <el-select
                popper-class="dropdownbox"
                v-model="addForm.spotno"
                placeholder="請選擇"
                @change="getProcess"
                clearable
              >
                <el-option
                  v-for="item in potdataList"
                  :key="item.DISINFECTION"
                  :label="item.POTNAME"
                  :value="item.DISINFECTION"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="包裝方式" prop="sprocess">
              <el-select
                popper-class="dropdownbox"
                v-model="addForm.sprocess"
                placeholder="請選擇"
                clearable
              >
                <el-option
                  v-for="item in processList"
                  :key="item.PROCESSNO"
                  :label="item.PROCESSNAME"
                  :value="item.PROCESSNO"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="保存天數" prop="sday">
              <el-select
                popper-class="dropdownbox"
                v-model="addForm.sday"
                placeholder="請選擇"
                clearable
              >
                <el-option
                  v-for="item in dateList"
                  :key="item"
                  :label="processDate[item]"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-form-item>
          <el-form-item label="成本">
            <el-input v-model="addForm.price"></el-input>
          </el-form-item>
          <el-form-item label="CSR基本量">
            <el-input v-model="addForm.numbaseCsr"></el-input>
          </el-form-item>
          <el-form-item label="CSR2基本量">
            <el-input v-model="addForm.numbaseCsr2"></el-input>
          </el-form-item>
          <el-form-item label="AOR基本量">
            <el-input v-model="addForm.numbaseOr1"></el-input>
          </el-form-item>
          <el-form-item label="BOR基本量">
            <el-input v-model="addForm.numbaseOr2"></el-input>
          </el-form-item>
          <el-form-item label="GOR基本量">
            <el-input v-model="addForm.numbaseOr3"></el-input>
          </el-form-item>
          <el-form-item label="WR基本量">
            <el-input v-model="addForm.numbaseWr1"></el-input>
          </el-form-item>
          <el-form-item label="儲位">
            <el-input v-model="addForm.positionId"></el-input>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="addSetno">確定</el-button>
          <el-button type="info" @click="addDialogVisible = false"
            >取消</el-button
          >
        </div>
      </template>
    </el-dialog>
    <!--修改_對話框-->
    <el-dialog
      v-model="editDialogVisible"
      @close="editDialogClosed"
      width="40%"
      title="修改盤包"
    >
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="盤包代號" prop="setno">
            <el-input v-model="addForm.setno"></el-input>
          </el-form-item>
          <el-form-item label="中文名稱" prop="setnamech">
            <el-input v-model="addForm.setnamech"></el-input>
          </el-form-item>
          <el-form-item label="英文名稱" prop="setname">
            <el-input v-model="addForm.setname"></el-input>
          </el-form-item>
          <!--新增_下拉選單-->
          <el-form-item label="標籤製作預設值" prop="x">
            <el-form-item label="消毒方式" prop="spotno">
              <el-select
                popper-class="dropdownbox"
                v-model="addForm.spotno"
                placeholder="請選擇"
                @change="getProcess"
                clearable
              >
                <el-option
                  v-for="item in potdataList"
                  :key="item.DISINFECTION"
                  :label="item.POTNAME"
                  :value="item.DISINFECTION"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="包裝方式" prop="sprocess">
              <el-select
                popper-class="dropdownbox"
                v-model="addForm.sprocess"
                placeholder="請選擇"
                clearable
              >
                <el-option
                  v-for="item in processList"
                  :key="item.PROCESSNO"
                  :label="item.PROCESSNAME"
                  :value="item.PROCESSNO"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="保存天數" prop="sday">
              <el-select
                popper-class="dropdownbox"
                v-model="addForm.sday"
                placeholder="請選擇"
                clearable
              >
                <el-option
                  v-for="item in dateList"
                  :key="item"
                  :label="processDate[item]"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-form-item>
          <el-form-item label="成本">
            <el-input v-model="addForm.price"></el-input>
          </el-form-item>
          <el-form-item label="CSR基本量">
            <el-input v-model="addForm.numbaseCsr"></el-input>
          </el-form-item>
          <el-form-item label="CSR2基本量">
            <el-input v-model="addForm.numbaseCsr2"></el-input>
          </el-form-item>
          <el-form-item label="AOR基本量">
            <el-input v-model="addForm.numbaseOr1"></el-input>
          </el-form-item>
          <el-form-item label="BOR基本量">
            <el-input v-model="addForm.numbaseOr2"></el-input>
          </el-form-item>
          <el-form-item label="GOR基本量">
            <el-input v-model="addForm.numbaseOr3"></el-input>
          </el-form-item>
          <el-form-item label="WR基本量">
            <el-input v-model="addForm.numbaseWr1"></el-input>
          </el-form-item>
          <el-form-item label="儲位">
            <el-input v-model="addForm.positionId"></el-input>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="editSetno">確定</el-button>
          <el-button type="info" @click="editDialogVisible = false"
            >取消</el-button
          >
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      potdataList: [],
      processList: [],
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
      queryInfo: {
        searchName: "",
        pageno: "",
        pagesize: "",
      },
      setnoList: [],
      depnoList: [],
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {
        price: 0,
        numbaseCsr: 0,
        numbaseCsr2: 0,
        numbaseOr1: 0,
        numbaseOr2: 0,
        numbaseOr3: 0,
        numbaseWr1: 0,
      },
      addFormRules: {
        setno: [
          { required: true, message: "請輸入盤包代號...", trigger: "blur" },
        ],
        sprocess: [
          {
            required: true,
            message: "請輸入標籤製作預設值...",
            trigger: "blur",
          },
        ],
        spotno: [
          {
            required: true,
            message: "請輸入標籤製作預設值...",
            trigger: "blur",
          },
        ],
        sday: [
          {
            required: true,
            message: "請輸入標籤製作預設值...",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.getPotdata();
    this.getSetnoList();
    this.getDepno();
  },
  methods: {
    /** 部門查詢 */
    getDepno() {
      this.$axios.get("/depno").then((res) => {
        this.depnoList = res.data.data.list;
        console.log(this.depnoList);
      });
    },
    /** 消毒鍋查詢 */
    getPotdata() {
      this.$axios.get("/setno/potdata").then((res) => {
        this.potdataList = res.data.data;
      });
    },
    /** 消毒包裝查詢 */
    getProcess() {
      this.$axios.get("/setno/potdata/" + this.addForm.spotno).then((res) => {
        this.processList = res.data.data;
      });
    },
    /**列表查詢 */
    getSetnoList() {
      this.$axios.get("/setno", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.setnoList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getSetnoList();
    },
    /**清空訊息 */
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.addForm = {};
    },
    /**清空訊息 */
    editDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.addForm = {};
    },
    /**新增盤包 */
    addSetno() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.$axios.post("/setno", this.addForm).then(() => {
          this.addDialogVisible = false;
          this.getSetnoList();
        });
      });
    },
    /**編輯 */
    editSetno() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        console.log(this.addForm);
        this.$axios.put("/setno/" + this.addForm.id, this.addForm).then(() => {
          this.editDialogVisible = false;
          this.getSetnoList();
        });
      });
    },
    /**顯示修改資料 */
    showEditDialon(id) {
      this.$axios.get("/setno/" + id).then((res) => {
        this.addForm = res.data.data;
      });
      this.editDialogVisible = true;
    },
    /**刪除 */
    deleteSetno(id, name) {
      this.$msgbox
        .confirm("確定要刪除 " + name + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/setno/" + id).then(() => {
            this.getSetnoList();
          });
        })
        .catch(() => {
          //nothing to do
        });
    },
    addUdi(id) {
      this.$router.push({ path: `/setno2/${id}` });
    },
  },
  watch: {
    "addForm.setno": function () {
      if (this.addForm.setno != undefined) {
        if (this.addForm.setno.length === 6) {
          this.addForm.setno = this.addForm.setno.toUpperCase();
        }
      }
    },
  },
};
</script>

<style lang="less" scope>
</style>