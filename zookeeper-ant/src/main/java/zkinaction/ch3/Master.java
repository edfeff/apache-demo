package zkinaction.ch3;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

public class Master implements Watcher {
    ZooKeeper zooKeeper;
    String hostPort;

    public Master(String hostPort) {
        this.hostPort = hostPort;
    }

    void startZK() throws IOException {
        zooKeeper = new ZooKeeper(hostPort, 15000, this);
    }

    void stopZK() throws InterruptedException {
        zooKeeper.close();
    }

    @Override
    public void process(WatchedEvent event) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        System.out.println("isDaemon\t" + thread.isDaemon());
        System.out.println("watcher:" + Thread.currentThread() + "\n" + event);
    }


    public static void main(String[] args) throws Exception {
        String hostPort = "localhost:2181";
        if (args.length == 1) {
            hostPort = args[0];
        }
        Master master = new Master(hostPort);
        master.startZK();

        master.runForMaster();

        System.out.println("main:" + Thread.currentThread());
        Thread.sleep(60000);
        master.stopZK();
    }

    Random random = new Random();
    String serverID = Integer.toHexString(random.nextInt());
    boolean isLeader = false;

    boolean checkMaster() {
        while (true) {
            try {
                Stat stat = new Stat();
                byte[] data = zooKeeper.getData("/master", false, stat);
                isLeader = serverID.equals(new String(data));
                return true;
            } catch (InterruptedException e) {
            } catch (KeeperException e) {
                if (e instanceof KeeperException.NoNodeException) {
                    return false;
                }
            }
        }
    }

    void runForMaster() throws InterruptedException {
        while (true) {
            try {
                zooKeeper.create("/master", serverID.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (KeeperException e) {
                if (e instanceof KeeperException.NodeExistsException) {
                    isLeader = false;
                }
            }
            if (checkMaster()) {
                break;
            }
        }

    }
}
