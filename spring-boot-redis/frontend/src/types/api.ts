// 1. 通用 API 响应格式（所有接口统一返回此结构）
export interface ApiResponse<T = any> {
  code: number; // 业务状态码（如 200 成功，401 未授权）
  msg: string; // 与代码中 response.msg 匹配
  data: T; // 响应数据（泛型，根据接口动态变化）
}

// 2. 用户信息类型（登录后返回的用户数据结构）
export interface User {
  id: number;
  name: string;
  email: string;
  avatar?: string; // 可选字段（头像）
  createTime: string; // 创建时间（如 "2024-01-01 10:00:00"）
}

// 3. 登录请求参数类型（短信验证码登录时传递的参数）
export interface LoginRequest {
  phone: string; // 手机号
  code: string; // 与代码中传递的 code 匹配
}