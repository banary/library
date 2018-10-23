package com.banary.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Person {

    public Long id;
    private String name;

    public Person() {
    }

    class Card{

        private String id;

        public Card() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static void getWeiren(){
        System.out.println("This is a weiren");
    }

    public void getNiuren(){
        System.out.println("This is a niuren");
    }

    public static void main(String[] args) {
        Class personClass = Person.class;

        try {
            Method method = personClass.getDeclaredMethod("getWeiren");
            method.invoke(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("--------------万恶的分割线------------");

        try {
            Method method = personClass.getDeclaredMethod("getNiuren");
            method.invoke(new Person());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
