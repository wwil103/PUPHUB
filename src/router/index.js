//配置路由
import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

// 创建并暴露一个路由器
const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/home',
    },
    {
      path: '/admin',
      redirect: '/main',
    },
    {
      path: '/',
      component: () => import('../views/MainView'),
      children: [
        {
          path: '/home',
          component: () => import('../views/PupHome'),
        },
        {
          path: '/help',
          component: () => import('../views/HelpView'),
        },
        {
          path: '/about',
          component: () => import('../views/AboutView'),
        },
        {
          path: '/services',
          component: () => import('../views/PupServices'),
        },
        {
          path: '/contact',
          component: () => import('../views/ContactView'),
        },
        {
          name: 'ServiceContent',
          path: '/services',
          component: () => import('../views/serve/ServiceContent'),
        },
        {
          name: 'ServiceSubscribe',
          path: '/sub',
          component: () => import('../views/serve/ServiceSubscribe'),
        },
        {
          path: '/login',
          component: () => import('../views/LoginView'),
        },
        {
          path: '/register',
          component: () => import('../views/LoginView'),
        },
        {
          path: '/person',
          component: () => import('../views/person/PersonCenter'),
        },
        {
          path: '/person/edit',
          component: () => import('../views/person/PersonEdit'),
        },
      ],
    },
    {
      path: '/',
      component: () => import('../views/ManageView'),
      children: [
        {
          path: '/main',
          component: () => import('../views/manage/AdminView'),
        },
        {
          path: '/user',
          component: () => import('../views/manage/UserView'),
        },
        {
          path: '/service',
          component: () => import('../views/manage/ServiceView'),
        },
        {
          path: '/order',
          component: () => import('../views/manage/OrderView'),
        },
      ],
    },
  ],
});

router.beforeEach((to, from, next) => {
  const uId = localStorage.getItem('user');
  const userName = localStorage.getItem('userName');
  if (uId == null || userName == null) {
    if (to.path != '/person' || to.path != '/person/edit') {
      next();
    } else {
      next('/home');
    }
  } else {
    next();
  }
});

export default router;
