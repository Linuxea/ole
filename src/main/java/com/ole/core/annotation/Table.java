package com.ole.core.annotation;

import java.lang.annotation.*;

/**
 * Created by Linuxea on 2017/8/21.
 * one Table one table
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
	String name() default "";
}
