import Vue from "vue"
import App from "./App.vue"
import Router from "vue-router"
import Vuex, { StoreOptions } from "vuex"
import ElementUI from "element-ui"
import "element-ui/lib/theme-chalk/index.css"

Vue.config.productionTip = false

Vue.use(Vuex)
Vue.use(Router)
Vue.use(ElementUI)

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
          label: "单行文本",
          placeholder: "请输入...",
          icon: "el-icon-edit"
          // 'prefixIcon': ''
        },
        {
          id: 2,
          type: "el-input",
          prop: "input",
          label: "多行文本",
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
              value: "选项1",
              label: "黄金糕"
            },
            {
              value: "选项2",
              label: "双皮奶"
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
      // 判断是否有该字段，动态添加
      let target = state.data.items[idx]
      if (target.type == "el-input")
        Vue.set(target, "prefixIcon", item.prefixIcon)
      Object.assign(target, item)
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
  }
]

new Vue({
  router: new Router({ routes }),
  store: new Vuex.Store<FormState>(store),
  render: h => h(App)
}).$mount("#app")
