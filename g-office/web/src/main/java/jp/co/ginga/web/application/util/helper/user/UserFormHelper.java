package jp.co.ginga.web.application.util.helper.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.user.UserForm;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;

@Component
public class UserFormHelper {

	public List<UserForm> createUserFormList(List<UserDto> userDtoList) {

		List<UserForm> userFormList = new ArrayList<UserForm>();
		for(UserDto data : userDtoList) {
			UserForm userForm = this.createUserForm(data);
			userFormList.add(userForm);

		}
		return userFormList;
	}


	public UserForm createUserForm(UserDto userDto) {

		UserForm userForm = new UserForm(
				userDto.getUserId(),
				userDto.getUserName(),
				userDto.getPassword(),
				"",
				userDto.getGender(),
				userDto.getBirthday(),
				userDto.getContact(),
				"",
				userDto.getMailAddress(),
				"",
				userDto.getUserRoleList()
				);
		return userForm;
	}


	public List<UserDto> createUserDtoList(List<UserForm> userFormList) {
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		for(UserForm data : userFormList) {
			UserDto userDto = this.createUserDto(data);
			userDtoList.add(userDto);

		}
		return userDtoList;
	}

	public UserDto createUserDto(UserForm userForm) {

		UserDto userDto = new UserDto(
				userForm.getUserId(),
				userForm.getUserName(),
				userForm.getPassword(),
				null,
				userForm.getGender(),
				userForm.getBirthday(),
				userForm.getContact(),
				userForm.getMailAddress(),
				0,
				false,
				false,
				null,
				null,
				null,
				userForm.getUserRoleList()
				);

		return userDto;
	}

}
