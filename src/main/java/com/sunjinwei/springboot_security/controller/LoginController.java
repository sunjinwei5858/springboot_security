package com.sunjinwei.springboot_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: com.sunjinwei.springboot_security.controller
 * @author: sun jinwei
 * @create: 2023-07-16 14:18
 * @description:
 **/
@RestController
public class LoginController {

    @GetMapping("/login")
    public boolean login() {
        return true;
    }
}