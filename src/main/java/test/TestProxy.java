package test;

import java.lang.reflect.Proxy;

/**
 * Created by lemoon on 19/3/7 上午12:01
 */
public class TestProxy {

    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(hello);
        Hello helloProxy = (Hello)Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                dynamicProxy
        );
        helloProxy.say("Jack");
    }
}
