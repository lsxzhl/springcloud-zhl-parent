package com.example.demo.juc;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/5/15 17:33
 */
public class Test {

    public static void main(String[] args) {

     checkPermission("/inner_api/api/raw/charge_starttime");

    }

    public static void checkPermission(String signature) {

        if (signature.contains("/")) {
            signature = signature.substring(signature.lastIndexOf("/") + 1);
        }

        String permission = null;
        if (StringUtils.isNotBlank(signature)) {
            String[] segments = signature.split("_");

            StringBuilder builder = new StringBuilder();

            for (String segment : segments) {
                if (builder.length() != 0) {
                    segment = String.valueOf(segment.charAt(0)).toUpperCase() + segment.substring(1);
                }
                builder.append(segment);
            }
            permission = "PERM_query:" + builder.toString() + ":browse";
        }

    }
}
