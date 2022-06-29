<template>
  <div class="board-center" style="width: 60%">
    <h3>手術列表</h3>
    <!--卡片區塊-->
    <el-row class="bgcolor" :gutter="24">
      <el-col :span="8">
        <!--輸入框-->
        <el-input
          placeholder="手術代號 / 手術名稱"
          v-model="queryInfo.searchName"
          clearable
          @clear="getSurgicalList"
          @keyup.enter="getSurgicalList"
        >
          <template #append>
            <el-button class="search_button" @click="getSurgicalList">
              <el-icon><search /></el-icon>
            </el-button>
          </template>
        </el-input>
      </el-col>
      <el-col :span="12"> </el-col>
      <el-button
        class="edit_button"
        @click="
          closeExpand();
          addDialogVisible = true;
        "
        >新增手術</el-button
      >
    </el-row>
    <!--列表-->
    <el-table
      :data="surgicalList"
      style="width: 100%"
      @expand-change="showSurgicalDetail"
      class="elTable"
      :cell-style="{ borderColor: 'black' }"
      :header-cell-style="{ borderColor: 'black' }"
    >
      <!--下拉框-->
      <el-table-column type="expand" label="#" width="100">
        <el-card style="width: 80%;margin:0 auto">
          <el-table :data="csrSurgicalCasecar">
            <el-table-column
              prop="casecarno"
              label="個案車代號"
            >
            </el-table-column>
          </el-table>
        </el-card>
      </el-table-column>
      <el-table-column prop="surgicalno" label="手術代號"  />
      <el-table-column prop="surgicalnamech" label="手術名稱" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button class="edit_button" @click="showEditDialon(scope.row.id)"
            >修改</el-button
          >
          <el-button
            class="delete_button"
            @click="deleteSurgical(scope.row.surgicalno)"
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
    <!--新增-->
    <el-dialog
      v-model="addDialogVisible"
      @close="addDialogClosed"
      width="60%"
      title="添加手術"
    >
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="手術代號" prop="surgicalno" style="width: 90%">
            <el-input v-model="addForm.surgicalno"></el-input>
          </el-form-item>
          <el-form-item label="手術名稱" prop="surgicalnamech" style="width: 90%">
            <el-input v-model="addForm.surgicalnamech"></el-input>
          </el-form-item>
        </el-form>
        <!--自增列表-->
        <el-card class="box-card" style="width: 80%">
          <el-form :rules="addFormRules">
            <el-form-item label="個案車代號" />
            <el-input v-model="inputData.casecarno" />
            <el-button class="edit_button" @click="inputCasecar"
              >輸入</el-button
            >
          </el-form>

          <el-table :data="csrSurgicalCasecar" style="width: 100%">
            <el-table-column
              prop="casecarno"
              label="個案車代號"
              width="180"
            >
            </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-button
                  class="delete_button"
                  @click="deleteCasecar(scope.$index)"
                  >刪除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </template>

      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="addSurgical">確定</el-button>
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
      width="60%"
      title="修改手術"
    >
          <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="手術代號" prop="surgicalno" style="width: 90%">
            <el-input v-model="addForm.surgicalno"></el-input>
          </el-form-item>
          <el-form-item label="手術名稱" prop="surgicalnamech" style="width: 90%">
            <el-input v-model="addForm.surgicalnamech"></el-input>
          </el-form-item>
        </el-form>
        <!--自增列表-->
        <el-card class="box-card" style="width: 80%">
          <el-form :rules="addFormRules">
            <el-form-item label="個案車代號" />
            <el-input v-model="inputData.casecarno" />
            <el-button class="edit_button" @click="inputCasecar"
              >輸入</el-button
            >
          </el-form>

          <el-table :data="csrSurgicalCasecar" style="width: 100%">
            <el-table-column
              prop="casecarno"
              label="個案車代號"
              width="180"
            >
            </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-button
                  class="delete_button"
                  @click="deleteCasecar(scope.$index)"
                  >刪除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </template>

      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="editSurgical">確定</el-button>
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
      inputData: {},
      surgicalList: [],
      csrSurgicalCasecar: [],
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {},
      addFormRules: {
        surgicalno: [
          { required: true, message: "請輸入個案車代號...", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getSurgicalList();
  },
  methods: {
    /**列表查詢 */
    getSurgicalList() {
      this.queryInfo.searchName = this.queryInfo.searchName.toUpperCase();
      this.$axios.get("/surgical", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.surgicalList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getSurgicalList();
    },
    /**清空訊息 */
    addDialogClosed() {
      this.addForm = {};
      this.inputData = {};
      this.csrSurgicalCasecar = [];
    },
    /**清空訊息 */
    editDialogClosed() {
      this.addForm = {};
      this.inputData = {};
      this.csrSurgicalCasecar = [];
    },
    /**新增盤包 */
    addSurgical() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.addForm.csrSurgicalCasecar = this.csrSurgicalCasecar;
        this.$axios.post("/surgical", this.addForm).then(() => {
          this.addDialogVisible = false;
          this.getSurgicalList();
        });
      });
    },
    /**編輯 */
    editSurgical() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.addForm.csrSurgicalCasecar = this.csrSurgicalCasecar;
        this.$axios
          .put("/surgical/" + this.addForm.id, this.addForm)
          .then(() => {
            this.editDialogVisible = false;
            this.getSurgicalList();
          });
      });
    },
    /**顯示修改資料 */
    showEditDialon(id) {
      this.$axios.get("/surgical/" + id).then((res) => {
        this.addForm = res.data.data;
        this.csrSurgicalCasecar = res.data.data.csrSurgicalCasecar;
      });
      this.editDialogVisible = true;
    },
    /**刪除 */
    deleteSurgical(surgicalno) {
      this.$msgbox
        .confirm("確定要刪除 " + surgicalno + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/surgical/" + surgicalno).then(() => {
            this.getSurgicalList();
          });
        })
        .catch(() => {
          //nothing to do
        });
    },
    /**個案車輸入 => 列表 */
    inputCasecar() {
      var i = 0;
      this.casecarCheckList = localStorage.getItem("casecarAll").split(",");
      //個案車重複判斷
      this.csrSurgicalCasecar.some((e) => {
        if (e.casecarno === this.inputData.casecarno) {
          i = i + 1;
        }
      });
      if (i !== 0) {
        return true;
      }

      //個案車是否正確
      this.casecarCheckList.some((e) => {
        if (e === this.inputData.casecarno) {
          this.csrSurgicalCasecar.push({
              casecarno: this.inputData.casecarno,
          });
          return true;
        }
      });
    },
    /**刪除個案車 */
    deleteCasecar(id) {
      this.csrSurgicalCasecar.splice(id, 1);
    },
    /**下拉內容 */
    showSurgicalDetail(row) {
      this.$axios.get("/surgical/" + row.id).then((res) => {
        this.addForm = res.data.data;
        this.csrSurgicalCasecar = res.data.data.csrSurgicalCasecar;
      });
    },
    /**關閉下拉框 */
    closeExpand() {
      if (
        document.getElementsByClassName("el-table__expand-icon--expanded")[0]
      ) {
        this.getSurgicalList();
      }

      this.addForm = {};
      this.csrSurgicalCasecar = [];
    },
  },
  watch: {
    "inputData.casecarno": function () {
      if (this.inputData.casecarno != undefined) {
        if (this.inputData.casecarno.length === 6) {
          this.inputData.casecarno = this.inputData.casecarno.toUpperCase();
        }
      }
    },
  },
};
</script>

<style lang="less" scope>
.board-center {
  margin: 0 auto;
}
.box-card {
  margin: 0 auto;
}

.el-row.bgcolor {
  padding: 5px;
  border: 3px solid #114f4a;
  background: #d4debc;
}
</style>