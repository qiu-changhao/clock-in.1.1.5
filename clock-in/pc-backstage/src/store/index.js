import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import permission from "@/store/modules/permission";
import settings from "@/store/modules/settings";
import getters from "@/store/getters";
import admin from "@/store/modules/admin";

Vue.use(Vuex)

export default new Vuex.Store({
  getters,
  modules: {
    app,
    permission,
    settings,
    admin
  }
})
