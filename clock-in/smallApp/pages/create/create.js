// pages/create/create.js
const {
  post,
  get
} = require('../../utils/requestUtil.js')
const {
  formatDate
} = require('../../utils/util.js') //获取日期转换函数
Page({
  sbfl: true,
  data: { //页面的初始数据
    title: '',
    teamList: [],
    teamArray: [],
    teamIndex: 0,
    teamId: undefined,
    startDate: formatDate(new Date),
    endDate: formatDate(new Date),
    startTime: '08:00',
    endTime: '18:00',
    targetMap: { //选择打卡地点
      latitude: 0, //纬度
      longitude: 0, //经度
      name: '', //地名
      range: '1000' //距离
    }
  },
  setTitle(e) { //设置打卡标题
    let val = e.detail.value
    this.setData({
      title: val
    })
  },
  changeDate: function (e) { //选择时间
    let timeType = e.target.dataset.type
    console.log(e.detail.value);
    if (timeType == 'startDate' || timeType == 'endDate') {
      let currentTimeNum = new Date(...e.detail.value.split('-')).getTime()
      let startDateNum = new Date(...this.data.startDate.split('-')).getTime()
      let endDateNum = new Date(...this.data.endDate.split('-')).getTime()
      if ((timeType == 'startDate' && currentTimeNum <= endDateNum) || (timeType == 'endDate' && currentTimeNum >= startDateNum)) {
        this.setData({
          [timeType]: e.detail.value
        })
      } else {
        wx.showToast({
          title: timeType == 'startDate' ? '开始时间不能大于结束时间' : '结束时间不能小于开始时间',
          icon: "none"
        })
      }
    } else {
      this.setData({
        [timeType]: e.detail.value
      })
    }
  },
  setMap() { // 设置位置 获取经纬度
    console.log('setmap clicking');
    wx.chooseLocation({
      success: (res) => {
        console.log(res);
        this.setData({
          ['targetMap.latitude']: res.latitude,
          ['targetMap.longitude']: res.longitude,
          ['targetMap.name']: res.name,
          ['targetMap.address']: res.address,
        })
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
                          this.setMap()
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
  setrange(e) { // 设置距离
    this.setData({
      ['targetMap.range']: e.detail.value
    })
  },
  changeCurrent(e) { // 更改打卡者必填信息
    let id = e.currentTarget.dataset.id
    this.data.writeChoice.forEach((item, index, arr) => {
      if (index == id) {
        let oSelected = "writeChoice[" + index + "].state" //这里需要将设置的属性用字符串进行拼接
        console.log(oSelected);
        this.setData({
          [oSelected]: !this.data.writeChoice[index].state
        })
      }
    })
  },
  changeIsEveryWrite(e) { // 是否每次填写
    let id = e.detail.value
    let oSelected = 'isEveryWrite.index'
    this.setData({
      [oSelected]: id
    })
  },

  createClockEvent() { // 提交所有打卡信息
    let that = this
    if (this.data.title != '' && this.data.targetMap.latitude != 0 && this.data.teamId != undefined) {
      post({
        url: "/teacher/setClockIn",
        data: {
          title: this.data.title,
          startTime: this.data.startDate + " " + this.data.startTime,
          endTime: this.data.endDate + " " + this.data.endTime,
          latitude: this.data.targetMap.latitude,
          longitude: this.data.targetMap.longitude,
          address: this.data.targetMap.address,
          addressRange: this.data.targetMap.range,
          teamId: this.data.teamId
        }
      }).then(res => {
        wx.showToast({
          title: '创建成功',
          success() {
            setTimeout(() => {
              wx.navigateBack()
            }, 2000)
          }
        })
      })
    } else {
      wx.showToast({
        title: '打卡标题或地址或没有选择班级，请查看是否填写正确！',
        icon: "none"
      })
    }
  },
  goToHome() {
    wx.navigateBack()
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
      teamList.forEach(e => {
        teamArray.push(e.name)
      })
      this.setData({
        teamList,
        teamArray
      })
    })
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function (res) {

  },
  onShareTimeline: function (res) {

  },
  changeTeam: function (e) {
    let teamIndex = e.detail.value
    let teamId = this.data.teamList[teamIndex].id
    this.setData({
      teamIndex,
      teamId
    })
  }
})