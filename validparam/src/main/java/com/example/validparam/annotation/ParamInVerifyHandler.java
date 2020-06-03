package com.example.validparam.annotation;

import com.example.validparam.annotation.verify.In;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 参数校验
 * @author hcq
 * @date 2020/5/13 16:06
 */
public class ParamInVerifyHandler implements ConstraintValidator<In, Object> {

    private String[] values;

    @Override
    public void initialize(In constraintAnnotation) {
        this.values = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        for(String value : values){
            if(value.equals(o)){
                return true;
            }
        }
        return false;
    }

}
