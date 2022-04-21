<template>
  <h3>單一器械列表</h3>
  <!--卡片區塊-->
  <el-card class="box-card">
    <el-row :gutter="100">
      <el-col :span="8">
        <!--輸入框-->
        <el-input
          placeholder="單一器械代號"
          v-model="queryInfo.searchName"
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
      <el-table-column prop="code" label="UDI器械代號" />
      <el-table-column prop="cname" label="中文名稱" />
      <el-table-column prop="ename" label="英文名稱" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" @click="showFile(scope.row.id)"
            >瀏覽</el-button
          >
          <el-button class="edit_button" @click="showEditDialog(scope.row.id)"
            >修改</el-button
          >
          <el-button
            class="delete_button"
            @click="deleteudi(scope.row.id, scope.row.setnamech)"
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
        <el-form-item label="UDI單一器械代號" prop="code" label-width="100">
          <el-input v-model="addForm.code"></el-input>
        </el-form-item>
        <el-form-item label="中文名稱" prop="cname">
          <el-input v-model="addForm.cname"></el-input>
        </el-form-item>
        <el-form-item label="英文名稱" prop="ename">
          <el-input v-model="addForm.ename"></el-input>
        </el-form-item>
      </el-form>
      <!--圖片上傳-->
      <el-upload
        class="upload-demo"
        ref="upload"
        action=""
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList"
        :auto-upload="false"
        :on-change="handleShowImage"
        multiple
      >
        <el-button type="primary">圖片及檔案選取</el-button>
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
        <el-form-item label="UDI單一器械代號" prop="code" label-width="100">
          <el-input v-model="addForm.code"></el-input>
        </el-form-item>
        <el-form-item label="中文名稱" prop="cname">
          <el-input v-model="addForm.cname"></el-input>
        </el-form-item>
        <el-form-item label="英文名稱" prop="ename">
          <el-input v-model="addForm.ename"></el-input>
        </el-form-item>
        <!--檔案_標籤-->
        <el-form-item label="已存在檔案">
          <el-tag
            :key="tag"
            v-for="tag in dynamicTags"
            closable
            :disable-transitions="false"
            @close="deleteFile(addForm.id, tag)"
          >
            <a>{{ tag.substring(14) }} </a>
          </el-tag>
        </el-form-item>
      </el-form>
      <!--圖片上傳-->
      <el-upload
        class="upload-dem"
        ref="editUpload"
        action=""
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList"
        :auto-upload="false"
        :on-change="handleShowImage"
        multiple
      >
        <el-button size="small" type="primary">圖片及檔案選取</el-button>
      </el-upload>
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
  <!-- 瀏覽_對話框 -->
  <el-dialog
    v-model="fileDialogVisible"
    @close="fileDialogClosed"
    width="30%"
    title="圖檔"
  >
    <el-row type="flex" >
      <el-col :span="18">
        <!--檔案連結-->
        <div v-for="(item, index) in files" :key="index">
          <el-link type="primary" v-bind:href="item">{{
            item.substring(46)
          }}</el-link>
           <el-divider content-position="right">UDI檔案</el-divider>
        </div>       
        <br />
      </el-col>
    </el-row>
    <el-row justify="center">
      <el-col :span="18">
        <!-- 圖片展示 -->
        <div v-for="url in urls" :key="url" class="demo-image__lazy">
          <el-tooltip :content="url.substring(46)" placement="top">
            <el-image :src="url" lazy></el-image>
          </el-tooltip>
            <el-divider content-position="right">UDI圖片</el-divider>
        </div>
      
      </el-col>
    </el-row>

    <template #footer>
      <div class="dialog-footer">
        <el-button type="info" @click="fileDialogVisible = false"
          >關閉</el-button
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
      dynamicTags: [],
      urls: [],
      files: [],
      addFormRules: {
        code: [{ required: true, message: "請輸入代號...", trigger: "blur" }],
        cname: [
          { required: true, message: "請輸入中文名稱...", trigger: "blur" },
        ],
        ename: [
          { required: true, message: "請輸入英文名稱...", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getUDIList();
  },

  methods: {
    /**列表查詢 */
    getUDIList() {
      this.$axios.get("/udi", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.UDIList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getUDIList();
    },
    /**清空新增視窗訊息 */
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.$refs.upload.clearFiles();
      this.dynamicTags = [];
    },
    /**清空編輯視窗訊息 */
    editDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.$refs.editUpload.clearFiles();
      this.dynamicTags = [];
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
    /**新增 */
    addUDI() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        var formData = new FormData();
        var i = 0;
        this.fileList.forEach((x, index) => {
          console.log(x.raw.type)
          if (x.raw.type === "image/png" || x.raw.type =="image/jpeg") {
            formData.append("pic" + (index + 1 - i), x.raw);
          } else {
            formData.append("file" + (index + 1 - i), x.raw);
          }
          i = i + 1;
        });
        formData.append("code", this.addForm.code);
        formData.append("cname", this.addForm.cname);
        formData.append("ename", this.addForm.ename);
        this.$axios
          .post("/udi", formData, { Loading: true, isUpload: true })
          .then(() => {
            this.addDialogClosed();
            this.addDialogVisible = false;
            this.getUDIList();
          });
      });
    },
    /**修改*/
    editUDI() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        var formData = new FormData();
        var i = 0;
        this.fileList.forEach((x, index) => {
          if (x.raw.type === "image/png" || x.raw.type =="image/jpeg") {
            formData.append("pic" + (index + 1 - i), x.raw);
          } else {
            formData.append("file" + (index + 1 - i), x.raw);
          }
          i = i + 1;
        });
        formData.append("code", this.addForm.code);
        formData.append("cname", this.addForm.cname);
        formData.append("ename", this.addForm.ename);
        this.$axios
          .put("/udi/" + this.addForm.id, formData, {
            Loading: true,
            isUpload: true,
          })
          .then(() => {
            this.editDialogClosed();
            this.editDialogVisible = false;
            this.getUDIList();
          });
      });
    },
    /**顯示修改資料 */
    showEditDialog(id) {
      
      this.$axios.get("/udi/" + id).then((res) => {
        this.addForm = res.data.data;
        if (res.data.data.picpath1)
          this.dynamicTags.push(res.data.data.picpath1);
        if (res.data.data.picpath2)
          this.dynamicTags.push(res.data.data.picpath2);
        if (res.data.data.picpath3)
          this.dynamicTags.push(res.data.data.picpath3);
        if (res.data.data.filepath1)
          this.dynamicTags.push(res.data.data.filepath1);
        if (res.data.data.filepath2)
          this.dynamicTags.push(res.data.data.filepath2);
        if (res.data.data.filepath3)
          this.dynamicTags.push(res.data.data.filepath3);
      });
      this.editDialogVisible = true;
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
    /**刪除檔案 */
    deleteFile(id, file) {
      this.$msgbox
        .confirm("確定要刪除 " + file.substring(14) + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/udi/" + id + "/" + file).then(() => {
            this.dynamicTags.splice(this.dynamicTags.indexOf(file), 1);
          });
        })
        .catch(() => {
          //nothing to do
        });
    },
    /**展示圖片 */
    showFile(id) {
      this.$axios.get("/udi/" + id).then((res) => {
        if (res.data.data.picpath1)
          this.urls.push(
            "http://127.0.0.1:8282/HTPE/file/" + res.data.data.picpath1
          );
        if (res.data.data.picpath2)
          this.urls.push(
            "http://127.0.0.1:8282/HTPE/file/" + res.data.data.picpath2
          );
        if (res.data.data.picpath3)
          this.urls.push(
            "http://127.0.0.1:8282/HTPE/file/" + res.data.data.picpath3
          );
        if (res.data.data.filepath1)
          this.files.push(
            "http://127.0.0.1:8282/HTPE/file/" + res.data.data.filepath1
          );
        if (res.data.data.filepath2)
          this.files.push(
            "http://127.0.0.1:8282/HTPE/file/" + res.data.data.filepath2
          );
        if (res.data.data.filepath3)
          this.files.push(
            "http://127.0.0.1:8282/HTPE/file/" + res.data.data.filepath3
          );
      });
      this.fileDialogVisible = true;
    },
  },
};
</script>

<style lang="less" scope>
.el-tag {
  font-size: 15px;
  margin: 0 10px 10px 0;
}
.el-divider__text.is-right{
  background-color:#FEFBEE;
}
</style>