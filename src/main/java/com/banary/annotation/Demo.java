package com.banary.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author eden
 * @Date 2018/10/23 上午10:00
 */
@Target({ElementType.TYPE, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Demo {
}
