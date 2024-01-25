import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const actions = {
  //登录状态
  handelIsLogin(context, value) {
    context.commit('HANDELISLOGIN', value);
  },
  //全局用户名
  userName(context, value) {
    context.commit('USERNAME', value);
  },
};
const mutations = {
  HANDELISLOGIN(state, value) {
    let uId = localStorage.getItem('user');
    let token = localStorage.getItem('token');
    if (uId == null || token == null) {
      state.isLogin = !value;
    } else {
      state.isLogin = value;
    }
  },
  USERNAME(state, value) {
    state.userName = value;
  },
};
const state = {
  isLogin: false,
  userName: '',
};

export default new Vuex.Store({
  actions,
  mutations,
  state,
});
