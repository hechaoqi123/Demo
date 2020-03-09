package com.example.event.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，用于打印入参
 * @author hcq
 * @date 2019/7/21 16:42
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ParamLog {
}
