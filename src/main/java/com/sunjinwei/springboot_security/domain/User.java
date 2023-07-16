package com.sunjinwei.springboot_security.domain;

import lombok.Data;

import java.util.List;

/**
 * @program: com.sunjinwei.springboot_security.domain
 * @author: sun jinwei
 * @create: 2023-07-16 21:53
 * @description:
 **/
@Data
public class User {

    private Long id;

    private List<String> codes;


}