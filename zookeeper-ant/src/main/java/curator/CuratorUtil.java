package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author wpp
 */
public class CuratorUtil {
    public static CuratorFramework curatorFramework(String host) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(10000, 3);
        CuratorFramework framework =
                CuratorFrameworkFactory.newClient(host, 10000, 10000, retryPolicy);
        framework.start();
        return framework;
    }
}
