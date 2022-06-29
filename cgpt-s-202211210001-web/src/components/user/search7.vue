<template>
  <div style="width: 100%">
    <h3>點班作業報表</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <div class="searchBlock">
        <span> 庫房：</span>
        <el-select v-model="queryInfo.depno" placeholder="請選擇">
          <el-option
            v-for="item in depnoList"
            :key="item.depno"
            :label="item.depname"
            :value="item.depno"
          >
          </el-option>
        </el-select>

        <span> 盤包代號：</span>
        <el-input maxlength="6" style="width: 10%" v-model="queryInfo.setno" />

        <span>基本量為0：</span>
        <el-select v-model="queryInfo.baseNum" placeholder="請選擇" clearable>
          <el-option
            v-for="item in numList"
            :key="item"
            :label="numObj[item]"
            :value="item"
          >
          </el-option>
        </el-select>


        <span>庫存量為0：</span>
        <el-select v-model="queryInfo.warehousingNum" placeholder="請選擇" clearable>
          <el-option
            v-for="item in numList"
            :key="item"
            :label="numObj[item]"
            :value="item"
          >
          </el-option>
        </el-select>

        <el-button
          class="edit_button"
          style="margin-left: 10px"
          @click="searchValue"
          >搜尋</el-button
        >
      </div>

      <!--列表-->
      <el-table :data="searchList" style="width: 100%" stripe>
        <el-table-column prop="SETNO" label="盤包代號" width="160" />
        <el-table-column prop="SETNAMECH" label="物品名稱" />
        <el-table-column prop="POSITION_ID" label="儲位" />
        <el-table-column prop="BASENUM" label="基本數" />
        <el-table-column prop="warehousingNum" label="庫存量" />
        <el-table-column prop="potNum" label="待入庫" />
        <el-table-column prop="tagNum" label="待入鍋" />
        <el-table-column prop="reNum" label="已歸還" />
      </el-table>

    </el-card>
  </div>
</template>

<script>
import { ElMessage } from "element-plus";
export default {
  data() {
    return {
      queryInfo: {
        searchName: "",
        depno: null,
        warehousingNum:"",
        baseNum:"",
        setno: "",
      },
      numList: [0,1],
      numObj:{
        0:"否",
        1:"是"
      },
      depnoList: [],
      searchList: [],
    };
  },
  created() {
    this.getDepnoList();
  },
  methods: {
    searchValue() {
      if (!this.queryInfo.depno) {
        ElMessage.error("請輸入庫房");
        return;
      }

      //去除空資料
      for (let i in this.queryInfo) {
        if (this.queryInfo[i] === "" || this.queryInfo[i] === null) {
          this.queryInfo[i] = null; //速度比delete快
        }
      }

      this.$axios
        .get("/search6/", this.queryInfo)
        .then((res) => {
          this.searchList = res.data.data;
          console.log(this.searchList)
        });
    },

    /**列表查詢 */
    getDepnoList() {
      this.$axios.get("/depno", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.depnoList = res.data.data.list;
      });
    },
  },
};
</script>

<style lang="less" scope>
</style>