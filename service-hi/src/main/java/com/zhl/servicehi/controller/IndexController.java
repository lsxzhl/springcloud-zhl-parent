package com.zhl.servicehi.controller;

import cn.bit.redis.util.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhl.servicehi.interview.code.optimization.User;
import com.zhl.servicehi.interview.code.optimization.UserDTOConvert;
import com.zhl.servicehi.interview.code.optimization.UserDto;
import com.zhl.servicehi.interview.rsa.RsaExample;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sun.security.action.GetPropertyAction;

import java.net.URLEncoder;
import java.security.AccessController;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
public class IndexController {

    @Autowired
    private RedisUtil redisUtil;

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String index(@RequestParam("name") String name) {
        return "Hello " + name + " I am from port:" + port;
    }

    public String hiError(String name) {
        return "Hello " + name + "Sorry Error!!!";
    }

    @ResponseBody
    @PostMapping("/hset")
    public void hSet() {
        //this.redisUtil.hset("");
    }

    public static void main(String[] args) throws Exception {
        //vin---加密密钥
        String rsaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCmmHGgfjVDcWg6aP7Dm5YbshkLqMOCPBN+w7BFwVF2TSDYXZODhOMEqeytmMN+mZX4i5g+4ofyuJqjk4TLR84bFpMQ1s46oGMC+V1JtQOm1svxhee9uYlWgsPzyF01RMlEToPhpCTgyaUEgOjJNXqt4vssub5eDcyJ1nvaCWvmQIDAQAB";
        //vin_timestamp 签名密钥
        String signPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMldj1iFaxTlvw0v8AVJSN/RlW2EuJbF2JIl7b33tX5J8X6nTA8cUwdMy1fgTjOKHzj0gYdU3w1S1W/iRe5I+4gPhMx/T4Ela+lIUx1tErQnz2tdJAmh4gq4bMb6rYWMcGoqDspSSrZ+a5AvWyFqjkMIIRhjkwuYKp1cWb5FYWAFAgMBAAECgYB2Timx8HEBThn5PXjfIjdWiGQqfBeXPZYIB5CuU6KmF/tyVggxuIpvd4bgkkBft36wj7aqNAr4YPpVSbOcU/Sy6NTfUEshOwQLOxvobLEyOg62//mB4USxqj3ENeNehYwZ5CXLnr9wK7ni/RSnFJAW5nz5flXOCZNkPVdxdwND4QJBAOdkGYoOiP4m1NosqZCjvcxKmkBc0Ntj7vXQ2364Omn4hxYjjcdvQg6qbzuOlxbSsGGtzGsqdSVlVeXrQS0jmscCQQDex/p2mAeybl3IryAjHHkTOnOCDBbts90BAP1ie7oHN8w0a2yYZnITPpxbu7Rx5YQjlDiWYqeUUtSRo9huUsLTAkAxPTHaGQG545WD3+EtcEqhQHbWn2mqZfehw5IRwy5bApHseiBfgiNyb35AFDW+m5MBFjTb0SsgjBHdXVR/QIZdAkBObPnYC/cRslajkjrvAVQCF96X1mev8FSJyO5qYAICDnt9rJ+DGvVnnu/uPyrruY+F8uTk1AAIcAxS5OoJFGtvAkEAhD6tTAMoaBWxQf2UP1TsQoE7WpPd7Rl/T0dUCgCJum2+8AU/8h/ygWv78iRJAHoyys9wz0zweQYgPD0q1q8qvw==";
        //获取token
        String getTokenUrl = "https://oauth.bitnei.cn/acquire/token?username=test@bitnei.cn&password=abc123";
        String tokenResult = sendPostMethod(getTokenUrl);
        String data = JSONObject.parseObject(tokenResult).getString("data");
        String access_token = JSONObject.parseObject(data).getString("access_token");
        System.out.println("token: "+ access_token);
        long startTime = System.currentTimeMillis();
        //vin参数处理
        String vin = "LGXC76C3XJ0033125";
        String licensePlate = "京Q3R7W5";
        String encryVin = RsaExample.encrypt(vin,RsaExample.getPublicKey(rsaPublicKey));
        String encryLicensePlate = RsaExample.encrypt(licensePlate,RsaExample.getPublicKey(rsaPublicKey));
        String enc = AccessController.doPrivileged(
                new GetPropertyAction("file.encoding")
        );
        //URLEncoder.encode(data,AccessController.doPrivileged(new GetPropertyAction("file.encoding")));
        // SHA256withRSA签名
        long timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
        String signature = RsaExample.sign(vin + "_" + timestamp, RsaExample.getPrivateKey(signPrivateKey));
        //拼接请求地址
        System.out.println("encode  Vin：" + URLEncoder.encode(encryVin,enc));
        System.out.println("signature:" + signature);
        System.out.println("timestamp:"+ timestamp);
        String url = "https://saas.bitnei.cn/external-api/vrm/genilex/insurance/lable?vin=" + URLEncoder.encode(encryVin,"utf-8")+"&licensePlate="+ URLEncoder.encode(encryLicensePlate,"utf-8")+"&customerId=test";
        //发送请求
        String tenant = sendGetMethod(url, access_token,signature,timestamp);
        long endTime = System.currentTimeMillis();
        System.out.println("consume time is : " + (endTime-startTime));
        String data1 = JSONObject.parseObject(tenant).getString("data");
        JSONObject jsonObject = JSONObject.parseObject(data1);

        System.out.println(tenant);

    }

    /**
     * function HttpClient发送带token的GET请求
     *
     * @param url
     * @param Authorization
     * @return
     * @throws Exception
     */
    public static String sendGetMethod(String url, String Authorization, String signature, long timestamp) throws Exception {
        HttpClient httpClient;
        HttpGet getMethod;
        HttpResponse response;
        String reponseContent;
        httpClient = HttpClients.createDefault();
        getMethod = new HttpGet(url);
        getMethod.addHeader("Content-type", "application/json;charset=utf-8");
        getMethod.addHeader("accept", "*/*");
        getMethod.addHeader("Authorization", "bearer " + Authorization);
        getMethod.addHeader("signature", signature);
        getMethod.addHeader("timestamp", String.valueOf(timestamp));
        response = httpClient.execute(getMethod);
        HttpEntity httpEntity = response.getEntity();
        reponseContent = EntityUtils.toString(httpEntity);
        EntityUtils.consume(httpEntity);
        return reponseContent;
    }

    public static String sendPostMethod(String url) throws Exception {
        HttpClient httpClient;
        HttpPost postMethod;
        HttpResponse response;
        String reponseContent;
        httpClient = HttpClients.createDefault();
        postMethod = new HttpPost(url);
        postMethod.addHeader("Content-type", "application/json;charset=utf-8");
        postMethod.addHeader("accept", "*/*");
        response = httpClient.execute(postMethod);
        HttpEntity httpEntity = response.getEntity();
        reponseContent = EntityUtils.toString(httpEntity);
        EntityUtils.consume(httpEntity);
        return reponseContent;

    }

    public void addUser(@RequestBody UserDto userDto) {
        User user = new User();
        //1.使用get set方法属性多时显得臃肿，使用BeanUtils优化
        BeanUtils.copyProperties(userDto, user);
        //2.使用BeanUtils语音不明确，继续优化
        convertFor(userDto);
        //3.上面的方法复用性差，继续优化
        User us = (User) new UserDTOConvert().convert(userDto);
        //4.使用接口加实现类实现后，new这样一个对象是没有比要的
        //而且每一个转化对象都是在遇到DTO转化的时候才会出现
        userDto.convertToUser();
        //5.使用语义转换的代码

        //执行service方法
    }

    private User convertFor(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }


}
