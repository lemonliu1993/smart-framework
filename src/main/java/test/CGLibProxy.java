package test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by lemoon on 19/3/7 上午6:37
 */
public class CGLibProxy implements MethodInterceptor {
    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this);
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable{
        before();
        Object result = proxy.invokeSuper(obj,args);
        after();
        return result;
    }


    private void before(){
        System.out.println("CGLib before");
    }

    private void after(){
        System.out.println("CGLib after");
    }

    private static CGLibProxy instance = new CGLibProxy();

    private CGLibProxy(){

    }

    public static CGLibProxy getInstance(){
        return instance;
    }

    public static void main(String[] args) {
//        CGLibProxy cgLibProxy = new CGLibProxy();
//        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("Jack");
    }
}
