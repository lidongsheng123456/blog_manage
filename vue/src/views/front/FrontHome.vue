<template>
  <div>
    <div class="FrontHome-content-big-font">
      <div style="font-size: 80px;font-weight: 600;color: pink">
        {{ bigText }}
      </div>
      <div style="font-size: 40px;font-weight: 600;color: black">
        {{ smallText }}
      </div>
    </div>
    <div class="FrontHome-content-docs-body">
      <div class="FrontHome-content-docs-body-a">

        <div v-for="item in docs" :key="item.id" class="FrontHome-content-docs-body-a-body">
          <div style="overflow: hidden;font-size: 20px">
            {{ item.docsTitle }}
          </div>
          <br>
          <div style="overflow: hidden;font-size: 13px;display: flex;height: 20px">
            <div class="el-icon-user"> {{ item.username }}</div>
            <div class="el-icon-date"> {{ new Date(item.createTime).toLocaleString() }}</div>
          </div>
        </div>

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
      <div class="FrontHome-content-docs-body-b">
        <div class="FrontHome-content-docs-body-b-a">
          <el-image :src="userInfo.imgUrl || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"
                    style="border-radius: 50%;width: 100px;height: 100px;">
          </el-image>
          <div style="font-size: 20px;font-weight: 600;border-bottom: 1px solid #e4e4e4;margin: 20px 0;padding: 10px 0">
            {{ userInfo.username }}
          </div>
          <div style="padding: 20px">
            <div v-for="item in docs" :key="item.id"
                 style="border: 1px solid #cccccc;border-radius: 5px;display: inline-block;width: fit-content;margin: 10px">
              {{ item.docsTitle }}
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="FrontHome-content-bottom" style="text-align: center">
      <div>
        备案信息
      </div>
    </div>
  </div>
</template>

<script>
import {getDocs} from "@/api/display/FrontRequest";

export default {
  data() {
    return {
      bigText: '千里之行',
      smallText: '始于足下',
      docs: [],
      userInfo: {},
      docsTitle: '',
      page: 1,
      pageSize: 5,
      total: 0
    }
  },
  methods: {
    initializer() {
      getDocs(this.docsTitle, this.page, this.pageSize).then(res => {
        if (res.code === '200') {
          this.docs = res.data.data
          this.total = res.data.total
          console.log(this.docs)
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
      this.initializer(this.page, this.pageSize)
    },
    handleCurrentChange(val) {
      this.page = val
      this.initializer(this.page, this.pageSize)
    }
  },
  created() {
    this.userInfo = JSON.parse(localStorage.getItem('xm-user'))
    this.initializer()
  }
}
</script>

<style scoped>
.FrontHome-content-big-font {
  margin-top: 30%;
  transform: translateY(-50%);
  text-align: center;
  height: 300px;
}

.FrontHome-content-docs-body {
  display: flex;
  justify-content: space-between;
  width: 1000px;
  margin: 0 auto;
}

.FrontHome-content-docs-body-a {
  width: 700px;
}

.FrontHome-content-docs-body-b {
  width: 280px;
  background: #f9fafb;
  border-radius: 10px;
  box-shadow: 1px 1px 10px 5px #e3edff;
}

.FrontHome-content-docs-body-a-body {
  background: #f9fafb;
  border-radius: 10px;
  box-shadow: 1px 1px 10px 5px #e3edff;
  height: 70px;
  margin: 20px;
  padding: 25px;
}

.FrontHome-content-docs-body-a-body div {
  margin-right: 20px;
}

.FrontHome-content-docs-body-a .FrontHome-content-docs-body-a-body:first-child {
  margin-top: 0;
}

.FrontHome-content-docs-body-b-a {
  text-align: center;
  margin: 10px 0;
}

.block {
  margin: 20px 0;
  height: 0;
  text-align: center;
}

.FrontHome-content-bottom {
  padding: 50px 0;
  width: 1000px;
  margin: 50px auto;
}
</style>
