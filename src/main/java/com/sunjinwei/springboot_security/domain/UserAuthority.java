package com.sunjinwei.springboot_security.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @program: com.sunjinwei.springboot_security.domain
 * @author: sun jinwei
 * @create: 2023-07-16 21:59
 * @description:
 **/
@Data
public class UserAuthority implements GrantedAuthority {

    private String code;

    public UserAuthority(String code) {
        this.code = code;
    }

    @Override
    public String getAuthority() {
        return code;
    }
}