<template>
     <el-card class="box-card">
      <el-row :gutter="100">
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
        <el-col :span="12"> </el-col>
        <el-col :offset="11">
           <h3>盤包代號查詢</h3>
        </el-col>
      </el-row>
      <!--列表-->
      <el-table :data="setnoList" style="width: 100%">
        <el-table-column type="index" label="編號" width="100" />
        <el-table-column  label="盤包代號" width="200" >
         <template #default="scope">
           <el-link type="primary" @click="setInputValue(scope.row.setno)">{{scope.row.setno}}</el-link>    
        </template>
        </el-table-column>
        <el-table-column prop="setname" label="英文名稱" width="400" />
        <el-table-column prop="setnamech" label="中文名稱" width="400" />
        <el-table-column prop="numbaseCsr" label="CSR庫存量" />
        <el-table-column prop="numbaseCsr2" label="CSR2庫存量" />
        <el-table-column prop="numbaseOr1" label="AOR庫存量" />
        <el-table-column prop="numbaseOr2" label="BOR庫存量" />
        <el-table-column prop="numbaseOr3" label="GOR庫存量" />
        <el-table-column prop="numbaseWr1" label="WR庫存量" />
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
</template>

<script>
export default {
  data() {
    return {
      potdataList: [],
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
    /**顯示修改資料 */
    // showEditDialon(id) {
    //   this.$axios.get("/setno/" + id).then((res) => {
    //     this.addForm = res.data.data;
    //   });
    //   this.editDialogVisible = true;
    // }, 
    setInputValue(setno){
      localStorage.setItem('setno',setno)    
    }
  },
  watch: {
    "queryInfo.searchName": function () {
      if (this.queryInfo.searchName != undefined) {
        if (this.queryInfo.searchName.length === 6) {
          this.queryInfo.searchName = this.queryInfo.searchName.toUpperCase();
      
        }
      }
    },
  },


};
</script>

<style lang="less" scoped>
</style>