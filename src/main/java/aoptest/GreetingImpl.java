package aoptest;

/**
 * Created by lemoon on 19/3/8 上午6:53
 */
public class GreetingImpl implements Greeting{
    public void sayHello(String name) {
        before();
        System.out.println("Hello"+name);
        after();
    }

    private void before(){
        System.out.println("Before");
    }

    private void after(){
        System.out.println("After");
    }
}
