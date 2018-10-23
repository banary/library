package com.banary.annotation;

/**
 * @Description
 * @Author eden
 * @Date 2018/10/23 上午10:02
 */
public class AnnotationTest {

    public static void main(String[] args) {
        Class<SubClass1> subClass1 = SubClass1.class;
        System.out.println(subClass1.isAnnotationPresent(Demo.class));
        System.out.println(subClass1.getAnnotations());

        System.out.println("-------分割线--------");

        Class<SubClass2> subClass2 = SubClass2.class;
        System.out.println(subClass2.isAnnotationPresent(InheritedDemo.class));
        System.out.println(subClass2.getAnnotations());

        System.out.println("-------分割线--------");

        // 此处可见@Repeatable注解声明在容器内元素的注解上，最终在jvm解析成对应的容器注解
        Class<PersonClass> personClass = PersonClass.class;
        System.out.println(personClass.isAnnotationPresent(Persons.class));
        System.out.println(personClass.isAnnotationPresent(Person.class));
        System.out.println(personClass.getAnnotations().length);

        System.out.println("-------分割线--------");

        // 此处可见@Repeatable对应的容器注解可以被子类继承，在容器注解上加@Inherited
        Class<SubPersonClass> personClassClass = SubPersonClass.class;
        System.out.println(personClassClass.isAnnotationPresent(Persons.class));
        System.out.println(personClassClass.isAnnotationPresent(Person.class));
        System.out.println(personClassClass.getAnnotations().length);
    }
}
