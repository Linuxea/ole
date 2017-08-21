package com.ole.core.annotation;

import java.lang.annotation.*;

/**
 * Created by Linuxea on 2017/8/21.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE.METHOD})
public @interface Id {
	String name() default "id";
	Class type() default Integer.class;
}
