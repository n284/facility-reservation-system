package jp.co.ginga.web.application.controller.user.detail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.role.RoleForm;
import jp.co.ginga.web.application.util.form.user.UserForm;
import jp.co.ginga.web.application.util.form.userRole.UserRoleForm;
import jp.co.ginga.web.application.util.helper.role.RoleFormHelper;
import jp.co.ginga.web.application.util.helper.user.UserFormHelper;
import jp.co.ginga.web.application.util.helper.userrole.UserRoleFormHelper;
import jp.co.ginga.web.application.util.session.user.UserSession;
import jp.co.ginga.web.domain.service.user.UserManagerDto;
import jp.co.ginga.web.domain.service.util.dto.role.RoleDto;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;

@Component
public class UserDetailHelper {
	@Autowired
	UserFormHelper userFormHelper;

	@Autowired
	UserRoleFormHelper userRoleFormHelper;

	@Autowired
	RoleFormHelper roleFormHelper;

	/**
	 * UserSessionデータからUserDetailFormオブジェクト取得処理
	 * @param userSession
	 * @return
	 */
	public UserDetailForm getUserDetailForm(UserSession userSession) {
		UserDetailForm form = UserDetailForm.getInstance();
		form.setUserForm(userSession.getUserForm());
		form.setUserRoleFormList(userSession.getUserRoleFormList());
		form.setRoleFormList(userSession.getRoleFormList());
		return form;
	}

	/**
	 * UserDetailFormオブジェクト生成 処理
	 * @param userManagerDto
	 * @param userRoleManagerDto
	 * @return
	 */
	public UserDetailForm createUserDetailForm(UserManagerDto userManagerDto) {
		//ユーザーDtoの取得
		UserDto userDto = userManagerDto.getUserDto();
		//ユーザー権限Dtoリストの取得
		List<UserRoleDto> userRoleDtoList = userManagerDto.getUserRoleDtoList();
		//権限情報リストの取得
		List<RoleDto> roleDtoList = userManagerDto.getRoleDtoList();
		//UserDetailFormのインスタンス取得
		UserDetailForm form = UserDetailForm.getInstance();

		//DtoからFormへのデータ変換作業
		UserForm userForm = userFormHelper.createUserForm(userDto);
		List<UserRoleForm> userRoleFormList = userRoleFormHelper.createUserRoleFormList(userRoleDtoList);
		List<RoleForm> roleFormList = roleFormHelper.createRoleFormList(roleDtoList);

		form.setUserForm(userForm);
		form.setUserRoleFormList(userRoleFormList);
		form.setRoleFormList(roleFormList);

		return form;
	}

}
