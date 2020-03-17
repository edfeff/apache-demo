import Vue from 'vue'
import Router from 'vue-router'
import Layout from "../views/layout/Layout"

Vue.use(Router);
//导入不同的环境配置
const _import = require("./_import_" + process.env.NODE_ENV)

// 基本路由
const constantRouterMap = [
  {
    path: "/login",
    component: _import("login/index"),
    hidden: true
  },
  // {
  //   path: "/404",
  //   component: _import("404"),
  //   hidden: true
  // },
  // {
  //   path: "/",
  //   component: Layout,
  //   // redirect: "/dashboard",
  //   name: "index",
  //   hidden: true,
  //   // children: [
  //   //   {
  //   //     path: "dashboard",
  //   //     component: _import('dashboard/index')
  //   //   }
  //   // ]
  // }
];

//scrollBehavior 路由切换时的页面需要重新定位的位置
export default new Router({
  routes: constantRouterMap,
  scrollBehavior: () => ({y: 0})
});
