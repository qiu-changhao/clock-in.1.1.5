import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '@/components/Layout'

Vue.use(VueRouter)

export const routes = [
    {
        path: '/login',
        component: () => import('@/views/login'),
        hidden: true
    },
    {
        path: '',
        component: Layout,
        redirect: 'index',
        children: [
            {
                path: 'index',
                component: () => import('@/views/index'),
                name: 'Index',
                meta: {title: '首页', icon: 'dashboard', affix: true}
            }
        ]
    },
    {
        path: '/user',
        component: Layout,
        hidden: true,
        redirect: 'noredirect',
        children: [
            {
                path: 'profile',
                component: () => import('@/views/user/profile/index'),
                name: 'Profile',
                meta: { title: '个人中心', icon: 'user' }
            }
        ]
    },
    {
        path: '/user',
        component: Layout,
        meta: {
            title: '用户管理'
        },
        children: [
            {
                path: 'student',
                component: () => import('@/views/student/index'),
                name: 'studentIndex',
                meta: {title: '学生列表', affix: true}
            },
            {
                path: 'teacher',
                component: () => import('@/views/teacher/index'),
                name: 'teacherIndex',
                meta: {title: '老师列表', affix: true}
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history', // 去掉url中的#
    scrollBehavior: () => ({y: 0}),
    routes
})

export default router
