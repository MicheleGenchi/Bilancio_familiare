package com.example.demo.model;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;

@Retention(RUNTIME)
@Target(FIELD)
@ReportAsSingleViolation
@Constraint(validatedBy = StringFieldValidator.class)
public @interface StringField {

	String nomeField() default "campo";
	
    String message() default "Altro tipo di errore";

    String messageNotEmpty() default "Il campo non può essere vuoto";

    String messageLength() default "Errore lunghezza del campo";
    
    PATTERN pattern() default PATTERN.no_pattern;
    
    String messagePattern() default "";
    
    boolean notEmpty() default false;

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
