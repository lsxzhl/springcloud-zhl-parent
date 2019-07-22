package com.zhl.springbootelasticsearch.zk;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/6/19 17:42
 */
public class Student {

    private int id;

    private String status;

    private String name;

    public Student(int id, String status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
