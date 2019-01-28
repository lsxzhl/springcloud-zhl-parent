package com.example.demo.util;

import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MongoUtil {

    private static MongoClient MONGODB_CLIENT = null;
    private static String MONGODB_IP = null;
    private static Integer MONGODB_PORT = null;
    private static String MONGODB_DATABASE_NAME = null;
    private static String MONGODB_COLLECTION_NAME = null;
    static{
        CompositeConfiguration compositeConfiguration = new CompositeConfiguration();
        try {
            compositeConfiguration.addConfiguration(new PropertiesConfiguration("mongo.properties"));
        } catch (org.apache.commons.configuration.ConfigurationException e) {
            e.printStackTrace();
        }
        MONGODB_IP = compositeConfiguration.getString("MONGODB_IP");
        MONGODB_PORT = compositeConfiguration.getInt("MONGODB_PORT");
        MONGODB_DATABASE_NAME = compositeConfiguration.getString("MONGODB_DATABASE_NAME");
        MONGODB_COLLECTION_NAME = compositeConfiguration.getString("MONGODB_COLLECTION_NAME");
        MONGODB_CLIENT = new MongoClient(MONGODB_IP,MONGODB_PORT);
    }
    private MongoUtil() {
    }
    /**
     * 初始化mongodb数据源
     * @return
     */
    public static MongoDatabase getMongodbDatabase(){
        return MONGODB_CLIENT.getDatabase(MONGODB_DATABASE_NAME);
    }
    /**
     * 关闭MongoClient连接
     */
    public static void closeMongodbClient(){
        if(null != MONGODB_CLIENT){
            MONGODB_CLIENT.close();
            MONGODB_CLIENT = null;
        }
    }
    /**
     * 获取mongodb的表对象
     * @return
     */
    public static MongoCollection<Document> getMongoCollection(){
        return getMongodbDatabase().getCollection(MONGODB_COLLECTION_NAME);
    }
    /**
     * 通过map插入一条数据到表中
     * @param map
     */
    public static void insertOneCollectionByMap(Map<String,Object> map){
        getMongoCollection().insertOne(handleMap(map));
    }
    /**
     * 通过集合map一次性插入多条数据到表中
     * @param listMap
     */
    public static void insertManyCollectionByMap(List<Map<String,Object>> listMap){
        List<Document> list = new ArrayList<Document>();
        for(Map<String,Object> map : listMap){
            Document document = handleMap(map);
            list.add(document);
        }
        getMongoCollection().insertMany(list);
    }
    /**
     * 通过实体对象插入一条数据到表中
     * @param obj
     */
    public static void insertOneCollectionByModel(Object obj){
        getMongoCollection().insertOne(handleModel(obj));
    }
    /**
     * 通过集合实体对象一次性插入多条数据到表中
     * @param listObj
     */
    public static void insertManyCollectionByModel(List<Object> listObj){
        List<Document> list = new ArrayList<Document>();
        for(Object obj : listObj){
            Document document = handleModel(obj);
            list.add(document);
        }
        getMongoCollection().insertMany(list);
    }
    /**
     * 通过手工拼接条件获取查询结果集
     * 下面是拼接queryDocument例子
     * document = new Document();
     * 要注意value中的数据类型
     * document.append("num",new Document("$eq",20));//相等
     * document.append("num",new Document("$age",20));//不相等
     * document.append("num",new Document("$gt",20));//大于
     * document.append("num",new Document("$gte",21));//大于等于
     * document.append("num",new Document("$lte",21));//小于等于
     * document.append("num",new Document("$lt",21));//小于
     * 下面是或的写法
     * List<Document> documentList = new ArrayList<Document>();
     * documentList.add(new Document("num",1));
     * documentList.add(new Document("num",2));
     * document.append("$or",documentList);
     * @param queryDocument
     * @param sortDocument
     * @return
     */
    public static String queryCollectionByCondition(Document queryDocument,Document sortDocument){
        if(null == queryDocument || null == sortDocument){
            return null;
        }else{
            String returnList = getQueryCollectionResult(queryDocument,sortDocument);
            return returnList;
        }
    }
    /**
     * 通过不定条件map查询出表中的所有数据,只限于等于的条件
     * @param map
     * @param sortDocument
     * @return
     */
    public static String queryCollectionByMap(Map<String,Object> map,Document sortDocument){
        String sql = getQueryCollectionResult(handleMap(map),sortDocument);
        return sql;
    }
    /**
     * 通过不定条件实体对象obj查询出表中的所有数据,只限于等于的条件
     * @param obj
     * @param sortDocument
     * @return
     */
    public static String queryCollectionByModel(Object obj,Document sortDocument){
        String sql = getQueryCollectionResult(handleModel(obj),sortDocument);
        return sql;
    }
    /**
     * 接收Document组装的查询对象,处理子集查询结果并以JSON的形式返回前端
     * @param queryDocument
     * @param sortDocument
     * @return
     */
    private static String getQueryCollectionResult(Document queryDocument,Document sortDocument){
        FindIterable<Document> findIterable = getMongoCollection().find(queryDocument);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        StringBuilder sql = new StringBuilder();
        Integer lineNum = 0;
        while(mongoCursor.hasNext()){
            sql.append("{");
            Document documentVal = mongoCursor.next();
            Set<Map.Entry<String,Object>> sets = documentVal.entrySet();
            Iterator<Map.Entry<String,Object>> iterators = sets.iterator();
            while(iterators.hasNext()){
                Map.Entry<String,Object> map = iterators.next();
                String key = map.getKey();
                Object value = map.getValue();
                sql.append("\"");
                sql.append(key);
                sql.append("\"");
                sql.append(":");
                sql.append("\"");
                sql.append((value == null ? "" : value));
                sql.append("\",");
            }
            sql.deleteCharAt(sql.lastIndexOf(","));
            sql.append("},");
            lineNum++;
        }
        //这里判断是防止上述没值的情况
        if(sql.length() > 0){
            sql.deleteCharAt(sql.lastIndexOf(","));
        }
        String returnList = getFinalQueryResultsSql(lineNum,sql.toString());
        return returnList;
    }
    /**
     * 拼接返回前端的JSON串
     * @param lineNum   子集中JSON的条数
     * @param querySql  子集中的所有结果JSON
     * @return
     */
    private static String getFinalQueryResultsSql(Integer lineNum,String querySql) {
        StringBuilder sql = new StringBuilder();
        sql.append("{");
        sql.append("\"");
        sql.append("jsonRoot");
        sql.append("\"");
        sql.append(":");
        sql.append("\"");
        sql.append(lineNum);
        sql.append("\",");
        sql.append("\"");
        sql.append("jsonList");
        sql.append("\"");
        sql.append(":");
        sql.append("[");
        sql.append(querySql);
        sql.append("]");
        sql.append("}");
        return sql.toString();
    }
    /**
     * 以list的形式获取mongdb库中的所有表
     * @return
     */
    public static List<String> getALLCollectionNameOfList(){
        List<String> list = new ArrayList<String>();
        MongoIterable<String> mongoIterable = getMongodbDatabase().listCollectionNames();
        for(String name : mongoIterable){
            list.add(name);
        }
        return list;
    }
    /**
     * 以map的形式获取mongdb库中的所有表
     * @return
     */
    public static Map<String,String> getALLCollectionNameOfMap() {
        Map<String,String> map = new HashMap<String,String>();
        MongoIterable<String> mongoIterable = getMongodbDatabase().listCollectionNames();
        for(String name : mongoIterable){
            map.put(name,name);
        }
        return map;
    }
    /**
     * 获取表中的数据条数
     * @param queryDocument 传null为查询表中所有数据
     * @return
     */
    public static Integer queryCollectionCount(Document queryDocument){
        int count = (int) getMongoCollection().count(queryDocument);
        return count;
    }
    /**
     * 通过表ID获取某条数据
     * @param id
     * @return
     */
    public static String queryCollectionModelById(String id){
        ObjectId objectId = new ObjectId(id);//注意在处理主键问题上一定要用ObjectId转一下
        Document document = getMongoCollection().find(Filters.eq("_id",objectId)).first();
        return (document == null ? null : document.toJson());
    }
    /**
     * 根据ID更新某一条数据
     * @param id          查询条件主键ID
     * @param updateMap   更新内容,如果是此ID中不存在的字段,那么会在此ID对应的数据中加入新的字段内容
     * 注意这里跟updateOptions.upsert(ifInsert);没关系
     */
    public static void updateCollectionById(String id,Map<String,Object> updateMap){
        Document queryDocument = new Document();
        ObjectId objId = new ObjectId(id);//注意在处理主键问题上一定要用ObjectId转一下
        queryDocument.append("_id", objId);
        Document updateDocument = handleMap(updateMap);
        getMongoCollection().updateOne(queryDocument,new Document("$set",updateDocument));
    }
    /**
     * 根据某几个字段更新多条数据,document的条件拼接可参考queryCollectionByCondition方法
     * @param queryDocument      查询条件,一定不要加_id,根据ID处理的话参考updateCollectionById方法
     * @param updateDocument     更新内容,当查询条件和更新内容有出入并且ifInsert为true时才插入
     * @param ifInsert           数据不存在是否插入,true插入,false不插入
     */
    public static void updateCollectionByCondition(Document queryDocument,Document updateDocument,Boolean
            ifInsert){
        UpdateOptions updateOptions = new UpdateOptions();
        updateOptions.upsert(ifInsert);
        getMongoCollection().updateMany(queryDocument,new Document("$set",updateDocument),updateOptions);
    }
    /**
     * 根据ID删除某一条数据
     * @param id
     * @return
     */
    public static Integer deleteCollectionById(String id){
        ObjectId objectId = new ObjectId(id);
        Bson bson = Filters.eq("_id",objectId);
        DeleteResult deleteResult = getMongoCollection().deleteOne(bson);
        int count = (int) deleteResult.getDeletedCount();
        return count;
    }
    /**
     * 根据MAP删除表中的某些数据
     * @param map
     */
    public static void deleteCollectionByMap(Map<String,Object> map){
        getMongoCollection().deleteMany(handleMap(map));
    }
    /**
     * 根据实体对象删除表中的某些数据
     * @param obj
     */
    public static void deleteCollectionByModel(Object obj){
        getMongoCollection().deleteMany(handleModel(obj));
    }
    /**
     * 根据预先手工拼接的document删除表中的某些数据
     * @param document
     */
    public static void deleteCollectionByDocument(Document document){
        getMongoCollection().deleteMany(document);
    }
    /**
     * 通过实体对象obj拼接document
     * @param obj
     * @return
     */
    private static Document handleModel(Object obj){
        Document document = null;
        if(obj != null){
            document = new Document();
            try {
                Class clz = obj.getClass();
                Field fields[] = clz.getDeclaredFields();
                for(Field field : fields){
                    String fieldName = field.getName();
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName,clz);
                    Method method = propertyDescriptor.getReadMethod();
                    Object fieldValue = method.invoke(obj);
                    document.append(fieldName,(fieldValue == null ? "" : fieldValue));
                }
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            document = new Document("","");
        }
        return document;
    }
    /**
     * 通过集合map拼接document
     * @param map
     * @return
     */
    private static Document handleMap(Map<String,Object> map){
        Document document = null;
        if(null != map){
            document = new Document();
            Set<String> sets = map.keySet();
            Iterator<String> iterators = sets.iterator();
            while(iterators.hasNext()){
                String key = iterators.next();
                Object value = map.get(key);
                document.append(key,(value == null ? "" : value));
            }
        }else{
            document = new Document("","");//这种设置查询不到任何数据
        }
        return document;
    }
    /**
     * 删除某个库
     * @param databaseName
     */
    public static void dropDatabase(String databaseName){
        MONGODB_CLIENT.dropDatabase(databaseName);
    }
    /**
     * 删除某个库中的某个表
     * @param databaseName
     * @param collectionName
     */
    public static void dropCollection(String databaseName,String collectionName){
        MONGODB_CLIENT.getDatabase(databaseName).getCollection(collectionName).drop();
    }
    /**
     * 下述方式个人并不推荐,没有直接用document直接拼串方便
     */
    public static void testquery(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(20);
        list.add(21);
        list.add(22);
        FindIterable<Document> findIterable =
                //getMongoCollection().find(Filters.and(Filters.lt("num",22),Filters.gt("num",17)));
                //getMongoCollection().find(Filters.in("num",17,18));
                getMongoCollection().find(Filters.nin("num",list));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            Document document = mongoCursor.next();
            System.out.println(document.toJson());
        }
    }


}
