package com.example.demo.springdesignmode;

import org.aopalliance.aop.Advice;
import org.apache.shiro.aop.MethodInterceptor;
import org.springframework.aop.Advisor;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/4/10 9:38
 */
public interface AdvisorAdapter {

    boolean supportsAdvice(Advice advice);

    MethodInterceptor getInterceptor(Advisor advisor);
}
