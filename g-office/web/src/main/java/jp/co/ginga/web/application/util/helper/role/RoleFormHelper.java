package jp.co.ginga.web.application.util.helper.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.role.RoleForm;
import jp.co.ginga.web.application.util.form.userRole.UserRoleForm;
import jp.co.ginga.web.domain.service.util.dto.role.RoleDto;

@Component
public class RoleFormHelper {

	public List<RoleForm> createRoleFormList(List<RoleDto> roleDtoList) {

		List<RoleForm> roleFormList = new ArrayList<RoleForm>();
		for (RoleDto data : roleDtoList) {
			RoleForm roleForm = this.createRoleForm(data);
			roleFormList.add(roleForm);
		}

		return roleFormList;
	}

	public RoleForm createRoleForm(RoleDto roleDto) {

		RoleForm roleForm = new RoleForm(
				roleDto.getRoleId(),
				roleDto.getRoleName()

		);
		return roleForm;
	}

	public List<RoleDto> createRoleDtoList(List<RoleForm> roleFormList) {
		List<RoleDto> roleDtoList = new ArrayList<RoleDto>();
		for (RoleForm data : roleFormList) {
			RoleDto roleDto = this.createRoleDto(data);
			roleDtoList.add(roleDto);

		}
		return roleDtoList;

	}

	public RoleDto createRoleDto(RoleForm roleForm) {

		RoleDto roleDto = new RoleDto(
				roleForm.getRoleId(),
				roleForm.getRoleName());

		return roleDto;
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

	public UserRoleForm convertRole(RoleForm roleForm) {
		UserRoleForm userRoleForm = new UserRoleForm();

		userRoleForm.setRoleId(roleForm.getRoleId());

		return userRoleForm;
	}

	public List<RoleForm> convertUserRoleList(List<UserRoleForm> userRoleList) {

		List<RoleForm> roleList = new ArrayList<RoleForm>();

		for (UserRoleForm userRoleForm : userRoleList) {

			roleList.add(convertUserRole(userRoleForm));

		}
		return roleList;
	}

	public RoleForm convertUserRole(UserRoleForm userRoleForm) {
		RoleForm roleForm = new RoleForm();

		roleForm.setRoleId(userRoleForm.getRoleId());

		return roleForm;
	}
}
