package com.zhl.servicehi.interview.code.optimization;

import java.io.Serializable;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/21 16:23
 */
public class User implements Serializable {

    private String userName;
    private int age;
    private String content;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
