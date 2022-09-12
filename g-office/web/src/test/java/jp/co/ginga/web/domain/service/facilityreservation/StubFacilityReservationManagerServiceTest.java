package jp.co.ginga.web.domain.service.facilityreservation;

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
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StubFacilityReservationManagerServiceTest {

	/**
	 * テスト実施クラス
	 */
	@Autowired
	FacilityReservationManagerService facilityReservationManagerService;

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
	 * getFacility(); 正常系001 施設あり、種別あり
	 */
	@Test
	public void testGetFacility_normal_001() {
		System.out.println("testGetFacility_normal_001");

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

		FacilityTypeRepository facilityTypeRepository = new StubFacilityTypeRepository() {
			@Override
			public List<FacilityTypeEntity> findAll(){
				List<FacilityTypeEntity> facilityTypeEntityList = new ArrayList<FacilityTypeEntity>();
				FacilityTypeEntity facilityTypeEntity = new FacilityTypeEntity(
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

				facilityTypeEntityList.add(facilityTypeEntity);

				return facilityTypeEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.facilityReservationManagerService.setFacilityRepository(facilityRepository);
		this.facilityReservationManagerService.setFacilityTypeRepository(facilityTypeRepository);

		//テスト実施メソッド
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacility();

		//テスト検証メソッド
		assertEquals(1, result.getFacilityDtoList().size());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());

		assertEquals(1, result.getFacilityTypeDtoList().size());
		assertEquals(this.ft_facilityTypeId, result.getFacilityTypeDtoList().get(0).getFacilityTypeId());
		assertEquals(this.ft_name, result.getFacilityTypeDtoList().get(0).getName());
	}

	/**
	 * getFacilityList(); 正常系002 施設なし、種別あり
	 */
	@Test
	public void testGetFacility_normal_002() {
		System.out.println("testGetFacility_normal_002");

		//テスト用FacilityRepositoryスタブをインスタンス化
				FacilityRepository facilityRepository = new StubFacilityRepository() {
					@Override
					public List<FacilityEntity> findAll(){
						List<FacilityEntity> facilityEntityList = new ArrayList<FacilityEntity>();

						return facilityEntityList;
					}
				};

				FacilityTypeRepository facilityTypeRepository = new StubFacilityTypeRepository() {
					@Override
					public List<FacilityTypeEntity> findAll(){
						List<FacilityTypeEntity> facilityTypeEntityList = new ArrayList<FacilityTypeEntity>();
						FacilityTypeEntity facilityTypeEntity = new FacilityTypeEntity(
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

						facilityTypeEntityList.add(facilityTypeEntity);

						return facilityTypeEntityList;
					}
				};

				//スタブオブジェクトの設定
				this.facilityReservationManagerService.setFacilityRepository(facilityRepository);
				this.facilityReservationManagerService.setFacilityTypeRepository(facilityTypeRepository);


		//テスト実施メソッド
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacility();

		//テスト検証メソッド
		assertEquals(0, result.getFacilityDtoList().size());

		assertEquals(1, result.getFacilityTypeDtoList().size());
		assertEquals(this.ft_facilityTypeId, result.getFacilityTypeDtoList().get(0).getFacilityTypeId());
		assertEquals(this.ft_name, result.getFacilityTypeDtoList().get(0).getName());
	}

	/**
	 * getFacilityList(); 正常系003 施設なし、種別なし
	 */
	@Test
	public void testGetFacility_normal_003() {
		System.out.println("testGetFacility_normal_003");

		//テスト用FacilityRepositoryスタブをインスタンス化
				FacilityRepository facilityRepository = new StubFacilityRepository() {
					@Override
					public List<FacilityEntity> findAll(){
						List<FacilityEntity> facilityEntityList = new ArrayList<FacilityEntity>();

						return facilityEntityList;
					}
				};

				FacilityTypeRepository facilityTypeRepository = new StubFacilityTypeRepository() {
					@Override
					public List<FacilityTypeEntity> findAll(){
						List<FacilityTypeEntity> facilityTypeEntityList = new ArrayList<FacilityTypeEntity>();

						return facilityTypeEntityList;
					}
				};

				//スタブオブジェクトの設定
				this.facilityReservationManagerService.setFacilityRepository(facilityRepository);
				this.facilityReservationManagerService.setFacilityTypeRepository(facilityTypeRepository);


		//テスト実施メソッド
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacility();

		//テスト検証メソッド
		assertEquals(0, result.getFacilityDtoList().size());

		assertEquals(0, result.getFacilityTypeDtoList().size());
	}

	/**
	 * getFacility(); 異常系001 施設あり、種別なし
	 */
	@Test
	public void testGetFacility_abnormal_001() {
		System.out.println("testGetFacility_abnormal_001");

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

		FacilityTypeRepository facilityTypeRepository = new StubFacilityTypeRepository() {
			@Override
			public List<FacilityTypeEntity> findAll(){
				List<FacilityTypeEntity> facilityTypeEntityList = new ArrayList<FacilityTypeEntity>();

				return facilityTypeEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.facilityReservationManagerService.setFacilityRepository(facilityRepository);
		this.facilityReservationManagerService.setFacilityTypeRepository(facilityTypeRepository);

		//テスト実施メソッド
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacility();

		//テスト検証メソッド
		assertEquals(1, result.getFacilityDtoList().size());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());

		assertEquals(0, result.getFacilityTypeDtoList().size());
	}

	/**
	 * getFacilityByFacilityTypeId(); 正常系001 施設あり、種別あり
	 */
	@Test
	public void testGetFacilityByFacilityTypeId_normal_001() {
		System.out.println("testGetFacilityByFacilityTypeId_normal_001");

		//テスト用FacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {
			@Override
			public List<FacilityEntity> findByFacilityTypeId(int facilityTypeId){
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

		FacilityTypeRepository facilityTypeRepository = new StubFacilityTypeRepository() {
			@Override
			public List<FacilityTypeEntity> findAll(){
				List<FacilityTypeEntity> facilityTypeEntityList = new ArrayList<FacilityTypeEntity>();
				FacilityTypeEntity facilityTypeEntity = new FacilityTypeEntity(
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

				facilityTypeEntityList.add(facilityTypeEntity);

				return facilityTypeEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.facilityReservationManagerService.setFacilityRepository(facilityRepository);
		this.facilityReservationManagerService.setFacilityTypeRepository(facilityTypeRepository);

		//テスト実施メソッド
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacilityByFacilityTypeId(this.ft_facilityTypeId);

		//テスト検証メソッド
		assertEquals(1, result.getFacilityDtoList().size());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());
		assertEquals(this.ft_facilityTypeId, result.getFacilityDtoList().get(0).getFacilityTypeDto().getFacilityTypeId());

		assertEquals(1, result.getFacilityTypeDtoList().size());
		assertEquals(this.ft_facilityTypeId, result.getFacilityTypeDtoList().get(0).getFacilityTypeId());
		assertEquals(this.ft_name, result.getFacilityTypeDtoList().get(0).getName());
	}

	/**
	 * getFacilityByFacilityTypeId(); 正常系002 施設なし、種別あり
	 */
	@Test
	public void testGetFacilityByFacilityTypeId_normal_002() {
		System.out.println("testGetFacilityByFacilityTypeId_normal_002");

		//テスト用FacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {
			@Override
			public List<FacilityEntity> findByFacilityTypeId(int facilityTypeId){
				List<FacilityEntity> facilityEntityList = new ArrayList<FacilityEntity>();

				return facilityEntityList;
			}
		};

		FacilityTypeRepository facilityTypeRepository = new StubFacilityTypeRepository() {
			@Override
			public List<FacilityTypeEntity> findAll(){
				List<FacilityTypeEntity> facilityTypeEntityList = new ArrayList<FacilityTypeEntity>();
				FacilityTypeEntity facilityTypeEntity = new FacilityTypeEntity(
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

				facilityTypeEntityList.add(facilityTypeEntity);

				return facilityTypeEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.facilityReservationManagerService.setFacilityRepository(facilityRepository);
		this.facilityReservationManagerService.setFacilityTypeRepository(facilityTypeRepository);

		//テスト実施メソッド
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacilityByFacilityTypeId(this.ft_facilityTypeId);

		//テスト検証メソッド
		assertEquals(0, result.getFacilityDtoList().size());

		assertEquals(1, result.getFacilityTypeDtoList().size());
		assertEquals(this.ft_facilityTypeId, result.getFacilityTypeDtoList().get(0).getFacilityTypeId());
		assertEquals(this.ft_name, result.getFacilityTypeDtoList().get(0).getName());
	}

	/**
	 * getFacilityByFacilityTypeId(); 正常系003 施設なし、種別なし
	 */
	@Test
	public void testGetFacilityByFacilityTypeId_normal_003() {
		System.out.println("testGetFacilityByFacilityTypeId_normal_003");

		//テスト用FacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {
			@Override
			public List<FacilityEntity> findByFacilityTypeId(int facilityTypeId){
				List<FacilityEntity> facilityEntityList = new ArrayList<FacilityEntity>();

				return facilityEntityList;
			}
		};

		FacilityTypeRepository facilityTypeRepository = new StubFacilityTypeRepository() {
			@Override
			public List<FacilityTypeEntity> findAll(){
				List<FacilityTypeEntity> facilityTypeEntityList = new ArrayList<FacilityTypeEntity>();

				return facilityTypeEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.facilityReservationManagerService.setFacilityRepository(facilityRepository);
		this.facilityReservationManagerService.setFacilityTypeRepository(facilityTypeRepository);

		//テスト実施メソッド
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacilityByFacilityTypeId(this.ft_facilityTypeId);

		//テスト検証メソッド
		assertEquals(0, result.getFacilityDtoList().size());

		assertEquals(0, result.getFacilityTypeDtoList().size());
	}

	/**
	 * getFacilityByFacilityTypeId(); 異常系001 施設あり、種別なし
	 */
	@Test
	public void testGetFacilityByFacilityTypeId_abnormal_001() {
		System.out.println("testGetFacilityByFacilityTypeId_abnormal_001");

		//テスト用FacilityRepositoryスタブをインスタンス化
		FacilityRepository facilityRepository = new StubFacilityRepository() {
			@Override
			public List<FacilityEntity> findByFacilityTypeId(int facilityTypeId){
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

		FacilityTypeRepository facilityTypeRepository = new StubFacilityTypeRepository() {
			@Override
			public List<FacilityTypeEntity> findAll(){
				List<FacilityTypeEntity> facilityTypeEntityList = new ArrayList<FacilityTypeEntity>();

				return facilityTypeEntityList;
			}
		};

		//スタブオブジェクトの設定
		this.facilityReservationManagerService.setFacilityRepository(facilityRepository);
		this.facilityReservationManagerService.setFacilityTypeRepository(facilityTypeRepository);

		//テスト実施メソッド
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacilityByFacilityTypeId(this.ft_facilityTypeId);

		//テスト検証メソッド
		assertEquals(1, result.getFacilityDtoList().size());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());
		assertEquals(this.ft_facilityTypeId, result.getFacilityDtoList().get(0).getFacilityTypeDto().getFacilityTypeId());

		assertEquals(0, result.getFacilityTypeDtoList().size());
	}

}
