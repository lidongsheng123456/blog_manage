import request from "@/util/request";

//分页显示文档
export function queryDocsData(docsTitle, page, pageSize) {
    return request({
        url: '/admin/docs/queryDocsData',
        method: 'GET',
        params: {
            docsTitle,
            page,
            pageSize
        }
    })
}

//添加文档
export function addDocs(data) {
    return request({
        url: '/admin/docs',
        method: 'POST',
        data: data
    })
}

//删除文档
export function deleteDocs(data) {
    return request({
        url: '/admin/docs',
        method: 'DELETE',
        data: data
    })
}

//修改文档
export function updateDocs(data) {
    return request({
        url: '/admin/docs',
        method: 'PUT',
        data: data
    })
}

//查找是否有相同数据
export function validateDocs(docsTitle) {
    return request({
        url: '/admin/docs/validateDocs',
        method: 'POST',
        data: docsTitle
    })
}


