package aoptest;

/**
 * Created by lemoon on 19/3/8 上午6:56
 */
public class GreetingProxy implements Greeting{

    private GreetingImpl greetingImpl;

    public GreetingProxy(GreetingImpl greetingImpl) {
        this.greetingImpl = greetingImpl;
    }

    public void sayHello(String name) {
        before();
        greetingImpl.sayHello(name);
        after();
    }

    private void before(){
        System.out.println("Before");
    }

    private void after(){
        System.out.println("After");
    }
}
