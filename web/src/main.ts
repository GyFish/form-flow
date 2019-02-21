import Vue from "vue"
import App from "./App.vue"
import Router from "vue-router"
import Vuex, { StoreOptions } from "vuex"
import ElementUI from "element-ui"
import "element-ui/lib/theme-chalk/index.css"
import draggable from 'vuedraggable'

Vue.config.productionTip = false

Vue.use(Vuex)
Vue.use(Router)
Vue.use(ElementUI, {
  size: 'mini'
})
Vue.use(draggable)

interface FormState {
  activeIdx: any
  meta: any
  data: any
}
const store: StoreOptions<FormState> = {
  state: {
    meta: {
      form: {
        inline: false
      },
      items: [
        {
          id: 1,
          prop: "input",
          type: "el-input",
          label: "输入框",
          placeholder: "请输入...",
          icon: "el-icon-edit",
          'prefixIcon': "",
          inputType: "text"
        },
        {
          id: 2,
          type: "el-input",
          prop: "input",
          label: "文本域",
          icon: "el-icon-tickets",
          inputType: "textarea"
        },
        {
          id: 3,
          type: "el-select",
          prop: "input",
          label: "下拉框",
          icon: "el-icon-arrow-down",
          options: [
            {
              value: "k1",
              label: "选项一"
            },
            {
              value: "k2",
              label: "选项二"
            }
          ]
        },
        {
          id: 4,
          type: "el-date-picker",
          prop: "input",
          label: "日期选择器",
          placeholder: "请选择...zhanweifu...",
          icon: "el-icon-time"
        }
      ]
    },
    data: {
      form: {},
      items: []
    },
    activeIdx: -1
  },
  mutations: {
    active(state, idx) {
      state.activeIdx = idx
    },
    update(state, items) {
      state.data.items = items
    },
    updateByIdx(state, { idx, item }) {
      let target = state.data.items[idx]
      // 判断是否有该字段，动态添加
      // 有点麻烦，不如在初始化时就指定数据
      // if (target.type == "el-input")
      //   Vue.set(target, "prefixIcon", item.prefixIcon)
      Object.assign(target, item)
    },
    removeByIdx(state, idx) {
      state.data.items.splice(idx, 1)
    }
  }
}

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("./views/Home.vue")
  },
  {
    path: "/form",
    name: "form",
    component: () => import("./views/Form.vue")
  },
  {
    path: "/table",
    name: "table",
    component: () => import("./views/Table.vue")
  },
  {
    path: "/flow",
    name: "flow",
    component: () => import("./views/Flow.vue")
  }
]

new Vue({
  router: new Router({ routes }),
  store: new Vuex.Store<FormState>(store),
  render: h => h(App)
}).$mount("#app")
