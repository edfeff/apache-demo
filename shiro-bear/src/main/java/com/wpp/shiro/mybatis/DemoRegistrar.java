package com.wpp.shiro.mybatis;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

public class DemoRegistrar implements ImportBeanDefinitionRegistrar {
    DemoFactoryBean demoFactoryBean = new DemoFactoryBean();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanDefinitionRegistry) {
            @Override
            protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
                Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
                for (BeanDefinitionHolder holder : beanDefinitionHolders) {
                    BeanDefinition beanDefinition = holder.getBeanDefinition();
                    beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
                    beanDefinition.setBeanClassName(demoFactoryBean.getClass().getName());
                }
                return beanDefinitionHolders;
            }
        };
        scanner.addIncludeFilter(new AnnotationTypeFilter(Demo.class));
        scanner.scan("com.wpp.shiro.mybatis.dao");

    }
}
