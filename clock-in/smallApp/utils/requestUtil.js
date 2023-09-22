import {tansParams} from "./baseUtil"
/**
 * 基础URL
 */
const baseUrl = 'http://localhost:9999'

export const get = data => {
    let url = data.url
    const queryParams = data.queryParams
    const isNotToken = data.isNotToken ? data.isNotToken : false
    const token = wx.getStorageSync('token')
    let header = {
        'content-type': 'application/json',
    }
    if(token && !isNotToken){
        header.token = token
    }
    wx.showLoading({
        title: '加载中',
        mask: true
    })

    return new Promise((resolve) => {
        url = baseUrl + url +'?' + tansParams(queryParams)
        wx.request({
            url: url.slice(0, -1),
            method: 'GET',
            header: header,
            success: function (res) {
                if (res.data.code == 200) {
                    wx.hideLoading()
                    resolve(res.data)
                }else{
                    wx.showToast({
                        title: res.data.msg,
                        icon: 'none'
                    })
                }
            },
            fail: function (error) {
                wx.showToast({
                    title: '网络异常',
                    icon: 'none'
                })
            }
        })
    })
}

export const post = data => {
    const url = data.url
    const d = data.data
    const isNotToken = data.isNotToken ? data.isNotToken : false
    const token = wx.getStorageSync('token')
    let header = {
        'content-type': 'application/json',
    }
    if(token && !isNotToken){
        header.token = token
    }
    wx.showLoading({
        title: '加载中',
        mask: true
    })
    return new Promise((resolve) => {
        wx.request({
            url: baseUrl + url,
            method: 'POST',
            data:d,
            header: header,
            success: function (res) {
                if (res.data.code == 200) {
                    wx.hideLoading()
                    wx.showToast({
                        title: res.data.msg,
                        icon: 'none'
                    })
                    resolve(res.data)
                }else{
                    wx.showToast({
                        title: res.data.msg,
                        icon: 'none'
                    })
                }
            },
            fail: function (error) {
                wx.showToast({
                    title: '网络异常',
                    icon: 'none'
                })
            }
        })
    })
}

export const del = data=>{
    const url = data.url
    const d = data.data
    const isNotToken = data.isNotToken ? data.isNotToken : false
    const token = wx.getStorageSync('token')
    let header = {
        'content-type': 'application/json',
    }
    if(token && !isNotToken){
        header.token = token
    }
    wx.showLoading({
        title: '加载中',
        mask: true
    })
    return new Promise((resolve) => {
        wx.request({
            url: baseUrl + url,
            method: 'Delete',
            data:d,
            header: header,
            success: function (res) {
                if (res.data.code == 200) {
                    wx.hideLoading()
                    resolve(res.data)
                }else{
                    wx.showToast({
                        title: res.data.msg,
                        icon: 'none',
                        duration: 2000
                    })
                }
            },
            fail: function (error) {
                wx.showToast({
                    title: '网络异常',
                    icon: 'none'
                })
            }
        })
    })
}