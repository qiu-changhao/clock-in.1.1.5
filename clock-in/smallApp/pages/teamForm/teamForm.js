import {
    get,
    post
} from "../../utils/requestUtil"

// pages/teamForm/teamForm.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: undefined,
        name: '',
        sort: 0
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let id = options.id
        let that = this
        this.setData({
            id
        })
        get({
            url: "/team/get/" + id
        }).then(res=>{
            that.setData({
                name:res.data.name,
                sort:res.data.sort
            })
        })
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
    setName(e) {
        this.setData({
            name: e.detail.value
        })
    },
    setSort(e) {
        this.setData({
            sort: e.detail.value
        })
    },
    createTeam() {
        let name = this.data.name
        let sort = this.data.sort
        if (name == undefined || name.length == 0) {
            wx.showToast({
                title: '班级名称不能为空',
                icon: 'none',
                duration: 2000
            })
        }
        post({
            url: "/team/create",
            data: {
                name,
                sort
            }
        }).then(() => {
            setTimeout(() => {
                wx.navigateBack()
            }, 2000)
        })
    },
    updateTeam() {
        let name = this.data.name
        let sort = this.data.sort
        let id = this.data.id
        if (name == undefined || name.length == 0) {
            wx.showToast({
                title: '班级名称不能为空',
                icon: 'none',
                duration: 2000
            })
        }
        post({
            url: "/team/update",
            data: {
                id,
                name,
                sort
            }
        }).then(() => {
            setTimeout(() => {
                wx.navigateBack()
            }, 2000)
        })
    }
})