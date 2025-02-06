<template>
  <el-container style="height: 100vh">
    <el-header>
      <div class="manager-header-left">
        <img alt="头像" src="../assets/img/blog_logo.png">
        <div>博客</div>
      </div>

      <div class="manager-header-center">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{path: '/'}">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{path: $route.path}">{{ $route.meta.name }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>

      <div class="manager-header-right">
        <el-dropdown placement="bottom">
          <div class="img">
            <el-image :src="userInfo.imgUrl || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"
                      alt="img" style="height: 50px;border-radius: 50%"/>
            <div style="margin-left: 15px;color: white;font-size: 15px">{{ userInfo.username || '管理员' }}</div>
          </div>
          <el-dropdown-menu>
            <el-dropdown-item @click.native="queryPerson">个人中心</el-dropdown-item>
            <el-dropdown-item @click.native="$router.push('/managerPwd')">修改密码</el-dropdown-item>
            <el-dropdown-item @click.native="quit">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu :default-active="$route.path" :default-openeds="['docs','user','log']" router style="border: none">
          <el-menu-item index="/managerHome">
            <i class="el-icon-s-home"></i>
            <span style="font-weight: 540;font-size: 15px">系统首页</span>
          </el-menu-item>

          <el-submenu index="docs">
            <template slot="title">
              <i class="el-icon-menu"> 博客管理</i>
            </template>
            <el-menu-item class="el-menu-submenu-item" index="/managerDocs">文档信息</el-menu-item>
          </el-submenu>

          <el-submenu index="user">
            <template slot="title">
              <i class="el-icon-menu"> 用户管理</i>
            </template>
            <el-menu-item class="el-menu-submenu-item" index="/managerUser">用户信息</el-menu-item>
          </el-submenu>

          <el-submenu index="log">
            <template slot="title">
              <i class="el-icon-menu"> 日志管理</i>
            </template>
            <el-menu-item class="el-menu-submenu-item" index="/managerLog">操作日志</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <keep-alive>
            <router-view @update:user="updateUser"></router-view>
          </keep-alive>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      userInfo: {}
    }
  },
  methods: {
    updateUser() {
      this.userInfo = JSON.parse(localStorage.getItem('xm-user')) || {}
    },
    queryPerson() {
      if (this.userInfo.role === '管理员') {
        this.$router.push('/managerPerson')
      }
    },
    quit() {
      localStorage.removeItem('xm-user')
      this.$router.push('/login')
    },
  },
  created() {
    this.updateUser()
    if (!this.userInfo.id) {
      this.$router.push("/login")
    }
  }
}
</script>

<style scoped>
@import "@/assets/css/manager.css";
</style>
