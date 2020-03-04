package quickstart.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import quickstart.service.HelloService;
import quickstart.service.impl.HelloServiceImpl;

import java.util.concurrent.CountDownLatch;

/**
 * @author wpp
 */
public class DubboProvider {

    public static void main(String[] args) throws InterruptedException {
        RegistryConfig zookeeper = new RegistryConfig("zookeeper://localhost:2181");

        ServiceConfig<HelloService> helloService = new ServiceConfig<>();
        helloService.setApplication(new ApplicationConfig("first-dubbo-provider"));
        helloService.setRegistry(zookeeper);
        helloService.setInterface(HelloService.class);
        helloService.setRef(new HelloServiceImpl());
        helloService.export();

        new CountDownLatch(1).await();

    }
}
