<template>
  <div>
    <div class="ManagerUser-header">
      <div class="ManagerUser-header-a">
        <el-input v-model="queryMethodsName" placeholder="请输入方法名查询" style="width: 200px"></el-input>
        <el-button class="el-icon-search" style="margin-left: 10px" type="success" @click="initialize"> 查询
        </el-button>
        <el-button class="el-icon-s-tools" type="info" @click="reset"> 重置</el-button>
      </div>
      <div class="ManagerUser-header-b">
        <el-button class="el-icon-close" type="warning" @click="deleteLog"> 批量删除</el-button>
      </div>
    </div>

    <el-table
        :data="tableData"
        stripe
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          align="center"
          type="selection">
      </el-table-column>
      <el-table-column
          align="center"
          label="操作人id"
          prop="operateUserId"
          width="100">
      </el-table-column>
      <el-table-column
          align="center"
          label="开始操作时间">
        <template v-slot="scope">
          {{ new Date(scope.row.operateTime).toLocaleString() }}
        </template>
      </el-table-column>
      <el-table-column
          align="center"
          label="操作类名"
          prop="className"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          align="center"
          label="操作的方法名"
          prop="methodName"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          align="center"
          label="形参接收的参数"
          prop="methodParams"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          align="center"
          label="响应的数据"
          prop="returnValue"
          show-overflow-tooltip>
      </el-table-column>
      <el-table-column
          align="center"
          label="总耗时（毫秒）"
          prop="costTime">
      </el-table-column>
    </el-table>

    <div class="block">
      <el-pagination
          :current-page="page"
          :page-size="pageSize"
          :page-sizes="[5, 10, 15, 20]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>


import {deleteOperateLog, queryOperateLog} from "@/api/display/LogDisplay";

export default {
  data() {
    return {
      //当前登录的日志信息
      userInfo: {},
      //页面初始分页数据
      page: 1,
      pageSize: 5,
      //数据总数
      total: 0,
      //复选框id
      ids: [],
      //表格数据
      tableData: [],
      //标题条件查询日志
      queryMethodsName: null,
      //删除复选框表单
      deleteForm: [],
    }
  },
  methods: {
    //分页查询文档
    initialize() {
      queryOperateLog(null, this.page, this.pageSize).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.data
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
    //批量删除日志或删除单个日志
    deleteLog() {
      if (this.ids.length === 0) {
        return this.$message.error('请选择您要删除的数据')
      }
      this.$confirm('您确定要删除吗？', '确定删除', {type: 'warning'}).then(() => {
        deleteOperateLog(this.ids).then(res => {
          if (res.code === '200') {
            this.initialize()
            this.$message.success('删除成功')
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
    //重置条件查询框
    reset() {
      this.queryMethodsName = null
      this.page = 1
      this.initialize()
    },
    //控制复选框
    handleSelectionChange(rows) {
      this.ids = rows.map(s => s.id)
      console.log(this.ids)
    },
  },
  created() {
    //初始化日志信息，新增操作时会携带操作人信息
    this.userInfo = JSON.parse(localStorage.getItem('xm-user'))
    //初始化页面
    this.initialize()
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
