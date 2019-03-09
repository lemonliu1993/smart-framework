package aoptest;

import org.apache.commons.dbutils.ProxyFactory;

/**
 * Created by lemoon on 19/3/8 上午6:58
 */
public class Client {

    public static void main(String[] args) {
        Greeting greetingProxy = new GreetingProxy(new GreetingImpl());
        greetingProxy.sayHello("Jack");


        Greeting greeting = new JDKDynamicProxy(new GreetingImpl()).getProxy();
        greeting.sayHello("Jack");

        Greeting greeting1 = CGLibDynamicProxy.getInstance().getProx(GreetingImpl.class);
        greeting1.sayHello("Jack");
        

    }
}
