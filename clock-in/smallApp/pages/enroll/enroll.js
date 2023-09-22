const {
    post,
    get,
    del
} = require('../../utils/requestUtil.js')
Page({

    /**
     * 页面的初始数据
     */
    data: {
        teamList: [],
        teamArray: [],
        teamIndex: 0,
        teamId: undefined,
        bindingTeamList: []
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        get({
            url: "/team/list"
        }).then(res => {
            let teamList = res.data
            let teamArray = []
            let teamId = undefined
            if (teamList[0] != undefined) {
                teamId = teamList[0].id
            }
            teamList.forEach(e => {
                teamArray.push(e.name)
            })
            this.setData({
                teamList,
                teamArray,
                teamId
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
        get({
            url: "/student/getTeamList"
        }).then(res => {
            this.setData({
                bindingTeamList: res.data
            })
        })
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
    changeTeam: function (e) {
        let teamIndex = e.detail.value
        let teamId = this.data.teamList[teamIndex].id
        this.setData({
            teamIndex,
            teamId
        })
    },
    bindingTeam: function () {
        let that = this
        get({
            url: "/student/bindingTeam/" + this.data.teamId
        }).then(() => {
            that.onShow()
        })
    },
    setDeleteBinding: function () {
        this.setData({
            btnBgBackground: "red",
            btnBgValue: "删除"
        })
    },
    setDelBindingTeam: function (e) {
        let deleteId = e.currentTarget.dataset.smile
        let that = this
        wx.showModal({
            title: '请确认',
            content: `您确认删除绑定的班级吗?`,
            confirmText: '确认',
            success(res) {
                if (res.confirm) {
                    del({
                        url: "/student/delTeam/" + deleteId
                    }).then(()=>{
                        that.onShow()
                    })
                } else if (res.cancel) {
                    console.log('用户点击取消')
                }
            }
        })
    }
})