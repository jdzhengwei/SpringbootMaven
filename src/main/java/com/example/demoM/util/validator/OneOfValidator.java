package com.example.demoM.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OneOfValidator implements ConstraintValidator<OneOf, Object> {  
	private String[] of;
	
    public void initialize(OneOf oneOf) {
    	of = oneOf.of().split(",");
    }  
   
    public boolean isValid(Object value, ConstraintValidatorContext arg1) {  
       if (value == null || value.toString().trim().isEmpty())  
           return true;  
       String v = value.toString().trim();
       for(String f : of){
    	   if(f.trim().equals(v)){
    		   return true;
    	   }
       }
       return false;
    }  
   
}  