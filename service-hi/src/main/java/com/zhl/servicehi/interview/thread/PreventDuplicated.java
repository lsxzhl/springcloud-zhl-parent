package com.zhl.servicehi.interview.thread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/8/20 17:11
 */
public class PreventDuplicated {

    private final static String LOCK_PATH = "C:\\code";
    private final static String LOCK_FILE = ".lock";
    private final static String PERMISSIONS = "rw------";

    public static void main(String[] args) throws IOException {
        //1.注入Hook线程，在程序退出时删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The program recevied kill SIGNAL ");
            getLockFile().toFile().delete();
        }));
        //2.检查是否存在.lock文件
        checkRunning();
        //3.简单模拟当前程序正在运行
        for (; ; ) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("Program is running ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        if (path.toFile().exists()) {
            throw new RuntimeException("The program already running ");
        }
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path, PosixFilePermissions.asFileAttribute(perms));

    }


}
