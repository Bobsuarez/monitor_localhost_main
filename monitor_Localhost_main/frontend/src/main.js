import Vue from 'vue'
import App from './App.vue'
import Vuesax from 'vuesax'

import 'vuesax/dist/vuesax.css' //Vuesax styles
import 'material-icons/iconfont/material-icons.css';
// Vuex Store
import store from './store/store'

// Theme Configurations
import '../themeConfig.js'

import Vuebar from 'vuebar';
Vue.use(Vuebar);

import 'prismjs'
import 'prismjs/themes/prism.css'
import VsPrism from './components/system/prism/VsPrism.vue';
Vue.component(VsPrism.name, VsPrism);
import VsSidebarGroup from './components/system/vs-sidebar-group/Sidebar-Group.vue';
Vue.component(VsSidebarGroup.name, VsSidebarGroup);

//sweetalert2
import VueSweetalert2 from "vue-sweetalert2";
Vue.use(VueSweetalert2);

// i18n
import i18n from './i18n/i18n.js'
// Vue Router
import router from './router'
Vue.config.productionTip = false

Vue.use(Vuesax)


Vue.config.productionTip = false

new Vue({
  store,
  router,
  i18n,
  render: h => h(App),
}).$mount('#app')
import '@/assets/scss/style.scss'