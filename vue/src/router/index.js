import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

//处理面包屑导航中的vue-router在3.0版本频繁重定向导航报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

const routes = [
    {
        //后台路由
        path: '/',
        name: 'Manager',
        component: () => import('@/views/ManagerView.vue'),
        //要前台注掉，因为这会直接重定向到后台主页，不会进入前置路由守卫的if条件
        // redirect: '/ManagerHome',
        children: [
            {
                path: '403Manager',
                name: 'ManagerNoAuth',
                meta: {name: '后台无权限'},
                component: () => import('@/views/manager/403Manager.vue')
            },
            {
                path: 'managerDocs',
                name: 'ManagerDocs',
                meta: {name: '后台文档'},
                component: () => import('@/views/manager/ManagerDocs.vue')
            },
            {
                path: 'managerHome',
                name: 'ManagerHome',
                meta: {name: '后台主页'},
                component: () => import('@/views/manager/ManagerHome.vue')
            },
            {
                path: 'managerPerson',
                name: 'ManagerPerson',
                meta: {name: '后台个人中心'},
                component: () => import('@/views/manager/ManagerPerson.vue')
            },
            {
                path: 'managerPwd',
                name: 'ManagerPwd',
                meta: {name: '后台修改密码'},
                component: () => import('@/views/manager/ManagerPwd.vue')
            },
            {
                path: 'managerUser',
                name: 'ManagerUser',
                meta: {name: '后台用户'},
                component: () => import('@/views/manager/ManagerUser.vue')
            },

        ]
    },
    {
        //前台路由
        path: '/front',
        name: 'Front',
        component: () => import('@/views/FrontView.vue'),
        children: [
            {
                path: 'frontHome',
                name: 'FrontHome',
                meta: {name: '前台主页'},
                component: () => import('@/views/front/FrontHome.vue')
            },
            {
                path: 'frontPerson',
                name: 'FrontPerson',
                meta: {name: '前台个人中心'},
                component: () => import('@/views/front/FrontPerson.vue')
            },
            {
                path: 'frontTimeline',
                name: 'FrontTimeline',
                meta: {name: '前台时间线'},
                component: () => import('@/views/front/FrontTimeline.vue')
            }
        ]
    },
    {
        path: '/login',
        name: 'Login',
        meta: {name: '登录'},
        component: () => import('@/views/LoginView.vue')
    },
    {
        path: '/register',
        name: 'Register',
        meta: {name: '注册'},
        component: () => import('@/views/RegisterView.vue')
    },
    {
        path: '*',
        name: 'NotFound',
        meta: {name: '无法访问'},
        component: () => import('@/views/404View.vue')
    }
]

const router = new VueRouter({
    //history模式，去掉了#更干净，不过兼容性没hash好，
    //项目部署上线需要后端支持，解决页面服务端404找不到端口的问题
    mode: 'history',
    //将配置好的路由规则交给路由器
    routes
})

//不需要前台注掉
//前置路由守卫每次路由切换都会调用
//to导航到的路由路径
//from从哪个路由路径过来的
//next传参则导航到对应路由，不传参则放行
router.beforeEach((to, from, next) => {
    //获取当前登录用户信息
    let user = JSON.parse(localStorage.getItem("xm-user")) || {}
    //当用户登录成功来到系统里面时判断
    if (to.path === '/') {
        //判断有没有获取到角色
        if (user.role) {
            //查看用户权限导航到后台
            if (user.role === '管理员') {
                next('/managerHome')
            } else {
                //查看用户权限导航到前台
                next('/front/frontHome')
            }
        } else {
            //没获取到重新登录
            next('/login')
        }
    } else {
        //放行
        next()
    }
})

export default router
