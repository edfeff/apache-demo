package offical.example.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class Zoo {
    private String hostPort;
    private ZooKeeper zooKeeper;
    private int timeout;
    private InnerWatcher watcher;
    volatile boolean connected = false;

    public ZooKeeper getZooKeeper() {
        while (!connected) {
        }
        return zooKeeper;
    }

    public Zoo(String hostPort, int timeout) {
        this.hostPort = hostPort;
        this.timeout = timeout;
    }

    public Zoo() {
        this("localhost:2181", 15000);
        init();
    }

    private void init() {
        watcher = new InnerWatcher(this);
        try {
            zooKeeper = new ZooKeeper(hostPort, timeout, watcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class InnerWatcher implements Watcher {
        Zoo zoo;

        public InnerWatcher(Zoo zoo) {
            this.zoo = zoo;
        }

        @Override
        public void process(WatchedEvent event) {
            switch (event.getState()) {
                case SyncConnected:
                    connected();
                    break;
                case Expired:
                    reconnect();
                    break;
                case AuthFailed:
                    authFailed();
                    break;
                case Disconnected:
                    disconnected();
                    break;
                case SaslAuthenticated:
                case ConnectedReadOnly:
                default:
                    break;

            }
        }

        private void connected() {
            zoo.connected = true;
        }

        private void disconnected() {
            zoo.connected = false;

        }

        private void authFailed() {
            zoo.connected = false;
            throw new RuntimeException("Zookeeper认证失败");
        }

        private void reconnect() {
            try {
                zoo.zooKeeper = new ZooKeeper(zoo.hostPort, zoo.timeout, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
