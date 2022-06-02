<template>
  <h3>盤包序號維護</h3>
  <el-card class="box-card">
    <!--標籤頁-->
    <el-tabs v-model="tabChange" type="card" @tab-click="clearForm">
      <!--第一頁-->
      <el-tab-pane label="盤包序號列表">
        <el-row :gutter="100">
          <el-col :span="8">
            <!--輸入框-->
            <el-input
              placeholder="盤包代號"
              v-model="queryInfo.searchName"
              clearable
              @clear="getSeqList"
              @keyup.enter="getSeqList"
            >
              <template #append>
                <el-button class="search_button" @click="getSeqList">
                  <el-icon><search /></el-icon>
                </el-button>
              </template>
            </el-input>
          </el-col>
          <el-col :span="12"> </el-col>
        </el-row>
        <!--列表-->
        <el-table :data="seqList" style="width: 100%">
          <el-table-column type="index" label="編號" width="80" />
          <el-table-column prop="setno" label="盤包代號" />
          <el-table-column prop="setnamech" label="盤包名稱" />
          <el-table-column prop="seq" label="序號" />
          <el-table-column label="操作">
            <template #default="scope">
              <el-button
                class="edit_button"
                @click="showEditTabPan(scope.row.id)"
                >修改</el-button
              >
              <el-button
                class="delete_button"
                @click="deleteSeq(scope.row.id, scope.row.setnamech)"
                >刪除</el-button
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
      </el-tab-pane>
      <!--第二頁-->
      <el-tab-pane label="盤包序號製作">
        <div class="contain_center">
          <el-form
            ref="addFormRef"
            :model="addForm"
            label-width="120px"
            :rules="addFormRules"
          >
            <el-row>
              <el-col span="3">
                <el-form-item label="盤包代號" prop="setno"
              /></el-col>
              <el-col span="2"><el-input v-model="addForm.setno" /></el-col>
              <el-col class="Edit_setno_font" span="2">盤包名稱</el-col>
              <el-col span="2"><el-input v-model="addForm.setnamech" /></el-col>
              <el-col class="Edit_setno_font" span="2">盤包序號</el-col>
              <el-col span="2"><el-input v-model="addForm.seq" /></el-col>
              <el-col class="Edit_setno_font" span="2"
                ><router-link to="/setno_search" target="_blank">查詢盤包代號</router-link></el-col
              >
              <el-col span="4" :offset="2">
                <el-button id="add_button" class="edit_button" @click="addSeq"
                  >新增</el-button
                >
                <el-button id="edit_button" class="edit_button" @click="editSeq"
                  >修改</el-button
                >
                <el-button
                  class="delete_button"
                  id="delete_button"
                  @click="deleteSeq(this.addForm.id)"
                  >刪除</el-button
                >
              </el-col>
            </el-row>
          </el-form>
          <!--穿梭框-->
          <el-transfer
            v-model="value"
            :data="data"
            filterable
            :filter-method="filterMethod"
            filter-placeholder="UDI代號"
            :titles="[
              '未使用UDI &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 中文名稱 ',
              '已使用UDI &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 中文名稱 ',
            ]"
          >
            <template #default="{ option }">
              <span class="udiList">{{ option.label }}</span>
              <span class="udiList">{{ option.cname }}</span>
            </template>
          </el-transfer>
        </div>
      </el-tab-pane>
    </el-tabs>
  </el-card>
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
      seqList: [],
      unUDIlist: [],
      total: "",
      addForm: {
        ids: "",
      },
      addFormRules: {
        setno: [
          { required: true, message: "請輸入盤包代號...", trigger: "blur" },
        ],
      },
      value: [],
      data: [],
    };
  },
  created() {
    this.getSeqList();
    this.getUnUDIList();
  },
  mounted() {
    window.addEventListener("setItem", function (e) {
      console.log(e);
      this.addForm.setno = localStorage.getItem("setno");
    });
  },
  methods: {
    /**列表查詢 */
    getSeqList() {
      this.$axios.get("/seq", this.queryInfo).then((res) => {
        this.total = res.data.data.total;
        this.seqList = res.data.data.list;
      });
    },
    /**未使用UDI查詢 */
    getUnUDIList() {
      this.$axios.get("/seq/un/udi").then((res) => {
        this.unUDIlist = res.data.data;
        this.unUDIlist.forEach((udi) => {
          this.data.push({
            label: udi.CODE,
            key: udi.ID,
            cname: udi.CNAME,
            ename: udi.ENAME,
          });
        });
      });
    },
    /**監聽頁面刷新 */
    handleCurrentChange(newPage) {
      this.queryInfo.pageno = newPage;
      this.getSeqList();
    },
    /**新增Seq */
    addSeq() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        //陣列轉字串
        for (let i = 0; i < this.value.length; i++) {
          this.addForm.ids = this.addForm.ids + "," + this.value[i];
        }

        this.addForm.ids = this.addForm.ids.substring(1);
        this.$axios.post("/seq", this.addForm).then(() => {
          this.clearForm();
          this.getSeqList();
          document.getElementById("tab-0").click();

        });
      });
    },
    /**修改 */
    editSeq() {
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        //陣列轉字串
        for (let i = 0; i < this.value.length; i++) {
          this.addForm.ids = this.addForm.ids + "," + this.value[i];
        }

        this.addForm.ids = this.addForm.ids.substring(1);
        this.$axios.put("/seq/" + this.addForm.id, this.addForm).then(() => {
          this.clearForm();
          document.getElementById("tab-0").click();
        });
      });
    },
    /**顯示修改資料 */
    showEditTabPan(id) {
      //標籤跳轉
      document.getElementById("tab-1").click();
      document.getElementById("add_button").style.visibility = "hidden";
      document.getElementById("edit_button").style.visibility = "visible";
      document.getElementById("delete_button").style.visibility = "visible";
      this.$axios.get("/seq/" + id).then((res) => {
        this.addForm.id = res.data.data.id;
        this.addForm.setno = res.data.data.setno;
        this.addForm.setnamech = res.data.data.setnamech;
        this.addForm.seq = res.data.data.seq;
        res.data.data.seqUdi.forEach((udi) => {
          this.data.push({
            label: udi.csrUdi.code,
            key: udi.csrUdi.id,
            cname: udi.csrUdi.cname,
            ename: udi.csrUdi.ename,
          });
        });
        res.data.data.seqUdi.forEach((udi) => {
          this.value.push(udi.csrUdi.id);
        });
      });
    },
    /**刪除 */
    deleteSeq(id, name) {
      this.$msgbox
        .confirm("確定要刪除 " + name + " ?", "刪除", {
          cancelButtonText: "取消",
          confirmButtonText: "確定",
          type: "warning",
        })
        .then(() => {
          this.$axios.remove("/seq/" + id).then(() => {
            this.clearForm();
            this.getSeqList();
          });
        })
        .catch(() => {
          //nothing to do
        });
    },
    /**清除資料 */
    clearForm() {
      document.getElementById("add_button").style.visibility = "visible";
      document.getElementById("edit_button").style.visibility = "hidden";
      document.getElementById("delete_button").style.visibility = "hidden";
      this.data = [];
      this.value = [];
      this.addForm = { ids: "" };
      this.getUnUDIList();
    },
  },
  /**監聽 */
  watch: {
    "addForm.setno": function () {
      if (this.addForm.setno != undefined) {
        if (this.addForm.setno.length === 6) {
          this.addForm.setno = this.addForm.setno.toUpperCase();
          this.$axios.get("/setdata/" + this.addForm.setno).then((res) => {
            if (res.data.data !== null) {
              this.addForm.setnamech = res.data.data.setnamech;
            } else {
              this.addForm.setnamech = "";
            }
          });
        }
      }
    },
  },
};
</script>

<style lang="less" scope>
.Edit_setno_font {
  margin: 3px;
}

.contain_center {
  width: 80%;
  margin: 10px auto;
}
.el-transfer {
  margin: 30px auto;
}
.el-transfer-panel {
  .el-transfer-panel__body {
    height: 400px;
  }
  width: 35%;
}

.udiList {
  display: block;
  float: left;
  width: 50%;
  border-bottom: solid 1px rgba(90, 89, 89, 0.294);
}
</style>