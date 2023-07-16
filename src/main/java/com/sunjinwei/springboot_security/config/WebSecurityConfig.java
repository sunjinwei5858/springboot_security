package com.sunjinwei.springboot_security.config;

import com.sunjinwei.springboot_security.component.SecurityExceptionHandler;
import com.sunjinwei.springboot_security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .exceptionHandling()
                // 授权失败的处理
                .accessDeniedHandler(securityExceptionHandler)
                // 未认证的处理
                .authenticationEntryPoint(securityExceptionHandler)
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestCache().disable()
                .headers().disable()
                .authorizeRequests()
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