package jp.co.ginga.infra.userRole;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.ginga.infra.repository.role.RoleEntity;
import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.infra.repository.userrole.UserRoleEntity;
import jp.co.ginga.infra.repository.userrole.UserRoleRepository;
import jp.co.ginga.infra.util.PostgreDataManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")

/**
 * @author yuzuka ikeda
 */
public class UserRoleRepositoryTest {

	@Autowired
	UserRoleRepository userRoleRepository;

	/**
	 * ユーザー情報・システム権限情報 ユーザIDによる検索 権限IDにかかる情報取得 複数データがある場合
	 */
	@Test
	public void findRoleIdByUserIdMultipleData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_byid_dataset_userrole.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		String userId = "system1";

		List<Map<String, Object>> list = userRoleRepository.findRoleIdByUserId(userId);

		// 検証処理
		assertEquals(2, list.size());

		assertEquals("ROLE_ADMIN1", list.get(0).get("rolename"));
		assertEquals("ROLE_ADMIN2", list.get(1).get("rolename"));
	}

	/**
	 * ユーザー情報・システム権限情報 ユーザIDによる検索 権限IDにかかる情報取得 データが1件の場合
	 */
	@Test
	public void findRoleIdByUserIdSingleData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_byid_dataset_userrole.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		String userId = "system2";

		List<Map<String, Object>> list = userRoleRepository.findRoleIdByUserId(userId);

		// 検証処理
		assertEquals(1, list.size());

		assertEquals("ROLE_ADMIN2", list.get(0).get("rolename"));

	}

	/**
	 * ユーザー情報・システム権限情報 ユーザIDによる検索 権限IDにかかる情報取得 データが0件の場合 userId該当なし
	 */
	@Test
	public void findRoleIdByUserIdNoData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_byid_dataset_userrole.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		String userId = "system50";

		List<Map<String, Object>> list = userRoleRepository.findRoleIdByUserId(userId);

		// 検証処理
		assertEquals(0, list.size());
	}

	/**
	 * ユーザー情報・システム権限情報 ユーザーIDによる検索 複数データがある場合
	 */
	@Test
	public void findByUserIdMultipleData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_byid_dataset_userrole.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		String userId = "system1";
		List<UserRoleEntity> list = userRoleRepository.findByUserId(userId);

		// 検証処理
		assertEquals(list.size(), 2);

		// assertEquals(1, list.get(0).getId());
		assertEquals("admin1", list.get(0).getRoleId());

		// assertEquals(2, list.get(1).getId());
		assertEquals("admin2", list.get(1).getRoleId());
	}

	/**
	 * ユーザー情報・システム権限情報 ユーザーIDによる検索 データが1件の場合
	 */
	@Test
	public void findByUserIdSingleData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_single_dataset_userrole.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		String userId = "system";
		List<UserRoleEntity> list = userRoleRepository.findByUserId(userId);

		// 検証処理
		assertEquals(list.size(), 1);

		// assertEquals(1, list.get(0).getId());
		assertEquals("admin", list.get(0).getRoleId());
	}

	/**
	 * ユーザー情報・システム権限情報 ユーザーIDによる検索 データが0件の場合
	 */
	@Test
	public void findByUserIdNoData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/util/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		String userId = "system";
		List<UserRoleEntity> list = userRoleRepository.findByUserId(userId);

		// 検証処理
		assertEquals(list.size(), 0);
	}

	/**
	 * ユーザー情報・システム権限情報 権限IDによる検索 複数データがある場合
	 */
	@Test
	public void findByRoleIdMultipleData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_byid_dataset_userrole.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		String roleId = "admin2";
		List<UserRoleEntity> list = userRoleRepository.findByRoleId(roleId);

		// 検証処理
		assertEquals(list.size(), 2);

		// assertEquals(2, list.get(0).getId());
		assertEquals("system1", list.get(0).getUserId());

		// assertEquals(3, list.get(1).getId());
		assertEquals("system2", list.get(1).getUserId());
	}

	/**
	 * ユーザー情報・システム権限情報 権限IDによる検索 データが1件の場合
	 */
	@Test
	public void findByRoleIdSingleData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_single_dataset_userrole.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		String roleId = "admin";
		List<UserRoleEntity> list = userRoleRepository.findByRoleId(roleId);

		// 検証処理
		assertEquals(list.size(), 1);

		// assertEquals(1, list.get(0).getId());
		assertEquals("system", list.get(0).getUserId());
	}

	/**
	 * ユーザー情報・システム権限情報 権限IDによる検索 データが0件の場合
	 */
	@Test
	public void findByRoleIdNoData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/util/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		String roleId = "admin";
		List<UserRoleEntity> list = userRoleRepository.findByRoleId(roleId);

		// 検証処理
		assertEquals(list.size(), 0);
	}

	/**
	 * ユーザー情報・システム権限情報 登録処理 OK
	 */
	@Test
	public void insertOk() {
		try {
			PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_dataset_userrole.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		// int id = 5; // idは自動採番
		String userId = "userA";
		String roleId = "adminA";

		// UserEntity userEntity = new UserEntity(userId, null, null, null, null, null,
		// null, null, 0, false, false, null,
		// null, null);

		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(userId);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleId(roleId);

		UserRoleEntity userRoleEntity = new UserRoleEntity(userId, roleId);

		boolean result = userRoleRepository.insert(userRoleEntity);

		// 検証処理
		assertEquals(true, result);
	}

	/**
	 * ユーザー情報・システム権限情報 更新処理 OK
	 */

	// ロールに関してはアップデート文ではなくインサート文とデリート文でアップデート機能実現
	// （UserManagerServiceImplクラス参照）

	// @Test // 権限IDが変更・更新された場合
	// public void updateOk_roleID() {
	// try {
	// PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_dataset_userrole.xml",
	// DatabaseOperation.CLEAN_INSERT);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// System.out.println("データセット失敗");
	// fail();
	// }
	//
	// // テストデータ
	// // int id = 1;
	// String roleId = "admin1";
	// String afterRoleId = "admin4";
	//
	// UserRoleEntity afterUserRoleEntity = new UserRoleEntity();
	// afterUserRoleEntity.setRoleId(afterRoleId);
	//
	// UserRoleEntity beforeUserRoleEntity = new UserRoleEntity();
	// beforeUserRoleEntity.setRoleId(roleId);
	// // beforeUserRoleEntity.setId(id);
	//
	// //検証処理
	// //update処理前の件数を取得
	// List<UserRoleEntity> beforeList = userRoleRepository.findByRoleId(roleId);
	// assertEquals(1, beforeList.size());
	// assertEquals(true, beforeList.get(0).getRoleId().equals(roleId));
	//
	// //update処理
	// int result = userRoleRepository.update(afterUserRoleEntity,
	// beforeUserRoleEntity);
	// assertEquals(1, result);
	//
	// //update処理後の件数を取得
	// List<UserRoleEntity> afterList =
	// userRoleRepository.findByRoleId(afterRoleId);
	// assertEquals(1, afterList.size());
	// assertEquals(true, afterList.get(0).getRoleId().equals(afterRoleId));
	// }

	// @Test // ユーザーIDが変更・更新された場合
	// public void updateOk_userID() {
	// try {
	// PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_dataset_userrole.xml",
	// DatabaseOperation.CLEAN_INSERT);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// System.out.println("データセット失敗");
	// fail();
	// }
	//
	// // テストデータ
	// String userId = "system1";
	// String afterUserId = "system4";
	//
	// UserRoleEntity afterUserRoleEntity = new UserRoleEntity();
	// afterUserRoleEntity.setUserId(afterUserId);
	//
	// UserRoleEntity beforeUserRoleEntity = new UserRoleEntity();
	// beforeUserRoleEntity.setUserId(userId);
	//
	// //検証処理
	// //update処理前の件数を取得
	// List<UserRoleEntity> beforeList = userRoleRepository.findByUserId(userId);
	// assertEquals(1, beforeList.size());
	// assertEquals(true, beforeList.get(0).getUserId().equals(userId));
	//
	// //update処理
	// int result = userRoleRepository.update(afterUserRoleEntity,
	// beforeUserRoleEntity);
	// assertEquals(1, result);
	//
	// //update処理後の件数を取得
	// List<UserRoleEntity> afterList =
	// userRoleRepository.findByUserId(afterUserId);
	// assertEquals(1, afterList.size());
	// assertEquals(true, afterList.get(0).getUserId().equals(afterUserId));
	// }

	/**
	 * ユーザー情報・システム権限情報 更新処理 主キー無し NG
	 */
	// @Test
	// public void updateNgNoPk_roleID() {
	// try {
	// PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_dataset_userrole.xml",
	// DatabaseOperation.CLEAN_INSERT);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// System.out.println("データセット失敗");
	// fail();
	// }
	//
	// // テストデータ
	// String roleId = "admin100";
	// String afterRoleId = "admin1001";
	//
	// UserRoleEntity afterUserRoleEntity = new UserRoleEntity();
	// afterUserRoleEntity.setRoleId(afterRoleId);
	//
	// UserRoleEntity beforeUserRoleEntity = new UserRoleEntity();
	// beforeUserRoleEntity.setRoleId(roleId);
	//
	// //検証処理
	// //update処理前の件数を取得
	// List<UserRoleEntity> beforeList = userRoleRepository.findByRoleId(roleId);
	// assertEquals(0, beforeList.size());
	//
	// //update処理
	// int result = userRoleRepository.update(afterUserRoleEntity,
	// beforeUserRoleEntity);
	// assertEquals(0, result);
	//
	// //update処理後の件数を取得
	// List<UserRoleEntity> afterList =
	// userRoleRepository.findByRoleId(afterRoleId);
	// assertEquals(0, afterList.size());
	// }

	// @Test
	// public void updateNgNoPk_userID() {
	// try {
	// PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_dataset_userrole.xml",
	// DatabaseOperation.CLEAN_INSERT);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// System.out.println("データセット失敗");
	// fail();
	// }
	//
	// // テストデータ
	// String userId = "system100";
	// String afterUserId = "system1001";
	//
	// UserRoleEntity afterUserRoleEntity = new UserRoleEntity();
	// afterUserRoleEntity.setUserId(afterUserId);
	//
	// UserRoleEntity beforeUserRoleEntity = new UserRoleEntity();
	// beforeUserRoleEntity.setUserId(userId);
	//
	// //検証処理
	// //update処理前の件数を取得
	// List<UserRoleEntity> beforeList = userRoleRepository.findByUserId(userId);
	// assertEquals(0, beforeList.size());
	//
	// //update処理
	// int result = userRoleRepository.update(afterUserRoleEntity,
	// beforeUserRoleEntity);
	// assertEquals(0, result);
	//
	// //update処理後の件数を取得
	// List<UserRoleEntity> afterList =
	// userRoleRepository.findByUserId(afterUserId);
	// assertEquals(0, afterList.size());
	// }

	/**
	 * ユーザー情報・システム権限情報 削除処理 OK
	 */
	@Test
	public void deleteOk() {
		try {
			PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_dataset_userrole.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		int id = 1;
		String userId = "system1";
		String roleId = "admin1";

		UserRoleEntity userRoleEntity = new UserRoleEntity(userId, roleId);

		// 検証処理
		// delete処理前の件数を取得
		List<UserRoleEntity> beforeList = userRoleRepository.findByUserId(userId);
		assertEquals(1, beforeList.size());
		// assertEquals(1, beforeList.get(0).getId());

		// delete処理
		int result = userRoleRepository.delete(userId);
		assertEquals(1, result);

		// delete処理後の件数を取得
		List<UserRoleEntity> afterList = userRoleRepository.findByUserId(userId);
		assertEquals(0, afterList.size());
	}

	/**
	 * ユーザー情報・システム権限情報 削除処理 主キー無し NG
	 */
	@Test
	public void deleteNgNoPk() {
		try {
			PostgreDataManager.dataSet("./data/infra/repository/userrole/setup_dataset_userrole.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		int id = 10;
		String userId = "system5";
		String roleId = "admin5";

		UserRoleEntity userRoleEntity = new UserRoleEntity(userId, roleId);

		// 検証処理
		// delete処理前の件数を取得
		List<UserRoleEntity> beforeList = userRoleRepository.findByUserId(userId);
		assertEquals(0, beforeList.size());

		// delete処理
		int result = userRoleRepository.delete(userId);
		assertEquals(0, result);

		// delete処理後の件数を取得
		List<UserRoleEntity> afterList = userRoleRepository.findByUserId(userId);
		assertEquals(0, afterList.size());
	}
}
