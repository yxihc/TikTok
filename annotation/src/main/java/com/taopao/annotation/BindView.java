package com.taopao.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)//也就是编译时注解
@Target(ElementType.FIELD)
public @interface BindView {
    int value();
    // >>>>>> @Target
    // @Target说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、
    // 类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。
    // 在Annotation类型的声明中使用了target可更加明晰其修饰的目标。
    //作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
    //取值(ElementType)有：
    //1.CONSTRUCTOR:用于描述构造器
    //2.FIELD:用于描述域
    //3.LOCAL_VARIABLE:用于描述局部变量
    //4.METHOD:用于描述方法
    //5.PACKAGE:用于描述包
    //6.PARAMETER:用于描述参数
    //7.TYPE:用于描述类、接口(包括注解类型) 或enum声明


    // >>>>>> @Retention
//    SOURCE 被编译器忽略
//    CLASS 注解将会被保留在Class文件中，但在运行时并不会被VM保留。这是默认行为，所有没有用Retention注解的注解，都会采用这种策略。
////    RUNTIME 保留至运行时。所以我们可以通过反射去获取注解信息。
}
