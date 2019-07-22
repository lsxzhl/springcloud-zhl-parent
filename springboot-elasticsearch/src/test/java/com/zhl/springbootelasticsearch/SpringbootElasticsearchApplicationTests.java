package com.zhl.springbootelasticsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElasticsearchApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    TransportClient transportClient;

    @Test
    public void createDocumentByManually(){
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        //IndexRequestBuilder prepareIndex(String index, String type)
        final IndexResponse response  = this.transportClient.prepareIndex("zhl", "lsx")
                .setSource(json, XContentType.JSON).get();
        //获取索引
        final String _index = response.getIndex();
        //获取类型
        final String _type = response.getType();
        // 文档ID
        String _id = response.getId();
        // 版本
        long _version = response.getVersion();
        // 返回的操作状态
        RestStatus status = response.status();
        System.out.println("索引名称:"+_index+" "+"类型 :" +  _type + " 文档ID："+_id+" 版本 ："+_version+" 返回的操作状态："+status);

    }

    @Test
    public void createDocumentByMap(){
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user","kimchy");
        json.put("postDate",new Date());
        json.put("message","trying out Elasticsearch");
        //this.transportClient.prepareIndex 可以传入id
        IndexResponse response = this.transportClient.prepareIndex("map", "weet")
                .setSource(json, XContentType.JSON).get();
        //获取索引
        final String _index = response.getIndex();
        //获取类型
        final String _type = response.getType();
        // 文档ID
        String _id = response.getId();
        // 版本
        long _version = response.getVersion();
        // 返回的操作状态
        RestStatus status = response.status();
        System.out.println("索引名称:"+_index+" "+"类型 :" +  _type + " 文档ID："+_id+" 版本 ："+_version+" 返回的操作状态："+status);
    }

    /**
     *这种方式是使用jsckson来序列化一个bean的方式进行操作的
     * import com.fasterxml.jackson.databind.*;
     * */

    @Test
    public void createDocumentBySerialize(){

        try {
            // insstance a json mapper
            ObjectMapper mapper = new ObjectMapper(); // create once, reuse
            //构造一个类
            Person p = new Person();
            p.setName("kimchy");
            p.setPostDate(new Date());
            p.setMessage("trying out Elasticsearch");
            // generate json
            byte[] json = mapper.writeValueAsBytes(p);
            IndexResponse response = this.transportClient.prepareIndex("twitter3", "tweet")
                    .setSource(json, XContentType.JSON)
                    .get();
            // 索引名称
            String _index = response.getIndex();
            // 类型
            String _type = response.getType();
            // 文档ID
            String _id = response.getId();
            // 版本
            long _version = response.getVersion();
            // 返回的操作状态
            RestStatus status = response.status();
            System.out.println("索引名称:"+_index+" "+"类型 :" +  _type + " 文档ID："+_id+" 版本 ："+_version+" 返回的操作状态："+status);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
