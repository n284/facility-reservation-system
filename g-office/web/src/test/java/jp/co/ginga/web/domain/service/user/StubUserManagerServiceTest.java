package jp.co.ginga.web.domain.service.user;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.ginga.infra.repository.role.RoleEntity;
import jp.co.ginga.infra.repository.role.RoleRepository;
import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.infra.repository.user.UserRepository;
import jp.co.ginga.infra.repository.userrole.UserRoleEntity;
import jp.co.ginga.infra.repository.userrole.UserRoleRepository;
import jp.co.ginga.web.domain.service.stub.StubRoleRepository;
import jp.co.ginga.web.domain.service.stub.StubUserRepository;
import jp.co.ginga.web.domain.service.stub.StubUserRoleRepository;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StubUserManagerServiceTest {

	/**
	 * テスト実施クラス
	 */
	@Autowired
	UserManagerService service;

	// テストデータ
	private String u_userId = "test";
	private String u_userName = "テストユーザー";
	private String u_password = "pass1";
	private Timestamp u_passUpdateDate = null;
	private String u_gender = "男";
	private String u_birthday = "2021-04-01";
	private String u_contact = "090-1234-5678";
	private String u_mailAddress = "test@xxx.co.jp";
	private int u_loginMissTimes = 0;
	private boolean u_unlock = true;
	private boolean u_enabled = true;
	private Timestamp u_userDueDate = null;
	private Timestamp u_insertDate = null;
	private Timestamp u_updateDate = null;
	private String r_roleId = "tests";
	private String r_roleName = "ROLE_Test";
	private String ur_userId = "test";
	private String ur_roleId = "tests";
	private String ur_roleId1 = "tests2";
	// private String oldUser = "testOld";

	/**
	 * getUserList(); 正常系001 リストサイズ１
	 */
	@Test
	public void testGetUserList_normal_001() {
		System.out.println("testGetUserList_normal_001");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public List<UserEntity> findAll() {

				List<UserEntity> userEntityList = new ArrayList<UserEntity>();
				UserEntity userEntity = new UserEntity(u_userId, u_userName, u_password, u_passUpdateDate, u_gender,
						u_birthday, u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate,
						u_insertDate, u_updateDate);
				userEntityList.add(userEntity);

				return userEntityList;
			}
		};
		// テスト用のUserRoleRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {
			// スタブのメソッドを書き換え
			@Override
			public List<UserRoleEntity> findAll() {

				List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
				UserRoleEntity userRoleEntity = new UserRoleEntity(ur_userId, ur_roleId);
				userRoleEntityList.add(userRoleEntity);

				return userRoleEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.getUserList();

		// テスト検証メソッド
		assertEquals(1, result.getUserDtoList().size());
		assertEquals(u_userId, result.getUserDtoList().get(0).getUserId());
		assertEquals(u_userName, result.getUserDtoList().get(0).getUserName());
		assertEquals(u_password, result.getUserDtoList().get(0).getPassword());
		assertEquals(u_passUpdateDate, result.getUserDtoList().get(0).getPassUpdateDate());
		assertEquals(u_gender, result.getUserDtoList().get(0).getGender());
		assertEquals(u_birthday, result.getUserDtoList().get(0).getBirthday());
		assertEquals(u_contact, result.getUserDtoList().get(0).getContact());
		assertEquals(u_mailAddress, result.getUserDtoList().get(0).getMailAddress());
		assertEquals(u_loginMissTimes, result.getUserDtoList().get(0).getLoginMissTimes());
		assertEquals(u_userDueDate, result.getUserDtoList().get(0).getUserDueDate());
		assertEquals(u_insertDate, result.getUserDtoList().get(0).getInsertDate());
		assertEquals(u_updateDate, result.getUserDtoList().get(0).getUpdateDate());

		assertEquals(1, result.getUserRoleDtoList().size());
		assertEquals(ur_userId, result.getUserRoleDtoList().get(0).getUserId());
		assertEquals(ur_roleId, result.getUserRoleDtoList().get(0).getRoleId());

	}

	/**
	 * getUserList(); 正常系002 データなし
	 */
	@Test
	public void testGetUserList_normal_002() {
		System.out.println("testGetUserList_normal_002");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public List<UserEntity> findAll() {

				List<UserEntity> userEntityList = new ArrayList<UserEntity>();

				return userEntityList;
			}
		};
		// テスト用のUserRoleRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {
			// スタブのメソッドを書き換え
			@Override
			public List<UserRoleEntity> findAll() {

				List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();

				return userRoleEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.getUserList();

		assertEquals(0, result.getUserDtoList().size());
		assertEquals(0, result.getUserRoleDtoList().size());

	}

	/**
	 * getRoleList(); 正常系001 リストサイズ１
	 */
	@Test
	public void testGetRoleList_normal_001() {
		System.out.println("testGetRoleList_normal_001");

		// テスト用のRoleRepositoryスタブをインスタンス化
		RoleRepository roleRepository = new StubRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public List<RoleEntity> findAll() {

				List<RoleEntity> roleEntityList = new ArrayList<RoleEntity>();
				RoleEntity roleEntity = new RoleEntity(r_roleId, r_roleName);
				roleEntityList.add(roleEntity);

				return roleEntityList;
			}
		};

		service.setRoleRepository(roleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.getRoleList();

		// テスト検証メソッド
		assertEquals(1, result.getRoleDtoList().size());
		assertEquals(r_roleId, result.getRoleDtoList().get(0).getRoleId());
		assertEquals(r_roleName, result.getRoleDtoList().get(0).getRoleName());

	}

	/**
	 * getRoleList(); 正常系 002データなし
	 */
	@Test
	public void testGetRoleList_normal_002() {
		System.out.println("testGetRoleList_normal_002");

		// テスト用のRoleRepositoryスタブをインスタンス化
		RoleRepository roleRepository = new StubRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public List<RoleEntity> findAll() {

				List<RoleEntity> roleEntityList = new ArrayList<RoleEntity>();

				return roleEntityList;
			}
		};

		service.setRoleRepository(roleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.getRoleList();

		// テスト検証メソッド
		assertEquals(0, result.getRoleDtoList().size());

	}

	/**
	 * getUser(String userId); 正常系 001 データあり
	 */
	@Test
	public void testGetUser_normal_001() {
		System.out.println("testGetUser_normal_001");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public UserEntity findByUserId(String userId) {

				UserEntity userEntity = new UserEntity(u_userId, u_userName, u_password, u_passUpdateDate, u_gender,
						u_birthday, u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate,
						u_insertDate, u_updateDate);

				return userEntity;
			}
		};

		// テスト用のUserRoleRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public List<UserRoleEntity> findByUserId(String userId) {

				List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
				UserRoleEntity userRoleEntity = new UserRoleEntity(u_userId, ur_roleId);
				userRoleEntityList.add(userRoleEntity);

				return userRoleEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.getUser(u_userId);

		// 検証メソッド
		assertEquals(u_userId, result.getUserDto().getUserId());
		assertEquals(u_userName, result.getUserDto().getUserName());
		assertEquals(u_password, result.getUserDto().getPassword());
		assertEquals(u_passUpdateDate, result.getUserDto().getPassUpdateDate());
		assertEquals(u_gender, result.getUserDto().getGender());
		assertEquals(u_birthday, result.getUserDto().getBirthday());
		assertEquals(u_contact, result.getUserDto().getContact());
		assertEquals(u_mailAddress, result.getUserDto().getMailAddress());
		assertEquals(u_loginMissTimes, result.getUserDto().getLoginMissTimes());
		assertEquals(u_userDueDate, result.getUserDto().getUserDueDate());
		assertEquals(u_insertDate, result.getUserDto().getInsertDate());
		assertEquals(u_updateDate, result.getUserDto().getUpdateDate());

		assertEquals(1, result.getUserRoleDtoList().size());
		assertEquals(u_userId, result.getUserRoleDtoList().get(0).getUserId());
		assertEquals(ur_roleId, result.getUserRoleDtoList().get(0).getRoleId());
	}

	/**
	 * getUser(String userId); 正常系 002 データなし
	 */
	@Test
	public void testGetUser_normal_002() {
		System.out.println("testGetUser_normal_002");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public UserEntity findByUserId(String userId) {

				UserEntity userEntity = new UserEntity();

				return userEntity;
			}
		};

		// テスト用のUserRoleRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public List<UserRoleEntity> findByUserId(String userId) {

				List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();

				return userRoleEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.getUser(u_userId);

		// 検証メソッド
		assertNull(result.getUserDto().getUserId());
		assertNull(result.getUserDto().getUserId());
		assertNull(result.getUserDto().getUserName());
		assertNull(result.getUserDto().getPassword());
		assertNull(result.getUserDto().getPassUpdateDate());
		assertNull(result.getUserDto().getGender());
		assertNull(result.getUserDto().getBirthday());
		assertNull(result.getUserDto().getContact());
		assertNull(result.getUserDto().getMailAddress());
		assertEquals(0, result.getUserDto().getLoginMissTimes());
		assertNull(result.getUserDto().getUserDueDate());
		assertNull(result.getUserDto().getInsertDate());
		assertNull(result.getUserDto().getUpdateDate());

		assertEquals(0, result.getUserRoleDtoList().size());

	}

	/**
	 * getUserListByUserId(String userId); 正常系001データあり
	 */
	@Test
	public void testGetUserListByUserId_normal_001() {
		System.out.println("testGetUserListByUserId_normal_001");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public List<UserEntity> getUserIdByUserId(String userId) {

				List<UserEntity> userEntityList = new ArrayList<UserEntity>();
				UserEntity userEntity = new UserEntity(u_userId, u_userName, u_password, u_passUpdateDate, u_gender,
						u_birthday, u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate,
						u_insertDate, u_updateDate);
				userEntityList.add(userEntity);

				return userEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);

		// テスト実施メソッド
		UserManagerDto result = service.getUserListByUserId(u_userId);

		// テスト検証メソッド
		assertEquals(ServiceConst.THERE_IS_DATA, result.getResult());

		assertEquals(1, result.getUserDtoList().size());
		assertEquals(u_userId, result.getUserDtoList().get(0).getUserId());
		assertEquals(u_userName, result.getUserDtoList().get(0).getUserName());
		assertEquals(u_password, result.getUserDtoList().get(0).getPassword());
		assertEquals(u_passUpdateDate, result.getUserDtoList().get(0).getPassUpdateDate());
		assertEquals(u_gender, result.getUserDtoList().get(0).getGender());
		assertEquals(u_birthday, result.getUserDtoList().get(0).getBirthday());
		assertEquals(u_contact, result.getUserDtoList().get(0).getContact());
		assertEquals(u_mailAddress, result.getUserDtoList().get(0).getMailAddress());
		assertEquals(u_loginMissTimes, result.getUserDtoList().get(0).getLoginMissTimes());
		assertEquals(u_userDueDate, result.getUserDtoList().get(0).getUserDueDate());
		assertEquals(u_insertDate, result.getUserDtoList().get(0).getInsertDate());
		assertEquals(u_updateDate, result.getUserDtoList().get(0).getUpdateDate());

	}

	/**
	 * getUserListByUserId(String userId); 正常系002データなし
	 */
	@Test
	public void testGetUserListByUserId_normal_002() {
		System.out.println("testGetUserListByUserId_normal_002");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public List<UserEntity> getUserIdByUserId(String userId) {

				List<UserEntity> userEntityList = new ArrayList<UserEntity>();

				return userEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);

		// テスト実施メソッド
		UserManagerDto result = service.getUserListByUserId(u_userId);

		// テスト検証メソッド
		assertEquals(ServiceConst.NO_DATA, result.getResult());
		assertEquals(0, result.getUserDtoList().size());
	}

	/**
	 * add(UserManagerDto umsDto); 正常系001データあり
	 */
	@Test
	public void testAdd_normal_001() {
		System.out.println("testAdd_normal_001");

		// テストデータ
		UserManagerDto umsDto = new UserManagerDto();
		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();
		UserRoleDto urDto = new UserRoleDto(u_userId, ur_roleId);
		urDtoList.add(urDto);

		umsDto.setUserDto(new UserDto(u_userId, u_userName, u_password, u_passUpdateDate, u_gender, u_birthday,
				u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate, u_insertDate,
				u_updateDate, urDtoList));
		umsDto.setUserRoleDtoList(urDtoList);

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int insert(UserEntity entity) {
				return 1;
			}
		};

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public boolean insert(UserRoleEntity entity) {
				return true;
			}
		};

		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.add(umsDto);

		// 検証メソッド
		assertEquals(ServiceConst.THERE_IS_DATA, result.getResult());

	}

	/**
	 * add(UserManagerDto umsDto); 異常系00１データなし
	 */
	@Test
	public void testAdd_abnormal_001() {
		System.out.println("testAdd_abnormal_001");

		// テストデータ
		UserManagerDto umsDto = new UserManagerDto();
		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();
		UserRoleDto urDto = new UserRoleDto();
		urDtoList.add(urDto);

		umsDto.setUserDto(new UserDto());
		umsDto.setUserRoleDtoList(urDtoList);
		umsDto.getUserDto().setPassword(""); // 追加

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int insert(UserEntity entity) {
				return 0;
			}
		};

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public boolean insert(UserRoleEntity entity) {
				return false;
			}
		};

		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.add(umsDto);

		// 検証メソッド
		assertEquals(ServiceConst.NO_DATA, result.getResult());

	}

	/**
	 * update(UserManagerDto umsDto); 正常系 001 データあり権限権限変更なし
	 */
	@Test
	public void testUpdate_normal_001() {
		System.out.println("testUpdate_normal_001");

		// テストデータ
		UserManagerDto umsDto = new UserManagerDto();
		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();
		UserRoleDto urDto = new UserRoleDto(u_userId, ur_roleId);
		urDtoList.add(urDto);

		umsDto.setUserDto(new UserDto(u_userId, u_userName, u_password, u_passUpdateDate, u_gender, u_birthday,
				u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate, u_insertDate,
				u_updateDate, urDtoList));
		umsDto.setUserRoleDtoList(urDtoList);
		umsDto.setOptimisticRockValue(
				"test,テストユーザー,pass1,null,男,2021-04-01,090-1234-5678,test@xxx.co.jp,0,null,null,null");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int update(UserEntity entity) {
				return 1;
			}

			// スタブのメソッドを書き換え
			@Override
			public UserEntity findByUserId(String userId) {

				UserEntity userEntity = new UserEntity(u_userId, u_userName, u_password, u_passUpdateDate, u_gender,
						u_birthday, u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate,
						u_insertDate, u_updateDate);

				return userEntity;
			}
		};

		// テスト用のUserRoleRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public List<UserRoleEntity> findByUserId(String userId) {

				List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
				UserRoleEntity userRoleEntity = new UserRoleEntity(u_userId, ur_roleId);
				userRoleEntityList.add(userRoleEntity);

				return userRoleEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.update(umsDto);

		// 検証メソッド
		assertEquals(ServiceConst.OK, result.getResult());

	}

	/**
	 * update(UserManagerDto umsDto); 正常系 002 データあり権限権限追加
	 */
	@Test
	public void testUpdate_normal_002() {
		System.out.println("testUpdate_normal_002");

		// テストデータ
		UserManagerDto umsDto = new UserManagerDto();
		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();
		UserRoleDto urDto = new UserRoleDto(u_userId, ur_roleId);
		UserRoleDto urDto1 = new UserRoleDto(u_userId, ur_roleId1);
		urDtoList.add(urDto);
		urDtoList.add(urDto1);

		umsDto.setUserDto(new UserDto(u_userId, u_userName, u_password, u_passUpdateDate, u_gender, u_birthday,
				u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate, u_insertDate,
				u_updateDate, urDtoList));
		umsDto.setUserRoleDtoList(urDtoList);
		umsDto.setOptimisticRockValue(
				"test,テストユーザー,pass1,null,男,2021-04-01,090-1234-5678,test@xxx.co.jp,0,null,null,null");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int update(UserEntity entity) {
				return 1;
			}

			// スタブのメソッドを書き換え
			@Override
			public UserEntity findByUserId(String userId) {

				UserEntity userEntity = new UserEntity(u_userId, u_userName, u_password, u_passUpdateDate, u_gender,
						u_birthday, u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate,
						u_insertDate, u_updateDate);

				return userEntity;
			}
		};

		// テスト用のUserRoleRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public boolean insert(UserRoleEntity userRoleEntity) {
				return true;
			}

			// スタブのメソッドを書き換え
			@Override
			public List<UserRoleEntity> findByUserId(String userId) {

				List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
				UserRoleEntity userRoleEntity = new UserRoleEntity(u_userId, ur_roleId);
				userRoleEntityList.add(userRoleEntity);

				return userRoleEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.update(umsDto);

		// 検証メソッド
		assertEquals(ServiceConst.OK, result.getResult());

	}

	/**
	 * update(UserManagerDto umsDto); 正常系 003データあり権限削除
	 */
	@Test
	public void testUpdate_normal_003() {
		System.out.println("testUpdate_normal_003");

		// テストデータ
		UserManagerDto umsDto = new UserManagerDto();
		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();
		UserRoleDto urDto = new UserRoleDto(u_userId, ur_roleId);
		urDtoList.add(urDto);

		umsDto.setUserDto(new UserDto(u_userId, u_userName, u_password, u_passUpdateDate, u_gender, u_birthday,
				u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate, u_insertDate,
				u_updateDate, urDtoList));
		umsDto.setUserRoleDtoList(urDtoList);
		umsDto.setOptimisticRockValue(
				"test,テストユーザー,pass1,null,男,2021-04-01,090-1234-5678,test@xxx.co.jp,0,null,null,null");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int update(UserEntity entity) {
				return 1;
			}

			// スタブのメソッドを書き換え
			@Override
			public UserEntity findByUserId(String userId) {

				UserEntity userEntity = new UserEntity(u_userId, u_userName, u_password, u_passUpdateDate, u_gender,
						u_birthday, u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate,
						u_insertDate, u_updateDate);

				return userEntity;
			}
		};

		// テスト用のUserRoleRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public boolean updateDelete(UserRoleEntity userRoleEntity) {
				return true;
			}

			// スタブのメソッドを書き換え
			@Override
			public List<UserRoleEntity> findByUserId(String userId) {

				List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
				UserRoleEntity userRoleEntity = new UserRoleEntity(u_userId, ur_roleId);
				UserRoleEntity userRoleEntity1 = new UserRoleEntity(u_userId, ur_roleId1);
				userRoleEntityList.add(userRoleEntity);
				userRoleEntityList.add(userRoleEntity1);

				return userRoleEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.update(umsDto);

		// 検証メソッド
		assertEquals(ServiceConst.OK, result.getResult());

	}

	/**
	 * update(UserManagerDto umsDto); 正常系004データあり楽観ロック
	 */
	@Test
	public void testUpdate_normal_004() {
		System.out.println("testUpdate_normal_004");

		// テストデータ
		UserManagerDto umsDto = new UserManagerDto();
		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();
		UserRoleDto urDto = new UserRoleDto(u_userId, ur_roleId);
		urDtoList.add(urDto);

		umsDto.setUserDto(new UserDto(u_userId, u_userName, u_password, u_passUpdateDate, u_gender, u_birthday,
				u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate, u_insertDate,
				u_updateDate, urDtoList));
		umsDto.setUserRoleDtoList(urDtoList);

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public UserEntity findByUserId(String userId) {

				UserEntity userEntity = new UserEntity(u_userId, u_userName, u_password, u_passUpdateDate, u_gender,
						u_birthday, u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate,
						u_insertDate, u_updateDate);

				return userEntity;
			}
		};

		// テスト用のUserRoleRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public List<UserRoleEntity> findByUserId(String userId) {

				List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
				UserRoleEntity userRoleEntity = new UserRoleEntity(u_userId, ur_roleId);
				userRoleEntityList.add(userRoleEntity);

				return userRoleEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.update(umsDto);

		// 検証メソッド
		assertEquals(ServiceConst.OPTIMISTIC_ROCK_ERROR, result.getResult());

	}

	/**
	 * update(UserManagerDto umsDto); 異常系001データなし
	 */
	@Test
	public void testUpdate_abnormal_001() {
		System.out.println("testUpdate_abnormal_001");

		// テストデータ
		UserManagerDto umsDto = new UserManagerDto();
		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();
		UserRoleDto urDto = new UserRoleDto();
		urDtoList.add(urDto);

		umsDto.setUserDto(new UserDto());
		umsDto.setUserRoleDtoList(urDtoList);
		umsDto.getUserDto().setPassword(""); // 追加
		umsDto.setOptimisticRockValue(
				"test,テストユーザー,pass1,null,男,2021-04-01,090-1234-5678,test@xxx.co.jp,0,null,null,null");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public UserEntity findByUserId(String userId) {

				UserEntity userEntity = new UserEntity(u_userId, u_userName, u_password, u_passUpdateDate, u_gender,
						u_birthday, u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate,
						u_insertDate, u_updateDate);

				return userEntity;
			}
		};

		// テスト用のUserRoleRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public List<UserRoleEntity> findByUserId(String userId) {

				List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
				UserRoleEntity userRoleEntity = new UserRoleEntity();
				userRoleEntityList.add(userRoleEntity);

				return userRoleEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.update(umsDto);

		// 検証メソッド
		assertEquals(ServiceConst.ERROR, result.getResult());

	}

	/**
	 * update(UserManagerDto umsDto); 異常系002データあり 権限追加失敗
	 */
	@Test
	public void testUpdate_abnormal_002() {
		System.out.println("testUpdate_abnormal_002");

		// テストデータ
		UserManagerDto umsDto = new UserManagerDto();
		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();
		UserRoleDto urDto = new UserRoleDto(u_userId, ur_roleId);
		UserRoleDto urDto1 = new UserRoleDto(u_userId, ur_roleId1);
		urDtoList.add(urDto);
		urDtoList.add(urDto1);

		umsDto.setUserDto(new UserDto(u_userId, u_userName, u_password, u_passUpdateDate, u_gender, u_birthday,
				u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate, u_insertDate,
				u_updateDate, urDtoList));
		umsDto.setUserRoleDtoList(urDtoList);
		umsDto.setOptimisticRockValue(
				"test,テストユーザー,pass1,null,男,2021-04-01,090-1234-5678,test@xxx.co.jp,0,null,null,null");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int update(UserEntity entity) {
				return 1;
			}

			// スタブのメソッドを書き換え
			@Override
			public UserEntity findByUserId(String userId) {

				UserEntity userEntity = new UserEntity(u_userId, u_userName, u_password, u_passUpdateDate, u_gender,
						u_birthday, u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate,
						u_insertDate, u_updateDate);

				return userEntity;
			}
		};

		// テスト用のUserRoleRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public boolean insert(UserRoleEntity userRoleEntity) {
				return false;
			}

			// スタブのメソッドを書き換え
			@Override
			public List<UserRoleEntity> findByUserId(String userId) {

				List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
				UserRoleEntity userRoleEntity = new UserRoleEntity(u_userId, ur_roleId);
				userRoleEntityList.add(userRoleEntity);

				return userRoleEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.update(umsDto);

		// 検証メソッド
		assertEquals(ServiceConst.ERROR, result.getResult());

	}

	/**
	 * update(UserManagerDto umsDto); 異常系002データあり 権限削除失敗
	 */
	@Test
	public void testUpdate_abnormal_003() {
		System.out.println("testUpdate_abnormal_003");

		// テストデータ
		UserManagerDto umsDto = new UserManagerDto();
		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();
		UserRoleDto urDto = new UserRoleDto(u_userId, ur_roleId);
		urDtoList.add(urDto);

		umsDto.setUserDto(new UserDto(u_userId, u_userName, u_password, u_passUpdateDate, u_gender, u_birthday,
				u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate, u_insertDate,
				u_updateDate, urDtoList));
		umsDto.setUserRoleDtoList(urDtoList);
		umsDto.setOptimisticRockValue(
				"test,テストユーザー,pass1,null,男,2021-04-01,090-1234-5678,test@xxx.co.jp,0,null,null,null");

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int update(UserEntity entity) {
				return 1;
			}

			// スタブのメソッドを書き換え
			@Override
			public UserEntity findByUserId(String userId) {

				UserEntity userEntity = new UserEntity(u_userId, u_userName, u_password, u_passUpdateDate, u_gender,
						u_birthday, u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate,
						u_insertDate, u_updateDate);

				return userEntity;
			}
		};

		// テスト用のUserRoleRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public boolean updateDelete(UserRoleEntity userRoleEntity) {
				return false;
			}

			// スタブのメソッドを書き換え
			@Override
			public List<UserRoleEntity> findByUserId(String userId) {

				List<UserRoleEntity> userRoleEntityList = new ArrayList<UserRoleEntity>();
				UserRoleEntity userRoleEntity = new UserRoleEntity(u_userId, ur_roleId);
				UserRoleEntity userRoleEntity1 = new UserRoleEntity(u_userId, ur_roleId1);
				userRoleEntityList.add(userRoleEntity);
				userRoleEntityList.add(userRoleEntity1);

				return userRoleEntityList;
			}
		};
		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.update(umsDto);

		// 検証メソッド
		assertEquals(ServiceConst.ERROR, result.getResult());

	}

	/**
	 * delete(UserManagerDto umsDto); 正常系001データあり
	 */

	@Test
	public void testDelete_normal_001() {
		System.out.println("testDelete_normal_001");

		// テストデータ
		UserManagerDto umsDto = new UserManagerDto();
		List<UserRoleDto> urDtoList = new ArrayList<UserRoleDto>();
		UserRoleDto urDto = new UserRoleDto(u_userId, ur_roleId);
		urDtoList.add(urDto);

		umsDto.setUserDto(new UserDto(u_userId, u_userName, u_password, u_passUpdateDate, u_gender, u_birthday,
				u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate, u_insertDate,
				u_updateDate, urDtoList));

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int delete(String userId) {
				return 1;
			}
		};

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int delete(String userId) {
				return 1;
			}
		};

		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.delete(umsDto);

		// 検証メソッド
		assertEquals(ServiceConst.OK, result.getResult());

	}

	/**
	 * delete(UserManagerDto umsDto); 異常系001データなし
	 */
	@Test
	public void testDelete_abnormal_001() {
		System.out.println("testDelete_abnormal_001");

		// テストデータ
		UserManagerDto umsDto = new UserManagerDto();
		umsDto.setUserDto(new UserDto());

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRepository userRepository = new StubUserRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int delete(String userId) {
				return 0;
			}
		};

		// テスト用のUserRepositoryスタブをインスタンス化
		UserRoleRepository userRoleRepository = new StubUserRoleRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int delete(String userId) {
				return 0;
			}
		};

		// スタブオブジェクトの設定
		service.setUserRepository(userRepository);
		service.setUserRoleRepository(userRoleRepository);

		// テスト実施メソッド
		UserManagerDto result = service.delete(umsDto);

		// 検証メソッド
		assertEquals(ServiceConst.ERROR, result.getResult());

	}

}
