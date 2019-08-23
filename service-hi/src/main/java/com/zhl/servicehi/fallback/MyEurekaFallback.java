//package com.zhl.servicehi.fallback;
//
//import com.netflix.hystrix.exception.HystrixTimeoutException;
//import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.ClientHttpResponse;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class MyEurekaFallback implements FallbackProvider {
//    @Override
//    public ClientHttpResponse fallbackResponse(Throwable cause) {
//        //标记不同的异常为不同的http状态值
//        if(cause instanceof HystrixTimeoutException){
//            return response(HttpStatus.GATEWAY_TIMEOUT);
//        }else{
//            //可继续添加自定义异常类
//            return response(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    private ClientHttpResponse response(final HttpStatus status) {
//        return new ClientHttpResponse() {
//            @Override
//            public HttpStatus getStatusCode() throws IOException {
//                return status;
//            }
//
//            @Override
//            public int getRawStatusCode() throws IOException {
//                return status.value();
//            }
//
//            @Override
//            public String getStatusText() throws IOException {
//                return status.getReasonPhrase();
//            }
//
//            @Override
//            public void close() {
//
//            }
//
//            @Override
//            public InputStream getBody() throws IOException {
//                //可替换成相应的json串的 看业务规定了
//                return new ByteArrayInputStream("{\"code\":\"999999\",\"msg\":\"服务暂时不可用\"}".getBytes());
//            }
//
//            @Override
//            public HttpHeaders getHeaders() {
//                HttpHeaders httpHeaders = new HttpHeaders();
//                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//                return httpHeaders;
//            }
//        };
//    }
//
//    @Override
//    public String getRoute() {
//        return "service-hi";
//    }
//
//    @Override
//    public ClientHttpResponse fallbackResponse() {
//        return null;
//    }
//}
