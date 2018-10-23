package com.banary.cache.guava;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/17 下午2:11
 */
public class Main {

    public static void main(String[] args) {
        Other o = new Other();
        new Main().addOne(o);
        System.out.println(o.i);
    }

    public void addOne(final Other o){
        o.i++;
    }
}

class Other{
    public int i;
}
