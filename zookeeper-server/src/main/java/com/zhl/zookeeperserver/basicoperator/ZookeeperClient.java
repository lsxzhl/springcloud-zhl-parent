package com.zhl.zookeeperserver.basicoperator;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class ZookeeperClient implements Watcher {

    private static class PropertiesDynLoading{

        public static final String connectString = "127.0.0.1:2181";
        public static final Integer sessionTimeout = 3000;
        public static final String authScheme = "digest";
        public static final String accessKey = "cache:svcctlg";
        public static final Boolean authentication = false;

    }

    private ZooKeeper zk;

    /**
     * 创建zookeeper客户端
     * @return
     */
    private Boolean createZkclient(){
        try {
            zk = new ZooKeeper(PropertiesDynLoading.connectString,PropertiesDynLoading.sessionTimeout,this);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if(PropertiesDynLoading.authentication){
            zk.addAuthInfo(PropertiesDynLoading.authScheme,PropertiesDynLoading.accessKey.getBytes());
        }
        if(!isConnected()){
           log(" ZooKeeper client state [{}]",zk.getState().toString());
        }
        try {
            if(zk.exists("/zookeeper",false) != null){
                log("create ZooKeeper Client Success! connectString", PropertiesDynLoading.connectString);
                log(" ZooKeeper client state [{}]", zk.getState());
                return true;
            }
        } catch (KeeperException e) {
            this.log("create ZooKeeper Client Fail! connectString", PropertiesDynLoading.connectString);
            e.printStackTrace();
            e.printStackTrace();
        } catch (InterruptedException e) {
            this.log("create ZooKeeper Client Fail! connectString", PropertiesDynLoading.connectString);
            e.printStackTrace();
            e.printStackTrace();
        }
        return false;
    }

    /**
     *   新增持久化节点
     * @param path   节点路径
     * @param data   节点数据
     * @return
     */
    private Boolean createPersistentNode(String path, String data){
        if(isConnected()){
            if(PropertiesDynLoading.authentication){
                try {
                    zk.create(path,data.getBytes(),getAdminAcls(), CreateMode.PERSISTENT);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    /**
     *   创建瞬时节点
     * @param path   节点路径
     * @param data   节点数据
     * @return
     */
    private Boolean createEphemeralNode(String path, String data){
        if(isConnected()){
            try {
                if(PropertiesDynLoading.authentication){
                    zk.create(path,data.getBytes(),getAdminAcls(),CreateMode.PERSISTENT);
                }else{
                    zk.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
                log("{}",e);
                return false;
            }
        }
        this.log("zookeeper state" , zk.getState());
        return false;
    }

    /**
     *   修改数据
     * @param path  节点路径
     * @param data  节点数据
     * @return
     */
    private Boolean setNodeData(String path, String data){
        if(isConnected()){
            try {
                zk.setData(path,data.getBytes(),-1);
                return true;
            } catch (KeeperException e) {
                this.log("{}",e);
                e.printStackTrace();
            } catch (InterruptedException e) {
                this.log("{}",e);
                e.printStackTrace();
            }
        }
        this.log("zookeeper state [{}]",zk.getState());
        return false;
    }

    /**
     *    删除节点
     * @param path  节点路径
     * @return
     */
    private Boolean deleteNode(String path){
        if(isConnected()){
            try {
                zk.delete(path,-1);
                return true;
            } catch (InterruptedException e) {
                this.log("{}",e);
                e.printStackTrace();
            } catch (KeeperException e) {
                this.log("{}",e);
                e.printStackTrace();
            }
        }
        this.log("zookeeper state = [{}]", zk.getState());
        return false;
    }

    /**
     *   获取节点数据
     * @param path
     * @return
     */
    public String getNodeData(String path){
        if(isConnected()){
            String data = null;
            try {
                byte[] bytes = zk.getData(path, true, null);
                data = new String(bytes,"utf-8");
                return data;
            } catch (Exception e) {
                e.printStackTrace();
                this.log("{}",e);
                return null;
            }
        }
        this.log("zookeeper state [{}]",zk.getState() );
        return null;
    }

    /**
     *   获取子节点列表
     * @param path   节点路径
     * @return
     */
    public List<String> getCheildren(String path){
        if(isConnected()){
            try {
                return zk.getChildren(path,false);
            } catch (KeeperException e) {
                e.printStackTrace();
                return null;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
        this.log("zookeeper state [{}]",zk.getState() );
        return null;
    }

    /**
     *   关闭zookeeper
     */
    public void closeZk(){
        if(isConnected()){
            try {
                zk.close();
                this.log("close zookeeper [{}]", "success");
            } catch (InterruptedException e) {
                this.log("zookeeper state = [{}]", e);
                e.printStackTrace();
            }
        }else {
            this.log("zookeeper state = [{}]", zk.getState());
        }
    }

    public List<ACL> getAdminAcls(){
        List<ACL> aclList = new ArrayList<>(3);
        try {
            Id id = new Id(PropertiesDynLoading.authScheme,
                    DigestAuthenticationProvider.generateDigest(PropertiesDynLoading.accessKey));
            ACL acl = new ACL(ZooDefs.Perms.ALL, id);
            aclList.add(acl);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return ZooDefs.Ids.OPEN_ACL_UNSAFE;
        }
        return aclList;
    }

    /**
     * 是否存在path路径节点
     * @param path
     * @return
     */
    public Boolean exist(String path){
        try {
            return zk.exists(path,false) != null;
        } catch (KeeperException e) {
            this.log("{}",e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            this.log("{}",e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * zookeeper是否连接服务器
     * @return
     */
    public boolean isConnected() {
        return zk.getState().isConnected();
    }


    @Override
    public void process(WatchedEvent watchedEvent) {

    }

    public void log(String format, Object args){
        int index = format.indexOf("{");
        StringBuffer sb = new StringBuffer(format);
        sb.insert(index+1,"%s");
        System.out.println(String.format(sb.toString(),args));
    }


    public static void main(String[] args) {
        ZookeeperClient zkc = new ZookeeperClient();
        zkc.createZkclient();
        if (!zkc.exist("/windowcreate")) {

            zkc.createPersistentNode("/windowcreate", "windowcreate");
        }
        /*if (!zkc.exist("/windowcreate/value")) {
            System.out.println("not exists /windowcreate/value");

            zkc.createPersistentNode("/windowcreate/value", "A0431P001");
        }
        if (!zkc.exist("/windowcreate/valuetmp")) {
            System.out.println("not exists /windowcreate/valuetmp");
            zkc.createEphemeralNode("/windowcreate/valuetmp", "A0431P002");
        }
        System.out.println(zkc.getNodeData("/zookeeper"));
        System.out.println(zkc.getCheildren("/windowcreate"));
        System.out.println(zkc.getNodeData("/windowcreate/value"));
        System.out.println(zkc.getNodeData("/windowcreate/valuetmp"));
        zkc.setNodeData("/windowcreate/value", "A0431P003");
        System.out.println(zkc.getNodeData("/windowcreate/value"));
        zkc.deleteNode("/windowcreate/value");
        System.out.println(zkc.exist("/windowcreate/value"));
        zkc.closeZk();*/
    }


}
