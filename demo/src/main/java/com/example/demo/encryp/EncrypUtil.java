package com.example.demo.encryp;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/9 15:44
 */
public class EncrypUtil {

    private static final PasswordEncoder PASSWORDENCODER = new StandardPasswordEncoder();

    public static String encryp(String password){
        return PASSWORDENCODER.encode(password);
    }

    public static boolean match(String rapwd, String password){
        return PASSWORDENCODER.matches(rapwd, password);
    }

    public static void main(String[] args) {
        System.out.println("对123abc加密后的密码是：  " + encryp("123abc"));
        System.out.println("对123abc加密后的密码是：  " + encryp("123abc"));
        System.out.println("对123abc加密后的密码是：  " + encryp("123abc"));
        System.out.println("对123abc加密后的密码是：  " + encryp("123abc"));
        System.out.println("对123abc加密后的密码是：  " + encryp("123abc"));
        System.out.println("对123abc加密后的密码是：  " + encryp("123abc"));

    }
}
