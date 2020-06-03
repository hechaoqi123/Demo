package com.example.validparam.annotation.verify;

import com.example.validparam.annotation.ParamInVerifyHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义参数校验注解
 * IN（{xxx,xxx}）:代表参数只能在一个范围之间，
 * 否则ParamInValidHandler处理器将抛出BindException异常
 *
 * @author 86135
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
@Constraint(validatedBy = ParamInVerifyHandler.class)
public @interface In {

    String message() default "参数不合法";

    String[] value();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
