package com.zhl.servicehi.interview.code.optimization;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/21 16:41
 */
public interface DTOConvert<S,T> {
    T convert(S s);
}
