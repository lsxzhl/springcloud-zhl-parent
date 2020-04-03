package com.zhl.servicehi.interview.custom.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/12/6 10:08
 * 自定义java类加载器来实现java类的热加载
 */
public class CustomClassLoader extends ClassLoader {

    private static final Logger log = LoggerFactory.getLogger(CustomClassLoader.class);

    /**
     * 要加载的java类的classpath路径
     */
    private String classPath;

    public CustomClassLoader(String classPath) {
        //指定父加载器
        super(ClassLoader.getSystemClassLoader());
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    /**
     * 加载class文件中的内容
     *
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        try {
            name = name.replace(".", "//");
            FileInputStream inputStream = new FileInputStream(new File(classPath + name + ".class"));
            //定义字节数组输出流
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int b = 0;
            while ((b = inputStream.read()) != -1) {
                byteArrayOutputStream.write(b);
            }
            inputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
