package offical.example.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class Simple {
    String hostPort = "";
    ZooKeeper zooKeeper;

    public Simple(String hostPort) {
        this.hostPort = hostPort;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Simple("localhost:2181").start();
        new CountDownLatch(1).await();

    }

    private void start() {
        try {
            zooKeeper = new ZooKeeper(hostPort, 15000, e -> {
                System.out.println(e.getState());
                switch (e.getState()) {
                    case SyncConnected:
                        createZnode();
                        break;
                    default:
                        System.out.println(e.getState());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createZnode() {
        try {
            System.out.println("createZnode");
            PV pv = new PV("/a/b/c", "1".getBytes());
            for (int i = 1; i < pv.path.length(); i++) {
                if (pv.path.charAt(i) == '/') {
                    String path = pv.path.substring(0, i);
                    System.out.println(path);
                    Stat stat = zooKeeper.exists(path, false);
                    if (stat == null) {
                        String s = zooKeeper.create(path, pv.value, OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                        System.out.println(s);
                    } else {
                        zooKeeper.exists(path, new Watcher() {
                            @Override
                            public void process(WatchedEvent event) {
                                System.out.println("path:" + event.getPath());
                                System.out.println("state"+event.getState());
                                System.out.println(event);
                            }
                        });
                    }
                }
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void create(String str) {
        String[] paths = str.split("/");

    }

    class PV {
        String path;
        byte[] value;

        public PV(String path, byte[] value) {
            this.path = path;
            this.value = value;
        }
    }
}

