package com.example.demoM.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 手机号码验证
 * @author gexiangping
 *
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=PhoneValidator.class)  
public @interface Phone {  

    String message() default "手机号码格式不正确";  
     
    Class<?>[] groups() default {};  
     
    Class<? extends Payload>[] payload() default {};  
}  