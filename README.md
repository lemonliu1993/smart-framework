# smart-framework

目标：打造一个轻量级MVC框架

IOC:通过Inject注解实现Service实例化，
不是开发者自己通过new的方式来实例化，而是通过框架自身来实例化。(Ioc,控制反转)
控制不是由开发者来决定的，而是反转给框架了.
一般也将控制反转成为DI(依赖注入)，可以理解为将某个类需要依赖的成员注入到这个类中。

SLF4J 是日志框架的接口，而Log4J是日志框架的一种实现。

目标是在控制器上使用Controller注解，在控制器类的方法上使用Action注解，
在服务类上使用Service注解，在控制器类中可使用Inject注解将服务类依赖注入进来。

可以将带有Controller注解与Service注解的类所产生的对象，理解为由Smart框架所管理的Bean。

# 获取所有被Smart框架管理的Bean类，此时需要调用ClassHelper类的getBeanClassSet方法，随后需要循环调用ReflectionUtil类的newInstance方法，根据类来实例化对象，最后将每次创建的对象存放在一个静态的Map<Class<?>,Object>中。
我们需要随时获取该Map，还需要通过该Map的key(类名)去获取所对应的value(Bean对象)

#####################

Util工具类：
ClassUtil:类加载器加载基础包名下的所有类，比如使用了某注解的类，或者实现了某接口的类，再或者继承了某父类的所有子类等。
ReflectionUtil:反射工具类，封装Java反射相关的API


Helper类：
BeanHelper:BeanHelper相当于一个"Bean容器"，传入一个Bean类，就能获取Bean实例
IocHelper:遍历BeanHelper获取所有Bean Map。遍历这个映射关系，分别取出Bean类与Bean实例，进而通过反射获取类中所有的成员变量。
继续遍历这些成员遍历，在循环中判断当前成员遍历是否带有Inject注解，若带有该注解，则从Bean Map中根据Bean类取出Bean实例。
最后通过ReflectionUtil#setField方法来修改当前成员变量的值。

Handler对象获取Action的返回值，该返回值可能有两种情况：

DispatcherServlet:MVC架构中最核心的类

### AOP
对方法进行监控，也就是说，在方法调用的时候统计出方法执行时间。
不修改现有代码，在另外一个地方座性能监控。AOP(面向切面编程)
#### 
在AOP中，我们需要定义一个Aspect(切面)类来编写需要横切业务逻辑的代码，也就是上面提到的性能监控代码。此外，我们需要通过一个条件来匹配想要拦截的类，这个条件在AOP中成为PointCut(切点)。
例如：统计出执行每个Controller类的各个方法所消耗的时间。每个Controller类都带有Controller注解，也就是说，我们只需要拦截所有带有Controller注解的类就行了，切点很容易就能确定下来，剩下的就是做一个切面了。
代理：意思就是你不用去做，别人代替你去处理。

在DynamicProxy类中，定义了一个Object类型的target变量，它就是被代理的目标对象，通过构造函数来初始化。
DynamicProxy实现了InvocationHandler接口，那么必须实现该接口的invoke方法。