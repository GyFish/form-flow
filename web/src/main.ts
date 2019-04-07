Vue.config.productionTip = false

// 主页面
import App from "./App.vue"

// vue
import Vue from "vue"

// router
import router from "./routes/router"

// vuex
import store from "./stores/store"

// element
import ElementUI from "element-ui"
import "element-ui/lib/theme-chalk/index.css"
Vue.use(ElementUI, {
  size: "mini"
})

// 拖拽插件
import draggable from "vuedraggable"
Vue.use(draggable)

// 外部样式
import './styles/index.scss'



new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app")
