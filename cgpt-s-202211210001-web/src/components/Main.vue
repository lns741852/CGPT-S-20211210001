<template>
  <el-container class="home-container">
    <el-header>
      <img src="../assets/images/TVGHheader.png" />
      <div>
        <span>{{ usercname }} 您好</span>
        <span>歡迎使用本系統</span>
        <el-button class="success_button">個人資料編輯</el-button>
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
      usercname:""
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
      this.$axios.get("/info/"+  this.userNo).then((res) => {
        localStorage.setItem("usercname", res.data.data.usercname); //存入所有盤包
      this.usercname =res.data.data.usercname;
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
  background-color: #D4DEBC;
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