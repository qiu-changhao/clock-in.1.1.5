<template>
  <div class="app-wrapper">
    <div class="login-background"></div>
    <div class="login">
      <div style="height: 10px;background: rgb(3, 154, 255);"></div>
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-position="left" label-width="0px">
        <div class="title">
          后台管理系统
        </div>
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号">
            <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon"/>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码"
                    @keyup.enter.native="handleLogin" :show-password="true">
            <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon"/>
          </el-input>
        </el-form-item>
        <el-checkbox v-model="loginForm.rememberMe" style="margin:0 0 25px 0;">
          记住我
        </el-checkbox>
        <el-form-item style="width:100%;">
          <el-button :loading="loading" size="medium" type="primary" style="width:100%;"
                     @click.native.prevent="handleLogin">
            <span v-if="!loading">登 录</span>
            <span v-else>登 录 中...</span>
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import Cookies from "js-cookie";

export default {
  name: "loginPage",
  data() {
    return {
      codeUrl: "",
      cookiePassword: "",
      loginForm: {
        username: "admin",
        password: "123456",
        rememberMe: false,
      },
      loginRules: {
        username: [
          {required: true, trigger: "blur", message: "请输入您的账号"}
        ],
        password: [
          {required: true, trigger: "blur", message: "请输入您的密码"}
        ],
      },
      loading: false
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    this.getCookie();
  },
  methods: {
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : password,
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, {expires: 30});
            Cookies.set("password", this.loginForm.password, {expires: 30});
            Cookies.set('rememberMe', this.loginForm.rememberMe, {expires: 30});
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove('rememberMe');
          }
          this.$store.dispatch("Login", this.loginForm).then(() => {
            this.$router.push({path: this.redirect || "/"}).catch(() => {
            });
          }).catch(() => {
            this.loading = false;
          });
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.app-wrapper {
  background-color: #f9f9f9;
  width: 100%;
  height: 100%;

  .login-background {
    position: absolute;
    height: 360px;
    width: 100%;
    background-color: rgba(0, 153, 255, 0.99);
    background-image: url("../assets/images/loginBackground.png");
    background-position: center;
    background-repeat: no-repeat;
    top: 50%;
    transform: translateY(-50%);
  }

  .login {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 350px;
    height: 420px;
    background-color: #fff;
    overflow: hidden;

    .el-form {
      padding: 25px;

      .title {
        font-weight: 700;
        text-align: center;
        margin-bottom: 20px;
        color: rgba(0, 153, 255, 0.99);
      }

      .login-code {
        width: 38%;
        display: inline-block;
        height: 38px;
        float: right;

        img {
          cursor: pointer;
          vertical-align: middle
        }
      }
    }
  }
}
</style>
