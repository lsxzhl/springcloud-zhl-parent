package com.zhl.zookeeperserver.basicoperator;

import org.apache.zookeeper.*;

import java.io.IOException;

public class Demo {

    //会话超时时间，设置为与系统默认时间一致
    private static final  int SESSION_TIMEOUT = 3000;

    //创建zookeeper实例
    private ZooKeeper zk;

    //创建Watcher实例
    Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println(watchedEvent.toString());
        }
    };

    //初始化Zookeeper实例
    private void createZKInstance() throws IOException {
        zk = new ZooKeeper("localhost:2181",Demo.SESSION_TIMEOUT,this.watcher);
    }

    private void ZKOperations() throws KeeperException, InterruptedException {
        System.out.println("/n1. 创建 ZooKeeper 节点 (znode ： zoo2, 数据： myData2 ，权限： OPEN_ACL_UNSAFE ，节点类型： Persistent");
        zk.create("/zoo2","myData2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("/n2. 查看是否创建成功：");
        System.out.println(new String(zk.getData("/zoo2",false,null)));
        System.out.println("/n3. 修改节点数据 ");
        zk.setData("/zoo2","1314520".getBytes(),-1);
        System.out.println("/n4. 查看是否修改成功 ");
        System.out.println(new String(zk.getData("/zoo2",false,null)));

        System.out.println("/n5. 删除节点 ");
        zk.delete("/zoo2", -1);

        System.out.println("/n6. 查看节点是否被删除： ");
        System.out.println(" 节点状态： ["+zk.exists("/zoo2", false)+"]");

    }

    private void ZKClose() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Demo demo = new Demo();
        demo.createZKInstance();
        demo.ZKOperations();
        demo.ZKClose();
    }



}
