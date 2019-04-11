package com.example.demo.springdesignmode;

import org.aopalliance.aop.Advice;
import org.apache.shiro.aop.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;

import java.io.Serializable;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/4/10 9:41
 */
public class MethodBeforeAdviceAdapter implements AdvisorAdapter, Serializable {
    @Override
    public boolean supportsAdvice(Advice advice) {
        return (advice instanceof MethodBeforeAdviceAdapter);
    }

    @Override
    public MethodInterceptor getInterceptor(Advisor advisor) {
        MethodBeforeAdvice advice = (MethodBeforeAdvice) advisor.getAdvice();
        return (MethodInterceptor) new MethodBeforeAdviceInterceptor(advice);
    }
}
