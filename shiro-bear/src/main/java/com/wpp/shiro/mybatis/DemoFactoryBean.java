package com.wpp.shiro.mybatis;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Bean工厂
 *
 * @param <T>
 */
public class DemoFactoryBean<T> implements FactoryBean<T> {
    private Class<T> beanInterface;

    public DemoFactoryBean(Class<T> beanInterface) {
        this.beanInterface = beanInterface;
    }

    public DemoFactoryBean() {
    }

    @Override
    public T getObject() throws Exception {
        return createProxy(beanInterface);
    }

    private T createProxy(Class<T> beanInterface) {
        return (T) Proxy.newProxyInstance(beanInterface.getClassLoader(), new Class[]{beanInterface}, new InnerInvokerHandler());
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    static class InnerInvokerHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(proxy.getClass());
            return null;
        }
    }
}
