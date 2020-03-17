package com.wpp.shiro.mybatis;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Set;

public class DemoRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        MyClassPathBeanDefinitionScanner scanner = new MyClassPathBeanDefinitionScanner(beanDefinitionRegistry, false);

        scanner.registerFilters();

        scanner.scan("com.wpp.shiro.mybatis");

    }

    static class MyClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
        DemoFactoryBean demoFactoryBean = new DemoFactoryBean();

        public MyClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
            super(registry);
        }

        public MyClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
            super(registry, useDefaultFilters);
        }

        public MyClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment) {
            super(registry, useDefaultFilters, environment);
        }

        public MyClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, ResourceLoader resourceLoader) {
            super(registry, useDefaultFilters, environment, resourceLoader);
        }

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

        public void registerFilters() {
            addIncludeFilter(new AnnotationTypeFilter(Demo.class));
        }

    }
}
