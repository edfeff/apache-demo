package quickstart.service.impl;

import quickstart.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHi(String name) {
        return "hello";
    }
}
