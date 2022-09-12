package jp.co.ginga.web.application.controller.user.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.user.UserForm;
import jp.co.ginga.web.application.util.form.userRole.UserRoleForm;
import jp.co.ginga.web.application.util.helper.user.UserFormHelper;
import jp.co.ginga.web.application.util.helper.userrole.UserRoleFormHelper;
import jp.co.ginga.web.domain.service.user.UserManagerDto;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;

@Component
public class UserListHelper {

	@Autowired
	UserFormHelper userFormHelper;

	@Autowired
	UserRoleFormHelper userRoleFormHelper;

	public UserListForm createUserListForm(UserManagerDto dto) {

		UserListForm form = new UserListForm();

		List<UserDto> entityList = dto.getUserDtoList();
		List<UserRoleDto> useroRoleList = dto.getUserRoleDtoList();

		List<UserForm> formList = userFormHelper.createUserFormList(entityList);
		List<UserRoleForm> UserRoleForm = userRoleFormHelper.createUserRoleFormList(useroRoleList);

		form.setUserFormList(formList);
		form.setUserRoleFormList(UserRoleForm);
		//処理結果を元にメッセージの設定
		if (dto.getResult() == ServiceConst.NO_DATA) {
			form.setSysMsg("0件です。");
		}
		return form;
	}

}
