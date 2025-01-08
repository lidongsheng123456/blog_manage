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
        <el-form-item prop="confirmPwd">
          <el-input v-model="form.confirmPwd" placeholder="请确认密码" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%;background: pink;border-color: #ff7b7b;color: white" @click="register">注 册
          </el-button>
        </el-form-item>
        <div style="display: flex;align-items: center">
          <div style="flex: 1"></div>
          <div style="flex: 1;text-align: right">
            已有账号？请<a href="/login">登录</a>
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import {register} from "@/api/display/LoginRequest";

export default {
  data() {
    // rule：包含了验证规则的各种属性，如 field（字段名）、required（是否必填）、message（错误提示信息）等。
    // confirmPwd：当前输入框的值。即用户在确认密码输入框中输入的内容。
    // callback：如果验证通过，调用 callback()；如果验证失败，调用 callback(new Error('错误信息'))。
    const validatePassword = ((rule, confirmPwd, callback) => {
      if (confirmPwd === '') {
        callback(new Error("请确认密码"))
      } else if (confirmPwd !== this.form.pwd) {
        callback(new Error("两次输入的密码不一致"))
      } else {
        callback()
      }
    })
    return {
      form: {},
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'}
        ],
        pwd: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
        confirmPwd: [
          {validator: validatePassword, trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    register() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          register(this.form).then(res => {
            if (res.code === '200') {
              this.$router.push('/')
              this.$message.success('注册成功')
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
