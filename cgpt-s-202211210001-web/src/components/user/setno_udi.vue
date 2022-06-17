<template>
  <div style="width: 100%">
    <h3>配盤作業</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-row :gutter="24">
        <el-col :span="8">
          <!--輸入框-->
          <el-input
            placeholder="盤包代號 / 盤包名稱"
            v-model="queryInfo.searchName"
            clearable
            @clear="getSetnoList"
            @keyup.enter="getSetnoList"
          >
            <template #append>
              <el-button class="search_button" @click="getSetnoList">
                <el-icon><search /></el-icon>
              </el-button>
            </template>
          </el-input>
        </el-col>
      </el-row>
      <!--列表-->
      <el-table :data="setnoList" style="width: 100%" stripe>
        <el-table-column type="index" label="編號" width="60" />
        <el-table-column prop="setno" label="盤包代號" width="160" />
        <el-table-column prop="setname" label="英文名稱" />
        <el-table-column prop="setnamech" label="中文名稱" />
        <!-- <el-table-column prop="positionId" label="儲位" width="180" /> -->
        <!-- <el-table-column prop="numbaseCsr" label="CSR庫存量" width="80" />
        <el-table-column prop="numbaseCsr2" label="CSR2庫存量" width="80" />
        <el-table-column prop="numbaseOr1" label="AOR庫存量" width="80" />
        <el-table-column prop="numbaseOr2" label="BOR庫存量" width="80" />
        <el-table-column prop="numbaseOr3" label="GOR庫存量" width="80" />
        <el-table-column prop="numbaseWr1" label="WR庫存量" width="80" /> -->
        <el-table-column label="操作">
          <template #default="scope">
            <el-button  class="edit_button" @click="addUdi(scope.row.id)"
              >配盤</el-button
            >
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
export default {
  data() {
    return {
      queryInfo: {
        searchName: "",
        pageno: "",
        pagesize: "",
      },
      setnoList: [],
      total: "",
    };
  },
  created() {
    this.getSetnoList();
  },
  methods: {
    /**列表查詢 */
    getSetnoList() {
      this.$axios.get("/setno", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.setnoList = res.data.data.list;
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getSetnoList();
    },

    addUdi(id) {
      this.$router.push({ path: `/setno_udi2/${id}` });
    },
  },
};
</script>

<style lang="less" scope>
</style>