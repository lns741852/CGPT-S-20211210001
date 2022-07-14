<template>
  <el-container class="home-container">
    <el-header>
      <img src="../assets/images/TVGHheader.png" />
      <div>
        <span>{{ usercname }} 您好</span>
        <span>歡迎使用本系統</span>
        <el-button class="success_button" @click="userInfoShow"
          >個人資料編輯</el-button
        >
        <el-button class="delete_button" @click="logout">退出</el-button>
      </div>
    </el-header>
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '280px'">
        <div class="toggle-button" @click="toggleCollapse">
          <el-icon><fold /></el-icon>
        </div>
        <!--router自動對應路由-->
        <el-menu
          active-text-color="#E6A23C"
          unique-opened
          :collapse="isCollapse"
          :collapse-transition="false"
          :router="true"
          :default-active="activePath"
          background-color="#FFF"
        >
          <!--一級目路-->
          <!--key,index使用唯一值-->
          <el-sub-menu
            v-for="item in menulist"
            :key="item.oneId"
            :index="item.oneId"
          >
            <template #title>
              <component :is="icons[item.oneId]"></component>
              <span>{{ item.oneName }}</span>
            </template>
            <!--二級目錄-->
            <el-menu-item
              v-for="item in item.twoMenuList"
              :key="item.twoId"
              :index="item.url"
              @click="saveNavState(item.url)"
            >
              <template #title>
                <div class="line_two"></div>
                <span> {{ icons[item.twoId] }}{{ item.twoName }} </span>
              </template>
            </el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-aside>
      <!--main-->
      <el-main>
        <router-view></router-view>
        <el-dialog
          v-model="editDialogVisible"
          @close="editDialogClosed"
          width="40%"
          title="修改帳號"
        >
          <!--驗證-->
          <template #default>
            <el-form
              ref="addFormRef"
              :model="editForm"
              label-width="80px"
              :rules="addFormRules"
            >
              <el-form-item label="員工編號" prop="userno">
                <el-input v-model="editForm.userno" disabled></el-input>
              </el-form-item>
              <!--修改_下拉選單-->
              <el-form-item label="部門" prop="depno">
                <el-select
                  popper-class="dropdownbox"
                  v-model="editForm.depno"
                  placeholder="部門"
                  clearable
                  disabled
                >
                  <el-option
                    v-for="item in depnoList"
                    :key="item.DEPNO"
                    :label="item.DEPNAME"
                    :value="item.DEPNO"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="姓名" prop="usercname">
                <el-input v-model="editForm.usercname"></el-input>
              </el-form-item>
              <el-form-item label="密碼" prop="userpwd">
                <el-input v-model="editForm.userpwd"></el-input>
              </el-form-item>
              <!--radio-->
              <el-form-item label="權限" prop="systemprivilege">
                <el-radio-group v-model="editForm.systemprivilege" disabled>
                  <el-radio label="A">管理員</el-radio>
                  <el-radio label="B">護理長</el-radio>
                  <el-radio label="C">供應室職員</el-radio>
                  <el-radio label="D">病房職員</el-radio>
                </el-radio-group>
              </el-form-item>
              <!--checkbox-->
              <template v-for="item in auth" :key="item.oneId">
                <el-form-item :label="item.oneName">
                  <el-form-item>
                    <el-checkbox-group
                      v-model="ids"
                      v-for="item in item.twoMenuList"
                      :key="item.twoId"
                      disabled
                    >
                      <el-checkbox :label="item.twoId">
                        {{ item.twoName }}
                      </el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>
                </el-form-item>
              </template>
            </el-form>
          </template>
          <template #footer>
            <div class="dialog-footer">
              <el-button class="edit_button" @click="editAccount"
                >確定</el-button
              >
              <el-button type="info" @click="editDialogVisible = false"
                >取消</el-button
              >
            </div>
          </template>
        </el-dialog>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      menulist: [],
      //{Id,<自定參數>}
      icons: {
        1: "Operation",
        2: "Setting",
      },
      isCollapse: false,
      activePath: "",
      userNo: "",
      screenWidth: document.body.clientWidth,
      usercname: "",
      userInfo: {},
      editForm:{},
      editDialogVisible: false,
        auth: [],
    };
  },

  mounted() {
    const that = this;
    window.onresize = () => {
      return (() => {
        window.screenWidth = document.body.clientWidth;
        that.screenWidth = window.screenWidth;
      })();
    };
    this.getMenuList();
    this.activePath = localStorage.getItem("activePath");
    this.getUsername();
    this.saveAllSetno();
    this.saveAllCasecar();
    this.getAuth();
  },

  methods: {
    logout() {
      localStorage.clear();
      localStorage.setItem("authorization", "reset");
      this.$router.push("/login");
    },
    getMenuList() {
      this.$axios.get("/menu").then((res) => {
        this.menulist = res.data.data;
      });
    },
    toggleCollapse() {
      this.isCollapse = !this.isCollapse;
    },
    saveNavState(activePath) {
      localStorage.setItem("activePath", activePath);
      this.activePath = activePath;
    },
    getUsername() {
      this.userNo = localStorage.getItem("userno");
      this.$axios.get("/info/" + this.userNo).then((res) => {
        this.userInfo = res.data.data;

        localStorage.setItem("usercname", res.data.data.usercname); //存入所有盤包
        this.usercname = res.data.data.usercname;
      });
    },
    saveAllSetno() {
      this.$axios.get("/setdata/all").then((res) => {
        localStorage.setItem("setnoAll", res.data.data); //存入所有盤包
      });
    },
    saveAllCasecar() {
      this.$axios.get("/casecar/all").then((res) => {
        localStorage.setItem("casecarAll", res.data.data); //存入所有盤包
      });
    },
    async userInfoShow() {
      await this.$axios.get("/account/" + this.userInfo.id).then((res) => {
        this.editForm = res.data.data;
        let b = [];
        res.data.data.csrAccountAuths.forEach((item) => {
          b.push(item.csrAuth.id);
        });
        this.ids = b;
      });
      this.editDialogVisible = true;
    },
        /**權限查詢 */
    getAuth() {
      this.$axios.get("/auth").then((res) => {
        this.auth = res.data.data;
      });
    },
      /**編輯帳號 */
    // editAccount() {
    //   console.log(localStorage.getItem("userno"))

    //     this.$axios
    //       .put("/account/" + this.editForm.id, this.editForm)
    //       .then(() => {
    //         this.editDialogVisible = false;
    //         this.getAccountList();
    //       });

    // },
  },
  watch: {
    screenWidth(val) {
      // 為了避免頻繁觸發resize函式導致頁面卡頓，使用定時器
      if (!this.timer) {
        // 一旦監聽到的screenWidth值改變，就將其重新賦給data裡的screenWidth
        this.screenWidth = val;
        this.timer = true;
        let that = this;
        setTimeout(function () {
          // 列印screenWidth變化的值
          //console.log(that.screenWidth);
          if (that.screenWidth < 1200) {
            if (!that.isCollapse) {
              document.getElementsByClassName("toggle-button")[0].click();
            }
          }
          if (that.screenWidth > 1200) {
            if (that.isCollapse) {
              document.getElementsByClassName("toggle-button")[0].click();
            }
          }
          that.timer = false;
        }, 200);
      }
    },
  },
};
</script>

<style lang="less">
.home-container {
  height: 100%;
}
.el-header {
  display: flex;
  justify-content: space-between;
  background-color: #d4debc;
  height: 100px;
  img {
    display: block;
    max-width: 100%;
    height: 100%;
  }
  div {
    span {
      display: flex;
      justify-content: center;
    }
    .el-button {
      margin: 5px 20px;
    }
  }
}
.el-aside {
  background-color: #fff;
  .el-menu {
    border-right: none;
    span {
      font-size: 16px;
    }
  }
}
.toggle-button {
  text-align: right;
  font-size: 20px;
}
.el-main {
  background-color: #fefaee;
}
.line_two {
  float: left;
  margin: 0 10px 0 0;
  height: 50px;
  width: 5px;
  background: #c16124;
}
.icon {
  margin: 0 5px 0 0;
  width: 20px;
  height: 20px;
}
</style>