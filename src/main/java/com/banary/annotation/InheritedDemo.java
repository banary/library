package com.banary.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author eden
 * @Date 2018/10/23 上午9:26
 */
@Target({ElementType.TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface InheritedDemo {
}
