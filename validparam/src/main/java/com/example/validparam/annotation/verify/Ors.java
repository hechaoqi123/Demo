package com.example.validparam.annotation.verify;

import java.lang.annotation.*;

/**
 * 此注解用于辅助Or注解、使其可以在同一个对象上声明多次
 *
 * @author hcq
 * @date 2020/5/13 17:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface Ors {

    /**
     * 字段名列表
     */
    Or[] value();

}
