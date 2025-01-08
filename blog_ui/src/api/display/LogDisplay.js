import request from "@/util/request";

export function queryOperateLog(methodName = null, page, pageSize) {
    return request({
        url: '/operateLog',
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
        url: '/operateLog',
        method: 'DELETE',
        data: data
    })
}
