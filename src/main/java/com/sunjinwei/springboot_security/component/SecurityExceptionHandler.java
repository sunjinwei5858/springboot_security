package com.sunjinwei.springboot_security.component;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @program: com.sunjinwei.springboot_security.component
 * @author: sun jinwei
 * @create: 2023-07-16 15:02
 * @description: 未认证/未授权的处理
 **/
@Component
public class SecurityExceptionHandler implements AccessDeniedHandler, AuthenticationEntryPoint {

    /**
     * token失效 401
     *
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String msg = "invalid token";
        // 还可以做一些重定向的处理 比如跳到登陆页面
        response.getOutputStream().write(JSON.toJSONString(msg).getBytes(StandardCharsets.UTF_8));

    }

    /**
     * 无权限 403
     *
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        String msg = "access forbidden";
        response.getOutputStream().write(JSON.toJSONString(msg).getBytes(StandardCharsets.UTF_8));
    }
}