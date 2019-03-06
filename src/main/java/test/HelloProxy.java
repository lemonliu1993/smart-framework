package test;

/**
 * Created by lemoon on 19/3/5 上午8:48
 */
public class HelloProxy implements Hello{

    private Hello hello;


    public HelloProxy() {
        this.hello = new HelloImpl();
    }

    public void say(String name) {
        before();
        hello.say(name);
        after();
    }

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }


    public static void main(String[] args) {
        Hello helloProxy = new HelloProxy();
        helloProxy.say("Jack");
    }
}
