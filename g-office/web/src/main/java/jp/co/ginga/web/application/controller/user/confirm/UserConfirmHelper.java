package jp.co.ginga.web.application.controller.user.confirm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.controller.user.add.UserAddForm;
import jp.co.ginga.web.application.controller.user.detail.UserDetailForm;
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
public class UserConfirmHelper {

	//処理結果コード
	public static final int NO_UPDATE = 0;
	public static final int USERID_UPDATED = 1;
	public static final int USERNAME_UPDATED = 10;
	public static final int PASSWAOR_UPDATED = 100;
	public static final int GENDER_UPDATED = 1000;
	public static final int BIRTYDAY_UPDATED = 10000;
	public static final int CONTACT_UPDATED = 100000;
	public static final int MAILADDRESS_UPDATED = 1000000;

	@Autowired
	UserFormHelper userFormHelper;

	@Autowired
	UserRoleFormHelper userRoleFormHelper;

	@Autowired
	RoleFormHelper roleFormHelper;

	/**
	 * 確認画面Form生成処理
	 * @param userSession
	 * @return
	 */
	public UserConfirmForm createUserConfirmForm(UserSession userSession) {

		UserConfirmForm form = UserConfirmForm.getInstance();

		form.setUserForm(userSession.getUserForm());
		form.setUserRoleFormList(userSession.getUserRoleFormList());
		form.setRoleFormList(userSession.getRoleFormList());

		return form;
	}

	/**
	 * 確認画面Form生成処理
	 * @param facilitySession
	 * @return
	 */
	public UserConfirmForm createUserConfirmForm(UserManagerDto userManagerDto) {

		UserDto userDto = userManagerDto.getUserDto();
		//ユーザー権限Dtoリストの取得
		List<UserRoleDto> userRoleDtoList = userManagerDto.getUserRoleDtoList();
		//権限情報リストの取得
		List<RoleDto> roleDtoList = userManagerDto.getRoleDtoList();

		UserConfirmForm form = UserConfirmForm.getInstance();

		if (userDto != null) {
			UserForm userForm = userFormHelper.createUserForm(userDto);
			List<UserRoleForm> userRoleFormList = userRoleFormHelper.createUserRoleFormList(userRoleDtoList);
			List<RoleForm> roleFormList = roleFormHelper.createRoleFormList(roleDtoList);
			form.setUserForm(userForm);
			form.setUserRoleFormList(userRoleFormList);
			form.setRoleFormList(roleFormList);
		}

		return form;
	}

	/**
	 * ユーザー情報管理Dto生成処理
	 * @param inputForm
	 * @return
	 */
	public UserManagerDto createUserManagerServiceDto(UserAddForm inputForm) {

		UserForm userForm = inputForm.getUserForm();
		UserManagerDto userServiceDto = UserManagerDto.getInstance();

		UserDto userDto = userFormHelper.createUserDto(userForm);
		userServiceDto.setUserDto(userDto);

		return userServiceDto;

	}

	/**
	 * ユーザー情報管理Dto生成処理
	 * @param inputForm
	 * @return
	 */
	public UserManagerDto getUserManagerServiceDto(UserDetailForm inputForm) {

		UserForm userForm = inputForm.getUserForm();
		UserManagerDto userServiceDto = UserManagerDto.getInstance();

		UserDto userDto = userFormHelper.createUserDto(userForm);
		userServiceDto.setUserDto(userDto);

		return userServiceDto;

	}

	public UserRoleForm convertRole(RoleForm roleForm) {
		UserRoleForm userRoleForm = new UserRoleForm();

		userRoleForm.setRoleId(roleForm.getRoleId());

		return userRoleForm;
	}

	public List<UserRoleForm> convertRoleList(List<RoleForm> roleList) {

		List<UserRoleForm> userRoleList = new ArrayList<UserRoleForm>();

		for (RoleForm roleForm : roleList) {
			if (roleForm.getRoleId() != null) {
				userRoleList.add(convertRole(roleForm));
			}
		}
		return userRoleList;
	}

	/**
	 * ユーザー情報が更新されているかのチェック
	 * @param inputForm
	 * @param beforeUserForm
	 * @return
	 */
	public int checkUpdated(UserDetailForm inputForm, UserForm beforeUserForm) {

		return this.checkUpdated(inputForm.getUserForm(), beforeUserForm);
	}

	/**
	 * ユーザー情報が更新されているかのチェック
	 * @param userManagerDto
	 * @param beforeUserForm
	 * @return
	 */

	public int checkUpdated(UserManagerDto userManagerDto, UserForm beforeUserForm) {

		UserDto userDto = userManagerDto.getUserDto();
		UserForm userForm = userFormHelper.createUserForm(userDto);

		return this.checkUpdated(userForm, beforeUserForm);

	}

	/**
	 * ユーザー情報が更新されているかのチェック
	 * @param userForm
	 * @param beforeUserForm
	 * @return
	 */
	private int checkUpdated(UserForm userForm, UserForm beforeUserForm) {

		String userId = userForm.getUserId();
		String userName = userForm.getUserName();
		String password = userForm.getPassword();
		String gender = userForm.getGender();
		String birthday = userForm.getBirthday();
		String contact = userForm.getContact();
		String mailAddress = userForm.getMailAddress();

		String beforeUserId = beforeUserForm.getUserId();
		String beforeUserName = beforeUserForm.getUserName();
		String beforePassword = beforeUserForm.getPassword();
		String beforeGender = beforeUserForm.getGender();
		String beforeBirthday = beforeUserForm.getBirthday();
		String beforeContact = beforeUserForm.getContact();
		String beforeMailAddress = beforeUserForm.getMailAddress();

		int result = NO_UPDATE;

		if (userId.equals(beforeUserId) == false) {
			result += USERID_UPDATED;
		} else if (userName != null && userName.equals(beforeUserName) == false) {
			result += USERNAME_UPDATED;
		} else if (password != null && password.equals(beforePassword) == false) {
			result += PASSWAOR_UPDATED;
		} else if (gender != null && gender.equals(beforeGender) == false) {
			result += GENDER_UPDATED;
		} else if (birthday != null && birthday.equals(beforeBirthday) == false) {
			result += BIRTYDAY_UPDATED;
		} else if (contact != null && contact.equals(beforeContact) == false) {
			result += CONTACT_UPDATED;
		} else if (mailAddress != null && mailAddress.equals(beforeMailAddress) == false) {
			result += MAILADDRESS_UPDATED;
		}
		return result;
	}

}