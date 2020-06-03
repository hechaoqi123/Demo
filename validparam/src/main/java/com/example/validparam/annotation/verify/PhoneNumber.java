package com.example.validparam.annotation.verify;

import com.example.validparam.annotation.PhoneNumberVerifyHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 手机号码规则校验
 *
 * @author hcq
 * @date 2020/5/13 17:45
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = PhoneNumberVerifyHandler.class)
public @interface PhoneNumber {

    String message() default "手机号码格式有误";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
