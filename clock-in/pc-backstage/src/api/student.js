import request from "@/utils/request"

export function getStudentPage(query) {
    return request({
        url: '/student/page',
        method: 'get',
        params: query
    })
}

export function getStudentById(id) {
    return request({
        url: `/student/getStudent/${id}`,
        method: 'get'
    })
}

export function addStudent(data) {
    return request({
        url: '/student/add',
        method: 'post',
        data: data
    })
}

export function editStudent(data) {
    return request({
        url: '/student/update',
        method: 'put',
        data: data
    })
}

export function deleteStudent(ids) {
    return request({
        url: `/student/delete/${ids}`,
        method: 'delete'
    })
}
