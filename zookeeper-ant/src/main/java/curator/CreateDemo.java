package curator;

import org.apache.curator.framework.CuratorFramework;

public class CreateDemo {
    public static void main(String[] args) {
        CuratorFramework framework = CuratorUtil.curatorFramework("localhost:2181");
//        framework.create().
    }
}
