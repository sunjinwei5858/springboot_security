package com.sunjinwei.springboot_security.domain;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * @program: com.sunjinwei.springboot_security.domain
 * @author: sun jinwei
 * @create: 2023-07-16 21:55
 * @description:
 **/
@Data
public class UserAuthenticationToken implements Authentication {

    private List<UserAuthority> authorityList;

    public UserAuthenticationToken(List<UserAuthority> authorityList) {
        this.authorityList = authorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}