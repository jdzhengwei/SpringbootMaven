package com.example.demoM.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class DateValidator implements ConstraintValidator<Date, Object> {
   
    public boolean isValid(Object value, ConstraintValidatorContext arg1) {  
       if (value == null || value.toString().trim().isEmpty())  
           return true;
       
       String format14 = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";//yyyy-MM-dd
       String o = value.toString().trim();
       return Pattern.compile(format14).matcher(o).matches();
    }

	@Override
	public void initialize(Date constraintAnnotation) {
		
	}  
   
}  