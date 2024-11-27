<template>
  <div class="container">
    <div style="background-color: white;width: 400px;padding: 30px;border-radius: 10px">
      <div style="text-align: center;font-size: 20px;margin-bottom: 20px;color: #000000 ">欢迎使用东sheng博客</div>
      <el-form ref="formRef" :model="form" :rules="rules">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="请输入账号" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="pwd">
          <el-input v-model="form.pwd" placeholder="请输入密码" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%;background: pink;border-color: #ff7b7b;color: white" @click="login">登 录
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
import {login} from "@/api/display/LoginRequest";

export default {
  data() {
    return {
      form: {},
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'}
        ],
        pwd: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    login() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          login(this.form).then(res => {
            if (res.code === '200') {
              localStorage.setItem("xm-user", JSON.stringify(res.data))  // 存储用户数据
              this.$router.push('/')
              this.$message.success('登录成功')
            }
            if (res.code !== '200') {
              this.$message.error(res.msg)
            }
          }).catch(error => {
            console.log(error)
          })
        }
      })
    }
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
</style>
