import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'
import NewsInfo from '@/components/NewsInfo'
import NewsManage from '@/components/NewsManage'
import NewsAddmodify from '@/components/NewsAddmodify'

/*引入资源请求插件*/
import VueResource from 'vue-resource';

/*使用VueResource插件*/
Vue.use(VueResource);
// 在路由的请求中如此设置
Vue.http.options.emulateJSON = true
Vue.http.options.xhr = { withCredentials: true }
Vue.http.options.crossOrigin = true
Vue.http.options.emulateHTTP = true
Vue.http.options.root="http://localhost:8080/news" // URL.Purl指的是服务器路径
Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },{
      path: '/Login',
      name:'Login',
      component: Login
    },{
      path: '/NewsInfo',
      name:'NewsInfo',
      component: NewsInfo
    },{
      path:'/NewsManage',
      name:'NewsManage',
      component:NewsManage
    },{
      path:'/NewsAddmodify',
      name:'NewsAddmodify',
      component:NewsAddmodify
    }
  ]
})
