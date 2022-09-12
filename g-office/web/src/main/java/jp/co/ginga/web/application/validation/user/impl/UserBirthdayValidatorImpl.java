package jp.co.ginga.web.application.validation.user.impl;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import jp.co.ginga.web.application.validation.ValidationItem;
import jp.co.ginga.web.application.validation.user.UserBirthdayValidator;

public class UserBirthdayValidatorImpl implements ConstraintValidator<UserBirthdayValidator, Object> {

	@Autowired
	ValidationItem item;

	@Autowired
	MessageSource msg;

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// バリデート対象となる値
		String str = (String) value;

		if (item.isBlank(str) || item.isEmpty(str)) {
			return true;

		} else if (item.isExistDate(str) == false) {
			return item.createErrorWithMsg(msg.getMessage("User.noDate", null, Locale.getDefault()), context);

		} else if (item.isPastDate(str) == false) {
			return item.createErrorWithMsg(msg.getMessage("User.FutureDate", null, Locale.getDefault()), context);

		}

		return true;
	}

}