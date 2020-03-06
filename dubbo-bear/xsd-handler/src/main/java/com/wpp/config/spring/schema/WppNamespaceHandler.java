package com.wpp.config.spring.schema;

import com.wpp.config.AddressConfig;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class WppNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        this.registerBeanDefinitionParser("address",
                new WppBeanDefinitionParser(AddressConfig.class, true));
    }
}
