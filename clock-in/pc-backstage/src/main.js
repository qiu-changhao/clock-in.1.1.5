import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import Element from 'element-ui'

import plugins from './plugins' // plugins

import {resetForm,handleTree,parseTime,groupBy} from '@/utils/baseUtil'
import {download} from "@/utils/request";

// 分页组件
import Pagination from "@/components/Pagination";

import '@/assets/styles/element-variables.scss'

import '@/assets/styles/index.scss'
import '@/assets/styles/ruoyi.scss'

import '@/assets/icons'

import './permission'

Vue.prototype.resetForm = resetForm
Vue.prototype.handleTree = handleTree
Vue.prototype.parseTime = parseTime
Vue.prototype.groupBy = groupBy
Vue.prototype.download = download

Vue.component('Pagination', Pagination)

Vue.config.productionTip = false
Vue.use(plugins)
Vue.use(Element)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
