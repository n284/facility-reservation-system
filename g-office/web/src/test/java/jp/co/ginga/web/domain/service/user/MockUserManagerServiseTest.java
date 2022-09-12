package jp.co.ginga.web.domain.service.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.ginga.infra.repository.role.RoleEntity;
import jp.co.ginga.infra.repository.role.RoleRepository;
import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.infra.repository.user.UserRepository;
import jp.co.ginga.infra.repository.userrole.UserRoleEntity;
import jp.co.ginga.infra.repository.userrole.UserRoleRepository;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.role.RoleDto;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MockUserManagerServiseTest {

	/**
	 * テストデータ
	 */
	private String u_userId = "test";
	private String u_userName = "テストユーザー";
	private String u_password = "pass1";
	private Timestamp u_passUpdateDate = new Timestamp(System.currentTimeMillis());
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
	private String optimisticRockValue = u_userId + "," + u_userName + "," + u_password + "," + u_passUpdateDate + ","
			+ u_gender + "," + u_birthday + "," + u_contact + "," + u_mailAddress + "," + u_loginMissTimes + ","
			+ u_userDueDate + "," + u_insertDate + "," + u_updateDate;
	// 更新用
	private String upOptimisticRockValue = null;
	private String r_roleId = "tests";
	private String r_roleName = "ROLE_Test";
	private String ur_userId = "test";
	private String ur_roleId = "tests";
	private String ur_userId2 = "test";
	private String ur_roleId2 = "tests2";

	/**
	 * テスト用Entity
	 */
	private UserEntity userEntity;
	private UserEntity nullUserEntity;
	private RoleEntity roleEntity;
	private UserRoleEntity userRoleEntity;
	private UserRoleEntity userRoleEntity2;
	private List<UserEntity> userEntityList;
	private List<RoleEntity> roleList;
	private List<UserRoleEntity> userRoleList;
	// update用List<UserRoleDto>
	private List<UserRoleEntity> upUserRoleList;

	private List<UserEntity> nullUserList;
	private List<RoleEntity> nullRoleList;
	private List<UserRoleEntity> nullUserRoleList;

	/**
	 * テスト用Dto
	 */
	private UserDto dto;
	private List<UserDto> userDtoList;
	private UserRoleDto usRole;
	private List<UserRoleDto> userRoleDtoList;
	// update用List<UserRoleDto>
	private UserRoleDto upUsRole;
	// update用List<UserRoleDto>
	private List<UserRoleDto> upUserRoleDtoList;
	private RoleDto roleDto;
	private List<RoleDto> roleDtoList;

	/**
	 * テスト用ManagerDto
	 */
	private UserManagerDto umDto;
	// update用UserManagerDto
	private UserManagerDto upUmDto;
	// update(optimisticRockValue)エラー用UserManagerDto
	private UserManagerDto optUmDto;

	@Mock
	private UserRepository repoUser;
	@Mock
	private RoleRepository repoRole;
	@Mock
	private UserRoleRepository repoUserRole;
	@Mock
	private PasswordEncoder passwordEncoder;

	/**
	 * テスト実施クラス
	 */
	@InjectMocks
	@Autowired
	UserManagerService servise = new UserManagerServiceImpl();

	/**
	 * 宣言したインスタンスを初期化してMock化
	 */
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * テストデータのセットアップ
	 */
	@BeforeEach
	public void createData() {
		this.userEntity = new UserEntity(u_userId, u_userName, u_password, u_passUpdateDate, u_gender, u_birthday,
				u_contact, u_mailAddress, u_loginMissTimes, u_unlock, u_enabled, u_userDueDate, u_insertDate,
				u_updateDate);
		this.nullUserEntity = new UserEntity();
		this.roleEntity = new RoleEntity(r_roleId, r_roleName);
		this.userRoleEntity = new UserRoleEntity(ur_userId, ur_roleId);
		this.userRoleEntity2 = new UserRoleEntity(ur_userId2, ur_roleId2);

		this.userEntityList = new ArrayList<UserEntity>();
		this.userEntityList.add(userEntity);

		this.roleList = new ArrayList<RoleEntity>();
		this.roleList.add(roleEntity);

		this.userRoleList = new ArrayList<UserRoleEntity>();
		this.userRoleList.add(userRoleEntity);
		this.userRoleList.add(userRoleEntity2);

		this.upUserRoleList = new ArrayList<UserRoleEntity>();
		this.upUserRoleList.add(userRoleEntity);

		this.nullUserList = new ArrayList<UserEntity>();
		this.nullUserRoleList = new ArrayList<UserRoleEntity>();
		this.nullRoleList = new ArrayList<RoleEntity>();

		this.dto = new UserDto();
		this.dto.setUserId(u_userId);
		this.dto.setUserName(u_userName);
		this.dto.setPassword(u_password);
		this.dto.setPassUpdateDate(u_passUpdateDate);
		this.dto.setGender(u_gender);
		this.dto.setBirthday(u_birthday);
		this.dto.setContact(u_contact);
		this.dto.setMailAddress(u_mailAddress);
		this.dto.setLoginMissTimes(u_loginMissTimes);
		this.dto.setUnlock(u_unlock);
		this.dto.setEnabled(u_enabled);
		this.dto.setUserDueDate(u_userDueDate);
		this.dto.setInsertDate(u_insertDate);

		this.userDtoList = new ArrayList<UserDto>();
		this.userDtoList.add(dto);

		this.usRole = new UserRoleDto();
		this.usRole.setRoleId(ur_roleId);
		this.usRole.setUserId(ur_userId);

		this.userRoleDtoList = new ArrayList<UserRoleDto>();
		this.userRoleDtoList.add(usRole);

		this.upUsRole = new UserRoleDto();
		this.upUsRole.setRoleId(ur_roleId2);
		this.upUsRole.setUserId(ur_userId2);

		this.upUserRoleDtoList = new ArrayList<UserRoleDto>();
		this.upUserRoleDtoList.add(usRole);
		this.upUserRoleDtoList.add(upUsRole);

		this.roleDto = new RoleDto();
		this.roleDto.setRoleId(r_roleId);
		this.roleDto.setRoleName(r_roleName);

		this.roleDtoList = new ArrayList<RoleDto>();
		this.roleDtoList.add(roleDto);

		this.umDto = new UserManagerDto();
		this.umDto.setRoleDtoList(roleDtoList);
		this.umDto.setUserDto(dto);
		this.umDto.setUserRoleDtoList(userRoleDtoList);
		this.umDto.setUserDtoList(userDtoList);
		this.umDto.setOptimisticRockValue(optimisticRockValue);

		// update用
		this.upUmDto = new UserManagerDto();
		this.upUmDto.setUserDto(dto);
		this.upUmDto.setRoleDtoList(roleDtoList);
		this.upUmDto.setUserDtoList(userDtoList);
		this.upUmDto.setUserRoleDtoList(upUserRoleDtoList);
		this.upUmDto.setOptimisticRockValue(optimisticRockValue);

		// update(optimisticRockValue)エラー用
		this.optUmDto = new UserManagerDto();
		this.optUmDto.setUserDto(dto);
		this.optUmDto.setRoleDtoList(roleDtoList);
		this.optUmDto.setUserDtoList(userDtoList);
		this.optUmDto.setUserRoleDtoList(upUserRoleDtoList);
		this.optUmDto.setOptimisticRockValue(upOptimisticRockValue);
	}

	/**
	 * getUserList 正常系001
	 */
	@Test
	public void testGetUserList_normal_001() {
		System.out.println("testGetUserList_normal_001");

		// モックの設定
		when(this.repoUser.findAll()).thenReturn(this.userEntityList);
		when(this.repoUserRole.findAll()).thenReturn(this.userRoleList);

		UserManagerDto result = servise.getUserList();

		// 検証処理
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

		assertEquals(ur_userId, result.getUserRoleDtoList().get(0).getUserId());
		assertEquals(ur_roleId, result.getUserRoleDtoList().get(0).getRoleId());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).findAll();
		verify(this.repoUserRole, times(1)).findAll();

	}

	/**
	 * getUserList正常系002
	 *
	 * Listサイズ:0
	 */
	@Test
	public void testGetUserList_normal_002() {
		System.out.println("testGetUserList_normal_002");

		// モックの設定
		when(this.repoUser.findAll()).thenReturn(this.nullUserList);
		when(this.repoUserRole.findAll()).thenReturn(this.nullUserRoleList);

		// テスト実施メソッド
		UserManagerDto result = servise.getUserList();

		// 検証処理
		assertEquals(0, result.getUserRoleDtoList().size());
		assertEquals(0, result.getUserDtoList().size());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).findAll();
		verify(this.repoUserRole, times(1)).findAll();
	}

	/**
	 * getRoleList正常系001
	 *
	 *
	 */
	@Test
	public void testGetRoleList_nomal_001() {
		System.out.println("testGetRoleList_nomal_001");

		// モックの設定
		when(this.repoRole.findAll()).thenReturn(this.roleList);

		// テスト実施メソッド
		UserManagerDto result = servise.getRoleList();

		// 検証処理
		assertEquals(r_roleId, result.getRoleDtoList().get(0).getRoleId());
		assertEquals(r_roleName, result.getRoleDtoList().get(0).getRoleName());

		// モック呼び出し回数
		verify(this.repoRole, times(1)).findAll();

	}

	/**
	 * getRoleList正常系002
	 *
	 *
	 */
	@Test
	public void testGetRoleList_nomal_002() {
		System.out.println("testGetRoleList_nomal_002");

		// モックの設定
		when(this.repoRole.findAll()).thenReturn(this.nullRoleList);

		// テスト実施メソッド
		UserManagerDto result = servise.getRoleList();

		// 検証処理
		assertEquals(0, result.getRoleDtoList().size());

		// モック呼び出し回数
		verify(this.repoRole, times(1)).findAll();
	}

	/**
	 * getRoleDtoList正常系001
	 *
	 *
	 */
	@Test
	public void testGetUser_nomal_001() {
		System.out.println("testGetUser_nomal_001");

		// モックの設定
		when(this.repoUser.findByUserId(u_userId)).thenReturn(this.userEntity);
		when(this.repoUserRole.findByUserId(ur_userId)).thenReturn(this.userRoleList);

		// テスト実施メソッド
		UserManagerDto result = servise.getUser(this.u_userId);

		// 検証処理
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

		assertEquals(ur_userId, result.getUserRoleDtoList().get(0).getUserId());
		assertEquals(ur_roleId, result.getUserRoleDtoList().get(0).getRoleId());

		assertEquals(optimisticRockValue, result.getOptimisticRockValue());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).findByUserId(u_userId);
		verify(this.repoUserRole, times(1)).findByUserId(ur_userId);

	}

	/**
	 * getRoleDtoList正常系002
	 *
	 * null
	 */
	@Test
	public void testGetUser_nomal_002() {
		System.out.println("testGetUser_nomal_002");

		// モックの設定
		when(this.repoUser.findByUserId(u_userId)).thenReturn(nullUserEntity);
		when(this.repoUserRole.findByUserId(ur_userId)).thenReturn(nullUserRoleList);

		// テスト実施メソッド
		UserManagerDto result = servise.getUser(this.u_userId);

		// 検証処理
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

		// モック呼び出し回数
		verify(this.repoUser, times(1)).findByUserId(u_userId);
		verify(this.repoUserRole, times(1)).findByUserId(ur_userId);

	}

	/**
	 * getUserListByUserId正常系001
	 *
	 * 検索結果あり
	 */
	@Test
	public void testGetUserListByUserId_nomal_001() {
		System.out.println("testGetUserListByUserId_nomal_001");

		// モックの設定
		when(this.repoUser.getUserIdByUserId(u_userId)).thenReturn(this.userEntityList);

		// テスト実施メソッド
		UserManagerDto result = servise.getUserListByUserId(u_userId);

		// 検証処理
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

		assertEquals(1, result.getUserDtoList().size());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).getUserIdByUserId(u_userId);
	}

	/**
	 * getUserListByUserId正常系002
	 *
	 * 検索結果なし
	 */
	@Test
	public void testGetUserListByUserId_nomal_002() {
		System.out.println("testGetUserListByUserId_nomal_002");

		// モックの設定
		when(this.repoUser.getUserIdByUserId(u_userId)).thenReturn(this.nullUserList);

		// テスト実施メソッド
		UserManagerDto result = servise.getUserListByUserId(u_userId);

		// 検証処理
		assertEquals(0, result.getUserDtoList().size());

		assertEquals(ServiceConst.NO_DATA, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).getUserIdByUserId(u_userId);
	}

	/**
	 * add正常系001
	 *
	 * user〇 userRole〇
	 */
	@Test
	public void testAdd_nomal_001() {
		System.out.println("testAdd_nomal_001");

		// モックの設定
		when(this.passwordEncoder.encode(this.u_password)).thenReturn(this.u_password);
		when(this.repoUser.insert(this.userEntity)).thenReturn(1);
		when(this.repoUserRole.insert(this.userRoleEntity)).thenReturn(true);

		// テスト実施メソッド
		UserManagerDto result = servise.add(this.umDto);

		// 検証処理
		assertEquals(ServiceConst.THERE_IS_DATA, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).insert(this.userEntity);
		verify(this.repoUserRole, times(1)).insert(this.userRoleEntity);

	}

	/**
	 * 異常系001
	 *
	 * user× userRole×
	 */
	@Test
	public void testAdd_abnormal_001() {
		System.out.println("testAdd_abnormal_001");

		// モックの設定
		when(this.passwordEncoder.encode(this.u_password)).thenReturn(this.u_password);
		when(this.repoUser.insert(this.userEntity)).thenReturn(0);
		when(this.repoUserRole.insert(this.userRoleEntity)).thenReturn(false);

		// テスト実施メソッド
		UserManagerDto result = servise.add(this.umDto);

		// 検証処理
		assertEquals(ServiceConst.NO_DATA, result.getResult());

	}

	/**
	 * add異常系002
	 *
	 * user〇 userRole×
	 */
	@Test
	public void testAdd_abnormal_002() {
		System.out.println("testAdd_abnormal_002");

		// モックの設定
		when(this.passwordEncoder.encode(this.u_password)).thenReturn(this.u_password);
		when(this.repoUser.insert(this.userEntity)).thenReturn(1);
		when(this.repoUserRole.insert(this.userRoleEntity)).thenReturn(false);

		// テスト実施メソッド
		UserManagerDto result = servise.add(this.umDto);

		// 検証処理
		assertEquals(ServiceConst.NO_DATA, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).insert(this.userEntity);
		// verify(this.repoUserRole, times(1)).insert(this.userRoleEntity);

	}

	/**
	 * add異常系003
	 *
	 * user× userRole〇
	 */
	@Test
	public void testAdd_abnormal_003() {
		System.out.println("testAdd_abnormal_003");

		// モックの設定
		when(this.passwordEncoder.encode(this.u_password)).thenReturn(this.u_password);
		when(this.repoUser.insert(this.userEntity)).thenReturn(0);
		when(this.repoUserRole.insert(this.userRoleEntity)).thenReturn(true);

		// テスト実施メソッド
		UserManagerDto result = servise.add(this.umDto);

		// 検証処理
		assertEquals(ServiceConst.NO_DATA, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).insert(this.userEntity);
		verify(this.repoUserRole, times(1)).insert(this.userRoleEntity);

	}

	/**
	 * update正常系001
	 *
	 *
	 */
	@Test
	public void testUpdate_nomal_001() {
		System.out.println("testUpdate_nomal_001");

		// モックの設定
		when(this.repoUser.findByUserId(this.upUmDto.getUserDto().getUserId())).thenReturn(this.userEntity);
		when(this.repoUserRole.findByUserId(this.upUmDto.getUserDto().getUserId())).thenReturn(this.upUserRoleList);
		when(this.repoUser.update(any())).thenReturn(1);
		when(this.repoUserRole.insert(this.userRoleEntity2)).thenReturn(true);

		// テスト実施メソッド
		UserManagerDto result = servise.update(this.upUmDto);

		// 検証処理
		assertEquals(ServiceConst.OK, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).findByUserId(this.upUmDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).findByUserId(this.upUmDto.getUserDto().getUserId());
		verify(this.repoUser, times(1)).update(any());
		verify(this.repoUserRole, times(1)).insert(this.userRoleEntity2);
	}

	/**
	 * update正常系002
	 *
	 *
	 */
	@Test
	public void testUpdate_nomal_002() {
		System.out.println("testUpdate_nomal_002");

		// モックの設定
		when(this.repoUser.findByUserId(this.umDto.getUserDto().getUserId())).thenReturn(this.userEntity);
		when(this.repoUserRole.findByUserId(this.umDto.getUserDto().getUserId())).thenReturn(this.userRoleList);
		when(this.repoUser.update(any())).thenReturn(1);
		when(this.repoUserRole.updateDelete(this.userRoleEntity)).thenReturn(true);

		// テスト実施メソッド
		UserManagerDto result = servise.update(this.umDto);

		// 検証処理
		assertEquals(ServiceConst.OK, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).findByUserId(this.umDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).findByUserId(this.umDto.getUserDto().getUserId());
		verify(this.repoUser, times(1)).update(any());
		verify(this.repoUserRole, times(1)).updateDelete(this.userRoleEntity);
	}

	/**
	 * update正常系003
	 * パスワード更新なし
	 *
	 */
	@Test
	public void testUpdate_nomal_003() {
		System.out.println("testUpdate_nomal_001");

		// モックの設定
		when(this.repoUser.findByUserId(this.upUmDto.getUserDto().getUserId())).thenReturn(this.userEntity);
		when(this.repoUserRole.findByUserId(this.upUmDto.getUserDto().getUserId())).thenReturn(this.upUserRoleList);
		when(this.repoUser.update(any())).thenReturn(1);
		when(this.repoUserRole.insert(this.userRoleEntity2)).thenReturn(true);
		when(this.passwordEncoder.matches(this.upUmDto.getUserDto().getPassword(), this.userEntity.getPassword())).thenReturn(true);

		// テスト実施メソッド
		UserManagerDto result = servise.update(this.upUmDto);

		// 検証処理
		assertEquals(ServiceConst.OK, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).findByUserId(this.upUmDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).findByUserId(this.upUmDto.getUserDto().getUserId());
		verify(this.repoUser, times(1)).update(any());
		verify(this.repoUserRole, times(1)).insert(this.userRoleEntity2);
	}


	/**
	 * update異常系001
	 *
	 *
	 */
	@Test
	public void testUpdate_abnomal_001() {
		System.out.println("testUpdate_abnomal_001");

		// モックの設定
		when(this.repoUser.findByUserId(this.umDto.getUserDto().getUserId())).thenReturn(this.userEntity);
		when(this.repoUserRole.findByUserId(this.umDto.getUserDto().getUserId())).thenReturn(this.userRoleList);
		when(this.repoUserRole.updateDelete(this.userRoleEntity)).thenReturn(true);

		// テスト実施メソッド
		UserManagerDto result = servise.update(this.optUmDto);

		assertEquals(ServiceConst.OPTIMISTIC_ROCK_ERROR, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).findByUserId(this.umDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).findByUserId(this.umDto.getUserDto().getUserId());

	}

	/**
	 * update異常系002
	 *
	 * resultエラー
	 */
	@Test
	public void testUpdate_abnomal_002() {
		System.out.println("testUpdate_abnomal_002");

		// モックの設定
		when(this.repoUser.findByUserId(this.umDto.getUserDto().getUserId())).thenReturn(this.userEntity);
		when(this.repoUserRole.findByUserId(this.umDto.getUserDto().getUserId())).thenReturn(this.userRoleList);
		when(this.repoUserRole.updateDelete(this.userRoleEntity)).thenReturn(true);

		// テスト実施メソッド
		UserManagerDto result = servise.update(this.umDto);

		assertEquals(ServiceConst.ERROR, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).findByUserId(this.umDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).findByUserId(this.umDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).updateDelete(this.userRoleEntity);

	}

	/**
	 * update異常系003
	 *
	 *
	 */
	@Test
	public void testUpdate_abnomal_003() {
		System.out.println("testUpdate_abnomal_003");

		// モックの設定
		when(this.repoUser.findByUserId(this.upUmDto.getUserDto().getUserId())).thenReturn(this.userEntity);
		when(this.repoUserRole.findByUserId(this.upUmDto.getUserDto().getUserId())).thenReturn(this.upUserRoleList);
		when(this.repoUserRole.insert(this.userRoleEntity2)).thenReturn(false);

		// テスト実施メソッド
		UserManagerDto result = servise.update(this.upUmDto);

		// 検証処理
		assertEquals(ServiceConst.ERROR, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).findByUserId(this.upUmDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).findByUserId(this.upUmDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).insert(this.userRoleEntity2);

	}

	/**
	 * update異常系004
	 *
	 *
	 */
	@Test
	public void testUpdate_abnomal_004() {
		System.out.println("testUpdate_abnomal_004");

		// モックの設定
		when(this.repoUser.findByUserId(this.umDto.getUserDto().getUserId())).thenReturn(this.userEntity);
		when(this.repoUserRole.findByUserId(this.umDto.getUserDto().getUserId())).thenReturn(this.userRoleList);
		when(this.repoUserRole.updateDelete(this.userRoleEntity)).thenReturn(false);

		// テスト実施メソッド
		UserManagerDto result = servise.update(this.umDto);

		// 検証処理
		assertEquals(ServiceConst.ERROR, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).findByUserId(this.umDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).findByUserId(this.umDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).updateDelete(this.userRoleEntity);
	}

	/**
	 * delete正常系001
	 *
	 * user〇 userRole〇
	 */
	@Test
	public void testDelete_nomal_001() {
		System.out.println("testDelete_nomal_001");

		// モックの設定
		when(this.repoUser.delete(this.umDto.getUserDto().getUserId())).thenReturn(1);
		when(this.repoUserRole.delete(this.umDto.getUserDto().getUserId())).thenReturn(1);

		// テスト実施メソッド
		UserManagerDto result = servise.delete(this.umDto);

		// 検証処理
		assertEquals(ServiceConst.OK, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).delete(this.umDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).delete(this.umDto.getUserDto().getUserId());
	}

	/**
	 * delete異常系001
	 *
	 * user× userRole×
	 */
	@Test
	public void testDelete_abnormal_001() {
		System.out.println("testDelete_abnormal_001");

		// モックの設定
		when(this.repoUser.delete(this.umDto.getUserDto().getUserId())).thenReturn(0);
		when(this.repoUserRole.delete(this.umDto.getUserDto().getUserId())).thenReturn(0);

		// テスト実施メソッド
		UserManagerDto result = servise.delete(this.umDto);

		// 検証処理
		assertEquals(ServiceConst.ERROR, result.getResult());
		// モック呼び出し回数
		verify(this.repoUser, times(1)).delete(this.umDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).delete(this.umDto.getUserDto().getUserId());

	}

	/**
	 * delete異常系002
	 *
	 * user〇 userRole×
	 */
	@Test
	public void testDelete_abnormall_002() {
		System.out.println("testDelete_abnormal_002");

		// モックの設定
		when(this.repoUser.delete(this.umDto.getUserDto().getUserId())).thenReturn(1);
		when(this.repoUserRole.delete(this.umDto.getUserDto().getUserId())).thenReturn(0);

		// テスト実施メソッド
		UserManagerDto result = servise.delete(this.umDto);

		// 検証処理
		assertEquals(ServiceConst.ERROR, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).delete(this.umDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).delete(this.umDto.getUserDto().getUserId());

	}

	/**
	 * delete異常系003
	 *
	 * user× userRole〇
	 */
	@Test
	public void testDelete_abnormal_003() {
		System.out.println("testDelete_abnormal_003");

		// モックの設定
		when(this.repoUser.delete(this.umDto.getUserDto().getUserId())).thenReturn(0);
		when(this.repoUserRole.delete(this.umDto.getUserDto().getUserId())).thenReturn(1);

		// テスト実施メソッド
		UserManagerDto result = servise.delete(this.umDto);

		// 検証処理
		assertEquals(ServiceConst.ERROR, result.getResult());

		// モック呼び出し回数
		verify(this.repoUser, times(1)).delete(this.umDto.getUserDto().getUserId());
		verify(this.repoUserRole, times(1)).delete(this.umDto.getUserDto().getUserId());

	}
}