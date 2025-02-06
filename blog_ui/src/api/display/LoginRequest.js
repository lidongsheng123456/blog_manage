import request from "@/util/request";

//登录
export function login(data) {
    return request({
        url: '/login',
        method: 'POST', data: data,
        withCredentials: true // 确保发送Cookie
    })
}

//注册
export function register(data) {
    return request({
        url: '/register',
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
export function getCaptcha() {
    return fetch(process.env.VUE_APP_BASEURL + '/common/captcha/getCaptcha', {
        method: 'get',
        credentials: 'include' // 确保发送Cookie
    }).then(response => {
        if (!response.ok) {
            throw new Error('网络响应不正常');
        }
        return response.blob(); // 将响应体转换为 Blob 对象
    }).then(blob => {
        return URL.createObjectURL(blob);
    }).catch(error => {
        console.error('获取验证码失败:', error);
        return null;
    });
}

// 校验验证码是否正确
export function captchaCheck(code) {
    return request({
        url: '/captcha/check', method: 'post', params: code
    })
}
