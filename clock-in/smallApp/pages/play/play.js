// pages/play/play.js
const {
  get
} = require('../../utils/requestUtil.js')
const {
  formatDate,
  formatTime,
  formatDateTime,
  geoDistance
} = require('../../utils/util.js') //获取日期转换函数 及 距离测算函数
Page({
  flap: true,
  data: {
    /*** 页面的初始数据*/
    resetInputValue: '',
    clock_info: null,
    markers: null,
    userInfo: null, //到时候要展示打卡记录  的昵称和头像
    clock_logs: null
  },
  onLoad: function (options) {
    /*** 生命周期函数--监听页面加载 */
    //查询 当前打卡信息 并更新地图
    this.get_current_clock()
    this.get_logs() //获取打卡记录
  },
  get_current_clock() {
    get({
      url: "/clockInSet/getClockIn"
    }).then(res => {
      if (res.data) {
        this.setData({
          clock_info: res.data,
          markers: [{
            iconPath: "../../assets/icon_mark.png",
            id: 0,
            latitude: res.data.latitude,
            longitude: res.data.longitude,
            range: res.data.addressRange,
            width: 30,
            height: 30
          }]
        })
      } else {
        this.setData({
          clock_info: null
        })
      }
    })
  },
  setSadianClock: function (e) {
    let deleteId = e.currentTarget.dataset.smile
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
                wx.showToast({
                  title: '删除记录成功！',
                  icon: 'success',
                  duration: 2000
                })
                setTimeout(() => {
                  wx.navigateBack()
                }, 2000)
              } else {
                wx.showToast({
                  title: '删除失败……',
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

  mgcCheck(val, success) { // 敏感词校验
    val = val.trim()
    if (val != "" && val != null) {
      wx.cloud.callFunction({
        name: 'msgSC',
        data: {
          text: val
        } //需要检测的内容
      }).then((res) => {
        if (res.result.code == "200") { //检测通过
          success() //回调函数
        } else { //执行不通过
          wx.showToast({
            title: '包含敏感字哦。',
            icon: 'none',
            duration: 3000
          })
        }
      })
    }
  },

  get_logs() { //获取打卡记录
    get({
      url: "/student/getClockInRecordList"
    }).then((res) => {
      this.setData({
        clock_logs: res.data
      })
    })
  },
  submitFunction(e) {
    let that = this
    wx.showModal({
      title: '信息',
      content: "确定要打卡？",
      confirmText: '确认',
      success(res) {
        if (res.confirm) {
          that.play_clock()
        }
      }
    })
  },

  play_clock() { //点击打卡按钮时 提交打卡信息
    let clock_info = this.data.clock_info
    let startTime = this.data.clock_info.startTime.split("-").join("").split(":").join('').split(" ").join("")
    let endTime = this.data.clock_info.endTime.split("-").join("").split(":").join('').split(" ").join("")
    let date = formatDate(new Date)
    let time = formatTime(new Date)
    let bdate = date.split("-").join('') + time.split(":").join('')
    let targetMap = {
      latitude: this.data.clock_info.latitude,
      longitude: this.data.clock_info.longitude
    }

    wx.getLocation({ //获取当前位置
      type: "gcj02",
      isHighAccuracy: true, //开启高精度定位
      success: (res) => {
        let currentMap = {
          latitude: res.latitude,
          longitude: res.longitude
        } //用户打卡时经纬度
        let sss = geoDistance(targetMap, currentMap) //计算打卡距离
        let curjuli = clock_info.addressRange / 1000
        //大于设置的米数 则提示
        console.log(sss + "cur=" + curjuli, bdate, startTime)
        if (sss >= curjuli || bdate < startTime || bdate > endTime) {
          wx.showToast({
            title: '打卡失败!距离大于' + clock_info.addressRange + '米，或请查看资料和合法打卡时间!',
            icon: "none"
          })
        } else { //小于设定的距离才 进行查询 打卡记录 然后新增或修改打卡记录
          this.add_logs(clock_info.id)
        }
      },
      fail: (res) => {
        // 判断用户是否授权
        wx.getSetting({
          success: (res) => {
            var statu = res.authSetting;
            if (!statu['scope.userLocation']) { //没授权
              wx.showModal({
                title: '是否授权当前位置',
                content: '需要获取您的地理位置，请确认授权',
                confirmColor: '#f16765',
                success: res => {
                  if (res.confirm) {
                    wx.openSetting({
                      success: data => {
                        if (data.authSetting["scope.userLocation"]) {
                          this.play_clock()
                        }
                      }
                    })
                  }
                }
              })
            }
          }
        })
      }
    })
  },
  add_logs(clockId) { //新增打卡记录
    let that = this
    get({
      url: "/student/clockIn/" + clockId
    }).then(() => {
      that.onLoad()
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