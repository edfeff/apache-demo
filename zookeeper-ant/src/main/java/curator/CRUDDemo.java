package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class CRUDDemo {
    public static void main(String[] args) throws Exception {
//        链接策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", 5000, 5000, retryPolicy);
        client.start();

        Stat stat1 = client.checkExists().forPath("/a/b/c");
        if (stat1 != null) {
            byte[] bytes = client.getData().forPath("/a/b/c");
            if (bytes != null) {
                System.out.println(new String(bytes));
            }
        } else {
            client.create().creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath("/a/b/c", "inti".getBytes());
        }

        client.delete().guaranteed().deletingChildrenIfNeeded()
                .forPath("/a/b/c");


        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/a/b");

        client.setData().forPath("/a/b", "data".getBytes());

        byte[] bytes = client.getData().forPath("/a/b");
        System.out.println(new String(bytes));

        Stat stat2 = client.checkExists().forPath("/b");
        if (stat2 == null) {
            client.inTransaction()
                    .check().forPath("/a")
                    .and()
                    .create().withMode(CreateMode.PERSISTENT).forPath("/b", "1".getBytes())
                    .and()
                    .create().withMode(CreateMode.PERSISTENT).forPath("/c", "2".getBytes())
                    .and()
                    .commit();
        }

        List<String> list =
                client.getChildren().forPath("/");
        for (String s : list) {
            System.out.println(s);
        }

    }
}
