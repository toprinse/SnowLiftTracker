import Vue from 'vue'
import VueRouter from 'vue-router'

//import User Page
import HomePage from '@/views/HomePageView.vue'
import StationInterface from '@/views/StationInterfaceView.vue'
import Contact from '@/views/ContactView.vue'
import SkiLiftInterface from '@/views/SkiLiftInterfaceView.vue'

Vue.use(VueRouter)

const routes = [

  //User Page
  {
    path: '/',
    name: 'homepage',
    component: HomePage
  },
  {
    path: '/contact',
    name: 'contact',
    component: Contact
  },
  {
    path: '/stationinterface/:id',
    name: 'stationinterface',
    component: StationInterface
  },
  {
    path: '/skiliftinterface/:id',
    name: 'skiliftinterface',
    component: SkiLiftInterface
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
