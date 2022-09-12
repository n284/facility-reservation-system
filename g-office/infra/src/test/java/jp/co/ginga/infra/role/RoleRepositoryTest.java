//package jp.co.ginga.infra.role;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.junit.Assert.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.List;
//
//import org.dbunit.operation.DatabaseOperation;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import jp.co.ginga.infra.repository.role.RoleEntity;
//import jp.co.ginga.infra.repository.role.RoleRepository;
//import jp.co.ginga.infra.util.PostgreDataManager;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@TestPropertySource(locations = "classpath:application-test.properties")
//public class RoleRepositoryTest {
//
//	@Autowired
//	RoleRepository roleRepository;
//
//	/**
//	 * システム権限情報 全件検索
//	 */
//	@Test
//	public void findAllMultipleData() {
//
//		try {
//			PostgreDataManager.dataSet("./data/setup_dataset.xml", DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		List<RoleEntity> list = roleRepository.findAll();
//
//		assertEquals(list.size(), 2);
//
//		assertEquals("admin", list.get(0).getRoleId());
//		assertEquals("ROLE_ADMIN", list.get(0).getRoleName());
//
//		assertEquals("general", list.get(1).getRoleId());
//		assertEquals("ROLE_GENERAL", list.get(1).getRoleName());
//	}
//
//	/**
//	 * システム権限情報 全件検索
//	 */
//	@Test
//	public void findAllSingleData() {
//
//		try {
//			PostgreDataManager.dataSet("./data/setup_singledata.xml", DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		List<RoleEntity> list = roleRepository.findAll();
//
//		assertEquals(list.size(), 1);
//
//		assertEquals("admin", list.get(0).getRoleId());
//		assertEquals("ROLE_ADMIN", list.get(0).getRoleName());
//	}
//
//	@Test
//	public void findAllNoData() {
//
//		try {
//			PostgreDataManager.dataSet("./data/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		List<RoleEntity> list = roleRepository.findAll();
//
//		assertEquals(0, list.size());
//	}
//
//	@Test
//	public void findByRoleIdSingleData() {
//
//		try {
//			PostgreDataManager.dataSet("./data/setup_dataset.xml", DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		RoleEntity roleEntity = roleRepository.findByRoleId("admin");
//
//		assertEquals("admin", roleEntity.getRoleId());
//		assertEquals("ROLE_ADMIN", roleEntity.getRoleName());
//	}
//
//	@Test
//	public void findByRoleIdNoData() {
//
//		try {
//			PostgreDataManager.dataSet("./data/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//		RoleEntity roleEntity = roleRepository.findByRoleId("admin");
//
//		assertEquals(null, roleEntity);
//	}
//
//	@Test
//	public void insertOk() {
//
//		try {
//			PostgreDataManager.dataSet("./data/setup_dataset.xml", DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		String roleId = "test";
//		String roleName = "test";
//
//		RoleEntity roleEntity = new RoleEntity(roleId, roleName);
//
//		//テスト実施メソッド
//		int result = roleRepository.insert(roleEntity);
//		//検証処理
//		assertEquals(1, result);
//
//	}
//
//	@Test
//	public void insertNg() {
//
//		try {
//			PostgreDataManager.dataSet("./data/setup_dataset.xml", DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		String roleId = "admin";
//		String roleName = "test";
//
//		RoleEntity roleEntity = new RoleEntity(roleId, roleName);
//
//		try {
//			roleRepository.insert(roleEntity);
//
//		} catch (Exception e) {
//			assertThat(e, is(instanceOf(DataIntegrityViolationException.class)));
//		}
//
//	}
//
//	@Test
//	public void updateOk() {
//
//		try {
//			PostgreDataManager.dataSet("./data/setup_dataset.xml", DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		String roleId = "admin";
//		String roleName = "ROLE_TEST";
//
//		RoleEntity roleEntity = new RoleEntity(roleId, roleName);
//
//		//テスト実施メソッド
//		int result = roleRepository.update(roleEntity);
//		//検証処理
//		assertEquals(1, result);
//
//	}
//
//	@Test
//	public void updateNgNoPk() {
//
//		try {
//			PostgreDataManager.dataSet("./data/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		String roleId = "admin";
//		String roleName = "ROLE_TEST";
//
//		RoleEntity roleEntity = new RoleEntity(roleId, roleName);
//
//		//テスト実施メソッド
//		int result = roleRepository.update(roleEntity);
//		//検証処理
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void deleteOk() {
//
//		try {
//			PostgreDataManager.dataSet("./data/setup_dataset.xml", DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		int result = roleRepository.delete("admin");
//		assertEquals(1, result);
//	}
//
//	@Test
//	public void deleteNgNoPk() {
//
//		try {
//			PostgreDataManager.dataSet("./data/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		int result = roleRepository.delete("admin");
//		assertEquals(0, result);
//	}
//
//}
