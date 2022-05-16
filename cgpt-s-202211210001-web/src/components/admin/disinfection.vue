<template>
  <h3>消毒鍋列表</h3>
  <el-row class="bgcolor">
    <el-col :span="5" :offset="1">
      <!--輸入框-->
      <el-input
        placeholder="消毒鍋代號"
        v-model="queryInfo.searchName"
        clearable
        @clear="getDisinfection"
        @keyup.enter="getDisinfection"
      >
        <template #append>
          <el-button class="search_button" @click="getDisinfection"
            ><el-icon><search /></el-icon
          ></el-button>
        </template>
      </el-input>
    </el-col>
  </el-row>
  <el-row class="elPagination">
    <el-col span="4" :offset="18">
      <!--分頁-->
      <el-pagination
        :current-page="queryInfo.pageno"
        :page-size="queryInfo.pagesize"
        layout="total, prev, pager, next, jumper"
        :total="total"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </el-col>
  </el-row>

  <!--列表-->
  <el-table
    :data="disinfectionList"
    style="width: 100%"
    class="elTable"
    border
    :cell-style="{ borderColor: 'black' }"
    :header-cell-style="{ borderColor: 'black' }"
  >
    <el-table-column type="index" label="編號" width="80" />
    <el-table-column prop="disinfection" label="消毒鍋代號" />
    <el-table-column prop="potname" label="消毒鍋名稱" />
    <el-table-column label="操作">
      <template #default="scope">
        <el-button class="edit_button" @click="showEditDialon(scope.row.id)"
          >修改</el-button
        >
        <el-button
          class="delete_button"
          @click="deleteDisinfection(scope.row.disinfection)"
          >刪除</el-button
        >
      </template>
    </el-table-column>
  </el-table>

  <el-button class="edit_button" @click="addDialogVisible = true"
    >新增消毒鍋</el-button
  >
  <!--新增-->
  <el-dialog
    v-model="addDialogVisible"
    @close="addDialogClosed"
    width="40%"
    title="添加消毒鍋"
  >
    <!--驗證-->
    <template #default>
      <el-form
        ref="addFormRef"
        :model="addForm"
        label-width="80px"
        :rules="addFormRules"
      >
        <el-form-item label="消毒鍋代號" prop="disinfection">
          <el-input v-model="addForm.disinfection"></el-input>
        </el-form-item>
        <el-form-item label="消毒鍋名稱" prop="potname">
          <el-input v-model="addForm.potname"></el-input>
        </el-form-item>
        <!--checkbox-->
        <el-form-item label="包裝名稱">
          <el-checkbox-group
            v-model="checkboxList"
            v-for="item in processList"
            :key="item.processno"
          >
            <el-checkbox :label="item.processno">{{
              item.processname
            }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
    </template>
    <template #footer>
      <div class="dialog-footer">
        <el-button class="edit_button" @click="addDisinfection">確定</el-button>
        <el-button type="info" @click="addDialogVisible = false"
          >取消</el-button
        >
      </div>
    </template>
  </el-dialog>
  <!--修改-->
  <el-dialog
    v-model="editDialogVisible"
    @close="editDialogClosed"
    width="40%"
    title="修改消毒鍋"
  >
    <!--驗證-->
    <template #default>
      <el-form
        ref="addFormRef"
        :model="addForm"
        label-width="80px"
        :rules="addFormRules"
      >
        <el-form-item label="消毒鍋代號" prop="disinfection">
          <el-input v-model="addForm.disinfection"></el-input>
        </el-form-item>
        <el-form-item label="消毒鍋名稱" prop="potname">
          <el-input v-model="addForm.potname"></el-input>
        </el-form-item>
        <!--checkbox-->
        <el-form-item label="包裝名稱">
          <el-checkbox-group
            v-model="checkboxList"
            v-for="item in processList"
            :key="item.processno"
          >
            <el-checkbox :label="item.processno">{{
              item.processname
            }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
    </template>
    <template #footer>
      <div class="dialog-footer">
        <el-button class="edit_button" @click="editDisinfection">確定</el-button>
        <el-button type="info" @click="editDialogVisible = false"
          >取消</el-button
        >
      </div>
    </template>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      processList: [],
      checkboxList: [],
      queryInfo: {
        empNO: "",
        searchName: "",
        pageno: "",
        pagesize: "",
      },
      disinfectionList: [],
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {
        csrProcessPot: [],
      },
      addFormRules: {
        userno: [
          { required: true, message: "請輸入...", trigger: "blur" },
          { min: 3, max: 10, message: "長度3~10", trigger: "blur" },
        ],
        depno: [{ required: true, message: "請輸入...", trigger: "blur" }],
        usercname: [
          { required: true, message: "請輸入...", trigger: "blur" },
          { min: 3, max: 10, message: "長度3~10", trigger: "blur" },
        ],
        userpwd: [
          { required: true, message: "請輸入...", trigger: "blur" },
          { min: 3, max: 10, message: "長度3~10", trigger: "blur" },
        ],
        systemprivilege: [
          { required: true, message: "請輸入...", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getProcess();
    this.getDisinfection();
  },
  methods: {
    /** 消毒包查詢 */
    getProcess() {
      this.$axios.get("/disinfection/process").then((res) => {
        this.processList = res.data.data;
      });
    },
    /**列表查詢 */
    getDisinfection() {
      this.$axios.get("/disinfection", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.disinfectionList = res.data.data.list;
      });
    },
    /**權限查詢 */
    // getAuth() {
    //   this.$axios.get("/auth").then((res) => {
    //     this.auth = res.data.data;
    //   });
    // },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getDisinfection();
    },
    /**清空訊息 */
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.checkboxList = [];
    },
    /**清空訊息 */
    editDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.checkboxList = [];
    },
    /**新增 */
    addDisinfection() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.checkboxList.forEach((e) => {
          this.addForm.csrProcessPot.push({
            processno: e,
          });
        });

        this.$axios.post("/disinfection", this.addForm).then(() => {
          this.addDialogVisible = false;
          this.getDisinfection();
        });
      });
    },
    /**編輯帳號 */
    editDisinfection() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.checkboxList.forEach((e) => {
          this.addForm.csrProcessPot.push({
            processno: e,
          });
        });
        this.$axios
          .put("/disinfection/" + this.addForm.id, this.addForm)
          .then(() => {
            this.editDialogVisible = false;
            this.getDisinfection();
          });
      });
    },
    /**顯示修改帳戶 */
    showEditDialon(id) {
      this.$axios.get("/disinfection/" + id).then((res) => {
        this.addForm = res.data.data;
        res.data.data.csrProcessPot.forEach((e) => {
          this.checkboxList.push(e.processno);
        });
      });
      this.editDialogVisible = true;
    },
    /**刪除 */
    deleteDisinfection(name) {
      this.$msgbox
        .confirm("確定要刪除 " + name + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/disinfection/" + name).then(() => {
            this.getDisinfection();
          });
        })
        .catch(() => {
          //nothing to do
        });
    },
  },
};
</script>

<style lang="less" scope>
.el-row.bgcolor {
   padding: 10px 0 5px 0;
  border: 3px solid #114f4a;
  background: #d4debc;
}

.elPagination {
  margin-top: 10px;
}
</style>