<template>
  <div class="login_box">
    <div class="avatar_box">
      <img src="../assets/images/TVGHicon.png" />
    </div>
    <h3>台北榮總滅菌物品追蹤系統</h3>
    <!--表單-->
    <el-form
      :model="loginForm"
      ref="loginFormRef"
      :rules="loginFormRules"
      label-width="80px"
    >
      <el-form-item label="帳號: " prop="username">
        <el-input v-model="loginForm.username" ref="username"></el-input>
      </el-form-item>
      <el-form-item label="密碼: " prop="password">
        <el-input
          v-model="loginForm.password"
          type="password"
          @keyup.enter="login"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button class="sucess_button" @click="login">登入</el-button>
        <el-button class="reset_button" @click="resetLoginForm">重製</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //:model
      loginForm: {
        //v-model
        username: "",
        password: "",
      },
      //:rules
      loginFormRules: {
        //prop
        username: [
          { required: true, message: "請輸入...", trigger: "blur" },
          { min: 3, max: 10, message: "長度3~10", trigger: "blur" },
        ],
        password: [
          { required: true, message: "請輸入...", trigger: "blur" },
          { min: 3, max: 10, message: "長度3~10", trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
    this.$refs.username.focus();    
  },
  methods: {
    //重製
    resetLoginForm() {
      //ref
      this.$refs.loginFormRef.resetFields();
    },
    //登入
    login() {
      //validate驗證rules的結果
      //async,await 優化Promise，增加可讀性
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return;
        this.$axios.post("/login", this.loginForm).then((res) => {
          const jwt = res.headers["authorization"]; //拿token,  headers只能小寫
          localStorage.setItem("username", this.loginForm.username); //存入token
          localStorage.setItem("authorization", jwt); //存入token
          this.$router.push("/main");
        });
      });
    },
  },
};
</script>

<style lang="less" scoped>

.login_box {
  height: 550px;
  width: 450px;
  margin: 200px auto;
  img {
    display: block;
    margin: auto;
    height: 230px;
    width: 250px;
  }
  h3 {
    text-align: center;
    font-size: 26px;
  }
  /deep/.el-form {
    height: 280px;
    background: #d4debc;
    border-radius: 30px;
    padding-top: 30px;
  }
  /deep/ .el-form-item__label {
    margin-left: 30px;
    padding-top: 30px;
    font-size: 18px;
  }
  /deep/ .el-input {
    padding-top: 30px;
    width: 300px;
  }
  /deep/ .el-form-item__error {
    margin-top: 5px;
    font-size: 18px;
  }
  .sucess_button {
    margin: 20px 50px;
    background: #114f4a;
    font-size: 18px;
    color: #fff;
    border-radius: 5px;
}
  .reset_button {
    background: #c06123;
    font-size: 18px;
    color: #fff;
    border-radius: 5px;
  }
}
</style>
