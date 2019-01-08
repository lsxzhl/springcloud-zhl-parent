package com.zhl.servicefeign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;


public class FeignTokenInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        if(null == getHttpServletRequest()){
            return;
        }
        //将获取的Token对应的值往下传
        requestTemplate.header("oauthToken", getHeaders(getHttpServletRequest()).get("oauthToken"));
    }

    private HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     *  feign 拦截器拦截请求获取Token对应的值
     * @param request
     * @return
     */
    private Map<String, String> getHeaders(HttpServletRequest request){
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }



}
