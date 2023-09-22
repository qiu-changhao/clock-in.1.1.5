// pages/play/play.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    clock_list:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // let choiceClassNo=wx.getStorageSync('classInfo').index
    let classInfo=wx.getStorageSync('classInfo')
    let classNo=classInfo.classNo
    let classIndex=classInfo.index
    if(classIndex==0 || classIndex==undefined){
      wx.showToast({
        title: '请先登录或选择兼职',
        icon:"none",
        success(){
          setTimeout(()=>{
            wx.navigateBack()
          },2000)
        }
      })
    }else{
      wx.cloud.callFunction({
        name:'get_clock_list',
        data:{
          classNo:classNo[classIndex]//参与的打卡 只用传自己的打卡名
        },
        success:(res)=>{
          console.log(res.result.clock_list);
          this.setData({
            clock_list:res.result.clock_list
          })
        },
        fail(err){
          console.log(err);
        }
      })
    }
  },
  gotoPlay(){
    wx.navigateTo({
      url: '../play/play',
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
      title: '小程序打卡-VX:roadisvx'
    }
  }
})