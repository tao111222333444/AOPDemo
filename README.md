# AOPDemo
这是一个Android的AOP切面demo

AOP是Aspect Oriented Programming的缩写，即『面向切面编程』。它和我们平时接触到的OOP都是编程的不同思想，OOP，即『面向对象编程』，它提倡的是将功能模块化，对象化，而AOP的思想，则不太一样，它提倡的是针对同一类问题的统一处理，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

AspectJ
AspectJ实际上是对AOP编程思想的一个实践，AOP虽然是一种思想，但就好像OOP中的Java一样，一些先行者也开发了一套语言来支持AOP。目前用得比较火的就是AspectJ了，它是一种几乎和Java完全一样的语言，而且完全兼容Java（AspectJ应该就是一种扩展Java，但它不是像Groovy那样的拓展。）。当然，除了使用AspectJ特殊的语言外，AspectJ还支持原生的Java，只要加上对应的AspectJ注解就好。所以，使用AspectJ有两种方法：

完全使用AspectJ的语言。这语言一点也不难，和Java几乎一样，也能在AspectJ中调用Java的任何类库。AspectJ只是多了一些关键词罢了。
或者使用纯Java语言开发，然后使用AspectJ注解，简称@AspectJ。
基础概念

Aspect 切面：切面是切入点和通知的集合。

PointCut 切入点：切入点是指那些通过使用一些特定的表达式过滤出来的想要切入Advice的连接点。

Advice 通知：通知是向切点中注入的代码实现方法。

Joint Point 连接点：所有的目标方法都是连接点.

Weaving 编织：主要是在编译期使用AJC将切面的代码注入到目标中, 并生成出代码混合过的.class的过程.

# 类型	 描述
Before	前置通知, 在目标执行之前执行通知  

After	后置通知, 目标执行后执行通知

Around	环绕通知, 在目标执行中执行通知, 控制目标执行时机

AfterReturning	后置返回通知, 目标返回时执行通知

AfterThrowing	异常通知, 目标抛出异常时执行通知
