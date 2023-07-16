package com.sunjinwei.springboot_security.service.impl;

import com.alibaba.fastjson.JSON;
import com.sunjinwei.springboot_security.domain.User;
import com.sunjinwei.springboot_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @program: com.sunjinwei.springboot_security.service
 * @author: sun jinwei
 * @create: 2023-07-16 22:16
 * @description:
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User queryUserById(Long id) {

        // 先从redis中查询
        String redisKey = "login:" + id;
        String s = redisTemplate.opsForValue().get(redisKey);
        if (StringUtils.hasText(s)) {
            return JSON.parseObject(s, User.class);
        }
        // todo redis没有 从数据库中查询

        return null;
    }
}