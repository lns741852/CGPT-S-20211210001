<template>
  <h3>器械類列表</h3>
  <!--卡片區塊-->
  <el-card class="box-card">
    <el-row :gutter="100">
      <el-col :span="8">
        <!--輸入框-->
        <el-input
          placeholder="器械類代號"
          v-model="queryInfo.searchName"
          clearable
          @clear="getUDITypeList"
          @keyup.enter="getUDITypeList"
        >
          <template #append>
            <el-button class="search_button" @click="getUDITypeList"
              ><el-icon><search /></el-icon
            ></el-button>
          </template>
        </el-input>
      </el-col>
      <el-col :span="12"> </el-col>
      <el-button class="edit_button" @click="addDialogVisible = true"
        >新增器械類</el-button
      >
    </el-row>
    <!--列表-->
    <el-table :data="UDIList" style="width: 100%">
      <el-table-column label="器械類型" width="100">
        <template #default="item">
          <div>
            <el-image
              style="width: 100px; height: 100px"
              :src="item.row.url"
            ></el-image>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="name"  />
      <el-table-column prop="nameScientific" label="英文名稱" />
      <el-table-column prop="spec" label="規格" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" @click="showFile(scope.row.id)"
            >UDI預覽</el-button
          >
          <el-button class="edit_button" @click="showEditDialog(scope.row.id)"
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
        <el-form-item label="器械名稱" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="英文名稱" prop="nameScientific">
          <el-input v-model="addForm.nameScientific"></el-input>
        </el-form-item>
        <el-form-item label="規格" prop="spec">
          <el-input type="textarea" v-model="addForm.spec"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input type="textarea" v-model="addForm.description"></el-input>
        </el-form-item>
      </el-form>
      <!--圖片上傳-->
      <el-upload
        list-type="picture-card"
        ref="upload"
        action="#"
        :file-list="fileList"
        :on-remove="removeFile"
        :auto-upload="false"
        :on-change="handleShowImage"
        :limit="4"
        :on-exceed="handleExceed"
        multiple
        accept=".jpg"
      >
        <el-icon><plus /></el-icon>
      </el-upload>
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
        <el-form-item label="器械名稱" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="英文名稱" prop="nameScientific">
          <el-input v-model="addForm.nameScientific"></el-input>
        </el-form-item>
        <el-form-item label="規格" prop="spec">
          <el-input type="textarea" v-model="addForm.spec"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input type="textarea" v-model="addForm.description"></el-input>
        </el-form-item>
      </el-form>

      <!--圖片上傳-->
      <el-upload
        list-type="picture-card"
        ref="editUpload"
        action="#"
        :on-preview="handlePictureCardPreview"
        :file-list="fileList"
        :on-remove="removeFile"
        :auto-upload="false"
        :on-change="handleShowImage"
        :limit="4"
        :on-exceed="handleExceed"
        multiple
        accept=".jpg"
      >
        <el-icon><plus /></el-icon>
      </el-upload>
      <el-dialog v-model="dialogVisible">
        <img w-full :src="dialogImageUrl" alt="" />
      </el-dialog>
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
      queryInfo: {
        searchName: "",
        pageno: "",
        pagesize: "",
      },
      UDIList: [],
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      fileDialogVisible: false,
      addForm: {},
      urls: [],
      files: [],
      fileList: [],
      dialogImageUrl: "",
      dialogVisible: false,
      file: {},
      addFormRules: {
        name: [{ required: true, message: "請輸入代號...", trigger: "blur" }],
        nameScientific: [
          { required: true, message: "請輸入中文名稱...", trigger: "blur" },
        ],
        spec: [
          { required: true, message: "請輸入英文名稱...", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getUDITypeList();
  },

  methods: {
    /**列表查詢 */
    getUDITypeList() {
      this.$axios.get("/udi_type", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.UDIList = res.data.data.list;
        this.UDIList.forEach(item=>{
          item.url =  "http://127.0.0.1:8282/HTPE/file/" +item.csrUdiImages[0].csrFileResource.resourceName
        })
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getUDITypeList();
    },
    /**清空新增視窗訊息 */
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.$refs.upload.clearFiles();
      this.fileList = [];
    },
    /**清空編輯視窗訊息 */
    editDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.$refs.editUpload.clearFiles();
      this.fileList = [];
    },
    /**清空圖片 */
    fileDialogClosed() {
      this.urls = [];
      this.files = [];
    },
    /**圖片綁定 */
    handleShowImage(file, fileList) {
      this.fileList = fileList;
    },

    // 上傳文件超出個數
    handleExceed(files, fileList) {
      console.log(files + "---" + fileList);
      this.$message.warning("圖片數量超出限制");
    },

    // 移除文件
    removeFile(file) {
      // 移除文件時，要重新給fileList賦值
      const arr = [];
      for (let i = 0; i < this.fileList.length; i++) {
        if (this.fileList[i].uid !== file.uid) {
          arr.push(this.fileList[i]);
        }
      }
      this.fileList = arr;
    },
    /**新增 */
    addUDI() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;

        if (this.fileList.length === 0) {
          this.$message.warning("請選擇圖片");
          return;
        }
        var filetype = true;
        var formData = new FormData();
        this.fileList.forEach((file) => {
          const temp = file.name.substring(file.name.lastIndexOf(".") + 1);
          if (temp !== "jpg") {
            this.$message({
              message: "當前文件格式不符合要求",
              type: "error",
            });
            return (filetype = false);
          }
          formData.append("file", file.raw);
        });
        if (filetype === false) return;

        formData.append("name", this.addForm.name);
        formData.append("nameScientific", this.addForm.nameScientific);
        formData.append("spec", this.addForm.spec);
        formData.append("description", this.addForm.description);
        this.$axios
          .post("/udi_type", formData, { Loading: true, isUpload: true })
          .then(() => {
            this.fileList = [];
            this.addDialogClosed();
            this.addDialogVisible = false;
            this.getUDITypeList();
          });
      });
    },

    /**修改*/
    editUDI() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;

        if (this.fileList.length === 0) {
          this.$message.warning("請選擇圖片");
          return;
        }
        var filetype = true;
        var formData = new FormData();
        this.fileList.forEach((file) => {
          const temp = file.name.substring(file.name.lastIndexOf(".") + 1);
          if (temp !== "jpg") {
            this.$message({
              message: "當前文件格式不符合要求",
              type: "error",
            });
            return (filetype = false);
          }
          file.type = "image/jpeg";


          if(file.status ==='ready'){
              formData.append("file", file.raw);
          }
        });
        if (filetype === false) return;

        formData.append("name", this.addForm.name);
        formData.append("nameScientific", this.addForm.nameScientific);
        formData.append("spec", this.addForm.spec);
        formData.append("description", this.addForm.description);

        this.$axios
          .put("/udi_type/" + this.addForm.id, formData, {
            Loading: true,
            isUpload: true,
          })
          .then(() => {
            this.fileList = [];
            this.editDialogClosed();
            this.editDialogVisible = false;
            this.getUDITypeList();
          });
      });
    },
    /**顯示修改資料 */
    showEditDialog(id) {
      this.$axios.get("/udi_type/" + id).then((res) => {
        this.addForm = res.data.data;
        this.addForm.csrUdiImages.forEach((item) => {
          let name = item.csrFileResource.resourceName;
          let url =
            "http://127.0.0.1:8282/HTPE/file/" +
            item.csrFileResource.resourceName;
          this.fileList.push({ name: name.substring(14), url: url });
        });
      });
      this.editDialogVisible = true;
    },
    //圖片展示
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    showFile(id){
      this.$router.push({path: `/udi/${id}`});
    }
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