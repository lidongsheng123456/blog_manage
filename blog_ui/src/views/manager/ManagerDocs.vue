<template>
  <div>
    <div class="ManagerDocs-header">
      <div class="ManagerDocs-header-a">
        <el-input v-model="docsTitle" placeholder="请输入文档标题查询" style="width: 200px"></el-input>
        <el-button class="el-icon-search" style="margin-left: 10px" type="primary" @click="initialize"> 查询
        </el-button>
        <el-button class="el-icon-s-tools" type="info" @click="reset"> 重置</el-button>
      </div>
      <div class="ManagerDocs-header-b">
        <el-button plain class="el-icon-plus" type="primary" @click="addDocs"> 新增</el-button>
        <el-button plain class="el-icon-close" type="danger" @click="deleteDocs"> 批量删除</el-button>
      </div>
    </div>

    <div class="ManagerDocs-content">
      <el-table
          :data="docs"
          stripe
          style="width: 100%"
          @selection-change="handleSelectionChange">
        <el-table-column
            align="center"
            type="selection">
        </el-table-column>
        <el-table-column
            align="center"
            label="文档标题"
            prop="docsTitle">
        </el-table-column>
        <el-table-column
            align="center"
            label="文档内容"
            prop="docsContent"
            show-overflow-tooltip>
        </el-table-column>
        <el-table-column
            align="center"
            label="创建人"
            prop="username">
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
            <el-button plain class="el-icon-edit" type="warning" @click="editDocs(scope.row)"> 编辑</el-button>
            <el-button plain class="el-icon-close" type="danger" @click="deleteDocs"> 删除</el-button>
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
        <el-form-item :label-width="formLabelWidth" label="文档标题" prop="docsTitle">
          <el-input v-model="form.docsTitle" autocomplete="off" placeholder="请输入文档标题"></el-input>
        </el-form-item>
        <el-form-item :label-width="formLabelWidth" label="文档内容" prop="docsContent">
          <el-input
              v-model="form.docsContent"
              :rows="2"
              placeholder="请输入文档内容"
              type="textarea">
          </el-input>
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
import {addDocs, deleteDocs, queryDocsData, updateDocs, validateDocs} from "@/api/display/DocsDisplay";

export default {
  data() {
    //数据库不能存在俩个相同文章标题
    const validateDocsTitle = ((rule, value, callback) => {
      const DOCSID = this.form.id ? this.form.id : null
      validateDocs({[rule.fullField]: value, id: DOCSID}).then((res) => {
        if (res.code === '200') {
          callback()
        }
        if (res.code !== '200') {
          this.$message.error(res.msg)
          callback(new Error(res.msg))
        }
      })
    })
    return {
      //当前登录的用户信息
      userInfo: {},
      //页面初始分页数据
      page: 1,
      pageSize: 5,
      //数据总数
      total: 0,
      //文章数据
      docs: [],
      //标题条件查询文章
      docsTitle: null,
      //复选框id
      ids: [],
      //对话框显示隐藏
      dialogFormVisible: false,
      //新增对话框与修改对话框表单
      form: {},
      //对话框表单宽度
      formLabelWidth: '80px',
      //对话框表单标题
      dialogTitle: null,
      //表单规则
      rules: {
        docsTitle: [
          {required: true, validator: validateDocsTitle, trigger: 'blur'},
        ],
        docsContent: [
          {required: true, message: '请输入文档内容', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    //分页查询文档
    initialize() {
      queryDocsData(this.docsTitle, this.page, this.pageSize).then(res => {
        if (res.code === '200') {
          this.docs = res.data.data
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
      this.ids = rows.map(e => e.id)
    },
    //添加文章
    addDocs() {
      this.form = {}
      this.dialogTitle = '添加文章'
      this.dialogFormVisible = true
    },
    //批量删除文章或删除单个文章
    deleteDocs() {
      if (this.ids.length === 0) {
        return this.$message.error('请选择您要删除的数据')
      }
      this.$confirm('您确定要删除吗？', '确定删除', {type: 'warning'}).then(() => {
        deleteDocs(this.ids).then(res => {
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
    //修改文章
    editDocs(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogTitle = '编辑文章'
      this.dialogFormVisible = true
    },
    //重置条件查询框
    reset() {
      this.docsTitle = null
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
            updateDocs(this.form).then(res => {
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
            this.form.accountId = this.userInfo.id
            console.log(this.form)
            addDocs(this.form).then(res => {
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
    }
  },
  created() {
    //初始化用户信息，新增操作时会携带操作人信息
    this.userInfo = JSON.parse(localStorage.getItem('xm-user'))
    //初始化页面
    this.initialize()
  }
}
</script>

<style scoped>
.ManagerDocs-header {
  text-align: left;
}

.ManagerDocs-header-a, .ManagerDocs-header-b {
  background: #ffffff;
  border-radius: 10px;
  box-shadow: 1px 1px 5px 0 #bfbfbf;
  padding: 5px;
}

.ManagerDocs-header-b {
  margin: 5px 0;
}

.ManagerDocs-content {
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
</style>
