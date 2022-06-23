<template>
  <div style="width: 60%; margin: 0 auto">
    <h3>器械查詢</h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <template #default>
        <div>
          UDI代號：
          <el-input style="width: 20%" v-model="queryInfo.udi" />
          <el-button
            class="edit_button"
            style="margin-left: 10px"
            @click="searchValue()"
            >搜尋</el-button
          >
        </div>
      </template>
    </el-card>
    <el-card v-show="udi != ''" style="margin: 20px auto">
      <el-row gutter="20" >
        <el-col  :span="12" >
          <div
            v-for="(item, index) in udiType.csrUdiImages"
            :key="index"
          >
            <div v-if="index == '0'" style="width: 300px;" >
              <el-image
                :src="
                  'http://127.0.0.1:8282/HTPE/file/' +
                  item.csrFileResource.resourceName
                "
                :preview-src-list="srcList"
              >
              </el-image>
            </div>
            <div v-else style="width: 100px; float: left; margin: 10px 10px">
              <el-image
                :src="
                  'http://127.0.0.1:8282/HTPE/file/' +
                  item.csrFileResource.resourceName
                "
              >
              </el-image>
            </div>
          </div>
        </el-col>
        <el-col :offset="1" :span="10" v-if="udi != ''">
          <el-descriptions title="UDI資料" column="1">
            <el-descriptions-item label="UDI代號">{{
              udi
            }}</el-descriptions-item>
            <el-descriptions-item label="條碼代號">{{
              csrBarcode.barcode
            }}</el-descriptions-item>
            <el-descriptions-item label="中文名稱">{{
              udiType.name
            }}</el-descriptions-item>
            <el-descriptions-item label="英文名稱">{{
              udiType.nameScientific
            }}</el-descriptions-item>
            <el-descriptions-item label="規格">{{
              udiType.spec
            }}</el-descriptions-item>
            <el-descriptions-item label="描述">{{
              udiType.description
            }}</el-descriptions-item>
          </el-descriptions>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
//import { ElMessage } from "element-plus"
// import DateFmt from "../../assets/js/date";

export default {
  data() {
    return {
      picList: [],
      queryInfo: {
        udi: "",
        barcode: "",
      },

      udi: "",
      csrBarcode: {
        barcode: "",
      },
      udiType: {
        name: "",
        nameScientific: "",
        spec: "",
        description: "",
        csrUdiImages: {},
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

    //查詢barcode
    searchValue() {
      this.udi= "",
      this.csrBarcode= {
        barcode: "",
      },
      this.udiType= {
        name: "",
        nameScientific: "",
        spec: "",
        description: "",
        csrUdiImages: {},
      }
      let url = "";
      if (this.queryInfo.udi) {
        url += "udi=" + this.queryInfo.udi + "&";
      }
      if (this.queryInfo.barcode) {
        url += "barcode=" + this.queryInfo.udi + "&";
      }
      url = url.substring(0, url.length - 1);
      this.$axios.get("/search3/?" + url).then((res) => {
        if (res.data.data) {
          this.udi = res.data.data.udi;
          if (res.data.data.csrSetdataSeq) {
            this.csrBarcode = res.data.data.csrSetdataSeq.csrBarcode;
          }
          if (res.data.data.udiType) {
            this.udiType = res.data.data.udiType;
          }
        }

        console.log(res.data.data);
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.searchValue();
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