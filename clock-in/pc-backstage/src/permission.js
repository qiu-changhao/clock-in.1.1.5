import router from './router'
import store from './store'
import NProgress from 'nprogress'
import {getToken} from "@/utils/auth";

NProgress.configure({showSpinner: false})

const whiteList = ['/login']

router.beforeEach( (to, from, next) => {
    NProgress.start()
    let isLoadingDynamicRoute = store.getters.isLoadingDynamicRoute
    if (getToken()) {
        to.meta.title && store.dispatch('settings/setTitle', to.meta.title)
        /* has token*/
        if (to.path === '/login') {
            next({path: '/'})
            NProgress.done()
        } else {
            if (!isLoadingDynamicRoute) {
                store.dispatch("GetInfo").then(()=>{
                    store.dispatch('GenerateRoutes').then(res => {
                        res.forEach(route =>{
                            router.addRoute(route)
                        })
                        next({...to, replace: true})
                    })
                })
            } else {
                next()
            }

        }
    } else {
        // 没有token
        if (whiteList.indexOf(to.path) !== -1) {
            // 在免登录白名单，直接进入
            next()
        } else {
            next(`/login?redirect=${to.fullPath}`) // 否则全部重定向到登录页
            NProgress.done()
        }
    }

})

router.afterEach(() => {
    NProgress.done()
})
