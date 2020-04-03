package com.zhl.servicehi.interview.code.optimization;

import org.springframework.beans.BeanUtils;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/21 16:42
 */
public class UserDTOConvert implements DTOConvert {

    @Override
    public Object convert(Object o) {
        User user = new User();
        BeanUtils.copyProperties(o,user);
        return user;
    }
}
