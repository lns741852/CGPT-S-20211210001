<template>
  <h3>建立盤包</h3>
  <!--卡片區塊-->
  <el-card class="box-card" style="margin-top: 10px">
    <!---->
    <el-row :gutter="12">
      <el-col :span="6">
        <el-card>
          <el-descriptions
            style="width: 100%"
            :column="1"
            border
            title="盤包資訊"
          >
            <el-descriptions-item
              label="盤包代號："
              label-class-name="my-label"
              >{{ setnoData.setno }}</el-descriptions-item
            >
            <el-descriptions-item
              label="英文名稱："
              label-class-name="my-label"
              >{{ setnoData.setname }}</el-descriptions-item
            >
            <el-descriptions-item
              label="中文名稱："
              label-class-name="my-label"
              >{{ setnoData.setnamech }}</el-descriptions-item
            >
          </el-descriptions>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="height: 100%; position: relative">
          <span style="font-size: 16px; font-weight: 700">盤包序號</span>
          <div>
            <el-input
              v-model="setnoData.seq"
              style="
                width: 60%;
                position: absolute;
                bottom: 0px;
                padding: 20px;
                margin: 0px;
              "
            >
            </el-input>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="height: 100%; position: relative">
          <span style="font-size: 16px; font-weight: 700">加入器械</span>
          <div>
            <el-input
              v-model="udi"
              style="
                width: 70%;
                position: absolute;
                bottom: 0px;
                padding: 20px;
                margin: 0px;
              "
              @keyup.enter="getUDI"
            >
              <template #append>
                <el-button class="search_button" @click="getUDI">
                  <el-icon><plus /></el-icon>
                </el-button>
              </template>
            </el-input>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="height: 100%; position: relative">
          <span style="font-size: 16px; font-weight: 700">刪除器械</span>
          <div>
            <el-input
              v-model="rudi"
              style="
                width: 70%;
                position: absolute;
                bottom: 0px;
                padding: 20px;
                margin: 0px;
              "
              @keyup.enter="deleteUDI"
            >
              <template #append>
                <el-button class="search_button" @click="deleteUDI">
                  <el-icon><plus /></el-icon>
                </el-button>
              </template>
            </el-input>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!---->

    <!--列表-->
    <el-table class="setno_udi_table" :data="TypeList">
      <el-table-column prop="NAME" label="器械類型" />
      <el-table-column prop="NUM" label="需求總量" />

      <el-table-column label="已存數器械數量">
        <template #default="scope">
          <el-progress
            :percentage="scope.row.sum * (100 / scope.row.NUM)"
          ></el-progress>
          <!-- <el-button class="delete_button" @click="deleteUDI(scope.$index)"
                >刪除</el-button
              > -->
        </template>
      </el-table-column>
    </el-table>
    <el-button class="edit_button" @click="addSetno">建立盤包</el-button>
  </el-card>
</template>

<script>
import { ElMessage } from "element-plus";

export default {
  data() {
    return {
      id: this.$route.params.id,
      queryInfo: {
        searchName: "",
        pageno: "",
        pagesize: "",
      },
      udi: "",
      rudi: "",
      UDIList: [],
      total: "",
      seq: "",
      addDialogVisible: false,
      editDialogVisible: false,
      fileDialogVisible: false,
      setnoData: {      
      },
      setnoData2: {
        name: "",
        nameScientific: "",
        num: 0,
      },
      TypeList: [],
      udiList: [],
      setnoDataRules: {
        num: [{ required: true, message: "請輸入數量...", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getSetno();
    this.getUDIType();
  },

  methods: {
    /**顯示修改資料 */
    getSetno() {
      this.$axios.get("/setno/" + this.id).then((res) => {
        this.setnoData = res.data.data;
        this.setnoData.seq =""
      });
    },
    getUDIType() {
      this.$axios.get("/setno/udi/" + this.id).then((res) => {
        this.TypeList = res.data.data;

        this.TypeList.forEach((e) => {
          e.sum = 0;
        });
      });
    },
    getUDI() {
      if (this.udi != "") {
        this.$axios.get("/udi/name/" + this.udi).then((res) => {
          this.udiData = res.data.data;
          if (this.udiData === null) {
            ElMessage.error("UDI輸入錯誤");
            return;
          }
          if (this.udiData.status != 0) {
            ElMessage.error("UDI無法使用，請確認UDI狀態");
            return;
          }
          let i = 0;
          //盤包重複判斷
          this.udiList.some((e) => {
            if (e.id === this.udiData.id) {
              i = i + 1;
            }
          });

          if (i !== 0) {
            ElMessage.error("UDI已重複");
            return;
          }

          //UDI類型檢查
          let count = 0;
          this.TypeList.forEach((e) => {
            if (Number(e.UDITYPEID) === this.udiData.typeId) {
              if (e.NUM <= e.sum) {
                return;
              }
              e.sum += 1;
              count += 1;
            }
          });
          if (count == 0) {
            ElMessage.error("UDI類型、數量不正確");
            return;
          }

          this.udiList.push({
            id: this.udiData.id,
            udi: this.udiData.udi,
            typeId: this.udiData.typeId,
          });
          ElMessage.success("器械成功加入");
        });
      }
    },
    //建立盤包
    addSetno() {
      if (this.setnoData.seq === "") {
        ElMessage.error("請輸入盤包序號");
        return;
      }

      let count = 0;
      this.TypeList.forEach((e) => {
        if (e.NUM != e.sum) {
          count += 1;
        }
      });
      if (count != 0) {
        ElMessage.error("已存數器械數量不正確");
        return;
      }

  
      let addUdi={}
      addUdi.seq =this.setnoData.seq
      addUdi.setno =this.setnoData.setno
      addUdi.udi =this.udiList

      this.$axios.post("/setno/seq", addUdi).then((res) => {
        if(res.data.code == 200){
             this.$router.push({ path: `/setno_udi` });
        }
      });

    },
    /**刪除 */
    deleteUDI() {
      //UDI類型檢查
      let tempId = 0;
      let deleteId = null;
      this.udiList.forEach((e) => {
        if (e.udi === this.rudi) {
          deleteId = tempId;
        }
        tempId += 1;
      });

      if (deleteId === null) {
        ElMessage.error("UDI類型、數量不正確");
        return;
      }

      this.TypeList.forEach((e) => {
        if (Number(e.UDITYPEID) === this.udiList[deleteId].typeId) {
          e.sum -= 1;
        }
      });
      this.udiList.splice(deleteId, 1);
      ElMessage.success("器械成功刪除");
    },
  },
};
</script>

<style lang="less" scope>
.setno_udi_table .el-table__header .is-leaf.el-table__cell {
  background-color: rgb(255, 255, 255);
}
.my-label {
  width: 40%;
}
</style>