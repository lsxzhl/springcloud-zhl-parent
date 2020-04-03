package com.zhl.servicehi.interview.rest;

import org.springframework.web.client.RestTemplate;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/11/22 9:02
 */
public class ExtractRestTemplate extends FilterRestTemplate {

    private RestTemplate restTemplate;

    public ExtractRestTemplate(RestTemplate restTemplate) {
        super(restTemplate);
        this.restTemplate = restTemplate;
    }


}
