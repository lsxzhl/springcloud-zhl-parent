package com.zhl.servicehi.interview.thread.context;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/9/9 15:36
 */
public class ApplicationContext {

    /**
     * 在Context中保存configuration实例
     */
    private ApplicationConfiguration applicationConfiguration;

    /**
     * 在Context中保存runtimeInfo实例
     */
    private RuntimeInfo runtimeInfo;

    private static class Holder{

        private static ApplicationContext instance = new ApplicationContext();

    }

    private ConcurrentHashMap<Thread, ActionContext> contexts = new ConcurrentHashMap<>();

    public ActionContext getActionContext(){
        ActionContext actionContext = contexts.get(Thread.currentThread());
        if(actionContext == null){
            actionContext = new ActionContext();
            contexts.put(Thread.currentThread(), actionContext);
        }
        return actionContext;
    }

    public static ApplicationContext getContext(){
        return Holder.instance;
    }

    public ApplicationConfiguration getApplicationConfiguration() {
        return applicationConfiguration;
    }

    public void setApplicationConfiguration(ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    public RuntimeInfo getRuntimeInfo() {
        return runtimeInfo;
    }

    public void setRuntimeInfo(RuntimeInfo runtimeInfo) {
        this.runtimeInfo = runtimeInfo;
    }
}
