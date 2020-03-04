package offical.example.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class AllwaysListener {
    public static void main(String[] args) throws KeeperException, InterruptedException {
        Zoo zoo = new Zoo();

        ZooKeeper zooKeeper = zoo.getZooKeeper();
        String path = "/a";
        zooKeeper.exists(path, new AlwaysWatcher(zooKeeper, path, new Handler()));

        new CountDownLatch(1).await();
    }

    static class Handler {
        void dataChange() {
            System.out.println("change");
        }

        public void dataDelete() {
            System.out.println("delete");
        }
    }

    static class AlwaysWatcher implements Watcher {
        ZooKeeper zooKeeper;
        String path;
        Handler handler;

        public AlwaysWatcher(ZooKeeper zooKeeper, String path, Handler handler) {
            this.zooKeeper = zooKeeper;
            this.path = path;
            this.handler = handler;
        }

        @Override
        public void process(WatchedEvent event) {
            switch (event.getType()) {
                case NodeDeleted:
                    handler.dataDelete();
                    break;
                case NodeDataChanged:
                    handler.dataChange();
                    addSelf();
                default:
                    break;
            }
        }

        private void addSelf() {
            try {
                zooKeeper.exists(path, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
