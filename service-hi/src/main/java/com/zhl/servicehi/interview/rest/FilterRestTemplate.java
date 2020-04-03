package com.zhl.servicehi.interview.rest;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/21 18:26
 */
@AllArgsConstructor
public abstract class FilterRestTemplate implements RestOperations {

    @Delegate
     protected volatile RestTemplate restTemplate;

}
