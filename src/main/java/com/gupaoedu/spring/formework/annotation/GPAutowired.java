package com.gupaoedu.spring.formework.annotation;

import java.lang.annotation.*;


/**
 * 自动注入
 * @author Tom
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPAutowired {
	String value() default "";
}
