<template>
  <div style="width: 100%">
    <h3>8小時未入鍋入庫清單表</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <div class="searchBlock">
        <span> 製作庫房：</span>
        <el-select v-model="queryInfo.depno" placeholder="請選擇">
          <el-option
            v-for="item in depnoList"
            :key="item.depno"
            :label="item.depname"
            :value="item.depno"
          >
          </el-option>
        </el-select>
        <span> 製作日期：</span>
        <el-date-picker v-model="queryInfo.createdatetime" type="date">
        </el-date-picker>

        <span> 盤包代號：</span>
        <el-input maxlength="6" style="width: 10%" v-model="queryInfo.setno" />

        <span> 條碼狀態：</span>
        <el-select v-model="queryInfo.status" placeholder="請選擇">
          <el-option
            v-for="item in statusList"
            :key="item"
            :label="statusObj[item]"
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
        <el-table-column prop="setno" label="盤包代號" width="160" />
        <el-table-column prop="barcode" label="條碼編號" />
        <el-table-column prop="createdatetime" label="製作時間" />
        <el-table-column prop="disinfection" label="消毒方式" />
        <el-table-column prop="status" label="條碼狀態">
          <template #default="scope">
            <span v-if="scope.row.status == 1"> 待入鍋</span>
            <span v-else-if="scope.row.status == 2"> 待入庫</span>
          </template>
        </el-table-column>
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
    </el-card>
  </div>
</template>

<script>
import { ElMessage } from "element-plus";
// import {DateUtils} from "../../assets/js/date.js"

export default {
  data() {
    return {
      queryInfo: {
        searchName: "",
        pageno: "",
        pagesize: "",
        depno: null,
        createdatetime: null,
        setno: "",
        status: "1",
      },
      setnoList: [],
      depnoList: [],
      searchList: [],
      statusList: ["1", "2"],
      statusObj: {
        1: "待入鍋",
        2: "待入庫",
      },
      total: "",
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

      for (let i in this.queryInfo) {
        if (this.queryInfo[i] === "" || this.queryInfo[i] === null) {
          this.queryInfo[i] = null; //速度比delete快
        }
      }
      // if(this.queryInfo.createdatetime){
      //     this.queryInfo.createdatetime = JSON.stringify(this.queryInfo.createdatetime);
      //             console.log( this.queryInfo.createdatetime)
      // }
      this.$axios
        .get("/search/pot/warehousingn/", this.queryInfo)
        .then((res) => {
          this.total = res.data.data.total;
          this.searchList = res.data.data.list;
        });
    },

    /**列表查詢 */
    getDepnoList() {
      this.$axios.get("/depno", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.depnoList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getSetnoList();
    },
  },
};
</script>

<style lang="less" scope>
</style>