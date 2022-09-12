package jp.co.ginga.web.application.controller.user.complete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.user.UserForm;
import jp.co.ginga.web.application.util.helper.user.UserFormHelper;
import jp.co.ginga.web.application.util.session.user.UserSession;
import jp.co.ginga.web.domain.service.user.UserManagerDto;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;

@Component
public class UserCompleteHelper {

	@Autowired
	UserFormHelper userFormHelper;

	public UserManagerDto convertUserManagerDto(UserSession session) {

		UserManagerDto dto = UserManagerDto.getInstance();

		if (session.getUserForm() != null) {
			UserForm userForm = session.getUserForm();
			UserDto userDto = userFormHelper.createUserDto(userForm);
			dto.setUserDto(userDto);
		}

		if (session.getOptimisticRockValue() != null) {
			dto.setOptimisticRockValue(session.getOptimisticRockValue());
		}

		//dto.getUserDto().setUpdateUserDto(UserDto.getInstance());

		return dto;
	}

	/**
	 *
	 * @param dto
	 * @return
	 */
	public UserCompleteForm creatUserCompleteForm(UserManagerDto dto) {

		UserCompleteForm form = UserCompleteForm.getInstance();

		// 処理結果に伴い
		switch (dto.getResult()) {
		case 1:
			form.setSysMsg(null);
			break;
		case 2:
			form.setSysMsg(null);
			break;
		default:
			break;

		}

		return form;
	}
}