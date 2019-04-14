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
    path: "/appAdmin",
    name: "AppAdmin",
    component: () => import("@/components/app-admin/AppAdmin.vue"),
    children: [
      {
        path: "appInfo",
        component: () => import("@/components/app-admin/AppInfo.vue")
      }
    ]
  },
  {
    path: "/appReport",
    name: "AppReport",
    component: () => import("@/components/app-report/AppReport.vue")
  },
  {
    path: "/appClient",
    name: "AppClient",
    component: () => import("@/components/app-client/AppClient.vue"),
    children: [
      {
        path: "start",
        component: () => import("@/components/app-client/Start.vue")
      },
      {
        path: "createdList",
        component: () => import("@/components/app-client/CreatedList.vue")
      },
      {
        path: "todoList",
        component: () => import("@/components/app-client/TodoList.vue")
      },
      {
        path: "doneList",
        component: () => import("@/components/app-client/DoneList.vue")
      }
    ]
  }
]

export default new Router({ routes })
