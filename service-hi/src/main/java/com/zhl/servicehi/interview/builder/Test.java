package com.zhl.servicehi.interview.builder;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/21 18:11
 */
public class Test {

    Student student = Student.builder().age(12).name("test").build();
    Api api = Api.builder().id("5456").apiName("测试API").requestMethod("POST").build();

}
