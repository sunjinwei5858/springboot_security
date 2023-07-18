package com.sunjinwei.springboot_security.component;

import com.sunjinwei.springboot_security.constant.Constants;
import com.sunjinwei.springboot_security.domain.UserAuthority;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @program: com.sunjinwei.springboot_security.component
 * @author: sun jinwei
 * @create: 2023-07-18 08:12
 * @description: 对访问url进行权限认证处理
 **/
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    /**
     *
     * @param authentication 当前登陆用户的角色信息
     * @param object 请求url信息
     * @param configAttributes `UrlFilterInvocationSecurityMetadataSource`中的getAttributes方法传来的，表示当前请求需要的角色（可能有多个）
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 遍历角色
        for (ConfigAttribute configAttribute : configAttributes) {
            // 当前url请求需要的权限
            String needRole = configAttribute.getAttribute();
            // 判断是不是 need_login 的 角色
            if (Constants.ROLE_LOGIN.equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登陆");
                }
                throw new AccessDeniedException("未授权该url");
            }
            // 当前用户所具有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                UserAuthority authority1 = (UserAuthority) authority;
                if (authority1.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("请联系管理员分配权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}