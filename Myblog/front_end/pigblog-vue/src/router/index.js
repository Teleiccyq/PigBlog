import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Regist from "../views/Regist";
import Blogs from '../views/Blogs.vue'
import BlogEdit from '../views/BlogEdit.vue'
import BlogDetail from '../views/BlogDetail.vue'
import HomePage from "../views/HomePage";
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'HomePage',
        meta:{index:0},
        component: resolve => require(['@/views/HomePage'],resolve),
    },
    {
        path: '/blogs',
        name: 'Blogs',
        meta:{index:1},
        component: resolve => require(['@/views/Blogs'],resolve)
    },
    {
        path: '/login',
        name: 'Login',
        meta:{index:2},
        component: resolve => require(['@/views/Login'],resolve)
    },
    {
        path: '/regist',
        name: 'Regist',
        meta:{index:3},
        component: resolve => require(['@/views/Regist'],resolve)
    },
    {
        path: '/blog/add',
        name: 'BlogAdd',
        component: resolve => require(['@/views/BlogEdit'],resolve),
        meta:{
            requirAuth:true,
            index:4
        }
    },
    {
        path: '/blog/:blogId',
        name: 'BlogDetail',
        meta:{index:5},
        component: resolve => require(['@/views/BlogDetail'],resolve)
    },
    {
        path: '/blog/:blogId/edit',
        name: 'BlogEdit',
        component: resolve => require(['@/views/BlogEdit'],resolve),
        meta:{
            requirAuth:true,
            index:6
        }
    },



]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
