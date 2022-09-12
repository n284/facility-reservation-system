package jp.co.ginga.infra.user;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.infra.repository.user.UserRepository;
import jp.co.ginga.infra.util.PostgreDataManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	/**
	 * ユーザー情報 リポジトリ 全件検索処理 データ全件の場合
	 */

	@Test
	public void findAllMultipleData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
					DatabaseOperation.CLEAN_INSERT);
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		List<UserEntity> list = userRepository.findAll();

		assertEquals(list.size(), 2);

		for (int i = 0; i < list.size(); i++) {
			assertEquals("system" + (i + 1), list.get(i).getUserId());
			assertEquals("システム管理者" + (i + 1), list.get(i).getUserName());
			assertEquals("pass" + (i + 1), list.get(i).getPassword());
		}

	}

	/**
	 * ユーザー情報 リポジトリ 全件検索処理 データが1件の場合
	 */

	@Test
	public void findAllSingleData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/user/setup_single_dataset_user.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		List<UserEntity> list = userRepository.findAll();

		assertEquals(list.size(), 1);
		assertEquals("system1", list.get(0).getUserId());
		assertEquals("システム管理者1", list.get(0).getUserName());
		assertEquals("pass1", list.get(0).getPassword());

	}

	/**
	 * ユーザー情報 リポジトリ 全件検索処理 データが0件の場合
	 */
	@Test
	public void findAllNoData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/util/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
			// fail()：強制的に失敗になる
		}

		List<UserEntity> list = userRepository.findAll();

		assertEquals(list.size(), 0);

	}

	/**
	 * ユーザー情報 リポジトリ ユーザIDによる検索処理 データがある場合
	 */
	@Test
	public void findByUserIdData() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		UserEntity result = userRepository.findByUserId("system1");

		assertEquals("system1", result.getUserId());
		assertEquals("システム管理者1", result.getUserName());
		assertEquals("pass1", result.getPassword());

	}

	/**
	 * ユーザー情報 リポジトリ ユーザIDによる検索処理 データが0件の場合
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

		UserEntity result = userRepository.findByUserId("system1");

		assertEquals(null, result);

	}

	/**
	 * ユーザー情報 リポジトリ 登録処理 重複なし
	 */
	@Test
	public void insertOk() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

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

		UserEntity usEntity = new UserEntity(userId, userName, password, passUpdateDate, gender, birthday, contact,
				mailAddress, loginMissTimes, unlock, enabled, userDueDate, insertDate, updateDate);

		// テスト実施メソッド
		int result = userRepository.insert(usEntity);
		// 検証処理
		assertEquals(1, result);

		UserEntity result1 = userRepository.findByUserId("system3");
		// String(参照型)
		assertEquals(true, usEntity.getUserId().equals(result1.getUserId()));
		assertEquals(true, usEntity.getUserName().equals(result1.getUserName()));
		assertEquals(true, usEntity.getPassword().equals(result1.getPassword()));
		// Timestamp
		assertEquals(true, usEntity.getPassUpdateDate().equals(result1.getPassUpdateDate()));
		// String(参照型)
		assertEquals(true, usEntity.getGender().equals(result1.getGender()));
		assertEquals(true, usEntity.getBirthday().equals(result1.getBirthday()));
		assertEquals(true, usEntity.getContact().equals(result1.getContact()));
		assertEquals(true, usEntity.getMailAddress().equals(result1.getMailAddress()));
		// int(基本データ型)
		assertEquals(usEntity.getLoginMissTimes(), result1.getLoginMissTimes());
		// boolean(基本データ型)
		assertEquals(usEntity.isUnlock(), result1.isUnlock());
		assertEquals(usEntity.isEnabled(), result1.isEnabled());
		// Timestamp
		assertEquals(false, usEntity.getInsertDate().equals(result1.getInsertDate()));

	}

	/**
	 * ユーザー情報 リポジトリ 登録処理 重複あり
	 */
	@Test
	public void insertNo() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		String datetime = "2019-05-01 01:02:03";

		String userId = "system1";
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

		UserEntity usEntity = new UserEntity(userId, userName, password, passUpdateDate, gender, birthday, contact,
				mailAddress, loginMissTimes, unlock, enabled, userDueDate, insertDate, updateDate);

		try {
			// テスト実施メソッド
			userRepository.insert(usEntity);
			fail("例外発生なし");
		} catch (Exception e) {
			// スキーマ違反例外を期待
			assertThat(e, is(instanceOf(DataIntegrityViolationException.class)));
		}

	}

	/**
	 * ユーザー情報 リポジトリ 登録処理 境界値 ユーザ名 異常系 文字数 21
	 */
	@Test
	public void insertNg() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		String datetime = "2019-05-01 01:02:03";

		String userId = "system3";
		String userName = "111111111111111111111";
		String password = "pass1";
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

		UserEntity usEntity = new UserEntity(userId, userName, password, passUpdateDate, gender, birthday, contact,
				mailAddress, loginMissTimes, unlock, enabled, userDueDate, insertDate, updateDate);
		try {
			// テスト実施メソッド
			userRepository.insert(usEntity);
			fail("例外発生なし");
		} catch (Exception e) {
			// スキーマ違反例外を期待
			assertThat(e, is(instanceOf(DataIntegrityViolationException.class)));
		}

	}

	/**
	 * ユーザー情報 リポジトリ 更新処理 全データ更新処理
	 */

	@Test
	public void updateOk() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		String datetime = "2019-05-01 01:02:03";

		String userId = "system1";
		String userName = "システム管理者2";
		String password = "pass3";
		Timestamp passUpdateDate = Timestamp.valueOf(datetime);
		String gender = "男";
		String birthday = "1月1日";
		String contact = "a";
		String mailAddress = "mail";
		int loginMissTimes = 1;
		boolean unlock = false;
		boolean enabled = false;
		Timestamp userDueDate = Timestamp.valueOf(datetime);
		Timestamp insertDate = Timestamp.valueOf(datetime);
		Timestamp updateDate = Timestamp.valueOf(datetime);

		UserEntity usEntity = new UserEntity(userId, userName, password, passUpdateDate, gender, birthday, contact,
				mailAddress, loginMissTimes, unlock, enabled, userDueDate, insertDate, updateDate);

		// テスト実施メソッド
		int result = userRepository.update(usEntity);
		// 検証処理
		assertEquals(1, result);
	}

	/**
	 * ユーザー情報 リポジトリ 更新処理 unlock+enabled更新処理(SQL if文以外)
	 */

	@Test
	public void updateOkIfNo() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		String userId = "system1";
		String userName = "システム管理者1";
		String password = "pass1";
		Timestamp passUpdateDate = null;
		String gender = null;
		String birthday = null;
		String contact = null;
		String mailAddress = null;
		int loginMissTimes = 1;
		boolean unlock = false;
		boolean enabled = false;
		Timestamp userDueDate = null;
		Timestamp insertDate = null;
		Timestamp updateDate = null;

		UserEntity usEntity = new UserEntity(userId, userName, password, passUpdateDate, gender, birthday, contact,
				mailAddress, loginMissTimes, unlock, enabled, userDueDate, insertDate, updateDate);

		// テスト実施メソッド
		int result = userRepository.update(usEntity);
		// 検証処理
		assertEquals(1, result);
	}

	/**
	 * ユーザー情報 リポジトリ 更新処理 userName他更新処理(SQL if文のみ)
	 */

	@Test
	public void updateOkIfOk() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		String datetime = "2019-05-01 01:02:03";

		String userId = "system1";
		String userName = "システム管理者3";
		String password = "pass3";
		Timestamp passUpdateDate = Timestamp.valueOf(datetime);
		String gender = "男";
		String birthday = "1月1日";
		String contact = "a";
		String mailAddress = "mail";
		int loginMissTimes = 2;
		boolean unlock = true;
		boolean enabled = true;
		Timestamp userDueDate = Timestamp.valueOf(datetime);
		Timestamp insertDate = Timestamp.valueOf(datetime);
		Timestamp updateDate = Timestamp.valueOf(datetime);

		UserEntity usEntity = new UserEntity(userId, userName, password, passUpdateDate, gender, birthday, contact,
				mailAddress, loginMissTimes, unlock, enabled, userDueDate, insertDate, updateDate);

		// テスト実施メソッド
		int result = userRepository.update(usEntity);
		// 検証処理
		assertEquals(1, result);
	}

	/**
	 * ユーザー情報 リポジトリ 更新処理 境界値 ユーザ名 異常系 文字数 21
	 */

	@Test
	public void updateNg() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		// テストデータ
		String datetime = "2019-05-01 01:02:03";

		String userId = "system1";
		String userName = "システム管理者11111111111111";
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

		UserEntity usEntity = new UserEntity(userId, userName, password, passUpdateDate, gender, birthday, contact,
				mailAddress, loginMissTimes, unlock, enabled, userDueDate, insertDate, updateDate);

		try {
			// テスト実施メソッド
			userRepository.update(usEntity);
			fail("例外発生なし");
		} catch (Exception e) {
			// スキーマ違反例外を期待
			assertThat(e, is(instanceOf(DataIntegrityViolationException.class)));
		}
	}

	/**
	 * ユーザー情報アカウントロックステータス リポジトリ 更新処理
	 */

//	@Test
//	public void updateUnlockOk() {
//
//		try {
//			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
//					DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		//テスト実施メソッド
//		int result = userRepository.updateUnlock(true, "system1");
//
//		assertEquals(1, result);
//
//		UserEntity entity = userRepository.findByUserId("system1");
//
//		assertEquals(true, entity.isUnlock());
//
//	}
//
//	/**
//	 * ユーザー情報 有効ステータス リポジトリ 更新処理
//	 */
//	@Test
//	public void updateEnabledOk() {
//
//		try {
//			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
//					DatabaseOperation.CLEAN_INSERT);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("データセット失敗");
//			fail();
//		}
//
//		//テスト実施メソッド
//		int result = userRepository.updateEnabled(true, "system1");
//
//		assertEquals(1, result);
//
//		UserEntity entity = userRepository.findByUserId("system1");
//
//		assertEquals(true, entity.isEnabled());
//
//	}

	/**
	 * ユーザー情報 リポジトリ 削除処理
	 */

	@Test
	public void deleteOk() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/user/setup_dataset_user.xml",
					DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		int result = userRepository.delete("system1");

		assertEquals(1, result);

	}

	/**
	 * ユーザー情報 リポジトリ 削除処理 データなし
	 */

	@Test
	public void deleteNg() {

		try {
			PostgreDataManager.dataSet("./data/infra/repository/util/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		int result = userRepository.delete("system1");

		assertEquals(0, result);

	}

}