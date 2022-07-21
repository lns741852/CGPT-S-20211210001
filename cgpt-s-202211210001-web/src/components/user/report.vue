<template>
  <div class="board-center">
    <h3>報表一覽</h3>
    <!--卡片區塊-->
    <el-card class="box-card">
      <el-table :data="reportList" style="width: 100%">
        <el-table-column label="報表名稱" align="center">
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
        { name: "每月過期包一覽表（CSR） ", url: "/#/report2" },
        { name: "每月製作盤包種類及數目分佈圖  ", url: "/#/report3" },
        { name: "人員操作盤包數量統計 ", url: "/#/report4" },
        // { name: "病房送消件數統計表 ", url: "/report/02" },
        // { name: "各庫房物品沖帳一覽 ", url: "/report/02" },
        { name: "盤包製作/歸還列表 ", url: "/#/report6" },
        { name: "未使用歸還次數統計報表 ", url: "/#/report7" },
        { name: "瑕疵包補輸報表 ", url: "/#/report9" },
        // { name: "每月滅菌鍋監控作業統計表 ", url: "/report/02" },
        // { name: "滅菌鍋每月使用紀錄表 ", url: "/report/02" },
        { name: "滅菌鍋操作及測試紀錄報表 ", url: "/#/report8" },
        // { name: "每日器械盤包臨時申領紀錄表 ", url: "/report/02" },
        { name: "申領作業確認統計表 ", url: "/#/report5" },
        { name: "器械送修紀錄查詢", url: "/#/report11" },
        { name: "緊急申領包盤統計 ", url: "/#/report10" },
        // { name: "每月物品卸載統計 ", url: "/report/02" },
        // { name: "每月培養歸還次數統計報表 ", url: "/report/02" },
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