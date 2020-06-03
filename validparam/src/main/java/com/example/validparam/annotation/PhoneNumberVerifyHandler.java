package com.example.validparam.annotation;

import com.example.validparam.annotation.verify.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 手机号码规则校验
 * @author hcq
 * @date 2020/5/13 18:10
 */
public class PhoneNumberVerifyHandler implements ConstraintValidator<PhoneNumber, String> {

    private static final String REGEXP = "^\\d{11}$";

    @Override
    public boolean isValid(String  o, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(REGEXP, o);
    }

}
