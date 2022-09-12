package jp.co.ginga.web.domain.service.util.helper.userRole;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.ginga.infra.repository.userrole.UserRoleEntity;
import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;
import jp.co.ginga.web.domain.service.util.helper.userrole.UserRoleDtoHelper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRoleDtoHelperTest {

	/**
	 * テスト実施クラス
	 */
	@Autowired
	UserRoleDtoHelper userRoleDtoHelper;

	/**
	 * mapToUserRoleDtoメソッド正常系001
	 */
	@Test
	public void testMapToUserRoleDto_normal_001() {
		System.out.println("testMapToUserRoleDto_normal_001");

		// テストデータ
		//int id = 1;
		String userId = "1";
		String roleId = "2";

		UserRoleEntity usRoEntity = new UserRoleEntity(userId, roleId);

		//テスト実施メソッド
		//		List<UserRoleDto> result = userRoleDtoHelper.mapToUserRoleDtoList(entityRoleList);
		UserRoleDto result = userRoleDtoHelper.mapToUserRoleDto(usRoEntity);

		//検証処理
		//assertEquals(id, result.get(0).getId());
		assertEquals(userId, result.getUserId());
		assertEquals(roleId, result.getRoleId());

	}

	/**
	 * mapToUserRoleDtoメソッド正常系002
	 */
	@Test
	public void testMapToUserRoleDto_normal_002() {
		System.out.println("testMapToUserRoleDto_normal_002");

		UserRoleEntity usRoEntity = new UserRoleEntity();

		//テスト実施メソッド
		//		List<UserRoleDto> result = userRoleDtoHelper.mapToUserRoleDtoList(entityRoleList);
		UserRoleDto result = userRoleDtoHelper.mapToUserRoleDto(usRoEntity);

		//検証処理
		//assertEquals(id, result.get(0).getId());
		assertEquals(null, result.getUserId());
		assertEquals(null, result.getRoleId());

	}

	/**
	 * mapToUserRoleDtoListメソッド正常系001
	 */
	@Test
	public void testMapToUserRoleDtoList_normal_001() {
		System.out.println("testMapToUserRoleDtoList_normal_001");

		List<UserRoleEntity> entityRoleList = new ArrayList<UserRoleEntity>();

		// テストデータ
		//int id = 1;
		String userId = "1";
		String roleId = "2";

		UserRoleEntity usRoEntity = new UserRoleEntity(userId, roleId);
		entityRoleList.add(usRoEntity);

		//テスト実施メソッド
		//		List<UserRoleDto> result = userRoleDtoHelper.mapToUserRoleDtoList(entityRoleList);
		List<UserRoleDto> result = userRoleDtoHelper.mapToUserRoleDtoList(entityRoleList);

		//検証処理
		assertEquals(1, result.size());
		//assertEquals(id, result.get(0).getId());
		assertEquals(userId, result.get(0).getUserId());
		assertEquals(roleId, result.get(0).getRoleId());

	}

	/**
	 * mapToUserRoleDtoListメソッド正常系002
	 * データが0件の場合
	 */
	@Test
	public void testMapToUserRoleDtoList_normal_002() {
		System.out.println("testMapToUserRoleDtoList_normal_002");

		// テストデータ
		List<UserRoleEntity> entityRoleList = new ArrayList<>();

		//テスト実施メソッド
		List<UserRoleDto> result = userRoleDtoHelper.mapToUserRoleDtoList(entityRoleList);

		//検証処理
		assertEquals(0, result.size());

	}

	/**
	 * mapToUserRoleEntityメソッド正常系001
	 */
	@Test
	public void testMapToUserRoleEntity_normal_001() {
		System.out.println("testMapToUserRoleEntity_normal_001");

		// テストデータ
		//	int id = 1;
		String userId = "1";
		String roleId = "2";

		UserRoleDto usDto = new UserRoleDto(userId, roleId);

		//テスト実施メソッド
		UserRoleEntity result = userRoleDtoHelper.mapToUserRoleEntity(usDto);

		//検証処理
		//assertEquals(id, result.get(0).getId());
		assertEquals(userId, result.getUserId());
		assertEquals(roleId, result.getRoleId());

	}

	/**
	 * mapToUserRoleEntityメソッド正常系002
	 */
	@Test
	public void testMapToUserRoleEntity_normal_002() {
		System.out.println("testMapToUserRoleEntity_normal_002");

		UserRoleDto usDto = new UserRoleDto();

		//テスト実施メソッド
		UserRoleEntity result = userRoleDtoHelper.mapToUserRoleEntity(usDto);

		//検証処理
		//assertEquals(id, result.get(0).getId());
		assertEquals(null, result.getUserId());
		assertEquals(null, result.getRoleId());

	}

	/**
	 * mapToUserRoleEntityListメソッド正常系001
	 */
	@Test
	public void testMapToUserRoleEntityList_normal_001() {
		System.out.println("testMapToUserRoleEntityList_normal_001");

		List<UserRoleDto> roleDtoList = new ArrayList<UserRoleDto>();

		// テストデータ
		//	int id = 1;
		String userId = "1";
		String roleId = "2";

		UserRoleDto usDto = new UserRoleDto(userId, roleId);
		roleDtoList.add(usDto);

		//テスト実施メソッド
		List<UserRoleEntity> result = userRoleDtoHelper.mapToUserRoleEntityList(roleDtoList);

		//検証処理
		assertEquals(1, result.size());
		//assertEquals(id, result.get(0).getId());
		assertEquals(userId, result.get(0).getUserId());
		assertEquals(roleId, result.get(0).getRoleId());

	}

	/**
	 * mapToUserRoleEntityListメソッド正常系002
	 * データが0件の場合
	 */
	@Test
	public void testMapToUserRoleEntityList_normal_002() {
		System.out.println("testMapToUserRoleEntityList_normal_002");

		// テストデータ
		List<UserRoleDto> roleDtoList = new ArrayList<>();

		//テスト実施メソッド
		List<UserRoleEntity> result = userRoleDtoHelper.mapToUserRoleEntityList(roleDtoList);

		//検証処理
		assertEquals(0, result.size());

	}

}
