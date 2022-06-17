<template>
  <div style="width: 100%">
    <h3>包裝程序列表</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-row :gutter="24">
        <el-col :span="8">
          <!--輸入框-->
          <el-input
            placeholder="包裝程序查詢"
            v-model="queryInfo.searchName"
            clearable
            @clear="getPackageList"
            @keyup.enter="getPackageList"
          >
            <template #append>
              <el-button class="search_button" @click="getPackageList">
                <el-icon><search /></el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="12"> </el-col>
        <el-button class="edit_button" @click="addDialogVisible = true"
          >新增程序</el-button
        >
      </el-row>
      <!--列表-->
      <el-table :data="packageList" style="width: 100%" stripe>
        <el-table-column type="index" label="編號" width="80" />
        <el-table-column prop="processno" label="包裝程序代號" />
        <el-table-column prop="processname" label="包裝程序名稱" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button class="edit_button" @click="showEditDialon(scope.row.id)"
              >修改</el-button
            >
            <el-button
              class="delete_button"
              @click="deletePacket(scope.row.id, scope.row.setnamech)"
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
      title="添加包裝程序"
    >
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="包裝代號" prop="processno">
            <el-input v-model="addForm.processno"></el-input>
          </el-form-item>
          <el-form-item label="包裝名稱" prop="processname">
            <el-input v-model="addForm.processname"></el-input>
          </el-form-item>
          <!--新增_下拉選單-->
          <el-form-item label="保存日期" prop="day">
            <el-select
             popper-class="dropdownbox"
              v-model="addForm.day"
              placeholder="請選擇"
              clearable
            >
              <el-option
                v-for="item in date"
                :key="item"
                :label="processDate[item]"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="addPacket">確定</el-button>
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
      title="修改包裝程序"
    >
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="包裝代號" prop="processno">
            <el-input v-model="addForm.processno"></el-input>
          </el-form-item>
          <el-form-item label="包裝名稱" prop="processname">
            <el-input v-model="addForm.processname"></el-input>
          </el-form-item>
          <!--修改_下拉選單-->
          <el-form-item label="保存日期" prop="day">
            <el-select
             popper-class="dropdownbox"
              v-model="addForm.day"
              placeholder="請選擇"
              clearable
            >
              <el-option
                v-for="item in date"
                :key="item"
                :label="processDate[item]"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="editPacket">確定</el-button>
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
      date: [1, 7, 14, 30, 60, 90, 180, 365],
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
      packageList: [],
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {},
      addFormRules: {
        processno: [
          { required: true, message: "請輸入代號...", trigger: "blur" },
        ],
        processname: [
          { required: true, message: "請輸入名稱...", trigger: "blur" },
        ],
        day: [
          { required: true, message: "請輸入日期...", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getPackageList();
  },
  methods: {
    /**列表查詢 */
    getPackageList() {
      this.$axios.get("/package", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.packageList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getPackageList();
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
    addPacket() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.addForm.datename = this.processDate[this.addForm.day];
        this.$axios.post("/package", this.addForm).then(() => {
          this.addDialogVisible = false;
          this.getPackageList();
        });
      });
    },
    /**編輯 */
    editPacket() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        console.log(this.addForm);
        this.$axios
          .put("/package/" + this.addForm.id, this.addForm)
          .then(() => {
            this.editDialogVisible = false;
            this.getPackageList();
          });
      });
    },
    /**顯示修改資料 */
    showEditDialon(id) {
      this.$axios.get("/package/" + id).then((res) => {
        this.addForm = res.data.data;
      });
      this.editDialogVisible = true;
    },
    /**刪除 */
    deletePacket(id, name) {
      this.$msgbox
        .confirm("確定要刪除 " + name + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/package/" + id).then(() => {
            this.getPackageList();
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
</style>