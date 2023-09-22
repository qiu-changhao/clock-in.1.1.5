import {routes} from '@/router'
import store from "@/store";

const permission = {
    state: {
        sidebarRouters: [],
        isLoadingDynamicRoute: false,
    },
    mutations: {
        SET_SIDEBAR_ROUTERS: (state, routes) => {
            state.sidebarRouters = routes
        },
        SET_IS_LOADING_ROUTERS: (state, flag) => {
            state.isLoadingDynamicRoute = flag
        }
    },
    actions: {
        // 生成路由
        GenerateRoutes({commit}) {
            return new Promise((resolve) => {
                commit('SET_SIDEBAR_ROUTERS', routes)
                commit('SET_IS_LOADING_ROUTERS', true)
                resolve(routes)
            })
        }
    }
}


export default permission
