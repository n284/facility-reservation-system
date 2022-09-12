package jp.co.ginga.web.application.validation.user.impl;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import jp.co.ginga.web.application.validation.ValidationItem;
import jp.co.ginga.web.application.validation.user.UserMailAddressValidator;

public class UserMailAddressValidatorImpl implements ConstraintValidator<UserMailAddressValidator, Object> {

	@Autowired
	ValidationItem item;

	@Autowired
	MessageSource msg;

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// バリデート対象となる値
		String str = (String) value;

		if (item.isBlank(str)) {
			return item.createErrorWithMsg(msg.getMessage("User.blank", null, Locale.getDefault()), context);

		} else if (item.isEmpty(str)) {
			return true;

		} else if (item.isEmail(str) == false) {
			return item.createErrorWithMsg(msg.getMessage("User.mail", null, Locale.getDefault()), context);

		} else if (item.isBetween(str, 1, 50) == false) {
			return item.createErrorWithMsg(
					msg.getMessage("User.range", new String[] { "1", "50" }, Locale.getDefault()), context);
		}

		return true;
	}

}
