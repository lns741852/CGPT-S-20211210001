<template>
  <div style="width: 60%; margin: 0 auto">
    <h3>維修</h3>
    <el-card class="box-card">
      <!--表單驗證-->
      <div>
        <el-form
          ref="queryInfoRef"
          :model="queryInfo"
          label-width="15%"
          :rules="queryInfoRules"
        >
          <el-form-item label="UDI編號：">
            <el-input style="width: 40%" v-model="queryInfo.udi"></el-input>
          </el-form-item>

          <el-form-item>
            <el-radio-group v-model="queryInfo.status">
              <el-radio label="2">送修</el-radio>
              <el-radio label="0">歸還</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item>
            <el-radio-group v-model="type">
              <el-radio label="A">器械</el-radio>
              <el-radio label="B">器械盒</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
        <el-button class="edit_button" @click="getUDI()">查詢</el-button>
        <el-button
          class="edit_button"
          @click="inputFormData()"
          :disabled="UDIcheck"
          >確認</el-button
        >
      </div>
    </el-card>
    <el-card v-show="udiBox.udi != ''" style="margin: 20px auto">
        <el-descriptions>
         <el-descriptions-item label="UDI編號">{{
              udiBox.udi
            }}</el-descriptions-item>

        <el-descriptions-item label="狀態">
              <el-tag type="info" v-if="udiBox.status.trim() == 0"> 未使用</el-tag>
              <el-tag
                type="success"
                v-else-if=" udiBox.status.trim() == 1"
              >
                已使用</el-tag
              >
              <el-tag type="warning" v-else-if=" udiBox.status.trim() == 2"> 維修中</el-tag>
        </el-descriptions-item>







        </el-descriptions>
    </el-card>
    <el-card v-show="udi != ''" style="margin: 20px auto">
      <el-row>
        <el-col :offset="1" :span="10">
          <div
            v-for="(item, index) in udiType.csrUdiImages"
            :key="index"
            style="width: 100%"
          >
            <el-image
              v-if="index != 0"
              class="secondPic"
              :src="
                'http://127.0.0.1:8282/HTPE/file/' +
                item.csrFileResource.resourceName
              "
            >
            </el-image>
            <el-image
              v-else-if="index == 0"
              class="onePic"
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
              <el-link
                type="primary"
                :href="'/#/search2_2/' + csrBarcode.barcode"
                >{{ csrBarcode.barcode }}</el-link
              >
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
              <el-tag type="info" v-if="status == 0"> 未使用</el-tag>
              <el-tag
                type="success"
                v-else-if="status == 1 && csrBarcode.barcode != ''"
              >
                已使用</el-tag
              >
              <el-tag
                type="success"
                v-else-if="status == 1 && csrBarcode.barcode == ''"
              >
                已配盤</el-tag
              >
              <el-tag type="warning" v-else-if="status == 2"> 維修中</el-tag>
            </el-descriptions-item>
          </el-descriptions>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      UDIcheck: true,
      queryInfo: {
        udi: "",
        status: "",
      },
      type: "",
      udi: "",
      status: "",
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
      id:"",
      udiBox: {
        udi:"",
        status:""
      },
    };
  },
  created() {},
  methods: {
    /**UDI查詢*/
    async getUDI() {
        this.id ="",
      this.udiBox= {
        udi:"",
        status:""
      },


      (this.udi = ""),
        (this.csrBarcode = {
          barcode: "",
        }),
      this.udiType = {
        name: "",
        nameScientific: "",
        spec: "",
        description: "",
        csrUdiImages: {},
      };
      this.UDIcheck = true;

      if (this.queryInfo.udi == "") {
        ElMessage.error("請輸入UDI");
        return;
      }
      if (this.type == "") {
        ElMessage.error("請輸選擇類型");
        return;
      } else if (this.type == "A") {
        this.searchValue();
      } else {
        this.searchBox();
      }
    },

    //器械盒查詢
    searchBox() {
      this.$axios.get("/fix/box/" + this.queryInfo.udi).then((res) => {
        this.udiBox = res.data.data;
        this.UDIcheck = false;
      });
    },

    //查詢udi
    searchValue() {
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
          this.id = res.data.data.id
          this.udi = res.data.data.udi;
          this.status = res.data.data.status;
          if (res.data.data.csrSetdataSeq) {
            if (res.data.data.csrSetdataSeq.csrBarcode) {
              this.csrBarcode = res.data.data.csrSetdataSeq.csrBarcode;
            }
          }
          if (res.data.data.udiType) {
            this.udiType = res.data.data.udiType;
          }
          this.UDIcheck = false;
        }
      });
    },
    inputFormData() {
    let inputData={}
      if(this.udi !="" && this.queryInfo.status != this.status && this.status !="1"&& this.status !="3" && this.queryInfo.status != ""){
        inputData.status = this.queryInfo.status
        inputData.udi = this.udi
         this.$axios
          .put("/udi/"+this.id ,inputData)
          .then(() => {
            this.status = this.queryInfo.status
          });
      }else if(this.udiBox.udi != ""&&  this.queryInfo.status!= this.udiBox.status.trim() && this.udiBox.status.trim() !="1" && this.queryInfo.status != "" ){
       this.udiBox.status = this.queryInfo.status
       this.$axios.put("/box/" + this.udiBox.id, this.udiBox).then(() => {
        
        });
      }else{
        ElMessage.error("狀態異常")
      }


    },
  },
};
</script>

<style lang="less" scope>
</style>