package com.sunjinwei.springboot_security.component;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;

/**
 * @program: com.sunjinwei.springboot_security.component
 * @author: sun jinwei
 * @create: 2023-07-17 09:11
 * @description: spring security动态配置url权限认证: 1 配置认证数据源
 * @see <a href="https://blog.csdn.net/zxlyx/article/details/120929304?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-0-120929304-blog-83556600.235^v38^pc_relevant_sort&spm=1001.2101.3001.4242.1&utm_relevant_index=3">...</a>
 **/
public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}