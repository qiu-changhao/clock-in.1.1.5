import {getInfo, login} from '@/api/admin'
import {
    getToken,
    setToken,
    removeToken
} from '@/utils/auth'

const admin = {
    state: {
        token: getToken(),
        username: undefined
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        SET_USERNAME:(state, username)=>{
            state.username = username
        }
    },

    actions: {
        // 登录
        Login({commit}, userInfo) {
            const username = userInfo.username.trim()
            const password = userInfo.password
            return new Promise((resolve, reject) => {
                login({username, password}).then(res => {
                    setToken(res.data.token)
                    commit('SET_TOKEN', res.data.token)
                    resolve()
                }).catch(error => {
                    reject(error)
                })
            })
        },

        // 前端 登出
        FedLogOut({commit}) {
            return new Promise(resolve => {
                commit('SET_TOKEN', '')
                removeToken()
                resolve()
            })
        },

        // 用户信息
        GetInfo({commit, state}) {
            return new Promise((resolve, reject) => {
                getInfo().then(res => {
                    const data = res.data
                    commit('SET_USERNAME',data.username)
                    resolve(res)
                }).catch(error => {
                    reject(error)
                })
            })
        },
    }
}

export default admin
