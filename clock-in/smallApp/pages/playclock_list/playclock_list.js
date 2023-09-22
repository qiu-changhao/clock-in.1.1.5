import {
  get
} from "../../utils/requestUtil"

// pages/clock_list/clock_list.js
Page({
  /*** 页面的初始数据*/
  data: {
    clock_logs: [],
  },
  onLoad: function (options) {
    /*** 生命周期函数--监听页面加载*/
    get({
      url: "/teacher/getStatisticsStudentList"
    }).then(res => {
      this.setData({
        clock_logs: res.data
      })
    })
  },

  //复制功能
  copyText(e) {
    let content = e.currentTarget.dataset.text
    wx.setClipboardData({ //设置粘贴板的内容
      data: content,
      success(res) {
        wx.showToast({
          title: '复制成功',
          duration: 2000
        })
      }
    })
  },

  //拨打电话功能
  tels(e) {
    let content = e.currentTarget.dataset.text
    wx.makePhoneCall({
      phoneNumber: content,
    })
  },

  copyAllUser() {
    var that = this
    let data = ''
    let userArr = []
    for (let log = 0; log < that.data.clock_logs.length; log++) {
      userArr[log] = that.data.clock_logs[log].logsInfo[0].user
    }
    data = userArr.map(function (obj, index) {
      let result = '';
      if (obj.userName) {
        result = result + obj.userName;
      }
      if (obj.userPhone) {
        result = result + "  " + obj.userPhone;
      }
      if (obj.userId) {
        result = result + "  " + obj.userId;
      }
      return result;
    }).join("\n");

    wx.setClipboardData({ //设置粘贴板的内容
      data: data,
      success(res) {
        wx.showToast({
          title: '复制完成!',
          duration: 2000
        })
      }
    })
  },

  setSadianClock: function (e) {
    let deleteId = e.currentTarget.dataset.smile
    var that = this;
    wx.showModal({
      title: '请确认',
      content: `确定删除此条打卡记录?`,
      confirmText: '确认',
      success(res) {
        if (res.confirm) {
          wx.cloud.callFunction({ //获取打卡列表 如果列表已经存在 打卡者 且 是同一个兼职 则不add 而是 update
            name: "clock_logs_delete",
            data: {
              deleteId
            },
            success: (res) => {
              if (res.result.stats.removed === 1) {
                that.onLoad();
                wx.hideLoading()
                wx.showToast({
                  title: '删除记录成功！',
                  icon: 'success',
                  duration: 2000
                })
              } else {
                wx.showToast({
                  title: '删除记录失败……',
                  icon: 'loading',
                  duration: 3000
                })
              }
            }
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
  showDetail: function (e) {
    let cId = e.currentTarget.dataset.cid
    wx.navigateTo({
      url: '../playclock_detail_list/playclock_detail_list?cId=' + cId,
    })
  }
})