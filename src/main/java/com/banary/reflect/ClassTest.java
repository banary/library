package com.banary.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ClassTest {

    public static void main(String[] args) throws Exception{
        reflectEnum();
    }

    /**
     * 三种方式获取Class对象
     * 1. 调用对象的getClass方法
     * 2. 调用类名.class
     * 3. Class.forName("类的全名"), 这种方式是去JVM中查找，可能会抛出ClassNotFoundException
     */
    public static void getClassForPerson(){
        //1
        Class personClass1 = new Person().getClass();
        System.out.println(personClass1);

        //2
        Class personClass2 = Person.class;
        System.out.println(personClass2);

        //3
        try {
            Class personClass3 = Class.forName("com.banary.reflect.Person");
            System.out.println(personClass3);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void ClassMethodTest(){
        //获取全类名
        System.out.println(Person.class.getName());
        System.out.println(int.class.getName());
        System.out.println(int[][][].class.getName());
        System.out.println(Person.Card.class.getName());
        System.out.println(new Runnable() {
            @Override
            public void run() {
            }
        }.getClass().getName());

        Runnable runnable = ()-> System.out.println("test");
        System.out.println(runnable.getClass().getName());

        System.out.println("------------分割线------------");

        //获取类名
        System.out.println(Person.class.getSimpleName());
        System.out.println(int.class.getSimpleName());
        System.out.println(int[][][].class.getSimpleName());
        System.out.println(Person.Card.class.getSimpleName());
        System.out.println(new Runnable() {
            @Override
            public void run() {
            }
        }.getClass().getSimpleName());
        System.out.println(runnable.getClass().getSimpleName());

        System.out.println("------------分割线------------");

        /**
         * 获取官方类名
         * 1. getCanonicalName() 返回的也是全限定类名，但是对于内部类，不用 $ 开头，而用 .。
         * 2. getCanonicalName() 对于数组类型的 Class，同 simplename 一样直接在后面添加 [] 。
         * 3. getCanonicalName() 不同于 simplename 的地方是，不存在 canonicalName 的时候返回 null 而不是空字符串。
         * 4. 局部类和匿名内部类不存在 canonicalName。
         */
        System.out.println(Person.class.getCanonicalName());
        System.out.println(int.class.getCanonicalName());
        System.out.println(int[][][].class.getCanonicalName());
        System.out.println(Person.Card.class.getCanonicalName());
        System.out.println(new Runnable() {
            @Override
            public void run() {
            }
        }.getClass().getCanonicalName());
        System.out.println(runnable.getClass().getCanonicalName());
    }

    public static void getModifiers(){
        System.out.println(Modifier.class.getModifiers());
        System.out.println(Integer.toHexString(Modifier.class.getModifiers()));
        System.out.println(java.lang.reflect.Modifier.toString(Modifier.class.getModifiers()));
    }

    public static void getField(){
        Class personClass = SubPerson.class;
        //获取自身的所有的 public 属性，包括从父类继承下来的, 可能会抛出运行时异常SecurityException
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        System.out.println("------------分割线------------");

        //根据field名获取, 可能会抛出NoSuchFieldException(检查型), SecurityException
        try {
            Field field1 = personClass.getField("subName");
            System.out.println(field1.getName());
        } catch (NoSuchFieldException e){
            e.printStackTrace();
        }

        System.out.println("------------分割线------------");

        //获取所有的属性，但不包括从父类继承下来的属性
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }

        System.out.println("------------分割线------------");

        try{
            Field field2 = personClass.getDeclaredField("subName");
            System.out.println(field2.getType().getComponentType());
            System.out.println(field2.getName());
        } catch (NoSuchFieldException e){
            e.printStackTrace();
        }
    }

    public static void getConstructor(){
        Class subPersonClass = SubPerson.class;

        //获取所有的public构造器，由于父类的构造器不能继承，所以获取不到
        Constructor[] constructors = subPersonClass.getConstructors();
        for (Constructor c : constructors) {
            System.out.println(c.toString());
        }

        System.out.println("------------分割线------------");

        //获取所有的构造器，包括私有和公开的
        Constructor[] declaredConstructors = subPersonClass.getDeclaredConstructors();
        for (Constructor declaredConstructor :declaredConstructors) {
            System.out.println(declaredConstructor.toString());
        }
    }

    public static void reflectEnum() throws Exception{
        Class colorClass = Color.class;
        if(colorClass.isEnum()){
            System.out.println(String.format("class Color %s is Enum", colorClass.getName()));
            Field[] fields = colorClass.getDeclaredFields();
            for (Field field:fields) {
                if(field.isEnumConstant()){
                    System.out.println(String.format("%s is EnumConstant", field.getName()));
                }else{
                    System.out.println(String.format("%s is not EnumConstant", field.getName()));
                }
            }
        }

        System.out.println("_____________________");

        Constructor[] constructors = colorClass.getDeclaredConstructors();
        for(Constructor constructor : constructors){
            System.out.println(constructor.getName());
            constructor.setAccessible(true);
            Color color = (Color) constructor.newInstance("123123");
            System.out.println(color.getKey());
        }
        Color color = (Color) colorClass.newInstance();
        System.out.println(color.getKey());
    }
}
