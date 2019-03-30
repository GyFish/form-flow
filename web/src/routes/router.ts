import Router from "vue-router"

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
  }
]

export default new Router({ routes })