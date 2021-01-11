import Vue from 'vue'
import VueRouter from 'vue-router'

import SqlInput from '../views/sqlInput/index.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'SQL',
    component: SqlInput
  },
]

const router = new VueRouter({
  mode:'history',
  routes
})

export default router
