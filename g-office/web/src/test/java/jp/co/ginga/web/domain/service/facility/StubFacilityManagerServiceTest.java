package jp.co.ginga.web.domain.service.facility;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.ginga.infra.repository.facility.FacilityEntity;
import jp.co.ginga.infra.repository.facility.FacilityRepository;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeRepository;
import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.web.domain.service.stub.StubFacilityRepository;
import jp.co.ginga.web.domain.service.stub.StubFacilityTypeRepository;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StubFacilityManagerServiceTest {

	/**
	 * テスト実施クラス
	 */
	@Autowired
	FacilityManagerService service;

	/**
	 * テストデータ
	 */
	//登録者id、更新者id
	private String userId = "test";

	//施設種別用テストデータ
	private int ft_facilityTypeId = 1;
	private String ft_name = "test_type";
	private Date ft_insertDate = null;
	private UserEntity ft_insertUserEntity = null;
	private Date ft_updateDate = null;
	private UserEntity ft_updateUserEntity = null;
	private Date ft_deleteDate = null;
	private UserEntity ft_deleteUserEntity = null;
	private int ft_fgDelete = 0;

	//施設用テストデータ
	private int f_facilityId=1;
	private String f_name="test_facility";
	private int f_capacity = 100;
	private FacilityTypeEntity f_facilityTypeEntity = new FacilityTypeEntity(
			this.ft_facilityTypeId,
			this.ft_name,
			this.ft_insertDate,
			this.ft_insertUserEntity,
			this.ft_updateDate,
			this.ft_updateUserEntity,
			this.ft_deleteDate,
			this.ft_deleteUserEntity,
			this.ft_fgDelete
			);
	private Timestamp f_insertDate = null;
	private UserEntity f_insertUserEntity = new UserEntity();

	private Timestamp f_updateDate = null;
	private UserEntity f_updateUserEntity = new UserEntity();

	/**
	 * テスト用Dto
	 */
	private UserDto ft_insertUserDto = null;
	private UserDto f_insertUserDto = new UserDto(this.userId, null, null, null, null, null, null, null, 0, false, false, null, null, null, null);
	private FacilityTypeDto f_facilityTypeDto = new FacilityTypeDto(
			this.ft_facilityTypeId,
			this.ft_name,
			this.ft_insertDate,
			this.ft_insertUserDto
			);

	/**
	 * getFacilityList(); 正常系001 リストサイズ1
	 */
	@Test
	public void testGetFacilityList_normal_001() {
		System.out.println("testGetFacilityList_normal_001");

		//テスト用FacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {
			@Override
			public List<FacilityEntity> findAll(){
				List<FacilityEntity> facilityEntityList = new ArrayList<FacilityEntity>();
				FacilityEntity facilityEntity = new FacilityEntity(
						f_facilityId,
						f_name,
						f_capacity,
						f_facilityTypeEntity,
						f_insertDate,
						f_insertUserEntity,
						f_updateDate,
						f_updateUserEntity
					);
				facilityEntityList.add(facilityEntity);

				return facilityEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.service.setFacilityRepository(facilityRepository);

		//テスト実施メソッド
		FacilityManagerDto result = this.service.getFacilityList();

		//テスト検証メソッド
		//必要な情報だけを検証
		assertEquals(1, result.getFacilityDtoList().size());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());//
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());//
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());//
		assertEquals(this.f_facilityTypeDto, result.getFacilityDtoList().get(0).getFacilityTypeDto());//
		assertEquals(this.ft_facilityTypeId, result.getFacilityDtoList().get(0).getFacilityTypeDto().getFacilityTypeId());//
		assertEquals(this.ft_name, result.getFacilityDtoList().get(0).getFacilityTypeDto().getName());//
		assertEquals(this.ft_insertUserDto, result.getFacilityDtoList().get(0).getFacilityTypeDto().getInsertUserDto());
		assertNull(result.getFacilityDtoList().get(0).getUserDto());
	}

	/**
	 * getFacilityList(); 正常系002 データなし
	 */
	@Test
	public void testGetFacilityList_normal_002() {
		System.out.println("testGetFacilityList_normal_002");

		//テスト用FacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {
			@Override
			public List<FacilityEntity> findAll(){
				List<FacilityEntity> facilityEntityList = new ArrayList<FacilityEntity>();

				return facilityEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.service.setFacilityRepository(facilityRepository);

		//テスト実施メソッド
		FacilityManagerDto result = this.service.getFacilityList();

		//テスト検証メソッド
		assertEquals(0, result.getFacilityDtoList().size());
	}

	/**
	 * getFacilityDetailByFacilityId(int facilityId); 正常系001 データあり
	 */
	@Test
	public void testGetFacilityDetailByFacilityId_normal_001() {
		System.out.println("testGetFacilityDetailByFacilityId_normal_001");

		//テスト用FacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {
			@Override
			public FacilityEntity findByFacilityId(int facilityId){
				FacilityEntity facilityEntity = new FacilityEntity(
						f_facilityId,
						f_name,
						f_capacity,
						f_facilityTypeEntity,
						f_insertDate,
						f_insertUserEntity,
						f_updateDate,
						f_updateUserEntity
					);

				return facilityEntity;
			}
		};

		FacilityTypeRepository facilityTypeRepository = new StubFacilityTypeRepository() {
			@Override
			public List<FacilityTypeEntity> findAll(){
				List<FacilityTypeEntity> typeEntityList = new ArrayList<FacilityTypeEntity>();
				FacilityTypeEntity typeEntity = new FacilityTypeEntity(
						ft_facilityTypeId,
						ft_name,
						ft_insertDate,
						ft_insertUserEntity,
						ft_updateDate,
						ft_updateUserEntity,
						ft_deleteDate,
						ft_deleteUserEntity,
						ft_fgDelete
						);

				typeEntityList.add(typeEntity);

				return typeEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.service.setFacilityRepository(facilityRepository);
		this.service.setFacilityTypeRepository(facilityTypeRepository);

		//テスト実施メソッド
		FacilityManagerDto result = this.service.getFacilityDetailByFacilityId(this.f_facilityId);

		//テスト検証メソッド
		assertEquals(this.f_facilityId, result.getFacilityDto().getFacilityId());
		assertEquals(this.f_name, result.getFacilityDto().getName());
		assertEquals(this.f_capacity, result.getFacilityDto().getCapacity());
		assertEquals(this.f_facilityTypeDto, result.getFacilityDto().getFacilityTypeDto());
		assertEquals(this.ft_facilityTypeId, result.getFacilityDto().getFacilityTypeDto().getFacilityTypeId());
		assertNull(result.getFacilityDto().getUserDto());

		assertEquals(1, result.getFacilityTypeDtoList().size());
		assertEquals(this.ft_facilityTypeId, result.getFacilityTypeDtoList().get(0).getFacilityTypeId());
		assertEquals(this.ft_name, result.getFacilityTypeDtoList().get(0).getName());
		assertEquals(this.ft_insertDate, result.getFacilityTypeDtoList().get(0).getInsertDate());
		assertNull(result.getFacilityTypeDtoList().get(0).getInsertUserDto());
	}

	/**
	 * getFacilityDetailByFacilityId(int facilityId); 正常系002 データ無し
	 */
	@Test
	public void testGetFacilityDetailByFacilityId_normal_002() {
		System.out.println("testGetFacilityDetailByFacilityId_normal_001");

		//テスト用FacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {
			@Override
			public FacilityEntity findByFacilityId(int facilityId){
				FacilityEntity facilityEntity = new FacilityEntity();

				return facilityEntity;
			}
		};

		FacilityTypeRepository facilityTypeRepository = new StubFacilityTypeRepository() {
			@Override
			public List<FacilityTypeEntity> findAll(){
				List<FacilityTypeEntity> typeEntityList = new ArrayList<FacilityTypeEntity>();

				return typeEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.service.setFacilityRepository(facilityRepository);
		this.service.setFacilityTypeRepository(facilityTypeRepository);

		//テスト実施メソッド
		FacilityManagerDto result = this.service.getFacilityDetailByFacilityId(this.f_facilityId);

		//テスト検証メソッド
		assertEquals(0, result.getFacilityDto().getFacilityId());
		assertNull(result.getFacilityDto().getName());
		assertEquals(0, result.getFacilityDto().getCapacity());
		assertNull(result.getFacilityDto().getFacilityTypeDto());
		assertNull(result.getFacilityDto().getUserDto());
		assertEquals(0, result.getFacilityTypeDtoList().size());
	}

	/**
	 * getFacilityByName(String name); 正常系001 データあり
	 */
	@Test
	public void testGetFacilityByName_normal_001() {
		System.out.println("testGetFacilityByName_normal_001");

		//テスト用FacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {
			@Override
			public List<FacilityEntity> findByName(String name){
				List<FacilityEntity> facilityEntityList = new ArrayList<FacilityEntity>();
				FacilityEntity facilityEntity = new FacilityEntity(
						f_facilityId,
						f_name,
						f_capacity,
						f_facilityTypeEntity,
						f_insertDate,
						f_insertUserEntity,
						f_updateDate,
						f_updateUserEntity
					);

				facilityEntityList.add(facilityEntity);

				return facilityEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.service.setFacilityRepository(facilityRepository);

		//テスト実施メソッド
		FacilityManagerDto result = this.service.getFacilityByName(this.f_name);

		//テスト検証メソッド
		assertEquals(ServiceConst.THERE_IS_DATA, result.getResult());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());
		assertEquals(this.f_facilityTypeDto, result.getFacilityDtoList().get(0).getFacilityTypeDto());
		assertEquals(this.ft_facilityTypeId, result.getFacilityDtoList().get(0).getFacilityTypeDto().getFacilityTypeId());
		assertNull(result.getFacilityDtoList().get(0).getUserDto());
	}

	/**
	 * getFacilityByName(String name); 正常系001 データ無し
	 */
	@Test
	public void testGetFacilityByName_normal_002() {
		System.out.println("testGetFacilityByName_normal_002");

		//テスト用FacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {
			@Override
			public List<FacilityEntity> findByName(String name){
				List<FacilityEntity> facilityEntityList = new ArrayList<FacilityEntity>();

				return facilityEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.service.setFacilityRepository(facilityRepository);

		//テスト実施メソッド
		FacilityManagerDto result = this.service.getFacilityByName(this.f_name);

		//テスト検証メソッド
		assertEquals(ServiceConst.NO_DATA, result.getResult());
		assertEquals(0, result.getFacilityDtoList().size());
	}

//	/**
//	 * getFacilityByName(String name); 異常系001 データnull
//	 */
//	@Test
//	public void testGetFacilityByName_abnormal_001() {
//		System.out.println("testGetFacilityByName_abnormal_001");
//
//		//テスト用FacilityRepositoryスタブをインスタンス化
//		FacilityRepository facilityRepository = new StubFacilityRepository() {
//			@Override
//			public List<FacilityEntity> findByName(String name){
//				List<FacilityEntity> facilityEntityList = null;
//
//				return facilityEntityList;
//			}
//		};
//
//		//スタブオブジェクトの設定
//		this.service.setFacilityRepository(facilityRepository);
//
//		//テスト実施メソッド
//		FacilityManagerDto result = this.service.getFacilityByName(this.f_name);
//
//		//テスト検証メソッド
//		assertEquals(ServiceConst.ERROR, result.getResult());
//		assertNull(result.getFacilityDtoList());
//	}

	/**
	 * add(FacilityManagerDto facilityManagerDto4); 正常系001データあり
	 */
	@Test
	public void testAdd_normal_001() {
		System.out.println("testAdd_normal_001");

		// テストデータ
		FacilityManagerDto facilityManagerDto = new FacilityManagerDto();
		FacilityDto facilityDto = new FacilityDto(
				this.f_facilityId,
				this.f_name,
				this.f_capacity,
				this.f_facilityTypeDto,
				this.f_insertUserDto
				);
		facilityManagerDto.setFacilityDto(facilityDto);

		// テスト用のUserRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int insert(FacilityEntity entity) {
				return 1;
			}
		};

		// スタブオブジェクトの設定
		service.setFacilityRepository(facilityRepository);

		// テスト実施メソッド
		FacilityManagerDto result = service.add(facilityManagerDto);

		// 検証メソッド
		assertEquals(ServiceConst.THERE_IS_DATA, result.getResult());
	}


	/**
	 * add(FacilityManagerDto facilityManagerDto); 異常系001 データ無し
	 */
	@Test
	public void testAdd_abnormal_001() {
		System.out.println("testAdd_abnormal_001");

		// テストデータ
		FacilityManagerDto facilityManagerDto = new FacilityManagerDto();
		FacilityDto facilityDto = new FacilityDto();
		facilityDto.setUserDto(new UserDto());
		facilityDto.setFacilityTypeDto(new FacilityTypeDto());
		facilityManagerDto.setFacilityDto(facilityDto);

		// テスト用のFacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int insert(FacilityEntity entity) {
				return 0;
			}
		};

		// スタブオブジェクトの設定
		service.setFacilityRepository(facilityRepository);

		// テスト実施メソッド
		FacilityManagerDto result = service.add(facilityManagerDto);

		// 検証メソッド
		assertEquals(ServiceConst.NO_DATA, result.getResult());
	}

	/**
	 * update(FacilityManagerDto facilityManagerDto); 正常系 001 データあり
	 */
	@Test
	public void testUpdate_normal_001() {
		System.out.println("testUpdate_normal_001");

		// テストデータ
		FacilityManagerDto facilityManagerDto = new FacilityManagerDto();
		FacilityDto facilityDto = new FacilityDto(
				this.f_facilityId,
				this.f_name,
				this.f_capacity,
				this.f_facilityTypeDto,
				this.f_insertUserDto
				);
		facilityManagerDto.setFacilityDto(facilityDto);
		facilityManagerDto.setOptimisticRockValue(
				this.f_facilityId+","+
				this.f_name+","+
				this.f_capacity+","+
				this.f_facilityTypeEntity+","+
				(this.f_insertDate == null ? "null" : this.f_insertDate.toString())+","+
				(this.f_insertUserEntity == null ? "null" : this.f_insertUserEntity.toString()) +","+
				(this.f_updateDate == null ? "null" : this.f_updateDate.toString())+","+
				(this.f_updateUserEntity == null ? "null" : this.f_updateUserEntity.toString())
				);

		// テスト用のFacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int update(FacilityEntity entity) {
				return 1;
			}

			// スタブのメソッドを書き換え
			@Override
			public FacilityEntity findByFacilityId(int facilityId) {

				FacilityEntity facilityEntity = new FacilityEntity(
						f_facilityId,
						f_name,
						f_capacity,
						f_facilityTypeEntity,
						f_insertDate,
						f_insertUserEntity,
						f_updateDate,
						f_updateUserEntity
						);

				return facilityEntity;
			}
		};

		// スタブオブジェクトの設定
		service.setFacilityRepository(facilityRepository);

		// テスト実施メソッド
		FacilityManagerDto result = service.update(facilityManagerDto);

		// 検証メソッド
		assertEquals(ServiceConst.OK, result.getResult());
	}

	/**
	 * update(FacilityManagerDto facilityManagerDto); 正常系 002 データあり楽観ロック
	 */
	@Test
	public void testUpdate_normal_002() {
		System.out.println("testUpdate_normal_002");

		// テストデータ
		FacilityManagerDto facilityManagerDto = new FacilityManagerDto();
		FacilityDto facilityDto = new FacilityDto(
				this.f_facilityId,
				this.f_name,
				this.f_capacity,
				this.f_facilityTypeDto,
				this.f_insertUserDto
				);
		facilityManagerDto.setFacilityDto(facilityDto);
		facilityManagerDto.setOptimisticRockValue(
				this.f_facilityId+","+
				this.f_name+"update"+","+
				this.f_capacity+","+
				this.f_facilityTypeEntity+","+
				(this.f_insertDate == null ? "null" : this.f_insertDate.toString())+","+
				(this.f_insertUserEntity == null ? "null" : this.f_insertUserEntity.toString()) +","+
				(this.f_updateDate == null ? "null" : this.f_updateDate.toString())+","+
				(this.f_updateUserEntity == null ? "null" : this.f_updateUserEntity.toString())
				);

		// テスト用のFacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {

			// スタブのメソッドを書き換え
//			@Override
//			public int update(FacilityEntity entity) {
//				return 1;
//			}

			// スタブのメソッドを書き換え
			@Override
			public FacilityEntity findByFacilityId(int facilityId) {

				FacilityEntity facilityEntity = new FacilityEntity(
						f_facilityId,
						f_name,
						f_capacity,
						f_facilityTypeEntity,
						f_insertDate,
						f_insertUserEntity,
						f_updateDate,
						f_updateUserEntity
						);

				return facilityEntity;
			}
		};

		// スタブオブジェクトの設定
		service.setFacilityRepository(facilityRepository);

		// テスト実施メソッド
		FacilityManagerDto result = service.update(facilityManagerDto);

		// 検証メソッド
		assertEquals(ServiceConst.OPTIMISTIC_ROCK_ERROR, result.getResult());
	}

	/**
	 * update(FacilityManagerDto facilityManagerDto); 異常系 001 データ無し
	 */
	@Test
	public void testUpdate_abnormal_001() {
		System.out.println("testUpdate_abnormal_001");

		// テストデータ
		FacilityManagerDto facilityManagerDto = new FacilityManagerDto();
		FacilityDto facilityDto = new FacilityDto();
		facilityDto.setUserDto(new UserDto());
		facilityDto.setFacilityTypeDto(new FacilityTypeDto());
		facilityManagerDto.setFacilityDto(facilityDto);
		facilityManagerDto.setOptimisticRockValue(new FacilityEntity().toString());

		// テスト用のFacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {

			// スタブのメソッドを書き換え
//			@Override
//			public int update(FacilityEntity entity) {
//				return 1;
//			}

			// スタブのメソッドを書き換え
			@Override
			public FacilityEntity findByFacilityId(int facilityId) {

				FacilityEntity facilityEntity = new FacilityEntity();

				return facilityEntity;
			}
		};

		// スタブオブジェクトの設定
		service.setFacilityRepository(facilityRepository);

		// テスト実施メソッド
		FacilityManagerDto result = service.update(facilityManagerDto);

		// 検証メソッド
		assertEquals(ServiceConst.ERROR, result.getResult());
	}

	/**
	 * delete(FacilityManagerDto facilityManagerDto); 正常系001データあり
	 */

	@Test
	public void testDelete_normal_001() {
		System.out.println("testDelete_normal_001");

		// テストデータ
		FacilityManagerDto facilityManagerDto = new FacilityManagerDto();
		FacilityDto facilityDto = new FacilityDto(
				this.f_facilityId,
				this.f_name,
				this.f_capacity,
				this.f_facilityTypeDto,
				this.f_insertUserDto
				);
		facilityManagerDto.setFacilityDto(facilityDto);
		facilityManagerDto.setOptimisticRockValue(
				this.f_facilityId+","+
				this.f_name+","+
				this.f_capacity+","+
				this.f_facilityTypeEntity+","+
				(this.f_insertDate == null ? "null" : this.f_insertDate.toString())+","+
				(this.f_insertUserEntity == null ? "null" : this.f_insertUserEntity.toString()) +","+
				(this.f_updateDate == null ? "null" : this.f_updateDate.toString())+","+
				(this.f_updateUserEntity == null ? "null" : this.f_updateUserEntity.toString())
				);

		// テスト用のFacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int delete(int facilityId) {
				return 1;
			}
		};

		// スタブオブジェクトの設定
		service.setFacilityRepository(facilityRepository);

		// テスト実施メソッド
		boolean result = service.delete(facilityManagerDto);

		// 検証メソッド
		assertEquals(true, result);
	}

	/**
	 * delete(FacilityManagerDto facilityManagerDto); 異常系001データなし
	 */
	@Test
	public void testDelete_abnormal_001() {
		System.out.println("testDelete_abnormal_001");

		// テストデータ
		FacilityManagerDto facilityManagerDto = new FacilityManagerDto();
		facilityManagerDto.setFacilityDto(new FacilityDto());

		// テスト用のFacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {

			// スタブのメソッドを書き換え
			@Override
			public int delete(int facilityId) {
				return 0;
			}
		};

		// スタブオブジェクトの設定
		service.setFacilityRepository(facilityRepository);

		// テスト実施メソッド
		boolean result = service.delete(facilityManagerDto);

		// 検証メソッド
		assertEquals(false, result);
	}
}
