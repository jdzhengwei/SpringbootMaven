package com.example.demoM.util.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=TimeValidator.class)  
public @interface Time {
     
    String message() default "时间错误或yyyy-MM-dd HH:mi:ss格式错误";
    
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
   
}  