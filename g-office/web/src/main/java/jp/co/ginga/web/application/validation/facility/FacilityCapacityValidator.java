package jp.co.ginga.web.application.validation.facility;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import jp.co.ginga.web.application.validation.facility.impl.FacilityCapacityValidatorImpl;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FacilityCapacityValidatorImpl.class})
@ReportAsSingleViolation
public @interface FacilityCapacityValidator {
	String message() default "";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
