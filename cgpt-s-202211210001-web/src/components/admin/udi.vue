<template>
  <h3>單一器械列表</h3>
 <!--卡片區塊-->
  <el-card class="box-card">
    <el-row :gutter="100">
      <el-col :span="8">
        <!--輸入框-->
        <el-input
          placeholder="單一器械代號"
          v-model="searchName"
          clearable
          @clear="getUDIList"
          @keyup.enter="getUDIList"
        >
          <template #append>
            <el-button class="search_button" @click="getUDIList"
              ><el-icon><search /></el-icon
            ></el-button>
          </template>
        </el-input>
      </el-col>
      <el-col :span="12"> </el-col>
      <el-button class="edit_button" @click="addDialogVisible = true"
        >新增器械</el-button
      >
    </el-row>
    <!--列表-->
    <el-table :data="UDIList" style="width: 100%">
      <el-table-column type="index" label="編號" width="100" />
      <el-table-column prop="udi" label="UDI器械代號" />
      <el-table-column  label="狀態" >
          <template  #default="scope">
             <el-tag v-if="scope.row.status == 0" type="info">未使用</el-tag>
            <el-tag v-else-if="scope.row.status == 1" type="success"
              >使用中</el-tag
            >
            <el-tag v-else-if="scope.row.status == 2" type="warning"
              >維修中</el-tag
            >
            <el-tag v-else-if="scope.row.status == 3" type="danger"
              >已報廢</el-tag
            >
          </template>
      </el-table-column>
      <el-table-column prop="price" label="單價" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button class="edit_button" @click="showEditDialog(scope.row.id)"
            >修改</el-button
          >
          <el-button
            class="delete_button"
            @click="deleteudi(scope.row.id, scope.row.udi)"
            >刪除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </el-card>
  <!--新增用戶_對話框-->
  <el-dialog
    v-model="addDialogVisible"
    @close="addDialogClosed"
    width="40%"
    title="添加器械"
  >
    <!--表單-->
    <template #default>
      <el-form
        ref="addFormRef"
        :model="addForm"
        label-width="120px"
        :rules="addFormRules"
      >
        <el-form-item label="UDI" prop="udi" >
          <el-input v-model="addForm.udi"></el-input>
        </el-form-item>
        <el-form-item label="價格" prop="price">
          <el-input v-model="addForm.price"></el-input>
        </el-form-item>
      </el-form>
    </template>
    <template #footer>
      <div class="dialog-footer">
        <el-button class="edit_button" @click="addUDI">確定</el-button>
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
    title="修改器械"
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
        <el-form-item label="價格" prop="price">
          <el-input v-model="addForm.price"></el-input>
        </el-form-item>
        <div v-show="addForm.status != '1'">
        <el-form-item label="狀態">
          <el-radio v-model="addForm.status" label="0">維修完畢</el-radio>
          <el-radio v-model="addForm.status" label="2">維修中</el-radio>
          <el-radio v-model="addForm.status" label="3">報廢</el-radio>
        </el-form-item>
        </div>
      </el-form>
    </template>
    <template #footer>
      <div class="dialog-footer">
        <el-button class="edit_button" @click="editUDI">確定</el-button>
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
      searchName: "",
      UDIList: [],
      addDialogVisible: false,
      editDialogVisible: false,
      fileDialogVisible: false,
      addForm: {},
      id: this.$route.params.id,
      addFormRules: {
        udi: [{ required: true, message: "請輸入代號...", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getUDIList();
  },

  methods: {
    /**列表查詢 */
    getUDIList() {
      this.$axios.get("/udi/type/" + this.id).then((res) => {
        this.UDIList = res.data.data;
      });
    },
    /**清空新增視窗訊息 */
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
       this.addForm= {};
    },
    /**清空編輯視窗訊息 */
    editDialogClosed() {
      this.$refs.addFormRef.resetFields();
        this.addForm= {};
    },

    /**新增 */
    addUDI() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;

        this.addForm.typeId = this.id
        this.addForm.status = "0"

        this.$axios
          .post("/udi", this.addForm)
          .then(() => {
            this.addDialogClosed();
            this.addDialogVisible = false;
            this.getUDIList();
          });
      });
    },
    // 顯示
    showEditDialog(id){
      this.$axios.get("/udi/" + id).then((res) => {
        this.addForm = res.data.data;
      });
      this.editDialogVisible = true;
    },

    /**修改*/
    editUDI() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;

        this.addForm.createTime=null
        this.addForm.updateTime=null

        this.$axios
          .put("/udi/"+this.addForm.id ,this.addForm)
          .then(() => {
            this.editDialogClosed();
            this.editDialogVisible = false;
            this.getUDIList();
          });
      });
    },
    /**刪除 */
    deleteudi(id, name) {
      this.$msgbox
        .confirm("確定要刪除 " + name + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/udi/" + id).then(() => {
            this.getUDIList();
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
.el-tag {
  font-size: 15px;
  margin: 0 10px 10px 0;
}
.el-divider__text.is-right {
  background-color: #fefbee;
}
</style>