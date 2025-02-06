import request from "@/util/request";

export function queryOperateLog(methodName, page, pageSize) {
    return request({
        url: '/admin/operateLog',
        method: 'GET',
        params: {
            methodName,
            page,
            pageSize
        }
    })
}

export function deleteOperateLog(data) {
    return request({
        url: '/admin/operateLog',
        method: 'DELETE',
        data: data
    })
}
