import { createApp } from "vue";
import App from "./App.vue";

// 1. 引入 Element Plus 及全局样式
import ElementPlus from "element-plus";
import "element-plus/dist/index.css"; // Element Plus 全局样式

// 2. 引入 Vue Router（初始化时已生成 router 目录）
import router from "./router";

// 3. 创建 Vue 应用实例
const app = createApp(App);

// 4. 全局挂载 Element Plus 和 Router
app.use(ElementPlus); // 让所有组件可使用 Element Plus 组件
app.use(router); // 启用路由功能

// 5. 挂载应用到 HTML 的 #app 元素（对应 index.html 中的 <div id="app"></div>）
app.mount("#app");
