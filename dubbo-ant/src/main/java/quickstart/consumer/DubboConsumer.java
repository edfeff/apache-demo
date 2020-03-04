package quickstart.consumer;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import quickstart.service.HelloService;

/**
 * @author wpp
 */
public class DubboConsumer {
    public static void main(String[] args) {
        RegistryConfig zookeeper = new RegistryConfig("zookeeper://localhost:2181");
        ReferenceConfig<HelloService> helloService = new ReferenceConfig<>();
        helloService.setRegistry(zookeeper);
        helloService.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        helloService.setInterface(HelloService.class);
        HelloService service = helloService.get();
        String wpp = service.sayHi("wpp");
        System.out.println(wpp);

    }
}
