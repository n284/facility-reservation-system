package jp.co.ginga.web.application.controller.user.add;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.role.RoleForm;
import jp.co.ginga.web.application.util.helper.user.UserFormHelper;
import jp.co.ginga.web.application.util.session.user.UserSession;
import jp.co.ginga.web.domain.service.user.UserManagerDto;
import jp.co.ginga.web.domain.service.util.dto.role.RoleDto;

@Component
public class UserAddHelper {

	/**
	 * ユーザー情報ヘルパー
	 */
	@Autowired
	UserFormHelper userFormHelper;

	/**
	 * UserManagerDtoからUserAddForm生成処理
	 * 
	 * @param dto
	 * @return
	 */
	public UserAddForm createUserAddForm(final UserManagerDto dto) {

		UserAddForm form = UserAddForm.getInstance();

		List<RoleDto> list = dto.getRoleDtoList();
		List<RoleForm> convertList = new ArrayList<RoleForm>();

		for (RoleDto data : list) {
			convertList.add(new RoleForm(data.getRoleId(), data.getRoleName()));
		}

		form.setRoleFormList(convertList);
		return form;
	}

	/**
	 * セッションオブジェクトからUserAddForm生成処理
	 * 
	 * @param session 施設情報セッション
	 * @return
	 */
	public UserAddForm createUserAddForm(final UserSession session) {

		UserAddForm form = UserAddForm.getInstance();
		form.setUserForm(session.getUserForm());
		form.setUserRoleFormList(session.getUserRoleFormList());
		form.setRoleFormList(session.getRoleFormList());

		return form;
	}
}
