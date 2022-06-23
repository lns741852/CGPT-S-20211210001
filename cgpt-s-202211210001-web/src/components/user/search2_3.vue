<template>
  <div style="width: 100%">
    <h3>單位申請單列表</h3>
    <el-card class="box-card">
      <!--表單-->
      <el-descriptions title="" border column="1">
        <el-descriptions-item label="類型：" label-class-name="my-label">
          <span v-if="resData.req.allocatetype == 'G'">臨時申領</span>
          <span v-else-if="resData.req.allocatetype == 'B'">補輸</span>
          <span v-else-if="resData.req.allocatetype == 'I'">瑕疵補輸</span>
        </el-descriptions-item>
        <el-descriptions-item label="交易序號：" label-class-name="my-label">{{
          resData.req.reqno
        }}</el-descriptions-item>

        <el-descriptions-item label="配送庫房：" label-class-name="my-label">{{
          resData.req.depno
        }}</el-descriptions-item>
        <el-descriptions-item label="申領庫房：" label-class-name="my-label">{{
          resData.req.depnoask
        }}</el-descriptions-item>
        <el-descriptions-item label="室別：" label-class-name="my-label">{{
          resData.req.roomno
        }}</el-descriptions-item>
        <el-descriptions-item label="成本中心：" label-class-name="my-label">{{
          resData.req.centerno
        }}</el-descriptions-item>
        <el-descriptions-item label="病歷號碼" label-class-name="my-label">{{
          resData.req.patientno
        }}</el-descriptions-item>
        <el-descriptions-item label="配送庫房：" label-class-name="my-label">{{
          resData.req.depno
        }}</el-descriptions-item>
        <el-descriptions-item label="是否配送：" label-class-name="my-label">
          <span v-if="resData.req.checking == 'Y'">是</span>
          <span v-else
            >否</span
          ></el-descriptions-item
        >

        <el-descriptions-item label="申請：" label-class-name="my-label">
          <el-row gutter="24">
            <el-col :span="8">
              申請人員：
              <el-input v-model="resData.req.usercname" style="width: 60%" />
            </el-col>
            <el-col :span="9">
              申請時間：
              <el-input v-model="resData.req.datatime" style="width: 60%" />
            </el-col>
          </el-row>
        </el-descriptions-item>

        <el-descriptions-item label="配送：" label-class-name="my-label">
          <el-row gutter="24">
            <el-col :span="8">
              配送人員：
              <el-input v-model="resData.req.datausername" style="width: 60%" />
            </el-col>
            <el-col :span="9">
              配送時間：
              <el-input v-model="resData.req.allocatetime" style="width: 60%" />
            </el-col>
          </el-row>
        </el-descriptions-item>
      </el-descriptions>
    </el-card>
  </div>

  <el-card style="margin-top: 10px">
    <el-table :data="resData.reqDetail">
      <el-table-column prop="setno" label="盤包代號"> </el-table-column>
      <el-table-column prop="setcount" label="請領數"> </el-table-column>
      <el-table-column prop="realcount" label="轉出數"> </el-table-column>
    </el-table>
    <el-table :data="resData.barcode">
      <el-table-column prop="setno" label="盤包代號"> </el-table-column>
      <el-table-column prop="barcode" label="條碼編號">
        <template #default="scope">
          <el-link type="primary" :href="'/#/search2_2/' + scope.row.barcode">{{
            scope.row.barcode
          }}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="目前狀態">
        <template #default="scope">
          <span v-if="scope.row.status === 1">標籤列印 </span>
          <span v-else-if="scope.row.status == 2">入鍋 </span>
          <span v-else-if="scope.row.status == 3">入庫 </span>
          <span v-else-if="scope.row.status == 4">配送 </span>
          <span v-else-if="scope.row.status == 5">使用 </span>
          <span v-else-if="scope.row.status == 6">歸還 </span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      id: this.$route.params.id,
      depno: {},
      pname: {},
      depnopay: {},
      resData: {
        req: {},
        barcode: [],
        reqDetail: [],
      },
    };
  },
  created() {
    this.getCostcenterList();
    this.getDepnoList();
    this.getReqData();
  },
  mounted() {},
  methods: {
    async getReqData() {
      await this.$axios.get("/search/req/" + this.id).then((res) => {
        this.resData = res.data.data;

          this.resData.req.centerno =
          this.depnopay[ this.resData.req.centerno];
          this.resData.req.depno =
          this.depno[ this.resData.req.depno];
          this.resData.req.depnoask =
          this.depno[ this.resData.req.depnoask];
        if (
          this.resData.req.datatime != "" &&
          this.resData.req.datatime != null
        ) {
          this.resData.req.datatime = this.resData.req.datatime
            .substring(0, 19)
            .replace("T", " ");
        }

        if (
          this.resData.req.allocatetime != "" &&
          this.resData.req.allocatetime != null
        ) {
          this.resData.req.allocatetime = this.resData.req.allocatetime
            .substring(0, 19)
            .replace("T", " ");
        }
      });
    },

    /**列表查詢 */
    async getDepnoList() {
      await this.$axios.get("/depno").then((res) => {
        this.depnoList = res.data.data.list;
        this.depnoList.forEach((i) => {
          this.depno[i.depno] = `${i.depname}`;
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
  },
};
</script>

<style lang="less" scope>
.my-label {
  width: 15%;
}
</style>