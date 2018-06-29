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


Aspect的真正作用，它负责收集Jpoint，设置advice。一些简单的功能可在Aspect中来完成，而一些复杂的功能，则只是有Aspect来统一收集信息，并交给专业模块来处理。


Join Point
Join Point 表示连接点，即 AOP 可织入代码的点，下表列出了 AspectJ 的所有连接点：

Join Point	说明
Method call	方法被调用
Method execution	方法执行
Constructor call	构造函数被调用
Constructor execution	构造函数执行
Field get	读取属性
Field set	写入属性
Pre-initialization	与构造函数有关，很少用到
Initialization	与构造函数有关，很少用到
Static initialization	static 块初始化
Handler	异常处理
Advice execution	所有 Advice 执行
Pointcuts
Pointcuts 是具体的切入点，可以确定具体织入代码的地方，基本的 Pointcuts 是和 Join Point 相对应的。

Join Point	Pointcuts syntax
Method call	call(MethodPattern)
Method execution	execution(MethodPattern)
Constructor call	call(ConstructorPattern)
Constructor execution	execution(ConstructorPattern)
Field get	get(FieldPattern)
Field set	set(FieldPattern)
Pre-initialization	initialization(ConstructorPattern)
Initialization	preinitialization(ConstructorPattern)
Static initialization	staticinitialization(TypePattern)
Handler	handler(TypePattern)
Advice execution	adviceexcution()
除了上面与 Join Point 对应的选择外，Pointcuts 还有其他选择方法：

Pointcuts synatx	说明
within(TypePattern)	符合 TypePattern 的代码中的 Join Point
withincode(MethodPattern)	在某些方法中的 Join Point
withincode(ConstructorPattern)	在某些构造函数中的 Join Point
cflow(Pointcut)	Pointcut 选择出的切入点 P 的控制流中的所有 Join Point，包括 P 本身
cflowbelow(Pointcut)	Pointcut 选择出的切入点 P 的控制流中的所有 Join Point，不包括 P 本身
this(Type or Id)	Join Point 所属的 this 对象是否 instanceOf Type 或者 Id 的类型
target(Type or Id)	Join Point 所在的对象（例如 call 或 execution 操作符应用的对象）是否 instanceOf Type 或者 Id 的类型
args(Type or Id, ...)	方法或构造函数参数的类型
if(BooleanExpression)	满足表达式的 Join Point，表达式只能使用静态属性、Pointcuts 或 Advice 暴露的参数、thisJoinPoint 对象
Pointcut 表达式还可以 ！、&&、|| 来组合，!Pointcut 选取不符合 Pointcut 的 Join Point，Pointcut0 && Pointcut1 选取符合 Pointcut0 和 Pointcut1 的 Join Point，Pointcut0 || Pointcut1 选取符合 Pointcut0 或 Pointcut1 的 Join Point。

上面 Pointcuts 的语法中涉及到一些 Pattern，下面是这些 Pattern 的规则，[]里的内容是可选的：

Pattern	规则
MethodPattern	[!] [@Annotation] [public,protected,private] [static] [final] 返回值类型 [类名.]方法名(参数类型列表) [throws 异常类型]
ConstructorPattern	[!] [@Annotation] [public,protected,private] [final] [类名.]new(参数类型列表) [throws 异常类型]
FieldPattern	[!] [@Annotation] [public,protected,private] [static] [final] 属性类型 [类名.]属性名
TypePattern	其他 Pattern 涉及到的类型规则也是一样，可以使用 '!'、''、'..'、'+'，'!' 表示取反，'' 匹配除 . 外的所有字符串，'*' 单独使用事表示匹配任意类型，'..' 匹配任意字符串，'..' 单独使用时表示匹配任意长度任意类型，'+' 匹配其自身及子类，还有一个 '...'表示不定个数
TypePattern 也可以使用 &&、|| 操作符，其他 Pointcut 更详细的语法说明，见官网文档 Pointcuts Language Semantics。




参考文献 
https://www.jianshu.com/p/9fb07b2596f7
https://www.jianshu.com/p/691acc98c0b8
