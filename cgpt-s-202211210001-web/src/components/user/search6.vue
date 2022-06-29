<template>
  <div class="board-center" style="width: 60%">
    <h3>手術對應盤包資料查詢</h3>
    <!--卡片區塊-->
    <el-row class="bgcolor" :gutter="24">
      <el-col :span="10">
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
        <el-card style="width: 80%; margin: 0 auto">
          <el-table :data="csrSurgicalCasecar">
            <el-table-column prop="casecarno" label="個案車代號" align="center">
              <template #default="scope">
                <el-link
                  type="primary"
                  :href="'/#/search6_2/' +scope.row.casecarno"
                  >{{ scope.row.casecarno }}</el-link
                >
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-table-column>
      <el-table-column prop="surgicalno" label="手術代號" />
      <el-table-column prop="surgicalnamech" label="手術名稱" />
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