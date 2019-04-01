package com.example.demo.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @Author: zhanghailong@bitnei.cn
 * @Date: 2019/3/29 11:31
 */
public class TestJson {

    public static void main(String[] args) {
        String asscess =   "{\n\"type\":\"ApiThrottleStrategy\",\n\"docId\":\"accessRate\",\n\"tenantId\":\"fdvdfv5dfvsdvs\",\n\"accessRate\":{\n\"frequency\":\"2\",\n\"maturityDate\":\"2019-09-12\",\n\"restrictIp\":\"47.99.99.12\"\n}}";
        String      json = "{\n\"type\":\"ApiThrottleStrategy\",\n\"docId\":\"accessRate\",\n\"tenantId\":\"fdvdfv5dfvsdvs\",\n\"accessRate\":{\n\"frequency\":\"2\",\n\"maturityDate\":\"2019-09-12\",\n\"restrictIp\":\"47.99.99.12\"\n}}";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputPageModel inputPageModel = (InputPageModel) objectMapper.readValue(asscess,Page.class);
            System.out.println(inputPageModel.getAccessRate().getFrequency());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
