// pages/home/home.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    type: 0
  },
  gotoJoin() { //查看参与的打卡
    wx.navigateTo({
      url: '../play/play',
    })
  },
  gotoList() { //查看创建的打卡
    wx.navigateTo({
      url: '../clock_list/clock_list',
    })
  },
  gotoCreate() { //创建打卡
    wx.navigateTo({
      url: '../create/create',
    })
  },
  gotoPlayclockList() { //管理打卡
    wx.navigateTo({
      url: '../playclock_list/playclock_list',
    })
  },
  gotoEnroll(){
    wx.navigateTo({
      url: '../enroll/enroll',
    })
  },
  gotoDangqianXuanze() {
    wx.navigateTo({
      url: '../playList/playList',
    })
  },
  gotoTeam() {
    wx.navigateTo({
      url: '../team/team',
    })
  },
  gotoChengfa() {
    wx.navigateTo({
      url: '../punish/punish',
    })
  },
  logout() {
    wx.removeStorageSync('token')
    wx.removeStorageSync('type')
    wx.showToast({
      title: '退出成功',
      icon: "success",
      duration: 2000
    })
    setTimeout(function(){
      wx.reLaunch({
        url: '../login/index',
      })
    },2000)
  },
  direction() {
    wx.navigateTo({
      url: '../image/image',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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
    this.setData({
      type: wx.getStorageSync('type')
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
  onPullDownRefresh: function () {},

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function (res) {

  },
})