<template>
  <div style="width: 100%">
    <h3>病房列表</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-row :gutter="24">
        <el-col :span="8">
          <!--輸入框-->
          <el-input
            placeholder="病房查詢"
            v-model="queryInfo.searchName"
            clearable
            @clear="getRoomList"
            @keyup.enter="getRoomList"
          >
            <template #append>
              <el-button class="search_button" @click="getRoomList">
                <el-icon><search /></el-icon>
              </el-button>
            </template>
          </el-input>
          <!-- <el-button style="margin-top:10px"  @click="getVic">獲取語音檔 </el-button> -->
        </el-col>
        <el-col :span="12"> </el-col>
        <el-button class="edit_button" @click="addDialogVisible = true"
          >新增病房</el-button
        >
      </el-row>
      <!--列表-->
      <el-table :data="RoomList" style="width: 100%">
        <el-table-column type="index" label="編號" width="80" />
        <el-table-column prop="roomno" label="病房代號" />
        <el-table-column prop="roomname" label="病房名稱" />
        <el-table-column prop="csrDepno.depname" label="部門名稱" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button class="edit_button" @click="showEditDialon(scope.row.id)"
              >修改</el-button
            >
            <el-button
              class="delete_button"
              @click="deleteSetno(scope.row.id, scope.row.roomno)"
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
      title="添加病房"
    >
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="病房代號" prop="roomno">
            <el-input v-model="addForm.roomno"></el-input>
          </el-form-item>
          <el-form-item label="病房名稱" prop="roomname">
            <el-input v-model="addForm.roomname"></el-input>
          </el-form-item>
          <!--新增_下拉選單-->
          <el-form-item label="部門" prop="day">
            <el-select
              popper-class="dropdownbox"
              v-model="addForm.depno"
              placeholder="請選擇"
              clearable
            >
              <el-option
                v-for="item in DepList"
                :key="item.depno"
                :label="item.depname"
                :value="item.depno"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="addRoom">確定</el-button>
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
      title="修改病房"
    >
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="病房代號" prop="roomno">
            <el-input v-model="addForm.roomno"></el-input>
          </el-form-item>
          <el-form-item label="病房名稱" prop="roomname">
            <el-input v-model="addForm.roomname"></el-input>
          </el-form-item>
          <!--修改_下拉選單-->
          <el-form-item label="部門" prop="day">
            <el-select
              popper-class="dropdownbox"
              v-model="addForm.depno"
              placeholder="請選擇"
              clearable
            >
              <el-option
                v-for="item in DepList"
                :key="item.depno"
                :label="item.depname"
                :value="item.depno"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </template>
      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="editRoom">確定</el-button>
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
      RoomList: [],
      DepList:[],
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
        ]
      },
    };
  },
  created() {
    this.getRoomList();
    this.getDepList();
  },
  methods: {
    /**列表查詢 */
    getRoomList() {
      this.$axios.get("/room", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.RoomList = res.data.data.list;
      });
    },
    /**部門查詢 */
    getDepList() {
      this.$axios.get("/depno").then((res) => {
        this.DepList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getRoomList();
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
    addRoom() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.$axios.post("/room", this.addForm).then(() => {
          this.addDialogVisible = false;
          this.getRoomList();
        });
      });
    },
    /**編輯 */
    editRoom() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.$axios
          .put("/room/" + this.addForm.id, this.addForm)
          .then(() => {
            this.editDialogVisible = false;
            this.getRoomList();
          });
      });
    },
    /**顯示修改資料 */
    showEditDialon(id) {
      this.$axios.get("/room/" + id).then((res) => {
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
          this.$axios.remove("/room/" + id).then(() => {
            this.getRoomList();
          });
        })
        .catch(() => {
          //nothing to do
        });
    },
    getVic(){ 
        let options={Loading: true, mock: false, isUpload: false, voice: true}
        this.$axios.get("/getVoiceContent.aspx?CompanyID=Spl&GUID=ccb60470-fddd-4142-a6e0-18f2539e7d28&Param1=語音轉文字&UserID=",'', options).then((res) => {
          this.queryInfo.searchName = this.$x2js.xml2js(res.data).VAIMA.VOICE[0]._STT_text
          console.log(this.$x2js.xml2js(res.data).VAIMA.VOICE[0]._STT_text)         
        });     
    } 
  }, 
 
};


</script>

<style lang="less" scope>
</style>