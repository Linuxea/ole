package com.ole.core.annotation;

import java.lang.annotation.*;

/**
 * Created by Linuxea on 2017/8/21.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Column {
    String name() default "";
}
