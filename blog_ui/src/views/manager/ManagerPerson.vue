<template>
  <div>
    <div class="ManagerPerson-header">
      {{ $route.meta.name }}
    </div>
    <div class="ManagerPerson-content">
      <el-form ref="formRef" :model="userInfo" :rules="rules">
        <div style="font-size: 20px;font-weight: 600;color: #2e324c;margin-bottom: 10px">点击上传头像</div>
        <el-upload
            :action="baseURL + '/files/upload'"
            :before-upload="beforeAvatarUpload"
            :on-success="handleAvatarSuccess"
            :show-file-list="false"
            class="avatar-uploader">
          <el-image :src="userInfo.imgUrl || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"
                    alt="用户头像" style="width: 100px;height:100px;border-radius: 50%">
          </el-image>
        </el-upload>
        <br>
        <el-form-item :label-width="formLabelWidth" label="角色" prop="role">
          <el-input v-model="userInfo.role" :disabled="true" placeholder="请输入角色"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="用户名" prop="username">
          <el-input v-model="userInfo.username" autocomplete="off" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="姓名" prop="name">
          <el-input v-model="userInfo.name" autocomplete="off" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="手机号" prop="phone">
          <el-input v-model="userInfo.phone" autocomplete="off" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="电子邮箱" prop="email">
          <el-input v-model="userInfo.email" autocomplete="off" placeholder="请输入电子邮箱"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submit">保 存</el-button>
      </div>
    </div>
  </div>
</template>

<script>

import {personCenter} from "@/api/display/UserDisplay";

export default {
  data() {
    return {
      //当前登录的用户信息
      userInfo: {},
      //对话框表单宽度
      formLabelWidth: '80px',
      baseURL: null,
      //表单规则
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'}
        ],
        role: [
          {required: true, message: '请选择角色', trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '请输入手机号', trigger: 'blur'}
        ],
        email: [
          {required: true, message: '请输入电子邮箱', trigger: 'blur'}
        ]
      },
    }
  },
  methods: {
    //提交表单
    submit() {
      this.$refs.formRef.validate((valid) => {
        //表单规则通过回调函数返回true
        if (valid) {
          personCenter(this.userInfo).then(res => {
            if (res.code === '200') {
              localStorage.setItem('xm-user', JSON.stringify(this.userInfo))
              this.$emit('update:user')
              this.$message.success('保存成功')
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
    handleAvatarSuccess(res) {
      this.userInfo.imgUrl = res.data
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  },
  created() {
    //初始化页面
    this.userInfo = JSON.parse(localStorage.getItem('xm-user'))
    this.baseURL = process.env.VUE_APP_BASEURL
  }
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
