import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import qs from 'qs'
import './plugins/element.js'

import { codemirror } from 'vue-codemirror'
import 'codemirror/lib/codemirror.css'


Vue.config.productionTip = false
// zy use axios
Vue.prototype.$axios = axios
Vue.prototype.qs = qs

/* 用于请求前缀 npm run serve 时默认匹配 /api 进行替换，部署时 修改为'' 即可 */
Vue.prototype.htpUrl = '/api'
// Vue.prototype.htpUrl = ''


Vue.use(codemirror)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
