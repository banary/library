package com.banary.annotation;

import java.lang.annotation.Repeatable;

/**
 * @Description
 * @Author eden
 * @Date 2018/10/23 上午10:26
 */
@Repeatable(Persons.class)
public @interface Person {

    String value() default "";
}
