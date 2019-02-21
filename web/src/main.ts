import Vue from "vue"
import App from "./App.vue"
import Router from "vue-router"
import Vuex, { StoreOptions } from "vuex"
import ElementUI from "element-ui"
import "element-ui/lib/theme-chalk/index.css"
import draggable from "vuedraggable"

Vue.config.productionTip = false

Vue.use(Vuex)
Vue.use(Router)
Vue.use(ElementUI, {
  size: "mini"
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
          prop: "input", // 字段
          value: "", // 值
          type: "el-input", // 类型
          label: "输入框", // 名称
          placeholder: "请输入...", // 占位值
          icon: "el-icon-edit", // 左侧栏图标
          prefixIcon: "", // 输入框前图标
          inputType: "text"
        },
        {
          id: 2,
          type: "el-input",
          prop: "text",
          label: "文本域",
          icon: "el-icon-tickets",
          inputType: "textarea"
        },
        {
          id: 3,
          type: "el-select",
          prop: "select",
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
          prop: "date",
          label: "日期选择器",
          placeholder: "请选择...zhanweifu...",
          icon: "el-icon-time"
        }
      ]
    },
    data: {
      form: {},
      items: [],
      result: {},
      table: []
    },
    activeIdx: -1
  },
  mutations: {
    active(state, idx) {
      state.activeIdx = idx
    },
    // 更新表单结构数据
    update(state, items) {
      state.data.items = items
    },
    // 根据顺序id更新
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
    },
    // 表单填值后，更新结果值
    updateResult(state, { field, value }) {
      Vue.set(state.data.result, field, value)
    },
    commitTable(state) {
      let { result, table } = state.data
      table.push(JSON.parse(JSON.stringify(result)))
      result = {}
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
