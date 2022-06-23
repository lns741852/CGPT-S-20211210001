<template>
  <div style="width: 100%">
    <h3>醫療物品歷史追蹤資料</h3>
    <el-card class="box-card">
      <!--表單-->
      <el-descriptions title="" border column="1">
        <el-descriptions-item label="條碼編號：" label-class-name="my-label">{{
          barcodeTable.BARCODE
        }}</el-descriptions-item>
        <el-descriptions-item label="盤包代號：" label-class-name="my-label">{{
          barcodeTable.SETNO
        }}</el-descriptions-item>
        <el-descriptions-item
          label="盤包英文名稱："
          label-class-name="my-label"
          >{{ barcodeTable.SENAME }}</el-descriptions-item
        >
        <el-descriptions-item
          label="盤包中文名稱："
          label-class-name="my-label"
          >{{ barcodeTable.SCNAME }}</el-descriptions-item
        >
        <!---->
        <el-descriptions-item
          label="條碼製作資料："
          label-class-name="my-label"
        >
          <el-row gutter="24">
            <el-col :span="5">
              製作部門：
              <el-input v-model="barcodeTable.DEPNO" style="width: 60%" />
            </el-col>
            <el-col :span="3">
              序號：
              <el-input v-model="barcodeTable.SETSN" style="width: 40%" />
            </el-col>
            <el-col :span="5">
              製作日期：
              <el-input
                v-model="barcodeTable.CREATEDATETIME"
                style="width: 60%"
              />
            </el-col>
            <el-col :span="5">
              操作人員：
              <el-input
                v-model="barcodeTable.DATAUSERNAME"
                style="width: 60%"
              />
            </el-col>
          </el-row>
        </el-descriptions-item>
        <!---->
        <el-descriptions-item label="入鍋資料：" label-class-name="my-label">
          <el-row gutter="24">
            <el-col :span="8">
              消毒部門：
              <el-input v-model="barcodeTable.PDEP" style="width: 60%" />
            </el-col>
            <el-col :span="16">
              消毒方式：
              <el-input v-model="barcodeTable.PNAME" style="width: 30%" />
            </el-col>
            <el-col :span="8" style="margin-top:10px">
              消毒鍋別：
              <el-input v-model="barcodeTable.PNO" style="width: 60%" />
            </el-col>
            <el-col :span="16" style="margin-top:10px">
              消毒鍋次：
              <el-input v-model="barcodeTable.PSN" style="width: 30%" />
            </el-col>
            <el-col :span="8" style="margin-top:10px">
              入鍋時間：
              <el-input v-model="barcodeTable.PTIME" style="width: 60%" />
            </el-col>
            <el-col :span="16" style="margin-top:10px">
              操作人員：
              <el-input v-model="barcodeTable.PUSER" style="width: 30%" />
            </el-col>
          </el-row>
        </el-descriptions-item>
        <!---->
        <el-descriptions-item label="入庫資料：" label-class-name="my-label">
          <el-row gutter="24">
            <el-col :span="6">
              入庫部門：
              <el-input v-model="barcodeTable.DEPNOSTORE" style="width: 60%" />
            </el-col>
            <el-col :span="9">
              入庫日期：
              <el-input
                v-model="barcodeTable.WEARHOUSEDATETIME"
                style="width: 60%"
              />
            </el-col>
            <el-col :span="6">
              操作人員：
              <el-input
                v-model="barcodeTable.WEARHOUSEUSERNAME"
                style="width: 60%"
              />
            </el-col>
          </el-row>
        </el-descriptions-item>
        <!---->
        <el-descriptions-item label="病歷號碼：" label-class-name="my-label">{{
          barcodeTable.PATIENTNO
        }}</el-descriptions-item>
        <el-descriptions-item label="有效期限：" label-class-name="my-label">{{
          barcodeTable.EFFECTIVEDATE
        }}</el-descriptions-item>
        <el-descriptions-item label="目前位置：" label-class-name="my-label">{{
          barcodeTable.LOCATION
        }}</el-descriptions-item>
        <el-descriptions-item label="目前狀態：" label-class-name="my-label">
          <span v-if="barcodeTable.STATUS == 1">待入鍋</span>
          <span v-if="barcodeTable.STATUS == 2">待入庫</span>
          <span v-if="barcodeTable.STATUS == 3">已入庫</span>
          <span v-if="barcodeTable.STATUS == 4">已配送</span>
          <span v-if="barcodeTable.STATUS == 5">已使用</span>
          <span v-if="barcodeTable.STATUS == 6">已歸還</span>
        </el-descriptions-item>
        <!---->
        <el-descriptions-item label="去汙區資料：" label-class-name="my-label">
          <el-row gutter="24">
            <el-col :span="8">
              歸還日期：
              <el-input v-model="barcodeTable.RECEIVETIME" style="width: 60%" />
            </el-col>
            <el-col :span="16">
              歸還人員：
              <el-input
                v-model="barcodeTable.RECEIVEUSERNAME"
                style="width: 30%"
              />
            </el-col>
            <el-col :span="8" style="margin-top:10px">
              清洗日期：
              <el-input v-model="barcodeTable.RECEIVETIME" style="width: 60%" />
            </el-col>
            <el-col :span="16" style="margin-top:10px">
              清洗人員：
              <el-input
                v-model="barcodeTable.CLEARUSERNAME"
                style="width: 30%"
              />
            </el-col>
            <el-col :span="8" style="margin-top:10px">
              去汙鍋別：
              <el-input v-model="barcodeTable.DPOTNAME" style="width: 60%" />
            </el-col>
            <el-col :span="16" style="margin-top:10px">
              去汙鍋次：
              <el-input
                v-if="barcodeTable.DPOTSN == 0"
                style="width: 30%"
              ></el-input>
              <el-input
                v-else
                v-model="barcodeTable.DPOTSN"
                style="width: 30%"
              />
            </el-col>
          </el-row>
        </el-descriptions-item>

        <!---->

        <el-descriptions-item label="成本中心：" label-class-name="my-label">{{
          barcodeTable.DEPNOPAY
        }}</el-descriptions-item>
      </el-descriptions>
    </el-card>
  </div>

  <el-card style="margin-top: 10px">
    <el-table :data="HistoryList">
      <el-table-column prop="RNO" label="交易序號"> 
        <template #default="scope">
          <el-link type="primary" :href="'/#/search2_3/' + scope.row.REQ_ID">{{
            scope.row.RNO
          }}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="LOCATION" label="位置" width="100px">
      </el-table-column>
      <el-table-column prop="USERNAME" label="使用人員" width="150px">
      </el-table-column>
      <el-table-column prop="DUTYNAME" label="操作人員" width="150px">
      </el-table-column>
      <el-table-column prop="PATIENTNO" label="病歷號碼" width="150px">
      </el-table-column>
      <el-table-column prop="USERTIME" label="操作時間"> </el-table-column>
      <el-table-column prop="ACTION" label="動作">
        <template #default="scope">
          <span v-if="scope.row.ACTION.trim() === 'W'">標籤列印 </span>
          <span v-else-if="scope.row.ACTION.trim() == 'X'">入鍋 </span>
          <span v-else-if="scope.row.ACTION.trim() == 'Y'">入庫 </span>
          <span v-else-if="scope.row.ACTION.trim() == 'A'">配送 </span>
          <span v-else-if="scope.row.ACTION.trim() == 'C'">轉出 </span>
          <span v-else-if="scope.row.ACTION.trim() == 'D'">使用 </span>
          <span v-else-if="scope.row.ACTION.trim() == 'J'">歸還 </span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      barcode: this.$route.params.barcode,
      barcodeTable: {},
      HistoryList: [],
      depno: {},
      pname: {},
      depnopay: {},
    };
  },
  async created() {
    await this.getCostcenterList();
    await this.getDepnoList();
    await this.getDisinfection();
    await this.getBarcode();
    await this.getHistoryList();
  },
  mounted() {},
  methods: {
    /**列表查詢 */
    async getDepnoList() {
      await this.$axios.get("/depno").then((res) => {
        this.depnoList = res.data.data.list;
        this.depnoList.forEach((i) => {
          this.depno[i.depno] = `${i.depname}`;
        });
      });
    },
    /**消毒鍋 */
    async getDisinfection() {
      await this.$axios.get("/disinfection").then((res) => {
        this.disinfectionList = res.data.data.list;
        this.disinfectionList.forEach((i) => {
          this.pname[i.disinfection] = `${i.potname}`;
        });
      });
    },
    /**成本中心查詢 */
    getCostcenterList() {
      this.$axios.get("/costcenter").then((res) => {
        this.costcenterList = res.data.data;
        this.costcenterList.forEach((i) => {
          this.depnopay[i.centerno] = `${i.centername}`;
        });
      });
    },

    /**Barcode查詢 */
    async getBarcode() {
      await this.$axios.get("/search/barcode/" + this.barcode).then((res) => {
        this.barcodeTable = res.data.data;

        this.barcodeTable.DEPNOPAY =
          this.depnopay[this.barcodeTable.DEPNOPAY.trim()];
  
        this.barcodeTable.DEPNO = this.depno[this.barcodeTable.DEPNO.trim()];

        this.barcodeTable.DEPNOSTORE =
          this.depno[this.barcodeTable.DEPNOSTORE.trim()];
        this.barcodeTable.LOCATION =
          this.depno[this.barcodeTable.LOCATION.trim()];

        if (this.barcodeTable.PNAME != "" && this.barcodeTable.PNAME != null) {
                this.barcodeTable.PNAME = this.pname[this.barcodeTable.PNAME.trim()];
        }
        if (this.barcodeTable.PDEP != "" && this.barcodeTable.PDEP != null) {
                    this.barcodeTable.PDEP = this.depno[this.barcodeTable.PDEP.trim()];
        }





        if (
          this.barcodeTable.CREATEDATETIME != "" &&
          this.barcodeTable.CREATEDATETIME != null
        ) {
          this.barcodeTable.CREATEDATETIME =
            this.barcodeTable.CREATEDATETIME.substring(0, 10);
        }

        if (this.barcodeTable.PTIME != "" && this.barcodeTable.PTIME != null) {
          this.barcodeTable.PTIME = this.barcodeTable.PTIME.substring(
            0,
            19
          ).replace("T", " ");
        }
        if (
          this.barcodeTable.WEARHOUSEDATETIME != "" &&
          this.barcodeTable.WEARHOUSEDATETIME != null
        ) {
          this.barcodeTable.WEARHOUSEDATETIME =
            this.barcodeTable.WEARHOUSEDATETIME.substring(0, 19).replace(
              "T",
              " "
            );
        }
        if (
          this.barcodeTable.EFFECTIVEDATE != "" &&
          this.barcodeTable.EFFECTIVEDATE != null
        ) {
          this.barcodeTable.EFFECTIVEDATE =
            this.barcodeTable.EFFECTIVEDATE.substring(0, 10);
        }
        if (
          this.barcodeTable.RECEIVETIME != "" &&
          this.barcodeTable.RECEIVETIME != null
        ) {
          this.barcodeTable.RECEIVETIME =
            this.barcodeTable.RECEIVETIME.substring(0, 10);
        }
      });
    },
    //歷史紀錄
    async getHistoryList() {
      await this.$axios.get("/search/history/" + this.barcode).then((res) => {
        this.HistoryList = res.data.data;

        this.HistoryList.forEach((i) => {
          if (i.USERTIME != "" && i.USERTIME != null) {
            i.USERTIME = i.USERTIME.substring(0, 19).replace("T", " ");

            this.depnoList.forEach((e) => {
              if (i.LOCATION.trim() === e.depno) {
                i.LOCATION = this.depno[i.LOCATION.trim()];
              }
            });
          }
        });
      });
    },
  },
};
</script>

<style lang="less" scope>
.my-label {
  width: 15%;
}
</style>