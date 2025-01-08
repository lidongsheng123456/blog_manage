<template>
  <div>
    <div class="ManagerPerson-header">
      {{ $route.meta.name }}
    </div>
    <div class="ManagerPerson-content">
      <el-form ref="formRef" :model="form" :rules="rules">
        <el-image :src="userInfo.imgUrl || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"
                  alt="头像" class="avatar" style="border-radius: 50%;height: 100px;width: 100px"/>
        <br><br>
        <el-form-item :label-width="formLabelWidth" label="角色">
          <el-input v-model="userInfo.role" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="用户名">
          <el-input v-model="userInfo.username" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="姓名">
          <el-input v-model="userInfo.name" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="手机号">
          <el-input v-model="userInfo.phone" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="电子邮箱">
          <el-input v-model="userInfo.email" :disabled="true"></el-input>
        </el-form-item>

        <el-form-item :label-width="formLabelWidth" label="原始密码" prop="pwd">
          <el-input v-model="form.pwd" placeholder="原始密码" show-password></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="新密码" prop="newPwd">
          <el-input v-model="form.newPwd" placeholder="新密码" show-password></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="确认新密码" prop="confirmPwd">
          <el-input v-model="form.confirmPwd" placeholder="确认密码" show-password></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submit">确认修改</el-button>
      </div>
    </div>
  </div>
</template>

<script>

import {getPersonPwd, updatePwd} from "@/api/display/UserDisplay";

export default {
  data() {
    const validatePwd = ((rule, value, callback) => {
      if (value === '') {
        callback(new Error('请确认密码'))
      } else if (value !== this.form.newPwd) {
        callback(new Error('确认密码错误'))
      } else {
        callback()
      }
    })

    return {
      //当前登录的用户信息
      userInfo: {},
      form: {
        pwd: '',
        newPwd: '',
        confirmPwd: ''
      },
      //对话框表单宽度
      formLabelWidth: '100px',
      rules: {
        pwd: [
          {required: true, message: '请输入原始密码', trigger: 'blur'},
        ],
        newPwd: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
        ],
        confirmPwd: [
          {validator: validatePwd, required: true, trigger: 'blur'},
        ],
      }
    }
  },
  methods: {
    //提交表单
    submit() {
      this.$refs.formRef.validate((valid) => {
        //表单规则通过回调函数返回true
        if (valid) {
          this.form.id = this.userInfo.id
          console.log(this.form)
          updatePwd(this.form).then(res => {
            if (res.code === '200') {
              localStorage.removeItem('xm-user')
              this.$router.push("/login")
              this.$message.success('修改成功')
            }
            if (res.code !== '200') {
              this.$message.error(res.msg)
            }
          }).catch(error => {
            console.log(error)
          })
        }
      })
    },
    getPersonPwd() {
      getPersonPwd(this.userInfo).then(res => {
        if (res.code === '200') {
          this.form.pwd = res.data.pwd
        }
        if (res.code !== '200') {
          this.$message.error(res.msg)
        }
      }).catch(error => {
        console.log(error)
      })
    }
  },
  created() {
    //初始化页面
    this.userInfo = JSON.parse(localStorage.getItem('xm-user'))
    this.getPersonPwd()
  },
}
</script>

<style scoped>
.ManagerPerson-header {
  height: 40px;
  background: #ffffff;
  border-radius: 10px;
  box-shadow: 1px 1px 5px 0 #bfbfbf;
  padding: 5px;
  line-height: 40px;
  font-size: 20px;
  font-weight: 600;
  width: 500px;
  margin: 0 auto;
}

.ManagerPerson-content {
  background: #ffffff;
  border-radius: 10px;
  box-shadow: 1px 1px 5px 0 #bfbfbf;
  padding: 5px;
  width: 500px;
  margin: 5px auto;
}
</style>

