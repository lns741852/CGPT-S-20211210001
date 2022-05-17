<template>
  <div style="width: 100%">
    <h3>標籤重印</h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="製作庫房：" prop="depno">
            <el-select
              v-model="queryInfo.depno"
              placeholder="請選擇"             
              @change="selectDisinfection(this.queryInfo.depno)"
            >
              <el-option
                v-for="item in depnoList"
                :key="item.depno"
                :label="item.depname"
                :value="item.depno"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="條碼編號：" prop="barcode">
            <el-input style="width: 20%" v-model="queryInfo.barcode" />
          </el-form-item>
          <el-form-item label="盤包代號：" prop="setno">
            <el-input style="width: 20%" v-model="queryInfo.setno" />
          </el-form-item>
          <el-form-item label="選擇印表機：" prop="printer">
            <el-select
              v-model="queryInfo.printer"
              placeholder="請選擇"
              
            >
              <el-option
                v-for="item in printerList"
                :key="item.address"
                :label="item.printername"
                :value="item.address"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-button class="edit_button" @click="searchBarcode()">搜尋</el-button>
      </template>
    </el-card>
  </div>

  <el-card style="margin-top: 10px">
    <!--分頁-->
    <el-pagination
      :current-page="queryInfo.pageno"
      :page-size="queryInfo.pagesize"
      layout="total, prev, pager, next, jumper"
      :total="total"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
    <!--表格-->
    <el-table :data="reprintList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column prop="barcode" label="條碼名稱" width="300px">
      </el-table-column>
      <el-table-column prop="setno" label="盤包名稱" width="300px">
      </el-table-column>
      <el-table-column prop="location" label="目前位置" width="300px">
      </el-table-column>
      <el-table-column prop="status" label="狀態" width="300px">
        <template #default="scope">
          <span v-if="scope.row.status == 1"> 待入鍋</span>
          <span v-else-if="scope.row.status == 2"> 待入庫</span>
          <span v-else-if="scope.row.status == 3"> 已入庫</span>
          <span v-else-if="scope.row.status == 4"> 已配送</span>
          <span v-else-if="scope.row.status == 5"> 已使用</span>
        </template>
      </el-table-column>
    </el-table>
    <el-button class="edit_button" @click="submitFrom()">列印</el-button>
  </el-card>
</template>

<script>
//import { ElMessage } from "element-plus"
export default {
  data() {
    return {
      queryInfo: {
        depno: "",
        pageno: "",
        pagesize: "",
        barcode: "",
        setno: "",
      },
      reprintList: [],
      depnoList: [],
      printerList: [],
      multipleSelection: [],
    };
  },
  created() {
    this.getDepnoList();
  },
  mounted() {},
  methods: {
    /**列表查詢 */
    getDepnoList() {
      this.$axios.get("/depno", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.depnoList = res.data.data.list;
      });
    },

    //印表機查詢
    selectDisinfection(name) {
      if (name != "") {
        this.$axios.get("/depno_name/" + name).then((res) => {
          this.printerList = res.data.data.csrPrinter;
        });
      }
    },
    //查詢barcode
    searchBarcode() {   
      this.$axios.get("/reprint", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.reprintList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.searchBarcode();
    },
    //列印標籤
    submitFrom() {
      if (this.multipleSelection.length > 0) {
        this.$axios.post("/tag/reprint", this.multipleSelection).then(() => {
          this.queryInfo = {
            depno: "",
            pageno: "",
            pagesize: "",
            barcode: "",
            setno: "",
          };
          this.reprintList = [];
          this.printerList = [];
          this.multipleSelection = [];
        });
      }
    },
    //監聽選擇框
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
  },
  watch: {
    "queryInfo.setno": function () {
      if (this.queryInfo.setno != undefined) {
        if (this.queryInfo.setno.length === 6) {
          this.queryInfo.setno = this.queryInfo.setno.toUpperCase();
        }
      }
    },
  },
};
</script>

<style lang="less" scope>
</style>