import request from "@/util/request";

export function getDocs(docsTitle, page, pageSize) {
    return request({
        url: '/front',
        method: 'GET',
        params: {
            docsTitle,
            page,
            pageSize
        }
    })
}
