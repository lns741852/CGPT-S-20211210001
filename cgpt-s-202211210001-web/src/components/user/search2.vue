<template>
  <div style="width: 100%">
    <h3>物品清單查詢</h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <template #default>
        <el-form
          ref="addFormRef"
          :model="addForm"
          label-width="120px"
          :rules="addFormRules"
        >
          <el-form-item label="庫房：">
            <el-select v-model="queryInfo.depno" placeholder="請選擇" clearable>
              <el-option
                v-for="item in depnoList"
                :key="item.depno"
                :label="item.depname"
                :value="item.depno"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="條碼編號：">
            <el-input style="width: 20%" v-model="queryInfo.barcode" />
          </el-form-item>
          <el-form-item label="盤包代號：">
            <el-input
              style="width: 20%"
              maxlength="6"
              v-model="queryInfo.setno"
            />
          </el-form-item>
          <el-form-item label="條碼序號：">
            <el-input
              style="width: 20%"
              maxlength="3"
              v-model="queryInfo.setsn"
            />
          </el-form-item>
          <el-form-item label="交易序號：">
            <el-input style="width: 20%" v-model="queryInfo.reqno" />
          </el-form-item>
          <el-form-item label="病歷號碼：">
            <el-input
              style="width: 20%"
              maxlength="8"
              v-model="queryInfo.patientno"
            />
          </el-form-item>
             <el-form-item label="申領庫房：">
            <el-select
              v-model="queryInfo.depnoask"
              placeholder="請選擇"
              @change="getRoomList(queryInfo.depnoask)"
              clearable
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
          <el-form-item label="室別：" prop="depno">
            <el-select
              v-model="queryInfo.roomno"
              clearable
              placeholder="請選擇"
            >
              <el-option
                v-for="item in roomList"
                :key="item.roomno"
                :label="item.roomname"
                :value="item.roomno"
              >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="狀態：">
            <el-select
              v-model="queryInfo.status"
              placeholder="請選擇"
              clearable
            >
              <el-option
                v-for="item in statusList"
                :key="item.value"
                :label="item.lable"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="過期狀態：">
            <el-radio-group v-model="queryInfo.timeoutStatus">
              <el-radio label="0">全部</el-radio>
              <el-radio label="1">未過期</el-radio>
              <el-radio label="2">警示</el-radio>
              <el-radio label="3">已過期</el-radio>
            </el-radio-group>
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
    <el-table :data="barcodeList" @selection-change="handleSelectionChange">
      <el-table-column label="條碼名稱" width="300px">
        <template #default="scope">
          <el-link type="primary" :href="'/#/search2_2/' + scope.row.barcode">{{
            scope.row.barcode
          }}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="setno" label="盤包名稱" width="300px">
      </el-table-column>
      <el-table-column prop="effectivedate" label="有效期限"> </el-table-column>
      <el-table-column prop="location" label="目前位置"> </el-table-column>
      <el-table-column prop="status" label="狀態">
        <template #default="scope">
          <span v-if="scope.row.status == 1"> 待入鍋</span>
          <span v-else-if="scope.row.status == 2"> 待入庫</span>
          <span v-else-if="scope.row.status == 3"> 已入庫</span>
          <span v-else-if="scope.row.status == 4"> 已配送</span>
          <span v-else-if="scope.row.status == 5"> 已使用</span>
          <span v-else-if="scope.row.status == 6"> 已歸還</span>
        </template>
      </el-table-column>

      <el-table-column label="過期狀態">
        <template #default="scope">
          <el-tag v-if="scope.row.effectivedateView == 0" type="warning"
            >過期警告</el-tag
          >
          <el-tag v-else-if="scope.row.effectivedateView < 0" type="danger"
            >過期</el-tag
          >
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
//import { ElMessage } from "element-plus"
import DateFmt from "../../assets/js/date";

export default {
  data() {
    return {
      queryInfo: {
        depno: "",
        barcode: "",
        setno: "",
        reqno: "",
        status: "",
        timeoutStatus: "1",
        patientno: "",
        setsn: "",
        pageno: "",
        pagesize: "",
        depnoask:"",
        roomno:""
      },
      statusList: [
        { value: "1", lable: "待入鍋" },
        { value: "2", lable: "待入庫" },
        { value: "3", lable: "已入庫" },
        { value: "4", lable: "已配送" },
        { value: "5", lable: "已使用" },
        { value: "6", lable: "已歸還" },
      ],
      date: new Date(),
      dateParse: "",
      barcodeList: [],
      depnoList: [],
      roomList:[],
      printerList: [],
      multipleSelection: [],
      depno:{}
    };
  },
  created() {
    this.getDepnoList();
    this.searchBarcode();
  },
  mounted() {},
  methods: {
    /**列表查詢 */
    getDepnoList() {
      this.$axios.get("/depno", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.depnoList = res.data.data.list;
        this.depnoList.forEach((i) => {
          this.depno[i.depno] = `${i.depname}`;
        });
      });
    },
        /**病房查詢 */
    getRoomList(depno) {
      this.queryInfo.roomno = "";
      this.$axios.get("/depno/room/" + depno).then((res) => {
        this.roomList = res.data.data;


      });
    },

    //查詢barcode
    searchBarcode() {
      this.$axios.get("/search/barcode", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.barcodeList = res.data.data.list;

        this.barcodeList.forEach((item) => {

        item.location =
          this.depno[item.location.trim()];

          item.effectivedate = item.effectivedate.substring(0, 10);
          item.effectivedateView = DateFmt.countDays(
            DateFmt.parseDate(item.effectivedate),
            this.date
          );
        });
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
          this.barcodeList = [];
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