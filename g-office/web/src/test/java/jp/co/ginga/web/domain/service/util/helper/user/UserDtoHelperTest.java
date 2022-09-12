package jp.co.ginga.web.domain.service.util.helper.user;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserDtoHelperTest {

	/**
	 * テスト実施クラス
	 */
	@Autowired
	UserDtoHelper userDtoHelper;

	/**
	 * mapToUserDtoメソッド正常系001
	 */
	@Test
	public void testMapToUserDto_normal_001() {
		System.out.println("testMapToUserDto_normal_001");

		// テストデータ
		String datetime = "2019-05-01 01:02:03";

		String userId = "system3";
		String userName = "システム管理者3";
		String password = "pass3";
		Timestamp passUpdateDate = Timestamp.valueOf(datetime);
		String gender = "男";
		String birthday = "1月1日";
		String contact = "a";
		String mailAddress = "mail";
		int loginMissTimes = 1;
		boolean unlock = true;
		boolean enabled = true;
		Timestamp userDueDate = Timestamp.valueOf(datetime);
		Timestamp insertDate = Timestamp.valueOf(datetime);
		Timestamp updateDate = Timestamp.valueOf(datetime);

		UserEntity usEntity = new UserEntity(userId, userName, password, passUpdateDate, gender,
				birthday, contact, mailAddress, loginMissTimes, unlock, enabled, userDueDate, insertDate, updateDate);

		//テスト実施メソッド
		UserDto result = userDtoHelper.mapToUserDto(usEntity);

		//検証処理
		assertEquals(userId, result.getUserId());
		assertEquals(userName, result.getUserName());
		assertEquals(password, result.getPassword());
		assertEquals(passUpdateDate, result.getPassUpdateDate());
		assertEquals(gender, result.getGender());
		assertEquals(birthday, result.getBirthday());
		assertEquals(contact, result.getContact());
		assertEquals(mailAddress, result.getMailAddress());
		assertEquals(loginMissTimes, result.getLoginMissTimes());
		assertEquals(unlock, result.isUnlock());
		assertEquals(enabled, result.isEnabled());
		assertEquals(userDueDate, result.getUserDueDate());
		assertEquals(insertDate, result.getInsertDate());
		assertEquals(updateDate, result.getUpdateDate());

	}

	/**
	 * mapToUserDtoメソッド正常系002
	 */
	@Test
	public void testMapToUserDto_normal_002() {
		System.out.println("testMapToUserDto_normal_002");

		UserEntity usEntity = new UserEntity();

		//テスト実施メソッド
		UserDto result = userDtoHelper.mapToUserDto(usEntity);

		//検証処理
		assertEquals(null, result.getUserId());
		assertEquals(null, result.getUserName());
		assertEquals(null, result.getPassword());
		assertEquals(null, result.getPassUpdateDate());
		assertEquals(null, result.getGender());
		assertEquals(null, result.getBirthday());
		assertEquals(null, result.getContact());
		assertEquals(null, result.getMailAddress());
		assertEquals(0, result.getLoginMissTimes());
		assertEquals(false, result.isUnlock());
		assertEquals(false, result.isEnabled());
		assertEquals(null, result.getUserDueDate());
		assertEquals(null, result.getInsertDate());
		assertEquals(null, result.getUpdateDate());

	}

	/**
	 * mapToUserDtoListメソッド正常系001
	 */
	@Test
	public void testMapToUserDtoList_normal_001() {
		System.out.println("testMapToUserDtoList_normal_001");

		List<UserEntity> entityList = new ArrayList<UserEntity>();

		// テストデータ
		String datetime = "2019-05-01 01:02:03";

		String userId = "system3";
		String userName = "システム管理者3";
		String password = "pass3";
		Timestamp passUpdateDate = Timestamp.valueOf(datetime);
		String gender = "男";
		String birthday = "1月1日";
		String contact = "a";
		String mailAddress = "mail";
		int loginMissTimes = 1;
		boolean unlock = true;
		boolean enabled = true;
		Timestamp userDueDate = Timestamp.valueOf(datetime);
		Timestamp insertDate = Timestamp.valueOf(datetime);
		Timestamp updateDate = Timestamp.valueOf(datetime);

		UserEntity usEntity = new UserEntity(userId, userName, password, passUpdateDate, gender,
				birthday, contact, mailAddress, loginMissTimes, unlock, enabled, userDueDate, insertDate, updateDate);
		entityList.add(usEntity);

		//テスト実施メソッド
		List<UserDto> result = userDtoHelper.mapToUserDtoList(entityList);

		//検証処理
		assertEquals(1, result.size());
		assertEquals(userId, result.get(0).getUserId());
		assertEquals(userName, result.get(0).getUserName());
		assertEquals(password, result.get(0).getPassword());
		assertEquals(passUpdateDate, result.get(0).getPassUpdateDate());
		assertEquals(gender, result.get(0).getGender());
		assertEquals(birthday, result.get(0).getBirthday());
		assertEquals(contact, result.get(0).getContact());
		assertEquals(mailAddress, result.get(0).getMailAddress());
		assertEquals(loginMissTimes, result.get(0).getLoginMissTimes());
		assertEquals(unlock, result.get(0).isUnlock());
		assertEquals(enabled, result.get(0).isEnabled());
		assertEquals(userDueDate, result.get(0).getUserDueDate());
		assertEquals(insertDate, result.get(0).getInsertDate());
		assertEquals(updateDate, result.get(0).getUpdateDate());

	}

	/**
	 * mapToFacilityDtoListメソッド正常系2
	 * データが0件の場合
	 */
	@Test
	public void testMapToUserDtoList_normal_002() {
		System.out.println("testMapToUserDtoList_normal_002");

		// テストデータ
		List<UserEntity> entityList = new ArrayList<>();

		//テスト実施メソッド
		List<UserDto> result = userDtoHelper.mapToUserDtoList(entityList);

		//検証処理
		assertEquals(0, result.size());

	}

	/**
	 * mapToUserEntityメソッド正常系001
	 */
	@Test
	public void testMapToUserEntity_normal_001() {
		System.out.println("testMapToUserEntity_normal_001");

		// テストデータ
		String datetime = "2019-05-01 01:02:03";

		String userId = "system3";
		String userName = "システム管理者3";
		String password = "pass3";
		Timestamp passUpdateDate = Timestamp.valueOf(datetime);
		String gender = "男";
		String birthday = "1月1日";
		String contact = "a";
		String mailAddress = "mail";
		int loginMissTimes = 1;
		boolean unlock = true;
		boolean enabled = true;
		Timestamp userDueDate = Timestamp.valueOf(datetime);
		Timestamp insertDate = Timestamp.valueOf(datetime);
		Timestamp updateDate = Timestamp.valueOf(datetime);
		List<UserRoleDto> userRoleList = null;

		UserDto usDto = new UserDto(userId, userName, password, passUpdateDate, gender,
				birthday, contact, mailAddress, loginMissTimes, unlock, enabled, userDueDate, insertDate, updateDate,
				userRoleList);

		//テスト実施メソッド

		UserEntity result = userDtoHelper.mapToUserEntity(usDto);

		//検証処理
		assertEquals(userId, result.getUserId());
		assertEquals(userName, result.getUserName());
		assertEquals(password, result.getPassword());
		assertEquals(passUpdateDate, result.getPassUpdateDate());
		assertEquals(gender, result.getGender());
		assertEquals(birthday, result.getBirthday());
		assertEquals(contact, result.getContact());
		assertEquals(mailAddress, result.getMailAddress());
		assertEquals(loginMissTimes, result.getLoginMissTimes());
		assertEquals(unlock, result.isUnlock());
		assertEquals(enabled, result.isEnabled());
		assertEquals(userDueDate, result.getUserDueDate());
		assertEquals(insertDate, result.getInsertDate());
		assertEquals(updateDate, result.getUpdateDate());

	}

	/**
	 * mapToUserEntityメソッド正常系001
	 */
	@Test
	public void testMapToUserEntity_normal_002() {
		System.out.println("testMapToUserEntity_normal_002");

		UserDto usDto = new UserDto();

		//テスト実施メソッド

		UserEntity result = userDtoHelper.mapToUserEntity(usDto);

		//検証処理
		assertEquals(null, result.getUserId());
		assertEquals(null, result.getUserName());
		assertEquals(null, result.getPassword());
		assertEquals(null, result.getPassUpdateDate());
		assertEquals(null, result.getGender());
		assertEquals(null, result.getBirthday());
		assertEquals(null, result.getContact());
		assertEquals(null, result.getMailAddress());
		assertEquals(0, result.getLoginMissTimes());
		assertEquals(false, result.isUnlock());
		assertEquals(false, result.isEnabled());
		assertEquals(null, result.getUserDueDate());
		assertEquals(null, result.getInsertDate());
		assertEquals(null, result.getUpdateDate());

	}

	/**
	 * mapToUserEntityListメソッド正常系001
	 */
	@Test
	public void testMapToUserEntityList_normal_001() {
		System.out.println("testMapToUserEntityList_normal_001");

		List<UserDto> dtoList = new ArrayList<UserDto>();

		// テストデータ
		String datetime = "2019-05-01 01:02:03";

		String userId = "system3";
		String userName = "システム管理者3";
		String password = "pass3";
		Timestamp passUpdateDate = Timestamp.valueOf(datetime);
		String gender = "男";
		String birthday = "1月1日";
		String contact = "a";
		String mailAddress = "mail";
		int loginMissTimes = 1;
		boolean unlock = true;
		boolean enabled = true;
		Timestamp userDueDate = Timestamp.valueOf(datetime);
		Timestamp insertDate = Timestamp.valueOf(datetime);
		Timestamp updateDate = Timestamp.valueOf(datetime);
		List<UserRoleDto> userRoleList = null;

		UserDto usDto = new UserDto(userId, userName, password, passUpdateDate, gender,
				birthday, contact, mailAddress, loginMissTimes, unlock, enabled, userDueDate, insertDate, updateDate,
				userRoleList);
		dtoList.add(usDto);
		//テスト実施メソッド

		List<UserEntity> result = userDtoHelper.mapToUserEntityList(dtoList);

		//検証処理
		assertEquals(1, result.size());
		assertEquals(userId, result.get(0).getUserId());
		assertEquals(userName, result.get(0).getUserName());
		assertEquals(password, result.get(0).getPassword());
		assertEquals(passUpdateDate, result.get(0).getPassUpdateDate());
		assertEquals(gender, result.get(0).getGender());
		assertEquals(birthday, result.get(0).getBirthday());
		assertEquals(contact, result.get(0).getContact());
		assertEquals(mailAddress, result.get(0).getMailAddress());
		assertEquals(loginMissTimes, result.get(0).getLoginMissTimes());
		assertEquals(unlock, result.get(0).isUnlock());
		assertEquals(enabled, result.get(0).isEnabled());
		assertEquals(userDueDate, result.get(0).getUserDueDate());
		assertEquals(insertDate, result.get(0).getInsertDate());
		assertEquals(updateDate, result.get(0).getUpdateDate());

	}

	/**
	 * mapToUserEntityListメソッド正常系2
	 * データが0件の場合
	 */
	@Test
	public void testMapToUserEntityList_normal_002() {
		System.out.println("testMapToUserEntityList_normal_002");

		// テストデータ
		List<UserDto> dtoList = new ArrayList<>();

		//テスト実施メソッド
		List<UserEntity> result = userDtoHelper.mapToUserEntityList(dtoList);

		//検証処理
		assertEquals(0, result.size());

	}

}
