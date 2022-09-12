package jp.co.ginga.web.application.validation.user.impl;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import jp.co.ginga.web.application.validation.ValidationItem;
import jp.co.ginga.web.application.validation.user.UserContactValidator;

public class UserContactValidatorImpl implements ConstraintValidator<UserContactValidator, Object> {

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

		} else if (item.isNumHyp(str) == false) {
			return item.createErrorWithMsg(msg.getMessage("User.tel", null, Locale.getDefault()), context);

		} else if (item.hasFullInt(str) == false) {
			return item.createErrorWithMsg(msg.getMessage("User.half", null, Locale.getDefault()), context);

		} else if (item.hasHyphen(str) == true) {
			return item.createErrorWithMsg(msg.getMessage("User.hyphen", null, Locale.getDefault()), context);

		} else if (item.isHalf(str) == false) {
			return item.createErrorWithMsg(msg.getMessage("User.half", null, Locale.getDefault()), context);

		} else if ((item.isPhoneNumber(str) || item.isMobilePhoneNumber(str)) == false) {
			return item.createErrorWithMsg(msg.getMessage("User.tel", null, Locale.getDefault()), context);
		}

		return true;
	}

}