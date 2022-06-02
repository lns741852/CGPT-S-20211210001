<template>
  <div style="width: 100%">
    <h3>申領確認交易序號列表</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-row :gutter="24">
        <el-col :span="18">
          <span>庫房：</span>
          <el-select v-model="depno" @change="selectallocate(depno)">
            <el-option
              v-for="item in DepList"
              :key="item.depno"
              :label="item.depname"
              :value="item.depno"
            >
            </el-option>
          </el-select>
        </el-col>
      </el-row>
      <!--列表-->
      <el-table :data="allocateList" style="width: 100%">
        <el-table-column prop="depnoName.depname" label="申領單位" />
        <el-table-column prop="usercname" label="申請人" />
        <el-table-column prop="reqno" label="交易序號" />
        <el-table-column prop="roomName.roomname" label="室別" />
        <el-table-column prop="allocatetype" label="申領類別" >
           <template #default="scope">
            <el-tag v-if="scope.row.box != 'G'" type="error">緊急</el-tag>
            <el-tag v-else type="info">常規
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button class="edit_button" @click="showAllocate(scope.row.reqId)"
              >查看</el-button
            >
            <el-button
              class="delete_button"
              @click="deleteReq(scope.row.reqId, scope.row.reqno)"
              >刪除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-card>
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
      depno: "CSR",
      RoomList: [],
      DepList: [],
      allocateList:[],
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
      },
    };
  },
  created() {
    this.selectallocate(this.depno);
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
    selectallocate(depno){
      this.$axios.get("/allocate/" + depno).then((res) => {
        this.allocateList = res.data.data;
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
        this.$axios.put("/room/" + this.addForm.id, this.addForm).then(() => {
          this.editDialogVisible = false;
          this.getRoomList();
        });
      });
    },
    /**顯示修改資料 */
    showAllocate(id) {
       this.$router.push({path: `/allocate2/${id}`});
    },
    /**刪除 */
    deleteReq(id, name) {
      this.$msgbox
        .confirm("確定要刪除 " + name + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/allocate/" + id).then(() => {
            this.selectallocate(this.depno);
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