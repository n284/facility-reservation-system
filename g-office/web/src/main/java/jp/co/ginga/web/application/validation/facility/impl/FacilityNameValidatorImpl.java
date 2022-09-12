package jp.co.ginga.web.application.validation.facility.impl;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import jp.co.ginga.web.application.validation.ValidationItem;
import jp.co.ginga.web.application.validation.facility.FacilityNameValidator;

public class FacilityNameValidatorImpl implements ConstraintValidator<FacilityNameValidator, Object> {

	@Autowired
	ValidationItem item;

	@Autowired
	MessageSource msg;

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String str = String.valueOf(value);

		if(this.item.isNull(str) || this.item.isEmpty(str)) {
			return this.item.createErrorWithMsg(this.msg.getMessage("Facility.required", null, Locale.JAPAN), context);
		}else if(this.item.isBlank(str)) {
			return this.item.createErrorWithMsg(this.msg.getMessage("Facility.blank", null, Locale.JAPAN), context);
		}else if(this.item.isFirstSpace(str)) {
			return this.item.createErrorWithMsg(this.msg.getMessage("Facility.blankFirst", null, Locale.JAPAN), context);
		}else if(this.item.isLastSpace(str)) {
			return this.item.createErrorWithMsg(this.msg.getMessage("Facility.blankLast", null, Locale.JAPAN), context);
		}else if(!this.item.isBetween(str, 1, 20)) {
			return this.item.createErrorWithMsg(this.msg.getMessage("Facility.range", new String[] {"1文字", "20文字"}, Locale.JAPAN), context);
		}else {
			return true;
		}
	}
}
