<template>
  <div style="width: 100%">
    <h3>儲位列表</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-row gutter="24">
        <el-col :span="8">
          <!--輸入框-->
          <el-input
            placeholder="儲位查詢"
            v-model="queryInfo.searchName"
            clearable
            @clear="getPositionList"
            @keyup.enter="getPositionList"
          >
            <template #append>
              <el-button class="search_button" @click="getPositionList">
                <el-icon><search /></el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="12"> </el-col>
        <el-button class="edit_button" @click="addDialogVisible = true"
          >新增儲位</el-button
        >
      </el-row>
      <!--列表-->
      <el-table :data="positionList" style="width: 100%">
        <el-table-column type="index" label="編號" width="80" />
        <el-table-column prop="code" label="儲位代號" />
        <el-table-column prop="name" label="儲位名稱" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button class="edit_button" @click="showEditDialon(scope.row.id)"
              >修改</el-button
            >
            <el-button
              class="delete_button"
              @click="deletePosition(scope.row.id, scope.row.name)"
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
      title="添加儲位"
    >
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="儲位代號" prop="code">
            <el-input v-model="addForm.code"></el-input>
          </el-form-item>
          <el-form-item label="儲位名稱" prop="name">
            <el-input v-model="addForm.name"></el-input>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="addPosition">確定</el-button>
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
      title="修改儲位"
    >
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
         <el-form-item label="儲位代號" prop="code">
            <el-input v-model="addForm.code"></el-input>
          </el-form-item>
          <el-form-item label="儲位名稱" prop="name">
            <el-input v-model="addForm.name"></el-input>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="editPosition">確定</el-button>
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
      positionList: [],
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {},
      addFormRules: {
        code: [
          { required: true, message: "請輸入代號...", trigger: "blur" },
        ],
        name: [
          { required: true, message: "請輸入名稱...", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getPositionList();
  },
  methods: {
    /**列表查詢 */
    getPositionList() {
      this.$axios.get("/position", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.positionList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getPositionList();
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
    /**新增儲位 */
    addPosition() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.$axios.post("/position", this.addForm).then(() => {
          this.addDialogVisible = false;
          this.getPositionList();
        });
      });
    },
    /**編輯 */
    editPosition() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.$axios
          .put("/position/" + this.addForm.id, this.addForm)
          .then(() => {
            this.editDialogVisible = false;
            this.getPositionList();
          });
      });
    },
    /**顯示修改資料 */
    showEditDialon(id) {
      this.$axios.get("/position/" + id).then((res) => {
        this.addForm = res.data.data;
      });
      this.editDialogVisible = true;
    },
    /**刪除 */
    deletePosition(id, name) {
      this.$msgbox
        .confirm("確定要刪除 " + name + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/position/" + id).then(() => {
            this.getPositionList();
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