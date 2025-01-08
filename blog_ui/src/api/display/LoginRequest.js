import request from "@/util/request";

//登录
export function login(data) {
    return request({
        url: '/user/login',
        method: 'POST',
        data: data
    })
}

//注册
export function register(data) {
    return request({
        url: '/user/register',
        method: 'POST',
        data: data
    })
}

// 退出方法
export function logout() {
    return request({
        url: '/logout',
        method: 'post'
    })
}

// 获取验证码
export function getCodeImg() {
    return request({
        url: '/captchaImage',
        method: 'get',
    })
}
