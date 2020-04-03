package com.zhl.servicehi.interview.delivery.time;


import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.Date;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/22 9:08
 */
public class EnsureDeliveryTime {

    final DateTime DISTRIBUTION_TIME_SPLIT_TIME = new DateTime().withTime(15,0,0,0);

    public Date calculateDistributionTimeByOrderCreateTime(Date orderCreateTime){
        DateTime orderCreateDateTime = new DateTime(orderCreateTime);
        Date tomorrow = orderCreateDateTime.plusDays(1).toDate();
        Date theDayAfterTomorrow = orderCreateDateTime.plusDays(2).toDate();
        return orderCreateDateTime.isAfter(DISTRIBUTION_TIME_SPLIT_TIME) ? wrapDistributionTime(theDayAfterTomorrow) : wrapDistributionTime(tomorrow);
    }

    public Date wrapDistributionTime(Date distributionTime) {
        DateTime currentDistributionDateTime = new DateTime(distributionTime);
        DateTime plusOneDay = currentDistributionDateTime.plusDays(1);
        boolean isSunday = (DateTimeConstants.SUNDAY == currentDistributionDateTime.getDayOfWeek());
        return isSunday ? plusOneDay.toDate() : currentDistributionDateTime.toDate();
    }


}
