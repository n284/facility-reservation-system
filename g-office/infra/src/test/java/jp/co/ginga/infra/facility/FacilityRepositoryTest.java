package jp.co.ginga.infra.facility;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

import jp.co.ginga.infra.repository.facility.FacilityEntity;
import jp.co.ginga.infra.repository.facility.FacilityRepository;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.infra.util.PostgreDataManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class FacilityRepositoryTest {
	@Autowired
	FacilityRepository facilityRepository;

	/**
	 * 施設情報 リポジトリ 全件検索処理 データ全体の場合
	 */
	@Test
	public void findAllMultipleData() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/Infra/repository/facility/setup_dataset_facility.xml", DatabaseOperation.CLEAN_INSERT);
			PostgreDataManager.dataSet("./data/Infra/repository/facilityType/setup_dataset_facility_type.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		List<FacilityEntity> entityList = this.facilityRepository.findAll();

		assertEquals(entityList.size(), 2);

		for(int i = 0; i < entityList.size(); i++) {
			assertEquals((i+1), entityList.get(i).getFacilityId());
			assertEquals("facility" + (i+1), entityList.get(i).getName());
			assertEquals((i+1), entityList.get(i).getCapacity());
			assertEquals((i+1), entityList.get(i).getFacilityTypeEntity().getFacilityTypeId());
			assertEquals(true, Timestamp.valueOf("2022-07-01 10:00:00").equals(entityList.get(i).getInsertDate()));
			assertEquals("system", entityList.get(i).getInsertUserEntity().getUserId());
		}
	}

	/**
	 * 施設情報 リポジトリ 全件検索処理 データ1体の場合
	 */
	@Test
	public void findAllSingleData() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/facility/setup_single_dataset_facility.xml", DatabaseOperation.CLEAN_INSERT);
			PostgreDataManager.dataSet("./data/Infra/repository/facilityType/setup_single_dataset_facility_type.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		List<FacilityEntity> entityList = this.facilityRepository.findAll();

		assertEquals(1, entityList.size());

		assertEquals(1, entityList.get(0).getFacilityId());
		assertEquals("facility1", entityList.get(0).getName());
		assertEquals(1, entityList.get(0).getCapacity());
		assertEquals(1, entityList.get(0).getFacilityTypeEntity().getFacilityTypeId());
		assertEquals(true, Timestamp.valueOf("2022-07-01 10:00:00").equals(entityList.get(0).getInsertDate()));
		assertEquals("system", entityList.get(0).getInsertUserEntity().getUserId());
	}

	/**
	 * 施設情報 リポジトリ 全件検索処理 データ0件の場合
	 */
	@Test
	public void findAllNoData() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/util/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
			// fail()：強制的に失敗になる
		}

		List<FacilityEntity> entityList = this.facilityRepository.findAll();

		assertEquals(entityList.size(), 0);
	}

	/**
	 * 施設情報 リポジトリ 施設idによる検索処理 データがある場合
	 */
	@Test
	public void findByFacilityIdData() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/facility/setup_dataset_facility.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		FacilityEntity facilityEntity= this.facilityRepository.findByFacilityId(1);

		assertEquals(1, facilityEntity.getFacilityId());
		assertEquals("facility1", facilityEntity.getName());
		assertEquals(1, facilityEntity.getCapacity());
		assertEquals(1, facilityEntity.getFacilityTypeEntity().getFacilityTypeId());
		assertEquals(true, Timestamp.valueOf("2022-07-01 10:00:00").equals(facilityEntity.getInsertDate()));
		assertEquals("system", facilityEntity.getInsertUserEntity().getUserId());
	}

	/**
	 * 施設情報 リポジトリ 施設idによる検索処理 データがない場合
	 */
	@Test
	public void findByFacilityIdNoData() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/util/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		FacilityEntity facilityEntity= this.facilityRepository.findByFacilityId(1);

		assertNull(facilityEntity);
	}

	/**
	 * 施設情報 リポジトリ 施設種別idによる検索処理 データがある場合
	 */
	@Test
	public void findByFacilityTypeIdData() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/facility/setup_dataset_facility.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		List<FacilityEntity> entityList= this.facilityRepository.findByFacilityTypeId(1);

		assertEquals(entityList.size(), 1);

		assertEquals(1, entityList.get(0).getFacilityId());
		assertEquals("facility1", entityList.get(0).getName());
		assertEquals(1, entityList.get(0).getCapacity());
		assertEquals(1, entityList.get(0).getFacilityTypeEntity().getFacilityTypeId());
		assertEquals(true, Timestamp.valueOf("2022-07-01 10:00:00").equals(entityList.get(0).getInsertDate()));
		assertEquals("system", entityList.get(0).getInsertUserEntity().getUserId());
	}

	/**
	 * 施設情報 リポジトリ 施設idによる検索処理 データがない場合
	 */
	@Test
	public void findByFacilityTypeIdNoData() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/util/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		List<FacilityEntity> entityList= this.facilityRepository.findByFacilityTypeId(1);

		assertEquals(entityList.size(), 0);
	}

	/**
	 * 施設情報 リポジトリ 施設名による検索処理 データがある場合
	 */
	@Test
	public void findByNameData() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/facility/setup_dataset_facility.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		List<FacilityEntity> entityList= this.facilityRepository.findByName("facility1");

		assertEquals(entityList.size(), 1);

		assertEquals(1, entityList.get(0).getFacilityId());
		assertEquals("facility1", entityList.get(0).getName());
		assertEquals(1, entityList.get(0).getCapacity());
		assertEquals(1, entityList.get(0).getFacilityTypeEntity().getFacilityTypeId());
		assertEquals(true, Timestamp.valueOf("2022-07-01 10:00:00").equals(entityList.get(0).getInsertDate()));
		assertEquals("system", entityList.get(0).getInsertUserEntity().getUserId());
	}

	/**
	 * 施設情報 リポジトリ 施設名による検索処理 データがない場合
	 */
	@Test
	public void findByNameNoData() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/Infra/repository/util/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		List<FacilityEntity> entityList= this.facilityRepository.findByFacilityTypeId(1);

		assertEquals(entityList.size(), 0);
	}

	/**
	 * 施設情報 リポジトリ 施設登録処理 重複無し
	 */
	@Test
	public void insertOk() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/facility/setup_dataset_facility.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		//テストデータ
		String datetime = "2022-07-01 10:00:00";
		int facilityId=3;
		String name = "facility3";
		int capacity = 3;
		int facilityTypeId = 3;
		Timestamp insertDate = Timestamp.valueOf(datetime);
		String insertUserId = "system";
		Timestamp updateDate = Timestamp.valueOf(datetime);
		String updateUserId = "system";
		FacilityTypeEntity facilityTypeEntity = new FacilityTypeEntity();
		UserEntity insertUserEntity = new UserEntity();
		UserEntity updateUserEntity = new UserEntity();
		facilityTypeEntity.setFacilityTypeId(facilityTypeId);
		insertUserEntity.setUserId(insertUserId);
		updateUserEntity.setUserId(updateUserId);

		//登録用
		FacilityEntity facilityEntity = new FacilityEntity(
				facilityId,
				name,
				capacity,
				facilityTypeEntity,
				insertDate,
				insertUserEntity,
				updateDate,
				updateUserEntity
				);

		int result = this.facilityRepository.insert(facilityEntity);
		assertEquals(1, result);
		FacilityEntity findFacilityEntity = this.facilityRepository.findByFacilityId(facilityId);
		assertEquals(facilityEntity.getFacilityId(), findFacilityEntity.getFacilityId());
		assertEquals(true,facilityEntity.getName().equals(findFacilityEntity.getName()));
		assertEquals(facilityEntity.getCapacity(), findFacilityEntity.getCapacity());
		assertEquals(facilityEntity.getFacilityTypeEntity().getFacilityTypeId(), findFacilityEntity.getFacilityTypeEntity().getFacilityTypeId());
		assertEquals(true, facilityEntity.getInsertDate().equals(findFacilityEntity.getInsertDate()));
		assertEquals(true,facilityEntity.getInsertUserEntity().getUserId().equals( findFacilityEntity.getInsertUserEntity().getUserId()));
		assertEquals(true, facilityEntity.getUpdateDate().equals(findFacilityEntity.getUpdateDate()));
		assertEquals(true,facilityEntity.getInsertUserEntity().getUserId().equals(findFacilityEntity.getUpdateUserEntity().getUserId()));
	}

	/**
	 * 施設情報 リポジトリ 施設登録処理 重複あり
	 */
	@Test
	public void insertNo() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/facility/setup_dataset_facility.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		//テストデータ
		String datetime = "2022-07-01 10:00:00";
		int facilityId=3;
		String name = "facility1";
		int capacity = 3;
		int facilityTypeId = 3;
		Timestamp insertDate = Timestamp.valueOf(datetime);
		String insertUserId = "system";
		Timestamp updateDate = Timestamp.valueOf(datetime);
		String updateUserId = "system";
		FacilityTypeEntity facilityTypeEntity = new FacilityTypeEntity();
		UserEntity insertUserEntity = new UserEntity();
		UserEntity updateUserEntity = new UserEntity();
		facilityTypeEntity.setFacilityTypeId(facilityTypeId);
		insertUserEntity.setUserId(insertUserId);
		updateUserEntity.setUserId(updateUserId);

		//登録用
		FacilityEntity facilityEntity = new FacilityEntity(
				facilityId,
				name,
				capacity,
				facilityTypeEntity,
				insertDate,
				insertUserEntity,
				updateDate,
				updateUserEntity
				);
		try {
			int result = this.facilityRepository.insert(facilityEntity);
			assertEquals(1, result);
		}catch(Exception e) {
			// スキーマ違反例外を期待
			assertThat(e, is(instanceOf(DataIntegrityViolationException.class)));
		}

	}

	/**
	 * 施設情報 リポジトリ 施設登録処理 境界値 施設名 異常系 文字数 21
	 */
	@Test
	public void insertNg() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/facility/setup_dataset_facility.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		//テストデータ
		String datetime = "2022-07-01 10:00:00";
		int facilityId=3;
		String name = "123456789012345678901";
		int capacity = 3;
		int facilityTypeId = 3;
		Timestamp insertDate = Timestamp.valueOf(datetime);
		String insertUserId = "system";
		Timestamp updateDate = Timestamp.valueOf(datetime);
		String updateUserId = "system";
		FacilityTypeEntity facilityTypeEntity = new FacilityTypeEntity();
		UserEntity insertUserEntity = new UserEntity();
		UserEntity updateUserEntity = new UserEntity();
		facilityTypeEntity.setFacilityTypeId(facilityTypeId);
		insertUserEntity.setUserId(insertUserId);
		updateUserEntity.setUserId(updateUserId);

		//登録用
		FacilityEntity facilityEntity = new FacilityEntity(
				facilityId,
				name,
				capacity,
				facilityTypeEntity,
				insertDate,
				insertUserEntity,
				updateDate,
				updateUserEntity
				);
		try {
			int result = this.facilityRepository.insert(facilityEntity);
			assertEquals(1, result);
			fail("例外発生なし");
		}catch(Exception e) {
			// スキーマ違反例外を期待
			assertThat(e, is(instanceOf(DataIntegrityViolationException.class)));
		}

	}

	/**
	 * 施設情報 リポジトリ 施設更新処理 全データ更新処理
	 */
	@Test
	public void updateOk() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/facility/setup_dataset_facility.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		//テストデータ
		String datetime = "2022-07-01 10:00:00";
		int facilityId=1;
		String name = "facility3";
		int capacity = 2;
		int facilityTypeId = 2;
		Timestamp insertDate = Timestamp.valueOf(datetime);
		String insertUserId = "system";
		Timestamp updateDate = Timestamp.valueOf(datetime);
		String updateUserId = "system";
		FacilityTypeEntity facilityTypeEntity = new FacilityTypeEntity();
		UserEntity insertUserEntity = new UserEntity();
		UserEntity updateUserEntity = new UserEntity();
		facilityTypeEntity.setFacilityTypeId(facilityTypeId);
		insertUserEntity.setUserId(insertUserId);
		updateUserEntity.setUserId(updateUserId);

		//登録用
		FacilityEntity facilityEntity = new FacilityEntity(
				facilityId,
				name,
				capacity,
				facilityTypeEntity,
				insertDate,
				insertUserEntity,
				updateDate,
				updateUserEntity
				);

		int result = this.facilityRepository.update(facilityEntity);
		assertEquals(1, result);

		FacilityEntity findFacilityEntity = this.facilityRepository.findByFacilityId(facilityId);
		assertEquals(facilityEntity.getFacilityId(), findFacilityEntity.getFacilityId());
		assertEquals(true,facilityEntity.getName().equals(findFacilityEntity.getName()));
		assertEquals(facilityEntity.getCapacity(), findFacilityEntity.getCapacity());
		assertEquals(facilityEntity.getFacilityTypeEntity().getFacilityTypeId(), findFacilityEntity.getFacilityTypeEntity().getFacilityTypeId());
		assertEquals(true, facilityEntity.getInsertDate().equals(findFacilityEntity.getInsertDate()));
		assertEquals(true,facilityEntity.getInsertUserEntity().getUserId().equals( findFacilityEntity.getInsertUserEntity().getUserId()));
		assertEquals(true, facilityEntity.getUpdateDate().equals(findFacilityEntity.getUpdateDate()));
		assertEquals(true,facilityEntity.getInsertUserEntity().getUserId().equals(findFacilityEntity.getUpdateUserEntity().getUserId()));
	}

	/**
	 * 施設情報 リポジトリ 施設更新処理 境界値 ユーザ名 異常系 文字数 21
	 */
	@Test
	public void updateNg() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/facility/setup_dataset_facility.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}

		//テストデータ
		String datetime = "2022-07-01 12:00:00";
		int facilityId=1;
		String name = "123456789012345678901";
		int capacity = 1;
		int facilityTypeId = 1;
		Timestamp insertDate = Timestamp.valueOf(datetime);
		String insertUserId = "system";
		Timestamp updateDate = Timestamp.valueOf(datetime);
		String updateUserId = "system";
		FacilityTypeEntity facilityTypeEntity = new FacilityTypeEntity();
		UserEntity insertUserEntity = new UserEntity();
		UserEntity updateUserEntity = new UserEntity();
		facilityTypeEntity.setFacilityTypeId(facilityTypeId);
		insertUserEntity.setUserId(insertUserId);
		updateUserEntity.setUserId(updateUserId);

		//登録用
		FacilityEntity facilityEntity = new FacilityEntity(
				facilityId,
				name,
				capacity,
				facilityTypeEntity,
				insertDate,
				insertUserEntity,
				updateDate,
				updateUserEntity
				);

		try {
			int result = this.facilityRepository.update(facilityEntity);
			assertEquals(1, result);
			fail("例外発生なし");
		}catch(Exception e) {
			// スキーマ違反例外を期待
			assertThat(e, is(instanceOf(DataIntegrityViolationException.class)));
		}
	}

	/**
	 * 施設情報 リポジトリ 施設削除処理 データあり
	 */
	@Test
	public void deleteOk() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/facility/setup_dataset_facility.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}
		int result = this.facilityRepository.delete(1);
		assertEquals(1, result);

	}

	/**
	 * 施設情報 リポジトリ 施設削除処理 データなし
	 */
	@Test
	public void deleteNo() {
		try {
			// CLEAN_INSERTでpgadminに登録しているデータを削除して再セットする
			PostgreDataManager.dataSet("./data/infra/repository/util/setup_nodata.xml", DatabaseOperation.CLEAN_INSERT);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("データセット失敗");
			fail();
		}
		int result = this.facilityRepository.delete(1);
		assertEquals(0, result);
	}

}
