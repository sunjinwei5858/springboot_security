package com.sunjinwei.springboot_security.component;

import com.sunjinwei.springboot_security.constant.Constants;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @program: com.sunjinwei.springboot_security.component
 * @author: sun jinwei
 * @create: 2023-07-17 09:11
 * @description: spring security动态配置url权限认证: 1 配置认证数据源
 * @see <a href="https://blog.csdn.net/zxlyx/article/details/120929304?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-0-120929304-blog-83556600.235^v38^pc_relevant_sort&spm=1001.2101.3001.4242.1&utm_relevant_index=3">...</a>
 **/
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 返回该url所需要的用户权限信息
     *
     * @param object 储存请求url信息
     * @return 标识不需要任何权限都可以访问
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取当前请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // TODO 忽略url请放在此处进行过滤放行 这里写死放行login和logout
        if ("/login".equals(requestUrl) || requestUrl.contains("logout")) {
            return null;
        }
        // 查询数据库所有url
        List<String> urlList = Arrays.asList("/hello");
        for (String it : urlList) {
            // 获取该url所对应的权限
            if (requestUrl.equals(it)) {
                // todo 从数据库查询该权限id有哪些角色
                List<String> permissions = Arrays.asList("teacher1", "teacher2");
                // 声明角色code集合
                List<String> roles = new LinkedList<>();
                if (!CollectionUtils.isEmpty(permissions)) {
                    // 获取角色id
                    String s = permissions.get(0);
                    roles.add(s);
                }
                // 保存该url对应角色权限信息
                return SecurityConfig.createList(roles.toArray(new String[0]));
            }
        }
        // 如果数据中没有找到相应url资源 则未非法访问 要求用户登陆再进行操作 这里不抛出异常 在UrlAccessDecisionManager进行处理
        return SecurityConfig.createList(Constants.ROLE_LOGIN);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}