package com.example.demoM.util.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeValidator implements ConstraintValidator<Time, Object> {  
   
    public boolean isValid(Object value, ConstraintValidatorContext arg1) {  
       if (value == null || value.toString().trim().isEmpty())  
           return true;
       
       String format14 = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";//yyyy-MM-dd HH:mm:ss
       String o = value.toString().trim();
       return Pattern.compile(format14).matcher(o).matches();
    }

	@Override
	public void initialize(Time constraintAnnotation) {
		
	}  
   
}  