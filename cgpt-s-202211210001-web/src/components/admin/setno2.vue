<template>
  <h3>建立盤包</h3>
  <!--卡片區塊-->
  <el-card class="box-card" style="margin-top: 10px">
    <el-row>
      <el-col :span="14">
        <!--輸入框-->
        <el-input
          placeholder="器械類代號"
          v-model="queryInfo.searchName"
          clearable
          @clear="getUDITypeList"
          @keyup.enter="getUDITypeList"
          style="width: 20%"
        >
          <template #append>
            <el-button class="search_button" @click="getUDITypeList"
              ><el-icon><search /></el-icon
            ></el-button>
          </template>
        </el-input>
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
          <el-table-column prop="name" />
          <el-table-column prop="nameScientific" label="英文名稱" />
          <el-table-column prop="spec" label="規格" />
          <el-table-column label="操作">
            <template #default="scope">
              <el-button class="edit_button" @click="showEditDialog(scope.row)"
                >添加</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :offset="2" :span="6">
        <el-descriptions
          style="width: 100%; margin-top: 45px"
          :column="1"
          border
        >
          <el-descriptions-item label="盤包代號：">{{
            addForm.setno
          }}</el-descriptions-item>
          <el-descriptions-item label="英文名稱：">{{
            addForm.setname
          }}</el-descriptions-item>
          <el-descriptions-item label="中文名稱：">{{
            addForm.setnamech
          }}</el-descriptions-item>
        </el-descriptions>
        <!--列表-->
        <el-table
          class="setno_udi_table"
          :data="addFormList"
          style="width: 80%"
        >
          <el-table-column prop="name" label="已選器械" />
          <el-table-column prop="num" label="數量" />
          <el-table-column label="操作">
            <template #default="scope">
              <el-button class="delete_button" @click="deleteUDI(scope.$index)"
                >刪除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="16">
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
      <el-col :span="6">
        <el-button class="edit_button" @click="addSetno" style="float: right"
          >建立盤包</el-button
        >
      </el-col>
    </el-row>
  </el-card>
  <!--添加-->
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
        :model="addForm2"
        label-width="120px"
        :rules="addFormRules"
      >
        <el-form-item label="器械名稱">
          <el-input v-model="addForm2.name"></el-input>
        </el-form-item>
        <el-form-item label="器械名稱">
          <el-input v-model="addForm2.nameScientific"></el-input>
        </el-form-item>
        <el-form-item label="器械數量">
          <el-input v-model="addForm2.num"></el-input>
        </el-form-item>
      </el-form>
    </template>
    <template #footer>
      <div class="dialog-footer">
        <el-button class="edit_button" @click="addUdi">確定</el-button>
        <el-button type="info" @click="editDialogVisible = false"
          >取消</el-button
        >
      </div>
    </template>
  </el-dialog>
</template>

<script>
import { ElMessage } from "element-plus";

export default {
  data() {
    return {
      id: this.$route.params.id,
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
      addForm2: {
        name: "",
        nameScientific: "",
        num: 0,
      },
      addFormList: [],
      addFormRules: {
        num: [{ required: true, message: "請輸入數量...", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getUDITypeList();
    this.getSetno();
    this.getUDI();
  },

  methods: {
    /**顯示修改資料 */
    getSetno() {
      this.$axios.get("/setno/" + this.id).then((res) => {
        this.addForm = res.data.data;
      });
    },
    getUDI() {
      this.$axios.get("/setno/udi/" + this.id).then((res) => {
        this.udiNum = res.data.data;
        this.udiNum.forEach((i) => {
          this.addFormList.push({
            udiId: i.UDITYPEID,
            name: i.NAME,
            num: i.NUM,
          });
        });
      });
    },

    /**列表查詢 */
    getUDITypeList() {
      this.$axios.get("/udi_type", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.UDIList = res.data.data.list;
        this.UDIList.forEach((item) => {
          item.url =
            "http://127.0.0.1:8282/HTPE/file/" +
            item.csrUdiImages[0].csrFileResource.resourceName;
        });
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getUDITypeList();
    },

    /**清空編輯視窗訊息 */
    editDialogClosed() {
      this.$refs.addFormRef.resetFields();
      this.editDialogVisible = false;
    },

    // 顯示
    showEditDialog(row) {
      this.addForm2 = row;
      this.editDialogVisible = true;
    },
    addUdi() {
      //數字是否正確
      if (isNaN(this.addForm2.num)) {
        ElMessage.error("請輸入正確數量");
        return true;
      }

      let i = 0;
      //盤包重複判斷
      this.addFormList.some((e) => {
        if (e.name === this.addForm2.name) {
          e.num = Number(this.addForm2.num);
          i = i + 1;
        }
      });

      if (i !== 0) {
        this.editDialogClosed();
        return;
      }
      this.addFormList.push({
        udiId: this.addForm2.id,
        name: this.addForm2.name,
        num: this.addForm2.num,
      });
      this.editDialogClosed();
    },
    addSetno() {
      if (this.addFormList.length <= 0) {
        return;
      }
      this.$axios.post("/udi_type/" + this.id, this.addFormList).then(() => {});
    },
    /**刪除 */
    deleteUDI(id) {
      this.addFormList.splice(id, 1);
    },
  },
};
</script>

<style lang="less" scope>
.setno_udi_table .el-table__header .is-leaf.el-table__cell {
  background-color: rgb(255, 255, 255);
}
</style>