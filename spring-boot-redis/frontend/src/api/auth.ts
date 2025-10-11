import request from '../utils/request'; // 假设 request.ts 路径是 utils/request.ts
import type { LoginRequest, ApiResponse } from '../types/api';

export const authApi = {
  // 发送短信接口：url 改为 /sms/send
  sendSms: (phone: string) => {
    return request<ApiResponse>({
      url: '/sms/send', 
      method: 'GET',
      params: { phone }, // GET 方法用 params 传参
    });
  },

  // 登录接口保持不变（已匹配）
  login: (loginData: LoginRequest) => {
    return request<ApiResponse<{ token: string; phone: string; message: string }>>({
      url: '/login',
      method: 'POST',
      data: loginData,
    });
  },
};