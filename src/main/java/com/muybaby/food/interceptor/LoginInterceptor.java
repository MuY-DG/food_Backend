package com.muybaby.food.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muybaby.food.bean.Result;
import com.muybaby.food.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;

@Slf4j

public class LoginInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    // 定义白名单：路径 + HTTP方法
    private static final List<RequestMatcher> PUBLIC_APIS = Arrays.asList(
            new RequestMatcher("/api/admin/category", "GET"),
            new RequestMatcher("/api/admin/dish", "GET"),
            new RequestMatcher("/api/admin/category/*", "GET"),
            new RequestMatcher("/uploads/", "GET"),
            new RequestMatcher("/api/admin/dish", "GET"),
            new RequestMatcher("/api/admin/dish/*", "GET")
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String method = request.getMethod().toUpperCase();
        System.out.println("Request URI: " + uri + ", Method: " + method);
        // 检查是否是白名单中的公开接口
        for (RequestMatcher matcher : PUBLIC_APIS) {
            if (antPathMatcher.match(matcher.getPattern(), uri) && matcher.getMethod().equals(method)) {
                // 放行，不检查权限
                return true;
            }
        }

        // 检查 Authorization 头
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            handleUnauthorized(response, "未提供有效的身份验证令牌");
            return false;
        }

        String token = authorization.substring(7);

        try {
            // 验证 JWT 令牌
            JwtUtil.verifyToken(token);

            // 获取用户角色
            String role = JwtUtil.getRoleFromToken(token);

            // 路径权限检查：以 /api/admin 开头的请求需要 ADMIN 角色
            if (uri.startsWith("/api") && !"ADMIN".equals(role)) {
                handleForbidden(response, "无权访问此资源");
                return false;
            }

            return true;
        } catch (Exception e) {
            log.error("Token verification failed", e);
            handleUnauthorized(response, "无效的身份验证令牌");
            return false;
        }
    }

    // 辅助类：定义路径和方法的匹配规则
    private static class RequestMatcher {
        private final String pattern;
        private final String method;

        public RequestMatcher(String pattern, String method) {
            this.pattern = pattern;
            this.method = method.toUpperCase();
        }

        public String getPattern() {
            return pattern;
        }

        public String getMethod() {
            return method;
        }
    }
    private void handleUnauthorized(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Result<Object> result = Result.error(HttpStatus.UNAUTHORIZED, message);
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

    private void handleForbidden(HttpServletResponse response, String message) throws Exception {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Result<Object> result = Result.error(HttpStatus.FORBIDDEN, message);
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}