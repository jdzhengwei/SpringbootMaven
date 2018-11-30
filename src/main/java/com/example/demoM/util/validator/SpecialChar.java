package com.example.demoM.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=SpecialCharValidator.class)
public @interface SpecialChar {

    String message() default " 包含非法特殊字符 ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
