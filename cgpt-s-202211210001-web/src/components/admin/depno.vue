<template>
  <div style="width: 100%">
    <h3>部門列表</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-row :gutter="24">
        <el-col :span="8">
          <!--輸入框-->
          <el-input
            placeholder="部門代號"
            v-model="queryInfo.searchName"
            clearable
            @clear="getDepnoList"
            @keyup.enter="getDepnoList"
          >
            <template #append>
              <el-button class="search_button" @click="getDepnoList">
                <el-icon><search /></el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
      </el-row>
      <!--列表-->
      <el-table :data="depnoList" style="width: 100%">
        <el-table-column type="index" label="編號" width="40" />
        <el-table-column prop="depname" label="部門代號" />
        <el-table-column prop="depnamech" label="部門名稱" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button class="edit_button" @click="showEditDialon(scope.row.id)"
              >修改</el-button
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
    <!--修改_對話框-->
    <el-dialog
      v-model="editDialogVisible"
      @close="editDialogClosed"
      width="60%"
      title="部門資料編輯"
    >
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="部門代號" prop="depname">
            <el-input v-model="addForm.depname"></el-input>
          </el-form-item>
          <el-form-item label="部門名稱" prop="setnamech">
            <el-input v-model="addForm.depnamech"></el-input>
          </el-form-item>
        </el-form>
        <!--消毒鍋-->
        <el-card class="box-card" style="width: 80%">
          <el-form
            label-width="120px"
            :model="csrPotDepno"
            :rules="addFormRules"
          >
            <!--下拉選單-->
            <el-form-item label="消毒鍋代號" prop="disinfection">
              <el-select
               popper-class="dropdownbox"
                v-model="csrPotDepno.disinfection"
                placeholder="請選擇"
                clearable
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
            <el-form-item label="字首" prop="top">
              <el-input v-model="csrPotDepno.head"></el-input>
            </el-form-item>
            <!--下拉選單-->
            <el-form-item label="起始編號" prop="x">
              <el-select
               popper-class="dropdownbox"
                v-model="csrPotDepno.beginnum"
                placeholder="請選擇"
                clearable
              >
                <el-option
                  v-for="item in num"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <!--下拉選單-->
            <el-form-item label="鍋別數量" prop="x">
              <el-select
               popper-class="dropdownbox"
                v-model="csrPotDepno.potnum"
                placeholder="請選擇"
                clearable
              >
                <el-option
                  v-for="item in num"
                  :key="item"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>

            <el-button class="edit_button" @click="addPot">輸入</el-button>
          </el-form>
          <el-table
            :data="addForm.csrPotDepno"
            style="width: 100%"
            empty-text="此欄位無資料"
          >
            <el-table-column prop="disinfection" label="消毒鍋代號" width="180">
            </el-table-column>
            <el-table-column prop="head" label="字首" width="180">
            </el-table-column>
            <el-table-column prop="beginnum" label="起始編號">
            </el-table-column>
            <el-table-column prop="potnum" label="鍋別數量"> </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-button
                  class="delete_button"
                  @click="deletePot(scope.row.id, scope.row.disinfection)"
                  >刪除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-card>

        <!--條碼機-->
        <el-card class="box-card" style="width: 80%">
          <el-form
            ref="addFormRef2"
            label-width="120px"
            :model="csrPrinter"
            :rules="addFormRules"
          >
            <el-form-item label="條碼機名稱" prop="printername">
              <el-input v-model="csrPrinter.printername"></el-input>
            </el-form-item>
            <el-form-item label="條碼機安裝檔名稱" prop="type">
              <el-input v-model="csrPrinter.type"></el-input>
            </el-form-item>
            <el-form-item label="IP位址設定" prop="address">
              <el-input v-model="csrPrinter.address"></el-input>
            </el-form-item>
            <el-form-item label="埠號" prop="port">
              <el-input v-model="csrPrinter.port"></el-input>
            </el-form-item>
            <el-button class="edit_button" @click="addPrinter">輸入</el-button>
          </el-form>
          <el-table
            :data="addForm.csrPrinter"
            style="width: 100%"
            empty-text="此欄位無資料"
          >
            <el-table-column
              prop="printername"
              label="條碼機名稱"
              width="180"
            />
            <el-table-column prop="type" label="條碼機安裝檔名稱" />
            <el-table-column prop="address" label="IP位址設定" />
            <el-table-column prop="port" label="埠號" />
            <el-table-column label="操作">
              <template #default="scope">
                <el-button
                  class="delete_button"
                  @click="deletePrinter(scope.row.id, scope.row.printername)"
                  >刪除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </template>
      <template #footer>
        <div class="dialog-footer">
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
      queryInfo: {
        searchName: "",
        pageno: "",
        pagesize: "",
      },
      csrPotDepno: {},
      csrPrinter: {},
      depnoList: [],
      num: [],
      addForm: {},
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      addFormRules: {
        depname: [
          { required: true, message: "請輸入盤包代號...", trigger: "blur" },
        ],
        printername: [
          { required: true, message: "請輸入條碼機名稱...", trigger: "blur" },
        ],
        address: [
          { required: true, message: "請輸入IP...", trigger: "blur" },
          {
            pattern:
              /^((25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)\.){3}(25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)*$/,
            message: "格式不正確",
            trigger: "blur",
          },
        ],
        port: [
          { required: true, message: "請輸入port...", trigger: "blur" },
          { type: "integer", message: `請輸入整數`, trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getDepnoList();
    this.getDisinfection();
    this.addNum();
  },
  methods: {
    /**列表查詢 */
    getDepnoList() {
      this.$axios.get("/depno", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.depnoList = res.data.data.list;
      });
    },
    /**消毒鍋查詢 */
    getDisinfection() {
      this.$axios.get("/disinfection").then((res) => {
        this.disinfectionList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getDepnoList();
    },
    /**清空訊息 */
    editDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.$refs.addFormRef2.resetFields();
      this.addForm = {};
      this.csrPrinter = {};
      this.csrPotDepno = {};
    },
    /**新增鍋 */
    addPot() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.csrPotDepno.depno = this.addForm.depno;
        this.$axios.post("/depno/potdata", this.csrPotDepno).then(() => {
          this.editDialogVisible = false;
        });
      });
    },
    /**新增條碼機 */
    addPrinter() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.csrPrinter.depno = this.addForm.depno;
        this.$axios.post("/depno/printer", this.csrPrinter).then(() => {
          this.editDialogVisible = false;
        });
      });
    },
    /**顯示修改資料 */
    showEditDialon(id) {
      this.$axios.get("/depno/" + id).then((res) => {
        this.addForm = res.data.data;
      });
      this.editDialogVisible = true;
    },
    /**刪除鍋 */
    deletePot(id, name) {
      this.$msgbox
        .confirm("確定要刪除 " + name + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/depno/potdata/" + id).then(() => {
            this.editDialogVisible = false;
          });
        })
        .catch(() => {
          //nothing to do
        });
    },
    /**刪除條碼機 */
    deletePrinter(id, name) {
      this.$msgbox
        .confirm("確定要刪除 " + name + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/depno/printer/" + id).then(() => {
            this.editDialogVisible = false;
          });
        })
        .catch(() => {
          //nothing to do
        });
    },
    addNum() {
      for (let i = 1; i <= 20; i++) {
        this.num.push(i);
      }
    },
  },
};
</script>

<style lang="less" scope>
.box-card {
  margin: 20px auto;
}
</style>