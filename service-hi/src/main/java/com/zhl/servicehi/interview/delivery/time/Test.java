package com.zhl.servicehi.interview.delivery.time;

import java.util.Date;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/22 9:35
 */
public class Test {

    public static void main(String[] args) {
        EnsureDeliveryTime ensureDeliveryTime = new EnsureDeliveryTime();
        Date date = ensureDeliveryTime.calculateDistributionTimeByOrderCreateTime(new Date());
        System.out.println(date);
    }

}
