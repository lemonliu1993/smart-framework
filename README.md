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