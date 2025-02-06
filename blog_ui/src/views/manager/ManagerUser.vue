<template>
  <div>
    <div class="ManagerUser-header">
      <div class="ManagerUser-header-a">
        <el-input v-model="queryUserName" placeholder="请输入用户名查询" style="width: 200px"></el-input>
        <el-button class="el-icon-search" style="margin-left: 10px" type="primary" @click="initialize"> 查询
        </el-button>
        <el-button class="el-icon-s-tools" type="info" @click="reset"> 重置</el-button>
      </div>
      <div class="ManagerUser-header-b">
        <el-button plain class="el-icon-plus" type="primary" @click="addUser"> 新增</el-button>
        <el-button plain class="el-icon-close" type="danger" @click="deleteUser"> 批量删除</el-button>
      </div>
    </div>

    <div class="ManagerUser-content">
      <el-table
          :data="user"
          stripe
          style="width: 100%"
          @selection-change="handleSelectionChange">
        <el-table-column
            align="center"
            type="selection">
        </el-table-column>
        <el-table-column
            align="center"
            label="头像">
          <template v-slot="scope">
            <el-image :src="scope.row.imgUrl || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"
                      alt="用户头像" style="width: 60px;height:60px;border-radius: 50%">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="用户名"
            prop="username">
        </el-table-column>
        <el-table-column
            align="center"
            label="姓名"
            prop="name">
        </el-table-column>
        <el-table-column
            align="center"
            label="角色"
            prop="role">
        </el-table-column>
        <el-table-column
            align="center"
            label="手机号"
            prop="phone">
        </el-table-column>
        <el-table-column
            align="center"
            label="邮箱"
            prop="email">
        </el-table-column>
        <el-table-column
            align="center"
            label="创建时间">
          <template v-slot="scope">
            {{ new Date(scope.row.createTime).toLocaleString() }}
          </template>
        </el-table-column>
        <el-table-column
            align="center"
            label="操作"
            width="300">
          <template v-slot="scope">
            <el-button plain class="el-icon-edit" type="warning" @click="editUser(scope.row)"> 编辑</el-button>
            <el-button plain class="el-icon-close" type="danger" @click="deleteUser"> 删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
            :current-page="page"
            :page-size="pageSize"
            :page-sizes="[5, 10, 20, 30]"
            :total="total"
            layout="total, sizes, prev, pager, next, jumper"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange">
        </el-pagination>
      </div>
    </div>

    <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible" width="500px">
      <el-form ref="formRef" :model="form" :rules="rules">
        <div style="font-size: 20px;font-weight: 600;color: #2e324c;margin-bottom: 10px">点击上传头像</div>
        <el-upload
            :action="baseURL + '/common/files/upload'"
            :before-upload="beforeAvatarUpload"
            :on-success="handleAvatarSuccess"
            :show-file-list="false"
            class="avatar-uploader">
          <el-image :src="form.imgUrl || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"
                    alt="头像" class="avatar" style="border-radius: 50%"/>
        </el-upload>
        <br>
        <el-form-item :label-width="formLabelWidth" label="用户名" prop="username">
          <el-input v-model="form.username" autocomplete="off" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="姓名" prop="name">
          <el-input v-model="form.name" autocomplete="off" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="角色" prop="role">
          <el-select v-model="form.role" placeholder="请选择角色" style="width: 380px">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="手机号" prop="phone">
          <el-input v-model="form.phone" autocomplete="off" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="电子邮箱" prop="email">
          <el-input v-model="form.email" autocomplete="off" placeholder="请输入电子邮箱"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addUser, deleteUser, queryUserData, updateUser, validateUser} from "@/api/display/UserDisplay";
import axios from "axios";

export default {
  data() {
    // 验证用户名是否存在
    const validateUsername = ((rule, value, callback) => {
      const USERID = this.form.id ? this.form.id : null
      validateUser({[rule.fullField]: value, id: USERID}).then((res) => {
        if (res.code === '200') {
          callback()
        }
        if (res.code !== '200') {
          this.$message.error(res.msg)
          callback(new Error(res.msg))
        }
      })
    })

    // 手机号正则表达式
    const validatePhone = (rule, value, callback) => {
      const phoneRegex = /^1[3-9]\d{9}$/;
      if (!phoneRegex.test(value)) {
        callback(new Error('请输入有效的11位手机号'));
      } else {
        callback();
      }
    };

    // 邮箱正则表达式
    const validateEmail = (rule, value, callback) => {
      const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
      if (!emailRegex.test(value)) {
        callback(new Error('请输入有效的邮箱地址'));
      } else {
        callback();
      }
    };

    return {
      //当前登录的用户信息
      userInfo: {},
      //页面初始分页数据
      page: 1,
      pageSize: 5,
      //数据总数
      total: 0,
      //用户数据
      user: [],
      //标题条件查询用户
      queryUserName: null,
      //删除复选框表单
      deleteForm: [],
      //对话框显示隐藏
      dialogFormVisible: false,
      //新增对话框与修改对话框表单
      form: {},
      //对话框表单宽度
      formLabelWidth: '80px',
      //对话框表单标题
      dialogTitle: null,
      baseURL: null,
      //表单规则
      rules: {
        username: [
          {required: true, validator: validateUsername, trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'}
        ],
        role: [
          {required: true, message: '请选择角色', trigger: 'blur'}
        ],
        phone: [
          {required: true, validator: validatePhone, trigger: 'blur'}
        ],
        email: [
          {required: true, validator: validateEmail, trigger: 'blur'}
        ]
      },
      //角色复选框
      options: [
        {
          value: '管理员',
          label: '管理员'
        },
        {
          value: '店长',
          label: '店长'
        },
        {
          value: '经理',
          label: '经理'
        }, {
          value: '员工',
          label: '员工'
        },
      ]
    }
  },
  methods: {
    //分页查询文档
    initialize() {
      queryUserData(this.userInfo.id, this.queryUserName, this.page, this.pageSize).then(res => {
        if (res.code === '200') {
          this.user = res.data.data
          this.total = res.data.total
        }
        if (res.code !== '200') {
          this.$message.error(res.msg)
        }
      }).catch(error => {
        console.log(error)
      })
    },
    //改变页面数据大小
    handleSizeChange(val) {
      this.page = 1
      this.pageSize = val
      this.initialize()
    },
    //控制第几页
    handleCurrentChange(val) {
      this.page = val
      this.initialize()
    },
    //控制复选框
    handleSelectionChange(rows) {
      this.deleteForm = rows
    },
    //添加用户
    addUser() {
      this.form = {}
      this.dialogTitle = '添加用户'
      this.dialogFormVisible = true
    },
    //批量删除用户或删除单个用户
    deleteUser() {
      if (this.deleteForm.length === 0) {
        return this.$message.error('请选择您要删除的数据')
      }
      this.$confirm('您确定要删除吗？', '确定删除', {type: 'warning'}).then(() => {
        let ids = this.deleteForm.map(e => e.id)
        let imgUrl = this.deleteForm.map(e => e.imgUrl);
        //删除图片
        for (let i = 0; i < imgUrl.length; i++) {
          axios.delete(imgUrl[i]).catch(error => {
            console.log(error)
          })
        }
        //删除用户数据
        deleteUser(ids).then(res => {
          if (res.code === '200') {
            this.$message.success('删除成功')
            this.initialize()
          }
          if (res.code !== '200') {
            this.$message.error(res.msg)
          }
        }).catch(error => {
          console.log(error)
        })
      }).catch((error) => {
        console.log(error)
      })
    },
    //修改用户
    editUser(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogTitle = '编辑用户'
      this.dialogFormVisible = true
    },
    //重置条件查询框
    reset() {
      this.queryUserName = null
      this.page = 1
      this.initialize()
    },
    //提交表单
    submit() {
      this.$refs.formRef.validate((valid) => {
        //表单规则通过回调函数返回true
        if (valid) {
          //表单有数据则代表是编辑操作，因为编辑会把当前列的数据赋值给数据模型的form表单用来给对话框组件回显数据
          if (this.form.id) {
            updateUser(this.form).then(res => {
              if (res.code === '200') {
                this.$message.success('编辑成功')
                this.initialize()
              }
              if (res.code !== '200') {
                this.$message.error(res.msg)
              }
            }).catch(error => {
              console.log(error)
            }).finally(() => {
              this.dialogFormVisible = false
            })
            //表单没数据则代表是新增操作，因为进行新增时会把表单清空，不进行回显
          } else {
            addUser(this.form).then(res => {
              if (res.code === '200') {
                this.$message.success('新增成功')
                this.initialize()
              }
              if (res.code !== '200') {
                this.$message.error(res.msg)
              }
            }).catch(error => {
              console.log(error)
            }).finally(() => {
              this.dialogFormVisible = false
            })
          }
        }
      })
    },
    handleAvatarSuccess(response) {
      this.form.imgUrl = response.data
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
    //初始化用户信息，新增操作时会携带操作人信息
    this.userInfo = JSON.parse(localStorage.getItem('xm-user'))
    //初始化页面
    this.initialize()
    this.baseURL = process.env.VUE_APP_BASEURL
  }
}
</script>

<style scoped>
.ManagerUser-header {
  text-align: left;
}

.ManagerUser-header-a, .ManagerUser-header-b {
  background: #ffffff;
  border-radius: 10px;
  box-shadow: 1px 1px 5px 0 #bfbfbf;
  padding: 5px;
}

.ManagerUser-header-b {
  margin: 5px 0;
}

.ManagerUser-content {
  border: 1px solid #cccccc;
  border-radius: 10px;
  box-shadow: 1px 1px 5px 0 #bfbfbf;
}

.block {
  margin: 10px 0;
}

.el-table__body-wrapper .el-table__row .el-table__cell .cell {
  height: 70px !important;
}

.el-dialog {
  width: 100px !important;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader i {
  border: 1px dashed #d9d9d9;
  border-radius: 50%;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
