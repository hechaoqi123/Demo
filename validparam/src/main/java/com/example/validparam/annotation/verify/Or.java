package com.example.validparam.annotation.verify;

import com.example.validparam.annotation.ParamOrVerifyHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 声明于类上，传入字段名称列表，当字段传入有误或者字段列表全部为null时将抛出BindException
 *
 * @author hcq
 * @date 2020/5/13 17:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Repeatable(Ors.class)
@Documented
@Constraint(validatedBy = ParamOrVerifyHandler.class)
public @interface Or {


    String message() default "参数不合法";

    String[] value();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
