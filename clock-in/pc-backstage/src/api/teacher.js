import request from "@/utils/request"

export function getTeacherPage(query) {
    return request({
        url: '/teacher/page',
        method: 'get',
        params: query
    })
}

export function getTeacherById(id) {
    return request({
        url: `/teacher/getTeacher/${id}`,
        method: 'get'
    })
}

export function addTeacher(data) {
    return request({
        url: '/teacher/add',
        method: 'post',
        data: data
    })
}

export function editTeacher(data) {
    return request({
        url: '/teacher/update',
        method: 'put',
        data: data
    })
}

export function deleteTeacher(ids) {
    return request({
        url: `/teacher/delete/${ids}`,
        method: 'delete'
    })
}
