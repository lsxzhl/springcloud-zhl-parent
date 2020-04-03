package com.zhl.servicehi.interview.custom.loader;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/12/6 10:29
 * 封装加载类的信息
 */
public class LoadInfo {

    /**
     * 自定义的类加载器
     */
    private CustomClassLoader customClassLoader;

    /**
     * 记录要加载的类的时间戳 --》加载时间
     */
    private long loadTime;

    /**
     * 需要被热加载的类
     */
    private BaseManager manager;

    public LoadInfo(CustomClassLoader customClassLoader, long loadTime) {
        this.customClassLoader = customClassLoader;
        this.loadTime = loadTime;
    }

    public CustomClassLoader getCustomClassLoader() {
        return customClassLoader;
    }

    public void setCustomClassLoader(CustomClassLoader customClassLoader) {
        this.customClassLoader = customClassLoader;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public BaseManager getManager() {
        return manager;
    }

    public void setManager(BaseManager manager) {
        this.manager = manager;
    }
}
