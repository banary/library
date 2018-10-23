package com.banary.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author eden
 * @Date 2018/10/23 上午10:14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Persons {

    Person[] value();
}
