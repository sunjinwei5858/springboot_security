package com.sunjinwei.springboot_security.config;

import com.sunjinwei.springboot_security.component.SecurityExceptionHandler;
import com.sunjinwei.springboot_security.component.UrlAccessDecisionManager;
import com.sunjinwei.springboot_security.component.UrlFilterInvocationSecurityMetadataSource;
import com.sunjinwei.springboot_security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @program: com.sunjinwei.springboot_security.config
 * @author: sun jinwei
 * @create: 2023-07-16 15:02
 * @description: Spring Security without the WebSecurityConfigurerAdapter
 * @see <a href="https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter">...</a>
 **/
@Configuration
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private SecurityExceptionHandler securityExceptionHandler;

    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager;

    @Autowired
    private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .exceptionHandling()
                // 授权失败的处理(登录过后访问无权限的接口时自定义403响应内容)
                .accessDeniedHandler(securityExceptionHandler)
                // 未认证的处理(未登录认证异常)
                .authenticationEntryPoint(securityExceptionHandler)
                .and()
                // url权限认证处理
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestCache().disable()
                .headers().disable()
                .antMatcher("/**")
                .authorizeRequests()
                // url权限认证处理
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(urlAccessDecisionManager);
                        return object;
                    }
                })
                // 其余所有请求都需要认证
                .anyRequest().authenticated()
                .and()
                // 配置过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(HttpSecurity http) throws Exception {
        return (web -> web.ignoring().antMatchers(HttpMethod.GET, "/webjars/**", "/swagger-ui.html"));
    }
}