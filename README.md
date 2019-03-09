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

使用DynamicProxy的好处是，接口变了，这个动态代理类不用动。而静态代理就不一样了，接口变了，实现类还要动。
但动态代理也有搞不定的时候，比如要代理一个没有任何接口的类，它就没有用武之地了。
这时候就该使用CGLib了，它是一个在运行期间动态生成字节码的工具，也就是动态生成代理类了。
CGLib提供的是方法级别的代理，也可以理解胃对方法的拦截(传说中的"方法拦截器").我们直接调用proxy的invokeSuper方法，将被代理的对象obj以及方法参数args传入其中即可
    
#### AOP很重要的工作就是去写"切面"。
切面是AOP的一个术语，表示从业务逻辑中分离出来的横切逻辑，比如性能监控，日志纪录，权限控制等，这些功能都可以从核心的业务逻辑代码中抽离出去。也就是说，通过AOP可以解决代码耦合问题，让职责更加单一。
最早出现AOP的概念的是AspectJ

Spring AOP:前置增强，后置增强，环绕增强(编程式)

用AOP的行话来说，对方法的增强叫Weaving(织入)，对类的增强叫做Introduction(引入)。Introduction Advice(引入增强)就是对类的功能增强，他也是Spring AOP提供的最后一种增强。
我们可以通过切面，将增强类与拦截条件组合在一起，然后将这个切面配置到ProxyFactory中，从而生成代理。
这个"拦截匹配条件"在AOP中就叫做Pointcut(切点)，其实说白了就是一个基于表达式的拦截条件。Advisor(切面)封装了Advice(增强)与Pointcut(切点)。
