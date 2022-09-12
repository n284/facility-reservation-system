package jp.co.ginga.web.application.validation.facility.impl;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import jp.co.ginga.web.application.validation.ValidationItem;
import jp.co.ginga.web.application.validation.facility.FacilityCapacityValidator;

public class FacilityCapacityValidatorImpl implements ConstraintValidator<FacilityCapacityValidator, Object>{
	@Autowired
	ValidationItem item;

	@Autowired
	MessageSource msg;

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String str = String.valueOf(value);

		if(this.item.isNull(str) || this.item.isEmpty(str)) {
			return this.item.createErrorWithMsg(this.msg.getMessage("Facility.required", null, Locale.JAPAN), context);
		}else if(!this.item.isInteger(str)) {
			return this.item.createErrorWithMsg(this.msg.getMessage("Facility.halfNum", null, Locale.JAPAN), context);
		}else if(!this.item.isRange(str, 1, 1000)) {
			return this.item.createErrorWithMsg(this.msg.getMessage("Facility.range", new String[] {String.valueOf(1), String.valueOf(1000)}, Locale.JAPAN), context);
		}else {
			return true;
		}
	}
}
