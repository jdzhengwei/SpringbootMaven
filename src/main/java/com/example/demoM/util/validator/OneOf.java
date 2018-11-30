package com.example.demoM.util.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=OneOfValidator.class)  
public @interface OneOf {
     
    String message() default "必须是{of}中的一个";
    
    String of();
    
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
   
}  