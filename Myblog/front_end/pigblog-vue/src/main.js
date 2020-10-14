import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import axios from 'axios'
import qs from 'qs'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import './axios.js' // 请求拦截
import "./permission"
import hljs from 'highlight.js' //导入代码高亮文件
import 'highlight.js/styles/monokai-sublime.css'  //导入代码高亮样式




Vue.use(Element)
Vue.use(mavonEditor)
Vue.prototype.$axios = axios
Vue.config.productionTip = false



new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

let instance = axios.create({
  timeout: 6000,
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  }
})

instance.interceptors.request.use(
    config => {
      config.data = qs.stringify(config.data) // 转为formdata数据格式
      return config
    },
    error => Promise.error(error)
)

//自定义一个代码高亮指令
Vue.directive('highlight',function (el) {
    let highlight = el.querySelectorAll('pre code');
    highlight.forEach((block)=>{
        hljs.highlightBlock(block)
    })
})

