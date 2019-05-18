package com.example.demo.security;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/15 11:32
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public void setVerificationCodeParameter(String verification_code) {
    }
}
