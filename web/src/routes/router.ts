import Vue from "vue"
import Router from "vue-router"
Vue.use(Router)

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("@/views/Home.vue")
  },
  {
    path: "/table",
    name: "table",
    component: () => import("@/views/Table.vue")
  },
  {
    path: "/formEditor",
    name: "formEditor",
    component: () => import("@/views/FormEditor.vue")
  },
  {
    path: "/flowEditor",
    name: "flowEditor",
    component: () => import("@/views/FlowEditor.vue")
  },
  {
    path: "/appStore",
    name: "AppStore",
    component: () => import("@/views/AppStore.vue")
  },
  {
    path: "/appLayout",
    name: "AppLayout",
    component: () => import("@/views/AppLayout.vue")
  }
]

export default new Router({ routes })