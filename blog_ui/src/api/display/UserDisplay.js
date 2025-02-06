import request from "@/util/request";

//分页显示文档
export function queryUserData(id, username, page, pageSize) {
    return request({
        url: '/admin/user/queryUserData',
        method: 'GET',
        params: {
            id: id,
            username: username,
            page: page,
            pageSize: pageSize
        }
    })
}

//添加文档
export function addUser(data) {
    return request({
        url: '/admin/user',
        method: 'POST',
        data: data
    })
}

//删除文档
export function deleteUser(data) {
    return request({
        url: '/admin/user',
        method: 'DELETE',
        data: data
    })
}

//修改文档
export function updateUser(data) {
    return request({
        url: '/admin/user',
        method: 'PUT',
        data: data
    })
}

//个人中心
export function personCenter(data) {
    return request({
        url: '/admin/user/personCenter',
        method: 'PUT',
        data: data
    })
}

export function getPersonPwd(id) {
    return request({
        url: '/admin/user/PersonPwd',
        method: 'GET',
        params: id
    })
}

//更新密码
export function updatePwd(data) {
    return request({
        url: '/admin/user/PersonPwd',
        method: 'PUT',
        data: data
    })
}

//效验用户信息
export function validateUser(data) {
    return request({
        url: '/admin/user/validateUser',
        method: 'POST',
        data: data
    })
}
