package offical.example.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Tree {
    String hostPort = "";
    ZooKeeper zooKeeper;

    public Tree(String hostPort) {
        this.hostPort = hostPort;
    }

    public static void main(String[] args) throws InterruptedException {
        new Tree("localhost:2181").start();
        new CountDownLatch(1).await();
    }

    volatile boolean connected = false;

    private void start() {
        try {
            zooKeeper = new ZooKeeper(hostPort, 15000, event -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    connected = true;
                    System.out.println("链接成功");
                    try {
                        List<String> children = zooKeeper.getChildren("/", false);
                        showChild(children);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showChild(List<String> list) {
        try {
            for (String s : list) {
                List<String> children = zooKeeper.getChildren("/" + s, false);
                System.out.println(s);
                showChild(children);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
