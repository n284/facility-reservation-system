package jp.co.ginga.web.application.validation.user.impl;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import jp.co.ginga.web.application.validation.ValidationItem;
import jp.co.ginga.web.application.validation.user.UserPassWordValidator;

public class UserPassWordValidatorImpl implements ConstraintValidator<UserPassWordValidator, Object> {

	@Autowired
	ValidationItem item;

	@Autowired
	MessageSource msg;

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// バリデート対象となる値
		String str = (String) value;

		if (item.isNull(value) || item.isEmpty(str)) {
			return item.createErrorWithMsg(msg.getMessage("User.required", null, Locale.getDefault()), context);

		} else if (item.isBlank(str)) {
			return item.createErrorWithMsg(msg.getMessage("User.blank", null, Locale.getDefault()), context);

		} else if (item.isAlphaDigit(str) == false) {
			return item.createErrorWithMsg(msg.getMessage("User.alphaDigit", null, Locale.getDefault()), context);

		} else if (item.isBetween(str, 8, 20) == false) {
			return item.createErrorWithMsg(
					msg.getMessage("User.range", new String[] { "8", "20" }, Locale.getDefault()), context);

		}

		return true;
	}

}
