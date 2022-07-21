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
      <el-row  >
        <el-col :offset="1" :span="10" >
          <div
            v-for="(item, index) in udiType.csrUdiImages"
            :key="index"
            style=" width:100%"
          >
              <el-image v-if="index !=0" class="secondPic"
                :src="
                  'http://127.0.0.1:8282/HTPE/file/' +
                  item.csrFileResource.resourceName
                "
              >
              </el-image>
              <el-image v-else-if="index ==0" class="onePic"
                :src="
                  'http://127.0.0.1:8282/HTPE/file/' +
                  item.csrFileResource.resourceName
                "
              >
              </el-image>
          </div>

        
        </el-col>
        <el-col :offset="1" :span="10" v-if="udi != ''">
          <el-descriptions title="UDI資料" column="1">
            <el-descriptions-item label="UDI代號">{{
              udi
            }}</el-descriptions-item>
            <el-descriptions-item label="條碼代號">
                <el-link type="primary" :href="'/#/search2_2/' +csrBarcode.barcode">{{
                  csrBarcode.barcode
                }}</el-link>
            </el-descriptions-item>
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
            <el-descriptions-item label="狀態">
                <el-tag type="info" v-if="status == 0"> 未使用</el-tag >
                <el-tag type="success"  v-else-if="status == 1 &&  csrBarcode.barcode !=''"> 已使用</el-tag >
                 <el-tag type="success"  v-else-if="status == 1 &&  csrBarcode.barcode ==''"> 已配盤</el-tag >
                <el-tag type="warning" v-else-if="status == 2"> 維修中</el-tag >
            </el-descriptions-item>
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
      queryInfo: {
        udi: "",
        barcode: "",
      },

      udi: "",
      status:"",
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

    };
  },
  created() {

  },
  mounted() {},
  methods: {
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
          this.status= res.data.data.status;
          if (res.data.data.csrSetdataSeq) {
            if(res.data.data.csrSetdataSeq.csrBarcode){
                  this.csrBarcode = res.data.data.csrSetdataSeq.csrBarcode;
            }
          
          }
          if (res.data.data.udiType) {
            this.udiType = res.data.data.udiType;
          }
        }

      });
    },
  },
};
</script>

<style lang="less" scope>

.secondPic{
  float: left;
  margin: 10px 20px 0 0;
  width: 30%;
  border-width:1px; border-style:solid;
}
.onePic{
  display: block;
  margin-top: 20px;
  width: 100%;
  border-width:1px; border-style:solid;
}
</style>