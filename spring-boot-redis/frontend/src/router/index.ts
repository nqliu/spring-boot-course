import { createRouter, createWebHistory } from "vue-router";
import type { RouteRecordRaw } from "vue-router";

// 定义路由规则
const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/home", // 根路径重定向到首页
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/Login.vue"), // 登录页组件（需自行创建）
  },
  {
    path: "/home",
    name: "Home",
    component: () => import("../views/Home.vue"), // 首页组件（需自行创建）
    meta: { requiresAuth: true }, // 标记该页面需登录鉴权
  },
];

// 创建路由实例
const router = createRouter({
  history: createWebHistory(), // HTML5 History 路由模式（无 # 号）
  routes, // 注入路由规则
});

// 路由守卫（登录鉴权逻辑）
router.beforeEach((to, _from, next) => {
  const token = localStorage.getItem("token"); // 从本地存储获取登录 Token
  if (to.meta.requiresAuth && !token) {
    // 需鉴权但无 Token → 跳登录页
    next("/login");
  } else if (to.path === "/login" && token) {
    // 已登录却访问登录页 → 跳首页
    next("/home");
  } else {
    // 其他情况 → 正常放行
    next();
  }
});

export default router;