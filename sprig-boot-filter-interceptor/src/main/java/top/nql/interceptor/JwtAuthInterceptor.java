package top.nql.interceptor;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import top.nql.utils.JwtUtil;
/**
 * JWT 权限拦截器
 * @author nql
 */

public class JwtAuthInterceptor implements HandlerInterceptor {
@Resource
private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            response.getWriter().write("{\"code\":401,\"msg\":\"请先认证，携带令牌访问\"}");
            return false;
        }
        boolean flag = jwtUtil.validateToken(token);
        if (!flag) {
            response.setStatus(401);
            response.getWriter().write("{\"code\":401,\"msg\":\"令牌验证失败\"}");
            return false;
        }
        request.setAttribute("userId", jwtUtil.getUserRoleFromToken(token));
        return true;

    }
}
