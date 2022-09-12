package jp.co.ginga.web.domain.service.facility;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jp.co.ginga.infra.repository.facility.FacilityEntity;
import jp.co.ginga.infra.repository.facility.FacilityRepository;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeRepository;
import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MockFacilityManagerServiceTest {

	/**
	 * テスト実施クラス
	 */
	@InjectMocks
	@Autowired
	FacilityManagerService service = new FacilityManagerServiceImpl();

	/**
	 * モック
	 */
	@Mock
	private FacilityRepository facilityRepository;

	@Mock
	private FacilityTypeRepository facilityTypeRepository;

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
		private Timestamp f_insertDate = null;
		private Timestamp f_updateDate = null;

		//施設用楽観ロック
		private String optimisticRockValue;
		private String changedOptimisticRockValue;

		/**
		 * テスト用Dto
		 */
		private UserDto insertUserDto;
		private UserDto updateUserDto;
		private FacilityDto facilityDto;
		private FacilityDto emptyFacilityDto;
		private FacilityTypeDto facilityTypeDto;
		private FacilityManagerDto addFacilityManagerDto;
		private FacilityManagerDto abnormalAddFacilityManagerDto;
		private FacilityManagerDto updateFacilityManagerDto;
		private FacilityManagerDto optimisticRockUpdateFacilityManagerDto;
		private FacilityManagerDto deleteFacilityManagerDto;
		/**
		 * テスト用Entity
		 */
		private UserEntity insertUserEntity;
		private UserEntity updateUserEntity;
		private FacilityEntity facilityEntity;
		private FacilityTypeEntity facilityTypeEntity;
		private List<FacilityEntity> facilityEntityList;
		private List<FacilityTypeEntity> facilityTypeEntityList;
		private FacilityEntity emptyFacilityEntity;
		private FacilityTypeEntity emptyFacilityTypeEntity;
		private List<FacilityEntity> emptyFacilityEntityList;
		private List<FacilityTypeEntity> emptyFacilityTypeEntityList;
	/**
	 * テストデータのセットアップ
	 */
	@BeforeEach
	public void createData() {
		//Entity
		this.insertUserEntity = new UserEntity();
		this.updateUserEntity = new UserEntity();
		this.facilityTypeEntity = new FacilityTypeEntity(
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
		this.facilityEntity = new FacilityEntity(
				this.f_facilityId,
				this.f_name,
				this.f_capacity,
				this.facilityTypeEntity,
				this.f_insertDate,
				this.insertUserEntity,
				this.f_updateDate,
				this.updateUserEntity
			);
		this.facilityEntityList = new ArrayList<FacilityEntity>();
		this.facilityEntityList.add(this.facilityEntity);
		this.facilityTypeEntityList = new ArrayList<FacilityTypeEntity>();
		this.facilityTypeEntityList.add(this.facilityTypeEntity);
		this.emptyFacilityEntity = new FacilityEntity();
		this.emptyFacilityTypeEntity = new FacilityTypeEntity();
		this.emptyFacilityEntityList = new ArrayList<FacilityEntity>();
		this.emptyFacilityTypeEntityList = new ArrayList<FacilityTypeEntity>();

		//楽観ロック値
		this.optimisticRockValue =
			this.f_facilityId+","+
			this.f_name+","+
			this.f_capacity+","+
			this.facilityTypeEntity+","+
			(this.f_insertDate == null ? "null" : this.f_insertDate.toString())+","+
			(this.insertUserEntity == null ? "null" : this.insertUserEntity.toString()) +","+
			(this.f_updateDate == null ? "null" : this.f_updateDate.toString())+","+
			(this.updateUserEntity == null ? "null" : this.updateUserEntity.toString())
		;
			this.changedOptimisticRockValue =
				this.f_facilityId+","+
				this.f_name+"_updated"+","+
				this.f_capacity+","+
				this.facilityTypeEntity+","+
				(this.f_insertDate == null ? "null" : this.f_insertDate.toString())+","+
				(this.insertUserEntity == null ? "null" : this.insertUserEntity.toString()) +","+
				(this.f_updateDate == null ? "null" : this.f_updateDate.toString())+","+
				(this.updateUserEntity == null ? "null" : this.updateUserEntity.toString())
			;

		//Dto
		this.insertUserDto = new UserDto();
		this.updateUserDto = new UserDto();
		this.facilityTypeDto = new FacilityTypeDto(
				this.ft_facilityTypeId,
				this.ft_name,
				this.ft_insertDate,
				null
				);
		this.facilityDto = new FacilityDto(
				this.f_facilityId,
				this.f_name,
				this.f_capacity,
				this.facilityTypeDto,
				this.insertUserDto
				);
		this.emptyFacilityDto = new FacilityDto();
		this.addFacilityManagerDto = new FacilityManagerDto();
		this.addFacilityManagerDto.setFacilityDto(this.facilityDto);
		this.abnormalAddFacilityManagerDto = new FacilityManagerDto();
		this.abnormalAddFacilityManagerDto.setFacilityDto(this.emptyFacilityDto);
		this.updateFacilityManagerDto = new FacilityManagerDto();
		this.updateFacilityManagerDto.setFacilityDto(this.facilityDto);
		this.updateFacilityManagerDto.setOptimisticRockValue(this.optimisticRockValue);
		this.optimisticRockUpdateFacilityManagerDto = new FacilityManagerDto();
		this.optimisticRockUpdateFacilityManagerDto.setFacilityDto(this.facilityDto);
		this.optimisticRockUpdateFacilityManagerDto.setOptimisticRockValue(this.changedOptimisticRockValue);
		this.deleteFacilityManagerDto = new FacilityManagerDto();
		this.deleteFacilityManagerDto.setFacilityDto(this.facilityDto);

	}

	/**
	 * Mock化
	 */
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * getFacilityList(); 正常系001 リストサイズ1
	 */
	@Test
	public void testGetFacilityList_normal_001() {
		System.out.println("testGetFacilityList_normal_001");

		//モックの設定
		when(this.facilityRepository.findAll()).thenReturn(this.facilityEntityList);

		//実行
		FacilityManagerDto result = this.service.getFacilityList();

		//検証処理
		assertEquals(1, result.getFacilityDtoList().size());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());
		assertEquals(this.facilityTypeDto, result.getFacilityDtoList().get(0).getFacilityTypeDto());
		assertEquals(this.ft_facilityTypeId, result.getFacilityDtoList().get(0).getFacilityTypeDto().getFacilityTypeId());
		assertNull(result.getFacilityDtoList().get(0).getUserDto());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findAll();
	}

	/**
	 * getFacilityList(); 正常系002 データなし
	 */
	@Test
	public void testGetFacilityList_normal_002() {
		System.out.println("testGetFacilityList_normal_002");

		//モックの設定
		when(this.facilityRepository.findAll()).thenReturn(this.emptyFacilityEntityList);

		//実行
		FacilityManagerDto result = this.service.getFacilityList();

		//検証処理
		assertEquals(0, result.getFacilityDtoList().size());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findAll();
	}


	/**
	 * getFacilityDetailByFacilityId(int facilityId); 正常系001 データあり
	 */
	@Test
	public void testGetFacilityDetailByFacilityId_normal_001() {
		System.out.println("testGetFacilityDetailByFacilityId_normal_001");

		//モックの設定
		when(this.facilityRepository.findByFacilityId(this.f_facilityId)).thenReturn(this.facilityEntity);
		when(this.facilityTypeRepository.findAll()).thenReturn(this.facilityTypeEntityList);

		//実行
		FacilityManagerDto result = this.service.getFacilityDetailByFacilityId(this.f_facilityId);

		//検証処理
		assertEquals(this.f_facilityId, result.getFacilityDto().getFacilityId());
		assertEquals(this.f_name, result.getFacilityDto().getName());
		assertEquals(this.f_capacity, result.getFacilityDto().getCapacity());
		assertEquals(this.facilityTypeDto, result.getFacilityDto().getFacilityTypeDto());
		assertEquals(this.ft_facilityTypeId, result.getFacilityDto().getFacilityTypeDto().getFacilityTypeId());
		assertNull(result.getFacilityDto().getUserDto());

		assertEquals(1, result.getFacilityTypeDtoList().size());
		assertEquals(this.ft_facilityTypeId, result.getFacilityTypeDtoList().get(0).getFacilityTypeId());
		assertEquals(this.ft_name, result.getFacilityTypeDtoList().get(0).getName());
		assertEquals(this.ft_insertDate, result.getFacilityTypeDtoList().get(0).getInsertDate());
		assertNull(result.getFacilityTypeDtoList().get(0).getInsertUserDto());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findByFacilityId(this.f_facilityId);
		verify(this.facilityTypeRepository, times(1)).findAll();
	}

	/**
	 * getFacilityDetailByFacilityId(int facilityId); 正常系002 データ無し
	 */
	@Test
	public void testGetFacilityDetailByFacilityId_normal_002() {
		System.out.println("testGetFacilityDetailByFacilityId_normal_001");

		//モックの設定
		when(this.facilityRepository.findByFacilityId(this.f_facilityId)).thenReturn(this.emptyFacilityEntity);
		when(this.facilityTypeRepository.findAll()).thenReturn(this.emptyFacilityTypeEntityList);

		//実行
		FacilityManagerDto result = this.service.getFacilityDetailByFacilityId(this.f_facilityId);

		//検証処理
		assertEquals(0, result.getFacilityDto().getFacilityId());
		assertNull(result.getFacilityDto().getName());
		assertEquals(0, result.getFacilityDto().getCapacity());
		assertNull(result.getFacilityDto().getFacilityTypeDto());
		assertNull(result.getFacilityDto().getUserDto());
		assertEquals(0, result.getFacilityTypeDtoList().size());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findByFacilityId(this.f_facilityId);
		verify(this.facilityTypeRepository, times(1)).findAll();
	}

	/**
	 * getFacilityByName(String name); 正常系001 データあり
	 */
	@Test
	public void testGetFacilityByName_normal_001() {
		System.out.println("testGetFacilityByName_normal_001");

		//モックの設定
		when(this.facilityRepository.findByName(this.f_name)).thenReturn(this.facilityEntityList);

		//実行
		FacilityManagerDto result = this.service.getFacilityByName(this.f_name);

		//検証処理
		assertEquals(ServiceConst.THERE_IS_DATA, result.getResult());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());
		assertEquals(this.facilityTypeDto, result.getFacilityDtoList().get(0).getFacilityTypeDto());
		assertEquals(this.ft_facilityTypeId, result.getFacilityDtoList().get(0).getFacilityTypeDto().getFacilityTypeId());
		assertNull(result.getFacilityDtoList().get(0).getUserDto());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findByName(this.f_name);
	}

	/**
	 * getFacilityByName(String name); 正常系001 データ無し
	 */
	@Test
	public void testGetFacilityByName_normal_002() {
		System.out.println("testGetFacilityByName_normal_002");
		//モックの設定
		when(this.facilityRepository.findByName(this.f_name)).thenReturn(this.emptyFacilityEntityList);

		//実行
		FacilityManagerDto result = this.service.getFacilityByName(this.f_name);

		//検証処理
		assertEquals(ServiceConst.NO_DATA, result.getResult());
		assertEquals(0, result.getFacilityDtoList().size());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findByName(this.f_name);
	}

	/**
	 * add(FacilityManagerDto facilityManagerDto4); 正常系001登録完了
	 */
	@Test
	public void testAdd_normal_001() {
		System.out.println("testAdd_normal_001");

		//モックの設定
		when(this.facilityRepository.insert(this.facilityEntity)).thenReturn(1);

		//実行
		FacilityManagerDto result = this.service.add(this.addFacilityManagerDto);

		//検証処理
		assertEquals(ServiceConst.THERE_IS_DATA, result.getResult());
		verify(this.facilityRepository, times(1)).insert(this.facilityEntity);
	}


	/**
	 * add(FacilityManagerDto facilityManagerDto); 異常系001 登録失敗
	 */
	@Test
	public void testAdd_abnormal_001() {
		System.out.println("testAdd_abnormal_001");

		//モックの設定
		when(this.facilityRepository.insert(this.facilityEntity)).thenReturn(0);

		//実行
		FacilityManagerDto result = this.service.add(this.addFacilityManagerDto);

		//検証処理
		assertEquals(ServiceConst.NO_DATA, result.getResult());
		verify(this.facilityRepository, times(1)).insert(this.facilityEntity);
	}

	/**
	 * update(FacilityManagerDto facilityManagerDto); 正常系 001 データあり
	 */
	@Test
	public void testUpdate_normal_001() {
		System.out.println("testUpdate_normal_001");

		//モックの設定
		when(this.facilityRepository.findByFacilityId(this.facilityDto.getFacilityId())).thenReturn(this.facilityEntity);
		when(this.facilityRepository.update(any())).thenReturn(1);

		// テスト実施メソッド
		FacilityManagerDto result = service.update(this.updateFacilityManagerDto);

		// 検証メソッド
		assertEquals(ServiceConst.OK, result.getResult());

		// モック呼び出し回数
		verify(this.facilityRepository, times(1)).findByFacilityId(this.facilityDto.getFacilityId());
		verify(this.facilityRepository, times(1)).update(any());
	}

	/**
	 * update(FacilityManagerDto facilityManagerDto); 正常系 002 データあり楽観ロック
	 */
	@Test
	public void testUpdate_normal_002() {
		System.out.println("testUpdate_normal_002");

		//モックの設定
		when(this.facilityRepository.findByFacilityId(this.facilityDto.getFacilityId())).thenReturn(this.facilityEntity);

		// テスト実施メソッド
		FacilityManagerDto result = service.update(this.optimisticRockUpdateFacilityManagerDto);

		// 検証メソッド
		assertEquals(ServiceConst.OPTIMISTIC_ROCK_ERROR, result.getResult());

		// モック呼び出し回数
		verify(this.facilityRepository, times(1)).findByFacilityId(this.facilityDto.getFacilityId());
	}

	/**
	 * update(FacilityManagerDto facilityManagerDto); 異常系 001 データ無し
	 */
	@Test
	public void testUpdate_abnormal_001() {
		System.out.println("testUpdate_abnormal_001");

		//モックの設定
		when(this.facilityRepository.findByFacilityId(this.facilityDto.getFacilityId())).thenReturn(this.facilityEntity);
		when(this.facilityRepository.update(any())).thenReturn(0);

		// テスト実施メソッド
		FacilityManagerDto result = service.update(this.updateFacilityManagerDto);

		// 検証メソッド
		assertEquals(ServiceConst.ERROR, result.getResult());

		// モック呼び出し回数
		verify(this.facilityRepository, times(1)).findByFacilityId(this.facilityDto.getFacilityId());
		verify(this.facilityRepository, times(1)).update(any());
	}

	/**
	 * delete(FacilityManagerDto facilityManagerDto); 正常系001データあり
	 */

	@Test
	public void testDelete_normal_001() {
		System.out.println("testDelete_normal_001");

		//モックの設定
		when(this.facilityRepository.delete(this.facilityDto.getFacilityId())).thenReturn(1);

		// テスト実施メソッド
		boolean result = this.service.delete(this.deleteFacilityManagerDto);

		// 検証メソッド
		assertEquals(true, result);

		// モック呼び出し回数
		verify(this.facilityRepository, times(1)).delete(this.facilityDto.getFacilityId());
	}

	/**
	 * delete(FacilityManagerDto facilityManagerDto); 異常系001データなし
	 */
	@Test
	public void testDelete_abnormal_001() {
		System.out.println("testDelete_abnormal_001");

		//モックの設定
		when(this.facilityRepository.delete(this.facilityDto.getFacilityId())).thenReturn(0);

		// テスト実施メソッド
		boolean result = this.service.delete(this.deleteFacilityManagerDto);

		// 検証メソッド
		assertEquals(false, result);

		// モック呼び出し回数
		verify(this.facilityRepository, times(1)).delete(this.facilityDto.getFacilityId());
	}
}
