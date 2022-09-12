package jp.co.ginga.web.domain.service.facilityreservation;

import static org.junit.jupiter.api.Assertions.*;
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
import jp.co.ginga.web.domain.service.facility.FacilityManagerDto;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MockFacilityReservationManagerServiceTest {
	@InjectMocks
	@Autowired
	FacilityReservationManagerService facilityReservationManagerService= new FacilityReservationManagerServiceImpl();

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
	 * getFacility(); 正常系001 施設あり、種別あり
	 */
	@Test
	public void testGetFacility_normal_001() {
		System.out.println("testGetFacility_normal_001");

		//モックの設定
		when(this.facilityRepository.findAll()).thenReturn(this.facilityEntityList);
		when(this.facilityTypeRepository.findAll()).thenReturn(this.facilityTypeEntityList);

		//実行
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacility();

		//検証処理
		assertEquals(1, result.getFacilityDtoList().size());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());

		assertEquals(1, result.getFacilityTypeDtoList().size());
		assertEquals(this.ft_facilityTypeId, result.getFacilityTypeDtoList().get(0).getFacilityTypeId());
		assertEquals(this.ft_name, result.getFacilityTypeDtoList().get(0).getName());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findAll();
		verify(this.facilityTypeRepository, times(1)).findAll();
	}

	/**
	 * getFacility(); 正常系002 施設なし、種別あり
	 */
	@Test
	public void testGetFacility_normal_002() {
		System.out.println("testGetFacility_normal_002");

		//モックの設定
		when(this.facilityRepository.findAll()).thenReturn(this.emptyFacilityEntityList);
		when(this.facilityTypeRepository.findAll()).thenReturn(this.facilityTypeEntityList);

		//実行
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacility();

		//検証処理
		assertEquals(0, result.getFacilityDtoList().size());

		assertEquals(1, result.getFacilityTypeDtoList().size());
		assertEquals(this.ft_facilityTypeId, result.getFacilityTypeDtoList().get(0).getFacilityTypeId());
		assertEquals(this.ft_name, result.getFacilityTypeDtoList().get(0).getName());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findAll();
		verify(this.facilityTypeRepository, times(1)).findAll();
	}

	/**
	 * getFacility(); 正常系002 施設なし、種別なし
	 */
	@Test
	public void testGetFacility_normal_003() {
		System.out.println("testGetFacility_normal_003");

		//モックの設定
		when(this.facilityRepository.findAll()).thenReturn(this.emptyFacilityEntityList);
		when(this.facilityTypeRepository.findAll()).thenReturn(this.emptyFacilityTypeEntityList);

		//実行
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacility();

		//検証処理
		assertEquals(0, result.getFacilityDtoList().size());

		assertEquals(0, result.getFacilityTypeDtoList().size());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findAll();
		verify(this.facilityTypeRepository, times(1)).findAll();
	}
	/**
	 * getFacility(); 正常系001 施設あり、種別なし
	 */
	@Test
	public void testGetFacility_abnormal_001() {
		System.out.println("testGetFacility_abnormal_001");

		//モックの設定
		when(this.facilityRepository.findAll()).thenReturn(this.facilityEntityList);
		when(this.facilityTypeRepository.findAll()).thenReturn(this.emptyFacilityTypeEntityList);

		//実行
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacility();

		//検証処理
		assertEquals(1, result.getFacilityDtoList().size());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());

		assertEquals(0, result.getFacilityTypeDtoList().size());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findAll();
		verify(this.facilityTypeRepository, times(1)).findAll();
	}

	/**
	 * getFacilityByFacilityTypeId(); 正常系001 施設あり、種別あり
	 */
	@Test
	public void testGetFacilityByFacilityTypeId_normal_001() {
		System.out.println("testGetFacilityByFacilityTypeId_normal_001");

		//モックの設定
		when(this.facilityRepository.findByFacilityTypeId(this.ft_facilityTypeId)).thenReturn(this.facilityEntityList);
		when(this.facilityTypeRepository.findAll()).thenReturn(this.facilityTypeEntityList);

		//実行
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacilityByFacilityTypeId(this.ft_facilityTypeId);

		//検証処理
		assertEquals(1, result.getFacilityDtoList().size());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());
		assertEquals(this.ft_facilityTypeId, result.getFacilityDtoList().get(0).getFacilityTypeDto().getFacilityTypeId());

		assertEquals(1, result.getFacilityTypeDtoList().size());
		assertEquals(this.ft_facilityTypeId, result.getFacilityTypeDtoList().get(0).getFacilityTypeId());
		assertEquals(this.ft_name, result.getFacilityTypeDtoList().get(0).getName());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findByFacilityTypeId(this.ft_facilityTypeId);
		verify(this.facilityTypeRepository, times(1)).findAll();
	}

	/**
	 * getFacilityByFacilityTypeId(); 正常系002 施設なし、種別あり
	 */
	@Test
	public void testGetFacilityByFacilityTypeId_normal_002() {
		System.out.println("testGetFacilityByFacilityTypeId_normal_002");

		//モックの設定
		when(this.facilityRepository.findByFacilityTypeId(this.ft_facilityTypeId)).thenReturn(this.emptyFacilityEntityList);
		when(this.facilityTypeRepository.findAll()).thenReturn(this.facilityTypeEntityList);

		//実行
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacilityByFacilityTypeId(this.ft_facilityTypeId);

		//検証処理
		assertEquals(0, result.getFacilityDtoList().size());

		assertEquals(1, result.getFacilityTypeDtoList().size());
		assertEquals(this.ft_facilityTypeId, result.getFacilityTypeDtoList().get(0).getFacilityTypeId());
		assertEquals(this.ft_name, result.getFacilityTypeDtoList().get(0).getName());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findByFacilityTypeId(this.ft_facilityTypeId);
		verify(this.facilityTypeRepository, times(1)).findAll();
	}

	/**
	 * getFacilityByFacilityTypeId(); 正常系003 施設なし、種別なし
	 */
	@Test
	public void testGetFacilityByFacilityTypeId_normal_003() {
		System.out.println("testGetFacilityByFacilityTypeId_normal_003");

		//モックの設定
		when(this.facilityRepository.findByFacilityTypeId(this.ft_facilityTypeId)).thenReturn(this.emptyFacilityEntityList);
		when(this.facilityTypeRepository.findAll()).thenReturn(this.emptyFacilityTypeEntityList);

		//実行
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacilityByFacilityTypeId(this.ft_facilityTypeId);

		//検証処理
		assertEquals(0, result.getFacilityDtoList().size());

		assertEquals(0, result.getFacilityTypeDtoList().size());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findByFacilityTypeId(this.ft_facilityTypeId);
		verify(this.facilityTypeRepository, times(1)).findAll();
	}

	/**
	 * getFacilityByFacilityTypeId(); 異常系001 施設あり、種別なし
	 */
	@Test
	public void testGetFacilityByFacilityTypeId_abnormal_001() {
		System.out.println("testGetFacilityByFacilityTypeId_abnormal_001");

		//モックの設定
		when(this.facilityRepository.findByFacilityTypeId(this.ft_facilityTypeId)).thenReturn(this.facilityEntityList);
		when(this.facilityTypeRepository.findAll()).thenReturn(this.emptyFacilityTypeEntityList);

		//実行
		FacilityReservationManagerDto result = this.facilityReservationManagerService.getFacilityByFacilityTypeId(this.ft_facilityTypeId);

		//検証処理
		assertEquals(1, result.getFacilityDtoList().size());
		assertEquals(this.f_facilityId, result.getFacilityDtoList().get(0).getFacilityId());
		assertEquals(this.f_name, result.getFacilityDtoList().get(0).getName());
		assertEquals(this.f_capacity, result.getFacilityDtoList().get(0).getCapacity());
		assertEquals(this.ft_facilityTypeId, result.getFacilityDtoList().get(0).getFacilityTypeDto().getFacilityTypeId());

		assertEquals(0, result.getFacilityTypeDtoList().size());

		//モックの呼び出し回数
		verify(this.facilityRepository, times(1)).findByFacilityTypeId(this.ft_facilityTypeId);
		verify(this.facilityTypeRepository, times(1)).findAll();
	}
}
