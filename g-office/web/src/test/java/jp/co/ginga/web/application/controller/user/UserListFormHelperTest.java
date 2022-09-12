package jp.co.ginga.web.application.controller.user;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.ginga.web.application.controller.user.list.UserListForm;
import jp.co.ginga.web.application.controller.user.list.UserListHelper;
import jp.co.ginga.web.domain.service.user.UserManagerDto;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserListFormHelperTest {

	@Autowired
	UserListHelper userListHelper;

	/**
	 * createUserListFormメソッド正常系001
	 */
	@Test
	public void testCreateUserListForm_normal_001() {
		System.out.println("testCreateUserListForm_normal_001");

		// テストデータ
		String u_userId = "test";
		String u_userName = "テストユーザー";
		String u_password = "pass1";
		Timestamp u_passUpdateDate = null;
		String u_gender = "男";
		String u_birthday = "2021-04-01";
		String u_contact = "090-1234-5678";
		String u_mailAddress = "test@xxx.co.jp";
		int u_loginMissTimes = 0;
		boolean u_unlock = true;
		boolean u_enabled = true;
		Timestamp u_userDueDate = null;
		Timestamp u_insertDate = null;
		Timestamp u_updateDate = null;
		String ur_roleId = "tests";

		UserManagerDto umDto = UserManagerDto.getInstance();

		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();
		UserRoleDto urDto = new UserRoleDto(u_userId, ur_roleId);
		urDtoList.add(urDto);

		List<UserDto> uDtoList = new ArrayList<UserDto>();
		UserDto userDto = new UserDto(u_userId, u_userName, u_password, u_passUpdateDate, u_gender, u_birthday,
				u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate, u_insertDate,
				u_updateDate, urDtoList);
		uDtoList.add(userDto);

		umDto.setUserDtoList(uDtoList);
		umDto.setUserRoleDtoList(urDtoList);

		//テスト実施メソッド
		UserListForm result = userListHelper.createUserListForm(umDto);

		//検証処理
		assertEquals(1, result.getUserFormList().size());
		assertEquals(1, result.getUserRoleFormList().size());
		assertEquals(u_userId, result.getUserFormList().get(0).getUserId());
		assertEquals(u_userName, result.getUserFormList().get(0).getUserName());
		assertEquals(u_userId, result.getUserRoleFormList().get(0).getUserId());
		assertEquals(ur_roleId, result.getUserRoleFormList().get(0).getRoleId());

	}

	/**
	 * createUserListFormメソッド正常系002
	 */
	@Test
	public void testCreateUserListForm_normal_002() {
		System.out.println("testCreateUserListForm_normal_002");

		// テストデータ
		UserManagerDto umDto = UserManagerDto.getInstance();

		List<UserDto> uDtoList = new ArrayList<UserDto>();
		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();

		umDto.setUserDtoList(uDtoList);
		umDto.setUserRoleDtoList(urDtoList);

		//テスト実施メソッド
		UserListForm result = userListHelper.createUserListForm(umDto);

		assertEquals(0, result.getUserFormList().size());
		assertEquals(0, result.getUserRoleFormList().size());
		assertEquals("0件です。", result.getSysMsg());

	}

}
