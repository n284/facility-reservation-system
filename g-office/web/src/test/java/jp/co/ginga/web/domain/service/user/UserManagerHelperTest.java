//package jp.co.ginga.web.domain.service.user;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.Timestamp;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import jp.co.ginga.infra.repository.role.RoleEntity;
//import jp.co.ginga.infra.repository.user.UserEntity;
//import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
//import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
//
///**
// * @author souken
// *
// */
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class UserManagerHelperTest {
//
//	@Autowired
//	UserManagerHelper userManagerHelper;
//
//	/**
//	 * 001
//	 * UserManagerDto生成処理
//	 * 引数　List<UserEntity> entityList
//	 * テストデータ複数件
//	 */
//	@Test
//	public void createUserServiceDtoMultipleData() {
//		System.out.println("createUserServiceDtoMultipleData");
//
//		List<UserEntity> entityList = new ArrayList<UserEntity>();
//
//		//テストデータ
//		String userId = "system";
//		String userName = "管理者";
//		String password = "passtest";
//		Timestamp passUpdateDate = null;
//		String gender = "男";
//		String birthday = "2021-04-01";
//		String contact = "090-1234-5678";
//		String mailAddress = "user1@xxx.co.jp";
//		int loginMissTime = 0;
//		boolean unlock = true;
//		boolean enabled = true;
//		Timestamp userDueDate = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate = null;
//
//		String userId1 = "user";
//		String userName1 = "一般ユーザー";
//		String password1 = "passtest";
//		Timestamp passUpdateDate1 = null;
//		String gender1 = "男";
//		String birthday1 = "2021-04-01";
//		String contact1 = "090-1234-5678";
//		String mailAddress1 = "user1@xxx.co.jp";
//		int loginMissTime1 = 0;
//		boolean unlock1 = true;
//		boolean enabled1 = true;
//		Timestamp userDueDate1 = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate1 = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate1 = null;
//
//		UserEntity entity = new UserEntity(
//				userId, userName, password, passUpdateDate, gender, birthday, contact, mailAddress, loginMissTime,
//				unlock, enabled, userDueDate, insertDate, updateDate);
//
//		UserEntity entity1 = new UserEntity(
//				userId1, userName1, password1, passUpdateDate1, gender1, birthday1, contact1, mailAddress1,
//				loginMissTime1, unlock1, enabled1, userDueDate1, insertDate1, updateDate1);
//
//		entityList.add(entity);
//		entityList.add(entity1);
//
//		//テスト対象メソッド
//		UserManagerDto dto = userManagerHelper.createUserServiceDto(entityList);
//
//		//検証処理
//		assertEquals(2, dto.getUserDtoList().size());
//		assertEquals(userId, dto.getUserDtoList().get(0).getUserId());
//		assertEquals(userName, dto.getUserDtoList().get(0).getUserName());
//		assertEquals(password, dto.getUserDtoList().get(0).getPassword());
//		assertEquals(passUpdateDate, dto.getUserDtoList().get(0).getPassUpdateDate());
//		assertEquals(gender, dto.getUserDtoList().get(0).getGender());
//		assertEquals(birthday, dto.getUserDtoList().get(0).getBirthday());
//		assertEquals(contact, dto.getUserDtoList().get(0).getContact());
//		assertEquals(mailAddress, dto.getUserDtoList().get(0).getMailAddress());
//		assertEquals(loginMissTime, dto.getUserDtoList().get(0).getLoginMissTimes());
//
//		assertEquals(userDueDate, dto.getUserDtoList().get(0).getUserDueDate());
//		assertEquals(insertDate, dto.getUserDtoList().get(0).getInsertDate());
//		assertEquals(updateDate, dto.getUserDtoList().get(0).getUpdateDate());
//
//		assertEquals(userId1, dto.getUserDtoList().get(1).getUserId());
//		assertEquals(userName1, dto.getUserDtoList().get(1).getUserName());
//		assertEquals(password1, dto.getUserDtoList().get(1).getPassword());
//		assertEquals(passUpdateDate1, dto.getUserDtoList().get(1).getPassUpdateDate());
//		assertEquals(gender1, dto.getUserDtoList().get(1).getGender());
//		assertEquals(birthday1, dto.getUserDtoList().get(1).getBirthday());
//		assertEquals(contact1, dto.getUserDtoList().get(1).getContact());
//		assertEquals(mailAddress1, dto.getUserDtoList().get(1).getMailAddress());
//		assertEquals(loginMissTime1, dto.getUserDtoList().get(1).getLoginMissTimes());
//
//		assertEquals(userDueDate1, dto.getUserDtoList().get(1).getUserDueDate());
//		assertEquals(insertDate1, dto.getUserDtoList().get(1).getInsertDate());
//		assertEquals(updateDate1, dto.getUserDtoList().get(1).getUpdateDate());
//
//	}
//
//	/**
//	 * 002
//	 * UserManagerDto生成処理
//	 * 引数　List<UserEntity> entityList
//	 * テストデータ1件
//	 * @throws ParseException
//	 */
//	@Test
//	public void createUserServiceDtSingleData() {
//		System.out.println("createUserServiceDtSingleData");
//
//		List<UserEntity> entityList = new ArrayList<UserEntity>();
//
//		//テストデータ
//		String userId = "system";
//		String userName = "管理者";
//		String password = "passtest";
//		Timestamp passUpdateDate = null;
//		String gender = "男";
//		String birthday = "2021-04-01";
//		String contact = "090-1234-5678";
//		String mailAddress = "user1@xxx.co.jp";
//		int loginMissTime = 0;
//		boolean unlock = true;
//		boolean enabled = true;
//		Timestamp userDueDate = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate = null;
//
//		UserEntity entity = new UserEntity(
//				userId, userName, password, passUpdateDate, gender, birthday, contact, mailAddress, loginMissTime,
//				unlock, enabled, userDueDate, insertDate, updateDate);
//
//		entityList.add(entity);
//
//		UserManagerDto dto = userManagerHelper.createUserServiceDto(entityList);
//
//		//検証処理
//		assertEquals(1, dto.getUserDtoList().size());
//		assertEquals(userId, dto.getUserDtoList().get(0).getUserId());
//		assertEquals(userName, dto.getUserDtoList().get(0).getUserName());
//		assertEquals(password, dto.getUserDtoList().get(0).getPassword());
//		assertEquals(passUpdateDate, dto.getUserDtoList().get(0).getPassUpdateDate());
//		assertEquals(gender, dto.getUserDtoList().get(0).getGender());
//		assertEquals(birthday, dto.getUserDtoList().get(0).getBirthday());
//		assertEquals(contact, dto.getUserDtoList().get(0).getContact());
//		assertEquals(mailAddress, dto.getUserDtoList().get(0).getMailAddress());
//		assertEquals(loginMissTime, dto.getUserDtoList().get(0).getLoginMissTimes());
//
//		assertEquals(userDueDate, dto.getUserDtoList().get(0).getUserDueDate());
//		assertEquals(insertDate, dto.getUserDtoList().get(0).getInsertDate());
//		assertEquals(updateDate, dto.getUserDtoList().get(0).getUpdateDate());
//
//	}
//
//	/**
//	 * 003
//	 * UserManagerDto生成処理
//	 * List<UserEntity> entityList
//	 * データなし
//	 */
//	@Test
//	public void createUserServiceDtoListNoData() {
//		System.out.println("createUserServiceDtoNoData");
//
//		List<UserEntity> entityList = new ArrayList<UserEntity>();
//
//		UserManagerDto dto = userManagerHelper.createUserServiceDto(entityList);
//
//		//検証処理
//		assertEquals(0, dto.getUserDtoList().size());
//	}
//
//	/**
//	 * 004
//	 * UserManagerDto生成処理
//	 * 引数　UserEntity entity, List<RoleEntity> entityList
//	 * UserEntityデータあり
//	 * List<RoleEntity>複数件
//	 */
//	@Test
//	public void createUserServiceDto_entity_data_roleList_multiple() {
//		System.out.println("createUserServiceDto_entity_roleList_multiple");
//
//		List<RoleEntity> list = new ArrayList<RoleEntity>();
//
//		//テストデータ
//		String userId = "system";
//		String userName = "管理者";
//		String password = "passtest";
//		Timestamp passUpdateDate = null;
//		String gender = "男";
//		String birthday = "2021-04-01";
//		String contact = "090-1234-5678";
//		String mailAddress = "user1@xxx.co.jp";
//		int loginMissTime = 0;
//		boolean unlock = true;
//		boolean enabled = true;
//		Timestamp userDueDate = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate = null;
//
//		String roleId = "admin";
//		String roleName = "ROLE_ADMIN";
//
//		String roleId1 = "general";
//		String roleName1 = "ROLE_GENERAL";
//
//		UserEntity entity = new UserEntity(
//				userId, userName, password, passUpdateDate, gender, birthday, contact, mailAddress, loginMissTime,
//				unlock, enabled, userDueDate, insertDate, updateDate);
//
//		RoleEntity role = new RoleEntity(roleId, roleName);
//		RoleEntity role1 = new RoleEntity(roleId1, roleName1);
//
//		list.add(role);
//		list.add(role1);
//
//		UserManagerDto dto = userManagerHelper.createUserServiceDto(entity, list);
//
//		//検証処理
//		assertEquals(userId, dto.getUserDto().getUserId());
//		assertEquals(userName, dto.getUserDto().getUserName());
//		assertEquals(password, dto.getUserDto().getPassword());
//		assertEquals(passUpdateDate, dto.getUserDto().getPassUpdateDate());
//		assertEquals(gender, dto.getUserDto().getGender());
//		assertEquals(birthday, dto.getUserDto().getBirthday());
//		assertEquals(contact, dto.getUserDto().getContact());
//		assertEquals(mailAddress, dto.getUserDto().getMailAddress());
//		assertEquals(loginMissTime, dto.getUserDto().getLoginMissTimes());
//		assertEquals(userDueDate, dto.getUserDto().getUserDueDate());
//		assertEquals(insertDate, dto.getUserDto().getInsertDate());
//		assertEquals(updateDate, dto.getUserDto().getUpdateDate());
//
//		assertEquals(roleId, dto.getRoleDtoList().get(0).getRoleId());
//		assertEquals(roleName, dto.getRoleDtoList().get(0).getRoleName());
//		assertEquals(roleId1, dto.getRoleDtoList().get(1).getRoleId());
//		assertEquals(roleName1, dto.getRoleDtoList().get(1).getRoleName());
//	}
//
//	/**
//	 * 005
//	 * UserManagerDto生成処理
//	 * 引数　UserEntity entity, List<RoleEntity> entityList
//	 * UserEntityデータあり
//	 * List<RoleEntity>1件
//	 */
//	@Test
//	public void createUserServiceDto_entity_data_roleList_single() {
//		System.out.println("createUserServiceDto_entity_data_roleList_single");
//
//		List<RoleEntity> list = new ArrayList<RoleEntity>();
//
//		//テストデータ
//		String userId = "system";
//		String userName = "管理者";
//		String password = "passtest";
//		Timestamp passUpdateDate = null;
//		String gender = "男";
//		String birthday = "2021-04-01";
//		String contact = "090-1234-5678";
//		String mailAddress = "user1@xxx.co.jp";
//		int loginMissTime = 0;
//		boolean unlock = true;
//		boolean enabled = true;
//		Timestamp userDueDate = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate = null;
//
//		String roleId = "admin";
//		String roleName = "ROLE_ADMIN";
//
//		UserEntity entity = new UserEntity(
//				userId, userName, password, passUpdateDate, gender, birthday, contact, mailAddress, loginMissTime,
//				unlock, enabled, userDueDate, insertDate, updateDate);
//
//		RoleEntity role = new RoleEntity(roleId, roleName);
//		list.add(role);
//
//		UserManagerDto dto = userManagerHelper.createUserServiceDto(entity, list);
//
//		//検証処理
//		assertEquals(userId, dto.getUserDto().getUserId());
//		assertEquals(userName, dto.getUserDto().getUserName());
//		assertEquals(password, dto.getUserDto().getPassword());
//		assertEquals(passUpdateDate, dto.getUserDto().getPassUpdateDate());
//		assertEquals(gender, dto.getUserDto().getGender());
//		assertEquals(birthday, dto.getUserDto().getBirthday());
//		assertEquals(contact, dto.getUserDto().getContact());
//		assertEquals(mailAddress, dto.getUserDto().getMailAddress());
//		assertEquals(loginMissTime, dto.getUserDto().getLoginMissTimes());
//		assertEquals(userDueDate, dto.getUserDto().getUserDueDate());
//		assertEquals(insertDate, dto.getUserDto().getInsertDate());
//		assertEquals(updateDate, dto.getUserDto().getUpdateDate());
//
//		assertEquals(1, dto.getRoleDtoList().size());
//		assertEquals(roleId, dto.getRoleDtoList().get(0).getRoleId());
//		assertEquals(roleName, dto.getRoleDtoList().get(0).getRoleName());
//
//	}
//
//	/**
//	 * 006
//	 * UserManagerDto生成処理
//	 * 引数　UserEntity entity, List<RoleEntity> entityList
//	 * UserEntityデータあり
//	 * List<RoleEntity>データなし
//	 */
//	@Test
//	public void createUserServiceDto_entity_data_roleList_noData() {
//		System.out.println("createUserServiceDto_entity_data_roleList_noData");
//
//		List<RoleEntity> list = new ArrayList<RoleEntity>();
//
//		//テストデータ
//		String userId = "system";
//		String userName = "管理者";
//		String password = "passtest";
//		Timestamp passUpdateDate = null;
//		String gender = "男";
//		String birthday = "2021-04-01";
//		String contact = "090-1234-5678";
//		String mailAddress = "user1@xxx.co.jp";
//		int loginMissTime = 0;
//		boolean unlock = true;
//		boolean enabled = true;
//		Timestamp userDueDate = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate = null;
//
//		UserEntity entity = new UserEntity(
//				userId, userName, password, passUpdateDate, gender, birthday, contact, mailAddress, loginMissTime,
//				unlock, enabled, userDueDate, insertDate, updateDate);
//
//		UserManagerDto dto = userManagerHelper.createUserServiceDto(entity, list);
//
//		//検証処理
//		assertEquals(userId, dto.getUserDto().getUserId());
//		assertEquals(userName, dto.getUserDto().getUserName());
//		assertEquals(password, dto.getUserDto().getPassword());
//		assertEquals(passUpdateDate, dto.getUserDto().getPassUpdateDate());
//		assertEquals(gender, dto.getUserDto().getGender());
//		assertEquals(birthday, dto.getUserDto().getBirthday());
//		assertEquals(contact, dto.getUserDto().getContact());
//		assertEquals(mailAddress, dto.getUserDto().getMailAddress());
//		assertEquals(loginMissTime, dto.getUserDto().getLoginMissTimes());
//		assertEquals(userDueDate, dto.getUserDto().getUserDueDate());
//		assertEquals(insertDate, dto.getUserDto().getInsertDate());
//		assertEquals(updateDate, dto.getUserDto().getUpdateDate());
//
//		assertEquals(0, dto.getRoleDtoList().size());
//
//	}
//
//	/**
//	 * 007
//	 * UserManagerDto生成処理
//	 * 引数　UserEntity entity, List<RoleEntity> entityList
//	 * UserEntityデータなし
//	 * List<RoleEntity>複数件
//	 */
//	@Test
//	public void createUserServiceDto_entity_noData_roleList_multiple() {
//		System.out.println("createUserServiceDto_entity_noData_roleList_multiple");
//
//		List<RoleEntity> list = new ArrayList<RoleEntity>();
//
//		//テストデータ
//		String roleId = "admin";
//		String roleName = "ROLE_ADMIN";
//
//		String roleId1 = "general";
//		String roleName1 = "ROLE_GENERAL";
//
//		RoleEntity role = new RoleEntity(roleId, roleName);
//		RoleEntity role1 = new RoleEntity(roleId1, roleName1);
//
//		list.add(role);
//		list.add(role1);
//
//		UserEntity entity = new UserEntity();
//		UserManagerDto dto = userManagerHelper.createUserServiceDto(entity, list);
//
//		//検証処理
//		assertEquals(null, dto.getUserDto().getUserId());
//		assertEquals(null, dto.getUserDto().getUserName());
//		assertEquals(null, dto.getUserDto().getPassword());
//		assertEquals(null, dto.getUserDto().getPassUpdateDate());
//		assertEquals(null, dto.getUserDto().getGender());
//		assertEquals(null, dto.getUserDto().getBirthday());
//		assertEquals(null, dto.getUserDto().getContact());
//		assertEquals(null, dto.getUserDto().getMailAddress());
//		assertEquals(0, dto.getUserDto().getLoginMissTimes());
//		assertEquals(null, dto.getUserDto().getUserDueDate());
//		assertEquals(null, dto.getUserDto().getInsertDate());
//		assertEquals(null, dto.getUserDto().getUpdateDate());
//
//		assertEquals(2, dto.getRoleDtoList().size());
//		assertEquals(roleId, dto.getRoleDtoList().get(0).getRoleId());
//		assertEquals(roleName, dto.getRoleDtoList().get(0).getRoleName());
//		assertEquals(roleId1, dto.getRoleDtoList().get(1).getRoleId());
//		assertEquals(roleName1, dto.getRoleDtoList().get(1).getRoleName());
//
//	}
//
//	/**
//	 * 008
//	 * UserManagerDto生成処理
//	 * 引数　UserEntity entity, List<RoleEntity> entityList
//	 * UserEntityデータなし
//	 * List<RoleEntity>1件
//	 */
//	@Test
//	public void createUserServiceDto_entity_noData_roleList_single() {
//		System.out.println("createUserServiceDto_entity_noData_roleList_single");
//
//		List<RoleEntity> list = new ArrayList<RoleEntity>();
//
//		//テストデータ
//		String roleId = "admin";
//		String roleName = "ROLE_ADMIN";
//
//		RoleEntity role = new RoleEntity(roleId, roleName);
//		list.add(role);
//
//		UserEntity entity = new UserEntity();
//		UserManagerDto dto = userManagerHelper.createUserServiceDto(entity, list);
//
//		//検証処理
//		assertEquals(null, dto.getUserDto().getUserId());
//		assertEquals(null, dto.getUserDto().getUserName());
//		assertEquals(null, dto.getUserDto().getPassword());
//		assertEquals(null, dto.getUserDto().getPassUpdateDate());
//		assertEquals(null, dto.getUserDto().getGender());
//		assertEquals(null, dto.getUserDto().getBirthday());
//		assertEquals(null, dto.getUserDto().getContact());
//		assertEquals(null, dto.getUserDto().getMailAddress());
//		assertEquals(0, dto.getUserDto().getLoginMissTimes());
//		assertEquals(null, dto.getUserDto().getUserDueDate());
//		assertEquals(null, dto.getUserDto().getInsertDate());
//		assertEquals(null, dto.getUserDto().getUpdateDate());
//
//		assertEquals(1, dto.getRoleDtoList().size());
//		assertEquals(roleId, dto.getRoleDtoList().get(0).getRoleId());
//		assertEquals(roleName, dto.getRoleDtoList().get(0).getRoleName());
//
//	}
//
//	/**
//	 * 009
//	 * UserManagerDto生成処理
//	 * 引数　UserEntity entity, List<RoleEntity> entityList
//	 * UserEntityデータなし
//	 * List<RoleEntity>データなし
//	 */
//	@Test
//	public void createUserServiceDto_entity_noData_roleList_noData() {
//		System.out.println("createUserServiceDto_entity_noData_roleList_noData");
//
//		List<RoleEntity> list = new ArrayList<RoleEntity>();
//
//		//テストデータ
//		UserEntity entity = new UserEntity();
//		UserManagerDto dto = userManagerHelper.createUserServiceDto(entity, list);
//
//		//検証処理
//		assertEquals(null, dto.getUserDto().getUserId());
//		assertEquals(null, dto.getUserDto().getUserName());
//		assertEquals(null, dto.getUserDto().getPassword());
//		assertEquals(null, dto.getUserDto().getPassUpdateDate());
//		assertEquals(null, dto.getUserDto().getGender());
//		assertEquals(null, dto.getUserDto().getBirthday());
//		assertEquals(null, dto.getUserDto().getContact());
//		assertEquals(null, dto.getUserDto().getMailAddress());
//		assertEquals(0, dto.getUserDto().getLoginMissTimes());
//		assertEquals(null, dto.getUserDto().getUserDueDate());
//		assertEquals(null, dto.getUserDto().getInsertDate());
//		assertEquals(null, dto.getUserDto().getUpdateDate());
//
//		assertEquals(0, dto.getRoleDtoList().size());
//
//	}
//
//	/**
//	 * 010
//	 * UserManagerDto生成処理
//	 * 引数　UserEntity entity
//	 * データあり
//	 */
//	@Test
//	public void createUserServiceDtoSingleData() {
//		System.out.println("createUserServiceDtoSingleData");
//
//		//テストデータ
//		String userId = "system";
//		String userName = "管理者";
//		String password = "passtest";
//		Timestamp passUpdateDate = null;
//		String gender = "男";
//		String birthday = "2021-04-01";
//		String contact = "090-1234-5678";
//		String mailAddress = "user1@xxx.co.jp";
//		int loginMissTime = 0;
//		boolean unlock = true;
//		boolean enabled = true;
//		Timestamp userDueDate = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate = null;
//
//		UserEntity entity = new UserEntity(
//				userId, userName, password, passUpdateDate, gender, birthday, contact, mailAddress, loginMissTime,
//				unlock, enabled, userDueDate, insertDate, updateDate);
//
//		UserManagerDto dto = userManagerHelper.createUserServiceDto(entity);
//
//		//検証処理
//		assertEquals(userId, dto.getUserDto().getUserId());
//		assertEquals(userName, dto.getUserDto().getUserName());
//		assertEquals(password, dto.getUserDto().getPassword());
//		assertEquals(passUpdateDate, dto.getUserDto().getPassUpdateDate());
//		assertEquals(gender, dto.getUserDto().getGender());
//		assertEquals(birthday, dto.getUserDto().getBirthday());
//		assertEquals(contact, dto.getUserDto().getContact());
//		assertEquals(mailAddress, dto.getUserDto().getMailAddress());
//		assertEquals(loginMissTime, dto.getUserDto().getLoginMissTimes());
//		assertEquals(userDueDate, dto.getUserDto().getUserDueDate());
//		assertEquals(insertDate, dto.getUserDto().getInsertDate());
//		assertEquals(updateDate, dto.getUserDto().getUpdateDate());
//	}
//
//	/**
//	 * 011
//	 * UserManagerDto生成処理
//	 * 引数　UserEntity entity
//	 * データなし
//	 */
//	@Test
//	public void createUserServiceDtoNoData() {
//		System.out.println("createUserServiceDtoNoData");
//
//		UserEntity entity = new UserEntity();
//		UserManagerDto dto = userManagerHelper.createUserServiceDto(entity);
//
//		//検証処理
//		assertEquals(null, dto.getUserDto().getUserId());
//		assertEquals(null, dto.getUserDto().getUserName());
//		assertEquals(null, dto.getUserDto().getPassword());
//		assertEquals(null, dto.getUserDto().getPassUpdateDate());
//		assertEquals(null, dto.getUserDto().getGender());
//		assertEquals(null, dto.getUserDto().getBirthday());
//		assertEquals(null, dto.getUserDto().getContact());
//		assertEquals(null, dto.getUserDto().getMailAddress());
//		assertEquals(0, dto.getUserDto().getLoginMissTimes());
//		assertEquals(null, dto.getUserDto().getUserDueDate());
//		assertEquals(null, dto.getUserDto().getInsertDate());
//		assertEquals(null, dto.getUserDto().getUpdateDate());
//	}
//
//	/**
//	 * 012
//	 * UserEntity生成処理
//	 * 引数　UserManagerDto dto
//	 * データあり
//	 */
//	@Test
//	public void getUserEntitySingleData() {
//		System.out.println("getUserEntitySingleData");
//
//		//テストデータ
//		String userId = "system";
//		String userName = "管理者";
//		String password = "passtest";
//		Timestamp passUpdateDate = null;
//		String gender = "男";
//		String birthday = "2021-04-01";
//		String contact = "090-1234-5678";
//		String mailAddress = "user1@xxx.co.jp";
//		int loginMissTime = 0;
//		boolean unlock = true;
//		boolean enabled = true;
//		Timestamp userDueDate = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate = null;
//
//		UserDto userDto = new UserDto(
//				userId, userName, password, passUpdateDate, gender, birthday, contact, mailAddress, loginMissTime,
//				unlock, enabled, userDueDate, insertDate, updateDate, null);
//
//		UserManagerDto dto = new UserManagerDto();
//		dto.setUserDto(userDto);
//
//		UserEntity entity = userManagerHelper.getUserEntity(dto);
//
//		//検証処理
//		assertEquals(userId, entity.getUserId());
//		assertEquals(userName, entity.getUserName());
//		assertEquals(password, entity.getPassword());
//		assertEquals(passUpdateDate, entity.getPassUpdateDate());
//		assertEquals(gender, entity.getGender());
//		assertEquals(birthday, entity.getBirthday());
//		assertEquals(contact, entity.getContact());
//		assertEquals(mailAddress, entity.getMailAddress());
//		assertEquals(loginMissTime, entity.getLoginMissTimes());
//		assertEquals(userDueDate, entity.getUserDueDate());
//		assertEquals(insertDate, entity.getInsertDate());
//		assertEquals(updateDate, entity.getUpdateDate());
//
//	}
//
//	/**
//	 * 013
//	 * UserEntity生成処理
//	 * 引数　UserManagerDto dto
//	 * データなし
//	 */
//	@Test
//	public void getUserEntityNoData() {
//		System.out.println("getUserEntityNoData");
//
//		UserDto userDto = new UserDto();
//
//		UserManagerDto dto = new UserManagerDto();
//		dto.setUserDto(userDto);
//
//		UserEntity entity = userManagerHelper.getUserEntity(dto);
//
//		//検証処理
//		assertEquals(null, entity.getUserId());
//		assertEquals(null, entity.getUserName());
//		assertEquals(null, entity.getPassword());
//		assertEquals(null, entity.getPassUpdateDate());
//		assertEquals(null, entity.getGender());
//		assertEquals(null, entity.getBirthday());
//		assertEquals(null, entity.getContact());
//		assertEquals(null, entity.getMailAddress());
//		assertEquals(0, entity.getLoginMissTimes());
//		assertEquals(null, entity.getUserDueDate());
//		assertEquals(null, entity.getInsertDate());
//		assertEquals(null, entity.getUpdateDate());
//	}
//
//	/**
//	 * 014
//	 * UserDto生成処理
//	 * 引数　UserEntity entity
//	 * データあり
//	 */
//	@Test
//	public void getUserDtoSingleData() {
//		System.out.println("getUserDtoSingleData");
//
//		//テストデータ
//		String userId = "system";
//		String userName = "管理者";
//		String password = "passtest";
//		Timestamp passUpdateDate = null;
//		String gender = "男";
//		String birthday = "2021-04-01";
//		String contact = "090-1234-5678";
//		String mailAddress = "user1@xxx.co.jp";
//		int loginMissTime = 0;
//		boolean unlock = true;
//		boolean enabled = true;
//		Timestamp userDueDate = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate = null;
//
//		UserEntity entity = new UserEntity(
//				userId, userName, password, passUpdateDate, gender, birthday, contact, mailAddress, loginMissTime,
//				unlock, enabled, userDueDate, insertDate, updateDate);
//
//		UserDto dto = userManagerHelper.getUserDto(entity);
//
//		//検証処理
//		assertEquals(userId, dto.getUserId());
//		assertEquals(userName, dto.getUserName());
//		assertEquals(password, dto.getPassword());
//		assertEquals(passUpdateDate, dto.getPassUpdateDate());
//		assertEquals(gender, dto.getGender());
//		assertEquals(birthday, dto.getBirthday());
//		assertEquals(contact, dto.getContact());
//		assertEquals(mailAddress, dto.getMailAddress());
//		assertEquals(loginMissTime, dto.getLoginMissTimes());
//		assertEquals(userDueDate, dto.getUserDueDate());
//		assertEquals(insertDate, dto.getInsertDate());
//		assertEquals(updateDate, dto.getUpdateDate());
//	}
//
//	/**
//	 * 015
//	 * UserDto生成処理
//	 * 引数　UserEntity entity
//	 * データなし
//	 */
//	@Test
//	public void getUserDtoNoData() {
//		System.out.println("getUserDtoNoData");
//
//		UserEntity entity = new UserEntity();
//		UserDto dto = userManagerHelper.getUserDto(entity);
//
//		//検証処理
//		assertEquals(null, dto.getUserId());
//		assertEquals(null, dto.getUserName());
//		assertEquals(null, dto.getPassword());
//		assertEquals(null, dto.getPassUpdateDate());
//		assertEquals(null, dto.getGender());
//		assertEquals(null, dto.getBirthday());
//		assertEquals(null, dto.getContact());
//		assertEquals(null, dto.getMailAddress());
//		assertEquals(0, dto.getLoginMissTimes());
//		assertEquals(null, dto.getUserDueDate());
//		assertEquals(null, dto.getInsertDate());
//		assertEquals(null, dto.getUpdateDate());
//
//	}
//
//	/**
//	 * 016
//	 * UserServiceDtoAdd生成処理
//	 * 引数　int result
//	 * result 1
//	 */
//	@Test
//	public void createUserServiceDtoAddResult_1() {
//		System.out.println("createUserServiceDtoAddResultData");
//
//		//テストデータ
//		int result = 1;
//
//		UserManagerDto dto = userManagerHelper.createUserServiceDtoAdd(result);
//
//		//検証処理
//		assertEquals(ServiceConst.THERE_IS_DATA, dto.getResult());
//
//	}
//
//	/**
//	 * 017
//	 * UserServiceDtoAdd生成処理
//	 * 引数　int result
//	 * result 2
//	 */
//	@Test
//	public void createUserServiceDtoAddResult_2() {
//		System.out.println("createUserServiceDtoAddResultData");
//
//		//テストデータ
//		int result = 2;
//
//		UserManagerDto dto = userManagerHelper.createUserServiceDtoAdd(result);
//
//		//検証処理
//		assertEquals(ServiceConst.NO_DATA, dto.getResult());
//
//	}
//
//	/**
//	 * 018
//	 * createUserServiceDtoAdd(int result)
//	 * result 0
//	 */
//	@Test
//	public void createUserServiceDtoAddResult_0() {
//		System.out.println("createUserServiceDtoAddResultData");
//
//		//テストデータ
//		int result = 0;
//
//		UserManagerDto dto = userManagerHelper.createUserServiceDtoAdd(result);
//
//		//検証処理
//		assertEquals(ServiceConst.ERROR, dto.getResult());
//	}
//
//	/**
//	 * 019
//	 * createUserServiceDtoAdd(int result)
//	 * result 3
//	 */
//	@Test
//	public void createUserServiceDtoAddResult_3() {
//		System.out.println("createUserServiceDtoAddResultData");
//
//		//テストデータ
//		int result = 3;
//
//		UserManagerDto dto = userManagerHelper.createUserServiceDtoAdd(result);
//
//		//検証処理
//		assertEquals(ServiceConst.ERROR, dto.getResult());
//
//	}
//
//	/**
//	 * 020
//	 * UserManagerDto生成処理
//	 * 引数　UserEntity userEntity
//	 * データあり
//	 */
//	@Test
//	public void createUserManagerServiceDtoData() {
//		System.out.println("createUserManagerServiceDtoData");
//
//		//テストデータ
//		String userId = "system";
//		String userName = "管理者";
//		String password = "passtest";
//		Timestamp passUpdateDate = null;
//		String gender = "男";
//		String birthday = "2021-04-01";
//		String contact = "090-1234-5678";
//		String mailAddress = "user1@xxx.co.jp";
//		int loginMissTime = 0;
//		boolean unlock = true;
//		boolean enabled = true;
//		Timestamp userDueDate = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate = null;
//
//		UserEntity entity = new UserEntity(
//				userId, userName, password, passUpdateDate, gender, birthday, contact, mailAddress, loginMissTime,
//				unlock, enabled, userDueDate, insertDate, updateDate);
//
//		UserManagerDto dto = userManagerHelper.createUserManagerServiceDto(entity);
//
//		//検証処理
//		assertEquals(userId, dto.getUserDto().getUserId());
//		assertEquals(userName, dto.getUserDto().getUserName());
//		assertEquals(password, dto.getUserDto().getPassword());
//		assertEquals(passUpdateDate, dto.getUserDto().getPassUpdateDate());
//		assertEquals(gender, dto.getUserDto().getGender());
//		assertEquals(birthday, dto.getUserDto().getBirthday());
//		assertEquals(contact, dto.getUserDto().getContact());
//		assertEquals(mailAddress, dto.getUserDto().getMailAddress());
//		assertEquals(loginMissTime, dto.getUserDto().getLoginMissTimes());
//		assertEquals(userDueDate, dto.getUserDto().getUserDueDate());
//		assertEquals(insertDate, dto.getUserDto().getInsertDate());
//		assertEquals(updateDate, dto.getUserDto().getUpdateDate());
//	}
//
//	/**
//	 * 021
//	 * UserManagerDto生成処理
//	 * 引数　UserEntity userEntity
//	 * データなし
//	 */
//	@Test
//	public void createUserManagerServiceDtoNoData() {
//		System.out.println("createUserManagerServiceDtoNoData");
//
//		UserEntity entity = new UserEntity();
//		UserManagerDto dto = userManagerHelper.createUserManagerServiceDto(entity);
//
//		//検証処理
//		assertEquals(null, dto.getUserDto().getUserId());
//		assertEquals(null, dto.getUserDto().getUserName());
//		assertEquals(null, dto.getUserDto().getPassword());
//		assertEquals(null, dto.getUserDto().getPassUpdateDate());
//		assertEquals(null, dto.getUserDto().getGender());
//		assertEquals(null, dto.getUserDto().getBirthday());
//		assertEquals(null, dto.getUserDto().getContact());
//		assertEquals(null, dto.getUserDto().getMailAddress());
//		assertEquals(0, dto.getUserDto().getLoginMissTimes());
//		assertEquals(null, dto.getUserDto().getUserDueDate());
//		assertEquals(null, dto.getUserDto().getInsertDate());
//		assertEquals(null, dto.getUserDto().getUpdateDate());
//
//	}
//
//	/**
//	 * 022
//	 * UserManagerDto生成処理
//	 * 引数　UserEntity userEntity
//	 * null
//	 */
//	@Test
//	public void createUserManagerServiceDtoNull() {
//		System.out.println("createUserManagerServiceDtoNull");
//
//		UserEntity entity = null;
//		UserManagerDto dto = userManagerHelper.createUserManagerServiceDto(entity);
//
//		//検証処理
//		assertEquals(ServiceConst.NO_DATA, dto.getResult());
//	}
//
//	/**
//	 * 023
//	 * UserDtoList生成処理
//	 * 引数　List<UserEntity> entityList
//	 * 複数件
//	 */
//	@Test
//	public void getUserListMultiple() {
//		System.out.println("getUserList_multiple");
//
//		List<UserEntity> list = new ArrayList<UserEntity>();
//
//		//テストデータ
//		String userId = "system";
//		String userName = "管理者";
//		String password = "passtest";
//		Timestamp passUpdateDate = null;
//		String gender = "男";
//		String birthday = "2021-04-01";
//		String contact = "090-1234-5678";
//		String mailAddress = "user1@xxx.co.jp";
//		int loginMissTime = 0;
//		boolean unlock = true;
//		boolean enabled = true;
//		Timestamp userDueDate = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate = null;
//
//		String userId1 = "user";
//		String userName1 = "一般ユーザー";
//		String password1 = "passtest";
//		Timestamp passUpdateDate1 = null;
//		String gender1 = "男";
//		String birthday1 = "2021-04-01";
//		String contact1 = "090-1234-5678";
//		String mailAddress1 = "user1@xxx.co.jp";
//		int loginMissTime1 = 0;
//		boolean unlock1 = true;
//		boolean enabled1 = true;
//		Timestamp userDueDate1 = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate1 = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate1 = null;
//
//		UserEntity entity = new UserEntity(
//				userId, userName, password, passUpdateDate, gender, birthday, contact, mailAddress, loginMissTime,
//				unlock, enabled, userDueDate, insertDate, updateDate);
//
//		UserEntity entity1 = new UserEntity(
//				userId1, userName1, password1, passUpdateDate1, gender1, birthday1, contact1, mailAddress1,
//				loginMissTime1, unlock1, enabled1, userDueDate1, insertDate1, updateDate1);
//
//		list.add(entity);
//		list.add(entity1);
//
//		List<UserDto> dtoList = userManagerHelper.getUserList(list);
//
//		//検証処理
//		assertEquals(2, dtoList.size());
//		assertEquals(userId, dtoList.get(0).getUserId());
//		assertEquals(userName, dtoList.get(0).getUserName());
//		assertEquals(password, dtoList.get(0).getPassword());
//		assertEquals(passUpdateDate, dtoList.get(0).getPassUpdateDate());
//		assertEquals(gender, dtoList.get(0).getGender());
//		assertEquals(birthday, dtoList.get(0).getBirthday());
//		assertEquals(contact, dtoList.get(0).getContact());
//		assertEquals(mailAddress, dtoList.get(0).getMailAddress());
//		assertEquals(loginMissTime, dtoList.get(0).getLoginMissTimes());
//		assertEquals(userDueDate, dtoList.get(0).getUserDueDate());
//		assertEquals(insertDate, dtoList.get(0).getInsertDate());
//		assertEquals(updateDate, dtoList.get(0).getUpdateDate());
//
//		assertEquals(userId1, dtoList.get(1).getUserId());
//		assertEquals(userName1, dtoList.get(1).getUserName());
//		assertEquals(password1, dtoList.get(1).getPassword());
//		assertEquals(passUpdateDate1, dtoList.get(1).getPassUpdateDate());
//		assertEquals(gender1, dtoList.get(1).getGender());
//		assertEquals(birthday1, dtoList.get(1).getBirthday());
//		assertEquals(contact1, dtoList.get(1).getContact());
//		assertEquals(mailAddress1, dtoList.get(1).getMailAddress());
//		assertEquals(loginMissTime1, dtoList.get(1).getLoginMissTimes());
//		assertEquals(userDueDate1, dtoList.get(1).getUserDueDate());
//		assertEquals(insertDate1, dtoList.get(1).getInsertDate());
//		assertEquals(updateDate1, dtoList.get(1).getUpdateDate());
//	}
//
//	/**
//	 * 024
//	 * UserDtoList生成処理
//	 * 引数　List<UserEntity> entityList
//	 * 1件
//	 */
//	@Test
//	public void getUserListSingle() {
//		System.out.println("getUserList_single");
//
//		List<UserEntity> list = new ArrayList<UserEntity>();
//
//		//テストデータ
//		String userId = "system";
//		String userName = "管理者";
//		String password = "passtest";
//		Timestamp passUpdateDate = null;
//		String gender = "男";
//		String birthday = "2021-04-01";
//		String contact = "090-1234-5678";
//		String mailAddress = "user1@xxx.co.jp";
//		int loginMissTime = 0;
//		boolean unlock = true;
//		boolean enabled = true;
//		Timestamp userDueDate = Timestamp.valueOf("2025-04-01 12:00:00");
//		Timestamp insertDate = Timestamp.valueOf("2000-04-01 12:00:00");
//		Timestamp updateDate = null;
//
//		UserEntity entity = new UserEntity(
//				userId, userName, password, passUpdateDate, gender, birthday, contact, mailAddress, loginMissTime,
//				unlock, enabled, userDueDate, insertDate, updateDate);
//
//		list.add(entity);
//
//		List<UserDto> dtoList = userManagerHelper.getUserList(list);
//
//		//検証処理
//		assertEquals(1, dtoList.size());
//		assertEquals(userId, dtoList.get(0).getUserId());
//		assertEquals(userName, dtoList.get(0).getUserName());
//		assertEquals(password, dtoList.get(0).getPassword());
//		assertEquals(passUpdateDate, dtoList.get(0).getPassUpdateDate());
//		assertEquals(gender, dtoList.get(0).getGender());
//		assertEquals(birthday, dtoList.get(0).getBirthday());
//		assertEquals(contact, dtoList.get(0).getContact());
//		assertEquals(mailAddress, dtoList.get(0).getMailAddress());
//		assertEquals(loginMissTime, dtoList.get(0).getLoginMissTimes());
//		assertEquals(userDueDate, dtoList.get(0).getUserDueDate());
//		assertEquals(insertDate, dtoList.get(0).getInsertDate());
//		assertEquals(updateDate, dtoList.get(0).getUpdateDate());
//	}
//
//	/**
//	 * 025
//	 *UserDtoList生成処理
//	 * 引数　List<UserEntity> entityList
//	 * データなし
//	 */
//	@Test
//	public void getUserListNoData() {
//		System.out.println("getUserListNoData");
//
//		List<UserEntity> list = new ArrayList<UserEntity>();
//
//		List<UserDto> dtoList = userManagerHelper.getUserList(list);
//
//		//検証処理
//		assertEquals(0, dtoList.size());
//
//	}
//
//}
