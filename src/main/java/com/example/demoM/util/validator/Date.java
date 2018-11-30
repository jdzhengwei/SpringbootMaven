package com.example.demoM.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=DateValidator.class)
public @interface Date {
     
    String message() default "日期错误或yyyy-MM-dd格式错误";
    
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
   
}  