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
    component: () => import("@/views/AppLayout.vue"),
    children: [
      {
        path: "start",
        component: () => import("@/components/app/Start.vue")
      },
      {
        path: "createdList",
        component: () => import("@/components/app/CreatedList.vue")
      },
      {
        path: "todoList",
        component: () => import("@/components/app/TodoList.vue")
      },
      {
        path: "doneList",
        component: () => import("@/components/app/DoneList.vue")
      },
    ]
  }
]

export default new Router({ routes })
