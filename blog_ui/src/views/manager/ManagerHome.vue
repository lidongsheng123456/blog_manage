<template>
  <div>
    <div class="ManagerHome-header">
      欢迎 {{ '管理员' }} 使用博客
    </div>
    <div class="ManagerHome-content">
      <div class="block">
        <el-timeline>
          <el-timeline-item v-for="item in docs"
                            :key="item.id"
                            :timestamp="new Date(item.createTime).toLocaleString()"
                            placement="top">
            <el-card>
              <h4>标题：{{ item.docsTitle }}</h4>

              <el-tooltip class="item" effect="dark" :content="item.docsContent + ''" placement="top-start">
                <div class="ManagerHome-content-body">
                  内容：{{ item.docsContent }}
                </div>
              </el-tooltip>
              <p>{{ item.username }} 提交于{{ new Date(item.createTime).toLocaleString() }}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
        <div class="block">
          <el-pagination
              :current-page="page"
              :page-size="pageSize"
              :page-sizes="[4, 8, 16, 32]"
              :total="total"
              layout="total, sizes, prev, pager, next, jumper"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange">
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {queryDocsData} from "@/api/display/DocsDisplay";

export default {
  data() {
    return {
      page: 1,
      pageSize: 4,
      total: 0,
      docs: null,
      userInfo: {}
    }
  },
  methods: {
    //分页查询文档
    initialize() {
      queryDocsData(null, this.page, this.pageSize).then(res => {
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
    handleSizeChange(val) {
      this.page = 1
      this.pageSize = val
      this.initialize(this.page, this.pageSize)
    },
    handleCurrentChange(val) {
      this.page = val
      this.initialize(this.page, this.pageSize)
    }
  },
  created() {
    //初始化当前后台登录用户信息
    this.userInfo = JSON.parse(localStorage.getItem('xm-user')) || {}
    //初始化页面
    this.initialize()
  }
}
</script>

<style scoped>
.ManagerHome-header {
  background: #ffffff;
  height: 40px;
  line-height: 40px;
  border-radius: 10px;
  box-shadow: 1px 1px 5px 0 #bfbfbf;
}

.block {
  margin: 20px 0;
  height: 0;
}

.el-card {
  height: 120px;
  line-height: 30px;
  text-align: left;
}

.el-timeline-item {
  text-align: left;
}

.ManagerHome-content-body {
  cursor: pointer;
  height: 30px;
  overflow: hidden;
  font-weight: 800;
  white-space: nowrap; /* 防止文本换行 */
  text-overflow: ellipsis; /* 显示省略号 */
}

.el-pagination {
  height: 50px;
}
</style>
