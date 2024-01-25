import Vue from 'vue';
import App from './App.vue';
import VueRouter from 'vue-router';
import router from './router';
import request from './utils/request';
import store from './store';
import ElementUI from 'element-ui'; // 引入ElementUI组件库
import 'element-ui/lib/theme-chalk/index.css'; // 引入ElementUI全部样式
import 'element-ui/lib/theme-chalk/display.css'; // 引入自适应隐藏样式类
import '@/assets/css/reset.css';

Vue.prototype.$axios = request;

Vue.config.productionTip = false;
Vue.use(VueRouter);
Vue.use(ElementUI);
new Vue({
  store,
  render: (h) => h(App),
  router: router,
}).$mount('#app');
