package com.sunjinwei.springboot_security.service;

import com.sunjinwei.springboot_security.domain.User;

/**
 * @program: com.sunjinwei.springboot_security.service
 * @author: sun jinwei
 * @create: 2023-07-16 22:16
 * @description:
 **/
public interface UserService {

    User queryUserById(Long id);
}
