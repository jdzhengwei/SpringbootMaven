package com.example.demoM.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharValidator implements ConstraintValidator<SpecialChar, Object> {

    private static final String REGEX = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    @Override
    public void initialize(SpecialChar constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null || value.toString().trim().isEmpty())
            return true;

        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(value.toString().trim());
        return !m.find();
    }
}
