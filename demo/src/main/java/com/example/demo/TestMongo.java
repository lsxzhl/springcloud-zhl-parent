package com.example.demo;

import com.example.demo.util.MongoUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.sql.*;
import java.util.List;

public class TestMongo {


    @Autowired
    MongoTemplate mongoTemplate;

    public static void main(String[] args) {

        String s = MongoUtil.queryCollectionByCondition(new Document("_id", new Document("$eq", "LJUB4W2W4GS001635")), new Document());

        /*try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "10.10.21.9" , 27017 );

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("hh");
            System.out.println("集合 hh 选择成功");

            //检索所有文档
            *//**
             * 1. 获取迭代器FindIterable<Document>
             * 2. 获取游标MongoCursor<Document>
             * 3. 通过游标遍历检索出的文档集合
             * *//*
            FindIterable<Document> findIterable = collection.find(new Document("_id",new Document("$eq","LJUB4W2W4GS001635")));
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                System.out.println(mongoCursor.next());
            }

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }*/

    }
    public void find(String date,Class entryClass){
        MongoClient mongoClient = new MongoClient( "10.10.21.9" , 27017 );

        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = mongoDatabase.getCollection("hh");
        List dt = mongoTemplate.find(new Query(Criteria.where("dt").is(date)), entryClass);
    }




}
