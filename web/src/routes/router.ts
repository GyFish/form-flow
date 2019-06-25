import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('@/views/Home.vue')
  },
  {
    path: '/formEditor',
    name: 'formEditor',
    component: () => import('@/views/FormEditor.vue')
  },
  {
    path: '/flowEditor',
    name: 'flowEditor',
    props: true,
    component: () => import('@/views/FlowEditor.vue')
  },
  {
    path: '/appRoom',
    name: 'AppRoom',
    component: () => import('@/views/AppRoom.vue')
  },
  {
    path: '/appAdmin',
    name: 'AppAdmin',
    component: () => import('@/components/admin/AdminLayout.vue'),
    children: [
      {
        path: 'appInfo',
        name: 'AppInfo',
        component: () => import('@/components/admin/AppInfo.vue')
      },
      {
        path: 'userAdmin',
        name: 'UserAdmin',
        component: () => import('@/components/admin/UserAdmin.vue')
      },
      {
        path: 'formAdmin',
        name: 'FormAdmin',
        component: () => import('@/components/admin/FormAdmin.vue')
      },
      {
        path: 'flowAdmin',
        name: 'FlowAdmin',
        component: () => import('@/components/admin/FlowAdmin.vue')
      }
    ]
  },
  {
    path: '/appReport',
    name: 'AppReport',
    component: () => import('@/components/report/AppReport.vue')
  },
  {
    path: '/appClient',
    name: 'AppClient',
    props: true,
    component: () => import('@/components/client/ClientLayout.vue'),
    children: [
      {
        path: 'start',
        name: 'Start',
        props: true,
        component: () => import('@/components/client/Start.vue')
      },
      {
        path: 'created',
        name: 'Created',
        props: true,
        component: () => import('@/components/client/Created.vue')
      },
      {
        path: 'todo',
        name: 'Todo',
        props: true,
        component: () => import('@/components/client/Todo.vue')
      },
      {
        path: 'done',
        name: 'Done',
        props: true,
        component: () => import('@/components/client/Done.vue')
      }
    ]
  }
]

export default new Router({ routes })
