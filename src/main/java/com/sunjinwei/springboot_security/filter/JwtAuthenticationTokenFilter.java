package com.sunjinwei.springboot_security.filter;

import com.sunjinwei.springboot_security.domain.User;
import com.sunjinwei.springboot_security.domain.UserAuthenticationToken;
import com.sunjinwei.springboot_security.domain.UserAuthority;
import com.sunjinwei.springboot_security.domain.UserInfo;
import com.sunjinwei.springboot_security.service.UserService;
import com.sunjinwei.springboot_security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @program: com.sunjinwei.springboot_security.filter
 * @author: sun jinwei
 * @create: 2023-07-16 21:20
 * @description: jwtToken的授权
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private String TOKEN = "Authorization";

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader(TOKEN);
        if (!StringUtils.hasText(token)) {
            // 放行
            filterChain.doFilter(request, response);
            return;
        }

        // 解析token
        UserInfo userInfo = JwtUtil.parseJwt(token);
        if (Objects.isNull(userInfo)) {
            throw new RuntimeException("token不存在");
        }

        // 查询用户信息和权限信息
        User user = userService.queryUserById(userInfo.getId());
        List<UserAuthority> userAuthorities = user.getCodes().stream().map(UserAuthority::new).collect(Collectors.toList());

        // 使用UserAuthenticationToken保存用户权限信息 然后存入SecurityContextHolder 并且设置该过滤器在addFilterBefore在UsernamePasswordAuthenticationFilter过滤之前
        // 核心过滤器链: UsernamePasswordAuthenticationFilter->ExceptionTranslationFilter->FilterSecurityInterceptor
        UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(userAuthorities);
        SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);

        // 放行
        filterChain.doFilter(request, response);

    }
}