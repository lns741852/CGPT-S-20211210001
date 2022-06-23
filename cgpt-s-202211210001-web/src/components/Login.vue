<template>
  <div class="login_box">
    <div class="avatar_box">
      <img src="../assets/images/TVGHicon.png" />
    </div>
    <h3>UDI器械盤包滅菌追蹤系統</h3>
    <!--表單-->
    <el-form
      class="loginForm"
      :model="loginForm"
      ref="loginFormRef"
      :rules="loginFormRules"
      label-width="80px"
    >
      <el-form-item class="loginFormItem" label="帳號: " prop="username">
        <el-input v-model="loginForm.username" ref="username"></el-input>
      </el-form-item>
      <el-form-item class="loginFormItem" label="密碼: " prop="password">
        <el-input
          v-model="loginForm.password"
          type="password"
          @keyup.enter="login"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button  class="sucess_button" @click="login"
          >登入</el-button
        >
        <el-button  class="reset_button" @click="resetLoginForm"
          >重製</el-button
        >
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
          const jwt = res.headers["authorization"]; //拿token
          localStorage.setItem("userno", this.loginForm.username); //存入token
          localStorage.setItem("authorization", jwt); //存入token
          this.$router.push("/main");
        });
      });
    },
  },
};
</script>

<style lang="less">
.login_box {
  height: 550px;
  width: 450px;
  margin: 200px auto;
  img {
    display: block;
    margin: auto;
    height: 200px;
    width: 250px;
  }
  h3 {
    margin: 16px 0;
    text-align: center;
    font-size: 26px;
    color: #055614;
  }
  .loginForm {
    height: 280px;
    background: #D4DEBC;
    border-radius: 30px;
    padding-top: 30px;
    .loginFormItem {
      margin: 40px 40px;
      width: 300px;
    }
    .el-form-item__label{
      font-size: 18px;
    }
    .el-form-item__error {
      margin-top: 5px;
      font-size: 18px;
    }
    .sucess_button {
      margin: 0 50px;
      font-size: 18px;
      border-radius: 5px;
      background-color: #0E4F49;
      color: #fff;
    }
    .reset_button {
      font-size: 18px;
      border-radius: 5px;
      background-color: #C16124;
      color: #fff;
    }
  }
}
</style>
