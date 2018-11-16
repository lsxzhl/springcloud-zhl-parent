package com.zhl.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

@Component
public class MyFilter extends ZuulFilter {

    @Autowired
    RedisTemplate redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(MyFilter.class);

    /** 返回的字符串代表过滤类型，pre--路由之前  routing--路由之时
                                 post--路由之后  error--发送错误调用*/
    @Override
    public String filterType() {
        return "pre";
    }

    /** 过滤顺序 */
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //逻辑判断    true --永远过滤
        return true;
    }

    /**  过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问  */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        String key = "limit" + request.getRequestURI().toString() + request.getRemoteAddr();
        Long increment = redisTemplate.opsForValue().increment(key);
        if(increment == 1){
            redisTemplate.expire(key, 30, TimeUnit.MICROSECONDS);
        }
        if(increment > 3){
            log.info("用户IP[" + request.getRemoteAddr() + "]访问地址[" + request.getRequestURL().toString() + "]超过了限定的次数[" + 3 + "]");
            return "用户IP[" + request.getRemoteAddr() + "]访问地址[" + request.getRequestURL().toString() + "]超过了限定的次数[" + 3 + "]";
        }
        log.info("======================"+ key);
        /** 处理token的 */
        String tokan = request.getParameter("token");
        if(tokan == null){
            log.warn("token is empty!!!");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            try {
                currentContext.getResponse().getWriter().write("token is empty!!!");
            } catch (IOException e) {
                log.error(e.getMessage(),e);
                return null;
            }
        }
        return null;
    }
}
