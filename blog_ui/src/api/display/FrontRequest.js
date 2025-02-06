import request from "@/util/request";

export function getDocs(docsTitle, page, pageSize) {
    return request({
        url: '/user/front',
        method: 'GET',
        params: {
            docsTitle,
            page,
            pageSize
        }
    })
}
