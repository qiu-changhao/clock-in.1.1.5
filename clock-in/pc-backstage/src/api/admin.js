import request from "@/utils/request"

export function login(data) {
    return request({
        url: '/admin/login',
        method: 'post',
        headers: {
            isToken: false
        },
        data: data
    })
}


export function getInfo() {
    return request({
        url: '/admin/getInfo',
        method: 'get'
    })
}

export function updatePassword(data) {
    return request({
        url: '/admin/updatePassword',
        method: 'post',
        data: data
    })
}

export function updateUser(data) {
    return request({
        url: '/admin/updateUser',
        method: 'post',
        data: data
    })
}
