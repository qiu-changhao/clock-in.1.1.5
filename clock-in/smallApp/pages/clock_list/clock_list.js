const {
  get,
  del
} = require('../../utils/requestUtil.js')
Page({
  /*** 页面的初始数据*/
  data: {
    userInfo: null,
    clock_list: [],
  },
  onLoad: function (options) {
    get({
      url: "/clockInSet/getClockInList"
    }).then(res => {
      this.setData({
        clock_list: res.data
      })
    })
  },

  setSadianClock: function (e) {
    let deleteId = e.currentTarget.dataset.smile
    let that = this
    wx.showModal({
      title: '请确认',
      content: `您确认删除此条打卡吗?`,
      confirmText: '确认',
      success(res) {
        if (res.confirm) {
          del({
            url: "/clockInSet/del/" + deleteId
          }).then(() => {
            that.onLoad()
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },
  setDeleteClock: function () {
    this.setData({
      btnBgBackground: "red",
      btnBgValue: "删除"
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
  onShareAppMessage: function (res) {
    return {
      title: '远航打卡-VX:roadisvx',
      path: '/pages/home/home', //用户点开后的默认页面，我默认为首页
      imageUrl: "/assets/loa.png", //自定义图片的地址
      success(res) {
        console.log('发送成功！')
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },
  onShareTimeline: function (res) {
    return {
      title: '远航打卡-VX:roadisvx'
    }
  }
})