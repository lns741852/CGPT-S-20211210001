<template>
  <div class="board-center" style="width: 60%">
    <h3>個案車列表</h3>
    <!--卡片區塊-->
    <el-row class="bgcolor" :gutter="24">
      <el-col :span="8">
        <!--輸入框-->
        <el-input
          placeholder="個案車代號"
          v-model="queryInfo.searchName"
          clearable
          @clear="getCasecarList"
          @keyup.enter="getCasecarList"
        >
          <template #append>
            <el-button class="search_button" @click="getCasecarList">
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
        >新增個案車</el-button
      >
    </el-row>
    <!--列表-->
    <el-table
      :data="casecarList"
      style="width: 100%"
      @expand-change="showCasecarDetail"
      class="elTable"
      :cell-style="{ borderColor: 'black' }"
      :header-cell-style="{ borderColor: 'black' }"
    >
      <!--下拉框-->
      <el-table-column type="expand" label="#" width="100">
        <el-card class="downList" style="width: 100%">
          <el-table :data="casecarSetdatas">
            <el-table-column prop="csrSetdata3m.setno" label="盤包代號">
            </el-table-column>
            <el-table-column prop="csrSetdata3m.setnamech" label="盤包名稱">
            </el-table-column>
            <el-table-column prop="num" label="數量"> </el-table-column>
          </el-table>
        </el-card>
      </el-table-column>

      <el-table-column type="index" label="編號" width="100" align="center"/>
      <el-table-column prop="casecarno" label="個案車代號" align="center" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button
            class="edit_button"
            @click="showEditDialon(scope.row.casecarId)"
            >修改</el-button
          >
          <el-button
            class="delete_button"
            @click="deleteCasecar(scope.row.casecarno)"
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
      title="添加個案車"
    >
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="個案車代號" prop="casecarno" style="width: 90%">
            <el-input v-model="addForm.casecarno"></el-input>
          </el-form-item>
        </el-form>
        <!--自增列表-->
        <el-card class="box-card" style="width: 80%">
          <el-form :rules="addFormRules">
            <el-row gutter="10">
              <el-col span="2"> <el-form-item label="盤包代號" /></el-col>
              <el-col :span="5"><el-input v-model="inputData.setno" /></el-col>
              <el-col span="2" class="Edit_setno_font">盤包名稱</el-col>
              <el-col :span="5"
                ><el-input v-model="inputData.setnamech"
              /></el-col>
              <el-col class="Edit_setno_font" span="2">盤包數量</el-col>
              <el-col :span="3"><el-input v-model="inputData.num" /></el-col>
              <el-col class="Edit_setno_font" span="2" 
                ><el-link
                  href="http://127.0.0.1:8080/setno_search"
                  target="_blank"
                  >查詢盤包代號</el-link
                ></el-col
              >
              <el-col span="2" :offset="20" >
                <el-button class="edit_button" @click="inputSetno"
                  >輸入</el-button
                >
              </el-col>
            </el-row>
          </el-form>

          <el-table :data="casecarSetdatas" style="width: 100%">
            <el-table-column
              prop="csrSetdata3m.setno"
              label="盤包代號"
              width="180"
            >
            </el-table-column>
            <el-table-column
              prop="csrSetdata3m.setnamech"
              label="盤包名稱"
              width="180"
            >
            </el-table-column>
            <el-table-column prop="num" label="數量"> </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-button
                  class="delete_button"
                  @click="deleteSetno(scope.$index)"
                  >刪除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </template>

      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="addCasecar">確定</el-button>
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
      title="修改個案車"
    >
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="個案車代號" prop="casecarno">
            <el-input v-model="addForm.casecarno" style="width: 89%"></el-input>
          </el-form-item>
        </el-form>
        <!--自增列表-->
        <el-card class="box-card" style="width: 80%">
          <el-form label-width="120px" :rules="addFormRules">
            <el-row gutter="10">
              <el-col span="3"> <el-form-item label="盤包代號" /></el-col>
              <el-col :span="5"><el-input v-model="inputData.setno" /></el-col>
              <el-col class="Edit_setno_font" span="2">盤包名稱</el-col>
              <el-col :span="5"
                ><el-input v-model="inputData.setnamech"
              /></el-col>
              <el-col class="Edit_setno_font" span="2">盤包數量</el-col>
              <el-col :span="3"><el-input v-model="inputData.num" /></el-col>
              <el-col class="Edit_setno_font" span="2"
                ><el-link
                  href="http://127.0.0.1:8080/setno_search"
                  target="_blank"
                  >查詢盤包代號</el-link
                ></el-col
              >
              <el-col span="4" :offset="20">
                <el-button class="edit_button" @click="inputSetno"
                  >輸入</el-button
                >
              </el-col>
            </el-row>
          </el-form>

          <el-table :data="casecarSetdatas" style="width: 100%">
            <el-table-column
              prop="csrSetdata3m.setno"
              label="盤包代號"
              width="180"
            >
            </el-table-column>
            <el-table-column
              prop="csrSetdata3m.setnamech"
              label="盤包名稱"
              width="180"
            >
            </el-table-column>
            <el-table-column prop="num" label="數量"> </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-button
                  class="delete_button"
                  @click="deleteSetno(scope.$index)"
                  >刪除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </template>

      <template #footer>
        <div class="dialog-footer">
          <el-button class="edit_button" @click="editCasecar">確定</el-button>
          <el-button type="info" @click="editDialogVisible = false"
            >取消</el-button
          >
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>

import { ElMessage } from "element-plus"


export default {
  data() {
    return {
      queryInfo: {
        searchName: "",
        pageno: "",
        pagesize: "",
      },
      inputData: {},
      casecarList: [],
      casecarSetdatas: [],
      total: "",
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {},
      addFormRules: {
        casecarno: [
          { required: true, message: "請輸入個案車代號...", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getCasecarList();
  },
  methods: {
    /**列表查詢 */
    getCasecarList() {
      this.queryInfo.searchName = this.queryInfo.searchName.toUpperCase();
      this.$axios.get("/casecar", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.casecarList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getCasecarList();
    },
    /**清空訊息 */
    addDialogClosed() {
      this.addForm = {};
      this.inputData = {};
      this.casecarSetdatas = [];
    },
    /**清空訊息 */
    editDialogClosed() {
      this.addForm = {};
      this.inputData = {};
      this.casecarSetdatas = [];
    },
    /**新增盤包 */
    addCasecar() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.addForm.casecarSetdatas = this.casecarSetdatas;
        this.$axios.post("/casecar", this.addForm).then(() => {
          this.addDialogVisible = false;
          this.getCasecarList();
        });
      });
    },
    /**編輯 */
    editCasecar() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        this.addForm.casecarSetdatas = this.casecarSetdatas;
        this.$axios
          .put("/casecar/" + this.addForm.casecarId, this.addForm)
          .then(() => {
            this.editDialogVisible = false;
            this.getCasecarList();
          });
      });
    },
    /**顯示修改資料 */
    showEditDialon(id) {
      console.log(id)
      this.$axios.get("/casecar/" + id).then((res) => {
        this.addForm = res.data.data;
        this.casecarSetdatas = res.data.data.casecarSetdatas;
      });
      this.editDialogVisible = true;
    },
    /**刪除 */
    deleteCasecar(casecarno) {
      this.$msgbox
        .confirm("確定要刪除 " + casecarno + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/casecar/" + casecarno).then(() => {
            this.getCasecarList();
          });
        })
        .catch(() => {
          //nothing to do
        });
    },
    /**盤包輸入 => 列表 */
    inputSetno() {
      //數字是否正確
      if (isNaN(this.inputData.num)) {
          ElMessage.error("請輸入正確數量");
        return true;
      }     
      var i = 0;
      this.setnoCheckList = localStorage.getItem("setnoAll").split(",");
      //盤包重複判斷
      this.casecarSetdatas.some((e) => {
        if (e.csrSetdata3m.setno === this.inputData.setno) {
          e.num = Number(e.num) + Number(this.inputData.num);
          i = i + 1;
        }
      });
      if (i !== 0) {
        return true;
      }
      //盤包是否正確
      this.setnoCheckList.some((e) => {
        if (e === this.inputData.setno) {
          this.casecarSetdatas.push({
            csrSetdata3m: {
              setno: this.inputData.setno,
              setnamech: this.inputData.setnamech,
            },
            num: this.inputData.num,
          });         
          return true;
        }
      });       
    },
    /**刪除盤包 */
    deleteSetno(id) {
      this.casecarSetdatas.splice(id, 1);
    },
    /**下拉內容 */
    showCasecarDetail(row) {
      this.$axios.get("/casecar/" + row.casecarId).then((res) => {
        this.addForm = res.data.data;
        this.casecarSetdatas = res.data.data.casecarSetdatas;
      });
    },
    /**關閉下拉框 */
    closeExpand() {
      if (
        document.getElementsByClassName("el-table__expand-icon--expanded")[0]
      ) {
        this.getCasecarList();
      }

      this.addForm = {};
      this.casecarSetdatas = [];
    },
  },
  watch: {
    "inputData.setno": function () {
      if (this.inputData.setno != undefined) {
        if (this.inputData.setno.length === 6) {
          this.inputData.setno = this.inputData.setno.toUpperCase();
          this.$axios.get("/setdata/" + this.inputData.setno).then((res) => {
            if(res.data.data !== null){
              this.inputData.setnamech = res.data.data.setnamech;
            }else{
              this.inputData.setnamech=""              
            }
          });
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
   padding: 10px 0 5px 0;
  border: 3px solid #114f4a;
  background: #d4debc;
}

</style>