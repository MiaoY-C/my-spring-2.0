package com.gupaoedu.spring.formework.annotation;

import java.lang.annotation.*;

/**
 * 页面交互
 * @author Tom
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPController {
	String value() default "";
}
