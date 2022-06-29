<template>
  <div class="board-center" style="width: 60%">
    <h3>個案車代號資料</h3>
    <!--卡片區塊-->
    <el-row class="bgcolor" :gutter="24">
      <el-col>
        <span>個案車代號：{{ casecarno }}</span>
      </el-col>
    </el-row>

      <!--列表-->
      <el-table
        :data="casecarSetdatas"
        style="width: 100%"
        @expand-change="showCasecarDetail"
        class="elTable"
        :cell-style="{ borderColor: 'black' }"
        :header-cell-style="{ borderColor: 'black' }"
      >
        <el-table-column prop="csrSetdata3m.setno" label="盤包代號" width="180" align="center"/>
        <el-table-column
          prop="csrSetdata3m.setnamech"
          label="盤包名稱"
          width="180"
          align="center"
        />

        <el-table-column prop="num" label="數量" align="center"/> 
      </el-table>

  </div>
</template>

<script>
export default {
  data() {
    return {
      casecarno: this.$route.params.casecarno,
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
    this.showEditDialon(this.casecarno);
  },
  methods: {
    /**顯示修改資料 */
    showEditDialon(casecarno) {
      this.$axios.get("/casecar/no/" + casecarno).then((res) => {
        this.addForm = res.data.data;
        this.casecarSetdatas = res.data.data.casecarSetdatas;
        console.log(this.addForm);
      });
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