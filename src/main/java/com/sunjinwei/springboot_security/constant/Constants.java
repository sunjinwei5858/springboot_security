package com.sunjinwei.springboot_security.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: com.sunjinwei.springboot_security.constant
 * @author: sun jinwei
 * @create: 2023-07-18 08:56
 * @description:
 **/
public class Constants {
    /**
     * 接口url
     */
    public static Map<String, String> URL_MAPPING_MAP = new HashMap<>();

    /**
     * 获取项目根目录
     */
    public static String PROJECT_ROOT_DIRECTORY = System.getProperty("user.dir");

    /**
     * 密码加密相关
     */
    public static String SALT = "xxx";

    public static final int HASH_ITERATIONS = 1;

    /**
     * 请求头 - token
     */
    public static final String REQUEST_HEADER = "X-Token";

    /**
     * 请求头类型：
     * application/x-www-form-urlencoded ： form表单格式
     * application/json ： json格式
     */
    public static final String REQUEST_HEADERS_CONTENT_TYPE = "application/json";

    /**
     * 未登录者角色
     */
    public static final String ROLE_LOGIN = "role_login";
}