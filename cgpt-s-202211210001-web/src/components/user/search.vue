<template>
  <div class="board-center">
    <h3>物品查詢作業</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-table :data="reportList" style="width: 100%">
        <el-table-column label="查詢項目" align="center">
          <template #default="scope">
            <el-link :href="scope.row.url">{{ scope.row.name }}</el-link>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      reportList: [
        { name: "物品清單查詢", url: "/#/search2" },
        { name: "物品目前分布查詢（庫房） ", url: "/report/02" },
        { name: "物品目前分布查詢（病房/室別）", url: "/report/02" },
        { name: "交易查詢 ", url: "/report/02" },
        { name: "申領單數量 ", url: "/report/02" },
        { name: "手術對應盤包資料查詢 ", url: "/report/02" },
        { name: "物品再處理流程監控查詢 ", url: "/report/02" },
        { name: "物品再處理流程監控查詢 ", url: "/report/02" },
        { name: "條碼物品 8小時未入鍋入庫項目清單表  ", url: "/report/02" },
      ],
    };
  },
  created() {},
  methods: {},
};
</script>

<style lang="less" scope>
div {
  .board-center {
    margin: 0 auto;
    width: 80%;
  }
  .el-link{
      font-size: 20px;
  }
}
</style>