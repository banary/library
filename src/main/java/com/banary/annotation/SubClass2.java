package com.banary.annotation;

import com.sun.istack.internal.NotNull;

/**
 * @Description
 * @Author eden
 * @Date 2018/10/23 上午10:04
 */
public class SubClass2 extends ParentClass2 {

    public int count(@NotNull String value){
        return 1;
    }
}
