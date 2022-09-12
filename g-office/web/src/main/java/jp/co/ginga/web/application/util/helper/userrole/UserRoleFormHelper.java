package jp.co.ginga.web.application.util.helper.userrole;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.user.UserForm;
import jp.co.ginga.web.application.util.form.userRole.UserRoleForm;
import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;

@Component
public class UserRoleFormHelper {

	public List<UserRoleForm> createUserRoleFormList(List<UserRoleDto> userRoleDtoList) {

		List<UserRoleForm> userRoleFormList = new ArrayList<UserRoleForm>();
		for (UserRoleDto data : userRoleDtoList) {
			UserRoleForm userRoleForm = this.createUserRoleForm(data);
			userRoleFormList.add(userRoleForm);

		}
		return userRoleFormList;
	}

	public UserRoleForm createUserRoleForm(UserRoleDto userRoleDto) {

		UserRoleForm userRoleForm = new UserRoleForm(

				//userRoleDto.getId(),
				userRoleDto.getUserId(),
				userRoleDto.getRoleId()

		);
		return userRoleForm;
	}

	public List<UserRoleDto> createUserRoleDtoList(List<UserRoleForm> userRoleFormList) {

		List<UserRoleDto> userRoleDtoList = new ArrayList<UserRoleDto>();
		for (UserRoleForm data : userRoleFormList) {
			UserRoleDto UserRoleDto = this.createUserRoleDto(data);
			userRoleDtoList.add(UserRoleDto);

		}
		return userRoleDtoList;
	}

	public UserRoleDto createUserRoleDto(UserRoleForm userRoleForm) {

		UserRoleDto userRoleDto = new UserRoleDto(
				//userRoleForm.getId(),
				userRoleForm.getUserId(),
				userRoleForm.getRoleId()

		);
		return userRoleDto;

	}

	public List<UserRoleDto> createUserRoleDtoList(List<UserRoleForm> userRoleFormList, UserForm userForm) {

		List<UserRoleDto> userRoleDtoList = new ArrayList<UserRoleDto>();
		for (UserRoleForm data : userRoleFormList) {
			UserRoleDto UserRoleDto = this.createUserRoleDto(data, userForm);
			userRoleDtoList.add(UserRoleDto);

		}
		return userRoleDtoList;
	}

	public UserRoleDto createUserRoleDto(UserRoleForm userRoleForm, UserForm userForm) {

		UserRoleDto userRoleDto = new UserRoleDto(
				//userRoleForm.getId(),
				userForm.getUserId(),
				userRoleForm.getRoleId()

		);
		return userRoleDto;

	}

}
