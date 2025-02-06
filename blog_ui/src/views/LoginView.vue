<template>
  <div class="container">
    <div style="background-color: white;width: 400px;padding: 30px;border-radius: 10px">
      <div style="text-align: center;font-size: 20px;margin-bottom: 20px;color: #000000 ">欢迎使用东sheng博客</div>
      <el-form ref="formRef" :model="loginForm" :rules="loginRules">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" placeholder="请输入账号" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="pwd">
          <el-input v-model="loginForm.pwd" placeholder="请输入密码" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item prop="code">
          <div style="display: flex">
            <el-input v-model="loginForm.code" placeholder="验证码" prefix-icon="el-icon-postcard"
                      @keyup.enter.native="login"></el-input>
            <div class="login-code">
              <img id="verificationCodeImg" title="看不清？换一张" alt="点击一下试试" @click="getCaptcha" src=""/>
            </div>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button
              style="width: 100%;background: pink;
              border-color: #ff7b7b;
              color: white"
              :loading="loading"
              @click="login">
            <span v-if="!loading">登 录</span>
            <span v-else>登 录 中...</span>
          </el-button>
        </el-form-item>
        <div style="display: flex;align-items: center">
          <div style="flex: 1"></div>
          <div style="flex: 1;text-align: right">
            还没有账号？请<a href="/register">注册</a>
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>

import {getCaptcha, login} from "@/api/display/LoginRequest";

export default {
  data() {
    return {
      baseUrl: process.env.VUE_APP_BASEURL,
      codeUrl: "",
      loginForm: {
        username: "admin",
        pwd: "123456",
        code: "",
      },
      loginRules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'}
        ],
        pwd: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
        code: [
          {required: true, message: "请输入验证码", trigger: "change"}
        ]
      },
      loading: false
    }
  },
  methods: {
    getCaptcha() {
      getCaptcha().then(imageUrl => {
        if (imageUrl) {
          document.getElementById('verificationCodeImg').src = imageUrl;
        }
      });
    },
    login() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          this.loading = true;
          login(this.loginForm).then(res => {
            if (res.code === '200') {
              localStorage.setItem("xm-user", JSON.stringify(res.data))  // 存储用户数据
              this.$router.push('/')
              this.$message.success('登录成功')
            }
            if (res.code !== '200') {
              this.$message.error(res.msg)
            }
          }).catch(() => {
            this.getCode();
          }).finally(() => {
            this.loading = false;
          })
        }
      })
    }
  },
  mounted() {
    this.getCaptcha();
  }
}
</script>

<style scoped>
.container {
  background: url("@/assets/img/bj.jpg") no-repeat center;
  background-size: cover;
  height: 100vh;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #858585;
}

.login-code {
  width: 150px;
  text-align: center;
  height: 41px;
  margin-left: 10px;
}

.login-code img {
  cursor: pointer;
}

.login-code-img {
  height: 100%;
}
</style>
