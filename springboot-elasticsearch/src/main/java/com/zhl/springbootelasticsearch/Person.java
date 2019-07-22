package com.zhl.springbootelasticsearch;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/6/13 15:56
 */
public class Person implements Serializable {

    private String name;

    private Date postDate;

    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
