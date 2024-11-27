<template>
  <div class="FrontHome">
    <div class="FrontHome-header">
      <div class="FrontHome-header-a">
        <el-image :src="userInfo.imgUrl || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"
                  style="border-radius: 50%;width: 80px;height: 80px;">
        </el-image>
        <div>
          {{ userInfo.username }}
        </div>
      </div>
      <div class="FrontHome-header-b">
        <i class="el-icon-search"></i>
        <input placeholder="请输入文档标题搜索"/>
        <router-link to="/front/frontHome">
          主页
        </router-link>
        <router-link to="/front/frontTimeline">
          时间线
        </router-link>
        <router-link to="/front/frontPerson">
          个人中心
        </router-link>
        <a style="cursor: pointer;" @click="quit">
          退出
        </a>
      </div>
    </div>

    <div class="FrontHome-content">
      <router-view @update:user="update"></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userInfo: {}
    }
  },
  methods: {
    quit() {
      localStorage.removeItem('xm-user')
      this.$router.push("/")
    },
    update() {
      this.userInfo = JSON.parse(localStorage.getItem('xm-user'))
    }
  },
  created() {
    this.update()
  }
}
</script>

<style scoped>
@import '@/assets/css/front.css';
</style>
