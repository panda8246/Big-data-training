# Spring框架

Spring是一个开放源代码的设计层面框架，他解决的是业务逻辑层和其他各层的松耦合问题，因此它将面向接口的编程思想贯穿整个系统应用。Spring是于2003 年兴起的一个轻量级的Java 开发框架，由Rod Johnson创建。简单来说，Spring是一个分层的JavaSE/EE full-stack(一站式) 轻量级开源框架。

**主要目的：解耦**

----------------------------

## Maven中配置Spring

```
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
    <!--spring的核心依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.3.13.RELEASE</version>
    </dependency>
```

## 配置Spring

Spring有一个核心配置文件，建议放在Resources静态资源文件夹下，命名为：applicationContext.xml，其中配置：

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 这句是重点，配置扫描路径，扫描路径里用注解配置的类用于IoC和DI -->
    <context:component-scan base-package="Test.Spring"/>

</beans>

```

## IoC控制反转

**这是Spring实现解耦的重要机制。**

传统的实现方式中，要使用一个类的功能时，需要在代码中new一个类的实例，继而调用它的方法，这造成了类和类的高度耦合，一旦类创建失败或者被创建的类修改，使用类也要做出相应的改变。

Spring实现了IoC控制反转机制，本质上是将类的创建交给Spring管理，当需要使用一个新对象时，只需要向Spring申请，无需关系类是如何被加载和创建的，这使得代码的耦合度得以降低。

一个使用IoC创建对象的例子：

```
    public void Test(){
        //根据指定的Spring配置文件，生成ApplicationContext对象
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //使用IoC创建对象
        MesPrinter printer = context.getBean(MesPrinter.class);
        //正常使用对象
        printer.Print();
    }
```

要交由IoC控制的类，可用注解标识，并在xml中配置好扫描路径。几个注解都可以表示该类由IoC管理：

```
    @Component      万用，打上就是IoC管理的Bean
    @Controller     标识Controller层的Bean
    @Service        标识Service层的Bean
    @Repository     标识持久层（Dao）的Bean
```

## DI依赖注入

DI—Dependency Injection，即“依赖注入”：是组件之间依赖关系由容器在运行期决定，形象的说，即由容器动态的将某个依赖关系注入到组件之中。依赖注入的目的并非为软件系统带来更多功能，而是为了提升组件重用的频率，并为系统搭建一个灵活、可扩展的平台。通过依赖注入机制，我们只需要通过简单的配置，而无需任何代码就可指定目标需要的资源，完成自身的业务逻辑，而不需要关心具体的资源来自何处，由谁实现。

如有一个Bean：
```
@Component
public class MesPrinter {

    @Autowired
    private Message mes;

    public Message getMes() {
        return mes;
    }

    public void setMes(Message mes) {
        this.mes = mes;
    }

    public void Print(){
        System.out.println(mes.GetMessage());
    }

}
```

MesPrinter由@Component注解标识为Bean，其中有一个对象成员变量mes是Message的实例，可用@Autowired注解实现依赖注入，MesPrinter依赖Message，但我们不需要手动创建Message的实例，Spring会自动帮我们提供Message。

还可依赖注入基本类型：
```
@Component
public class Message {

    @Value("假面骑士Build")
    private String mes;

    public String GetMessage(){
        return mes;
    }
}
```
这里使用了@Value注解，让Spring为mes赋值  

## Test

```
    @Test
    public void Test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MesPrinter printer = context.getBean(MesPrinter.class);
        printer.Print();
    }
```

很简洁，干劲又卫生嗷兄弟们









