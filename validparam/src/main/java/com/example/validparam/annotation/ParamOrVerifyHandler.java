package com.example.validparam.annotation;

import com.example.validparam.annotation.verify.Or;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * @author hcq
 * @date 2020/5/13 17:22
 */
@Slf4j
public class ParamOrVerifyHandler implements ConstraintValidator<Or, Object> {

    private String[] fields;


    @Override
    public void initialize(Or constraintAnnotation) {
        this.fields = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            for (String fieldName : fields) {
                Field field = o.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(o);
                if (fieldValue != null) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("参数校验异常：" + o);
            e.printStackTrace();
        }
        return false;
    }

}
