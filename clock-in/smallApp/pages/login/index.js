const {
    post
} = require("../../utils/requestUtil")
Page({

    /**
     * 页面的初始数据
     */
    data: {
        type: 0,
        username: "",
        password: ""
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let token = wx.getStorageSync('token')
        if (token) {
            wx.reLaunch({
                url: '/pages/home/home',
            })
        }
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },
    login: function () {
        let type = this.data.type
        let username = this.data.username.trim()
        let password = this.data.password
        if (username.length == 0) {
            wx.showToast({
                title: '用户名不能为空',
                icon: 'none',
                duration: 2000
            })
            return;
        }
        if (password.length == 0) {
            wx.showToast({
                title: '密码不能为空',
                icon: 'none',
                duration: 2000
            })
            return;
        }
        post({
            url: "/login",
            data: {
                type,
                username,
                password
            }
        }).then(res => {
            wx.setStorageSync('token', res.data.token)
            wx.setStorageSync('type', type)
            wx.reLaunch({
                url: '/pages/home/home',
            })
        })
    },
    register: function () {
        let type = this.data.type
        let username = this.data.username.trim()
        let password = this.data.password
        if (username.length == 0) {
            wx.showToast({
                title: '用户名不能为空',
                icon: 'none',
                duration: 2000
            })
            return;
        }
        if (password.length == 0) {
            wx.showToast({
                title: '密码不能为空',
                icon: 'none',
                duration: 2000
            })
            return;
        }
        post({
            url: "/register",
            data: {
                type,
                username,
                password
            }
        }).then(res => {
            wx.showToast({
                title: res.msg,
                icon: 'none',
                duration: 2000
            })
        })
    },
    setUsername: function (e) {
        this.setData({
            username: e.detail.value
        })
    },
    setPassword: function (e) {
        this.setData({
            password: e.detail.value
        })
    },
    setType: function (e) {
        this.setData({
            type: Number.parseInt(e.detail.value)
        })
    }
})