package com.example.demo.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringFieldValidator implements ConstraintValidator<StringField, String> {
    private Boolean notEmpty;
    private Integer min;
    private Integer max;
    private String messageNotEmpty;
    private String pattern;
    private String messagePattern;
    private String message;
	private String messageLength;
    private String nomeField;
    
    @Override
    public void initialize(StringField field) {
    	nomeField=field.nomeField();
    	message = field.message();
        notEmpty = field.notEmpty();
        min = field.min();
        max = field.max();
        messageNotEmpty = field.messageNotEmpty();
        pattern=field.pattern().get();
        messagePattern=field.messagePattern();
        messageLength = field.messageLength();
    }
    
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
	    context.disableDefaultConstraintViolation();
	    if (notEmpty && value.isEmpty()) {
            context.buildConstraintViolationWithTemplate(messageNotEmpty).addConstraintViolation();
            return false;
        }
	    if (!value.matches(pattern)) {
	    	context.buildConstraintViolationWithTemplate(messagePattern).addConstraintViolation();
	    	return false;
	    }
		return true;
	}

}
