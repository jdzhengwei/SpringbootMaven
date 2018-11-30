package com.example.demoM.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demoM.util.RegexUtil;

/**
 * 手机号码验证
 *
 * @author gexiangping
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone phone) {
        //sonar
    }

    public boolean isValid(String value, ConstraintValidatorContext arg1) {
        return RegexUtil.isPhone(value);
    }

}