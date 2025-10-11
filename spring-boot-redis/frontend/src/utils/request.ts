import axios, {
  type AxiosInstance,
  type InternalAxiosRequestConfig,
  type AxiosResponse,
} from "axios";
import { ElMessage } from "element-plus"; // Element Plus 提示组件
import type { ApiResponse } from "../types/api"; // 引入自定义类型

// 1. 创建 Axios 实例，配置基础参数
const request: AxiosInstance = axios.create({
  baseURL: "http://localhost:8888", // 后端 API 基础地址（需替换为你的实际地址）
  timeout: 10000, // 请求超时时间（10 秒）
  headers: {
    "Content-Type": "application/json;charset=UTF-8", // 默认请求头（JSON 格式）
  },
});

// 2. 请求拦截器：发送请求前执行（如添加 Token）
request.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    // 从 localStorage 获取登录后的 Token（登录成功后存储，未登录则为空）
    const token = localStorage.getItem("token");
    // 若 Token 存在，添加到请求头的 Authorization 字段（Bearer 认证格式）
    if (token && config.headers) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    // 请求发送前的错误（如配置错误）
    console.error("请求配置错误:", error);
    return Promise.reject(error);
  }
);

// 3. 响应拦截器：收到响应后执行（统一处理错误、状态码）
request.interceptors.response.use(
  (response: AxiosResponse<ApiResponse>) => {
    const { data } = response;
    // 网络请求成功（HTTP 200），直接返回响应数据
    if (response.status === 200) {
      return response;
    }
    // 网络成功但业务失败（如 code 非 200），提示错误
    ElMessage.error(data.msg || "请求失败，请稍后重试");
    return Promise.reject(new Error(data.msg || "请求失败"));
  },
  (error) => {
    // 处理 HTTP 错误（如 401 未授权、404 接口不存在、500 服务器错误）
    let errorMsg = "请求失败，请检查网络";
    if (error.response) {
      // 服务器返回错误状态码
      switch (error.response.status) {
        case 401:
          errorMsg = "登录已过期，请重新登录";
          // 清除本地存储的 Token、手机号等信息，跳转到登录页
          localStorage.removeItem("token");
          localStorage.removeItem("userPhone");
          localStorage.removeItem("loginTime");
          window.location.href = "/login"; // 强制跳转登录页
          break;
        case 403:
          errorMsg = "权限不足，无法访问";
          break;
        case 404:
          errorMsg = "请求的接口不存在";
          break;
        case 500:
          errorMsg = "服务器内部错误，请稍后重试";
          break;
        case 502:
          errorMsg = "网关错误，请联系管理员";
          break;
        case 503:
          errorMsg = "服务暂时不可用，请稍后重试";
          break;
        case 504:
          errorMsg = "网关超时，请检查服务器";
          break;
        default:
          errorMsg = `请求失败（状态码：${error.response.status}）`;
      }
    } else if (error.request) {
      // 请求已发送，但未收到响应（网络错误）
      errorMsg = "网络连接失败，请检查网络";
    }
    // 显示错误提示
    ElMessage.error(errorMsg);
    console.error("响应错误详情:", error);
    return Promise.reject(error);
  }
);

// 导出封装好的 Axios 实例，供其他模块使用
export default request;