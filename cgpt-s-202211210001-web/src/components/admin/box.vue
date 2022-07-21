<template>
  <div style="width: 100%">
    <h3>器械盒列表</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-row :gutter="24">
        <el-col :span="8">
          <!--輸入框-->
          <el-input
            placeholder="器械盒查詢"
            v-model="queryInfo.searchName"
            clearable
            @clear="getBoxList"
            @keyup.enter="getBoxList"
          >
            <template #append>
              <el-button class="search_button" @click="getBoxList">
                <el-icon><search /></el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
        <el-col :span="12"> </el-col>
        <el-button class="edit_button" @click="addDialogVisible = true"
          >新增器械盒</el-button
        >
      </el-row>
      <!--列表-->
      <el-table :data="boxList" style="width: 100%">
        <el-table-column type="index" label="編號" width="80" />
        <el-table-column prop="udi" label="器械盒代號" />
        <el-table-column prop="price" label="成本" />
        <el-table-column prop="status" label="狀態">
          <template #default="scope">
            <el-tag v-if="scope.row.status == 0" type="info">待使用</el-tag>
            <el-tag v-else-if="scope.row.status == 1" type="success"
              >使用中</el-tag
            >
            <el-tag v-else-if="scope.row.status == 2" type="warning"
              >維修中</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button class="edit_button" @click="showEditDialon(scope.row.id)"
              >修改</el-button
            >
            <el-button
              class="delete_button"
              @click="deleteBox(scope.row.id, scope.row.udi)"
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
    <!--新增-->
    <el-dialog
      v-model="addDialogVisible"
      @close="addDialogClosed"
      width="40%"
      title="添加器械盒"
    >
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="UDI" prop="udi">
            <el-input v-model="addForm.udi"></el-input>
          </el-form-item>
          <el-form-item label="成本" prop="price">
            <el-input v-model="addForm.price"></el-input>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="addBox">確定</el-button>
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
      title="修改器械盒"
    >
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="UDI" prop="udi">
            <el-input v-model="addForm.udi"></el-input>
          </el-form-item>
          <el-form-item label="成本" prop="price">
            <el-input v-model="addForm.price"></el-input>
          </el-form-item>
          <el-form-item v-if="addForm.status != 1" label="維修中" prop="status">
            <el-switch
              v-model="fix"
            >
            </el-switch>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="editBox">確定</el-button>
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
      boxList: [],
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {},
      fix:"",
      addFormRules: {
        udi: [{ required: true, message: "請輸入代號...", trigger: "blur" }],
        price: [{ required: true, message: "請輸入名稱...", trigger: "blur" }],
        status: [{ required: true, message: "請輸入日期...", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getBoxList();
  },
  methods: {
    /**列表查詢 */
    getBoxList() {
      this.$axios.get("/box", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.boxList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getBoxList();
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
    /**新增器械盒 */
    addBox() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.$axios.post("/box", this.addForm).then(() => {
          this.addDialogVisible = false;
          this.getBoxList();
        });
      });
    },
    /**編輯 */
    editBox() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        
        if(this.fix){
            this.addForm.status ="2"
        }else if(this.fix==""){
             this.addForm.status ="0"
        }else{
             this.addForm.status ="1"
        }
        this.$axios.put("/box/" + this.addForm.id, this.addForm).then(() => {
          this.editDialogVisible = false;
          this.getBoxList();
        });
      });
    },
    /**顯示修改資料 */
    showEditDialon(id) {
      this.$axios.get("/box/" + id).then((res) => {
        this.addForm = res.data.data;
        if(this.addForm.status == 2){
            this.fix = true
        }else if(this.addForm.status == 0){
            this.fix =false
        }else {
             this.fix = ""
        }
      });
      this.editDialogVisible = true;
    },
    /**刪除 */
    deleteBox(id, name) {
      this.$msgbox
        .confirm("確定要刪除 " + name + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/box/" + id).then(() => {
            this.getBoxList();
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