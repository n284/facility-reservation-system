package jp.co.ginga.web.domain.service.facility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.infra.repository.facility.FacilityEntity;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.helper.facility.FacilityDtoHelper;
import jp.co.ginga.web.domain.service.util.helper.facilityType.FacilityTypeDtoHelper;

@Component
public class FacilityManagerHelper {

	/**
	 * 施設Dtoヘルパー
	 */
	@Autowired
	FacilityDtoHelper facilityDtoHelper;

	/**
	 * 施設種別Dtoヘルパー
	 */
	@Autowired
	FacilityTypeDtoHelper facilityTypeDtoHelper;

	/**
	 * FacilityEntityListを格納したFacilityManagerDtoを作成
	 * @param facilityEntity:List<FacilityEntity>
	 * @return FacilityManagerDto
	 */
	public FacilityManagerDto createFacilityServiceDto(List<FacilityEntity> facilityEntityList) {
		FacilityManagerDto facilityManagerDto = FacilityManagerDto.getInstance();
		facilityManagerDto.setFacilityDtoList(this.facilityDtoHelper.mapToFacilityDtoList(facilityEntityList));

		return facilityManagerDto;
	}

	/**
	 * FacilityEntity, FacilityTypeEntityListを格納したFacilityManagerDtoを作成
	 * @param facilityEntity:FacilityEntity
	 * @param facilityTypeEntityList:List<FacilityTypeEntity>
	 * @return FacilityManagerDto
	 */
	public FacilityManagerDto createFacilityServiceDto(FacilityEntity facilityEntity, List<FacilityTypeEntity> facilityTypeEntityList) {
		FacilityManagerDto facilityManagerDto = this.createFacilityServiceDto(facilityEntity);
		facilityManagerDto.setFacilityTypeDtoList(this.facilityTypeDtoHelper.mapToFacilityTypeDtoList(facilityTypeEntityList));

		return facilityManagerDto;
	}

	/**
	 * FacilityEntityを格納したFacilityManagerDtoを作成
	 * @param facilityEntity:FacilityEntity
	 * @return FacilityManagerDto
	 */
	public FacilityManagerDto createFacilityServiceDto(FacilityEntity facilityEntity) {
		FacilityManagerDto facilityManagerDto = FacilityManagerDto.getInstance();
		facilityManagerDto.setFacilityDto(this.facilityDtoHelper.mapToFacilityDto(facilityEntity));

		return facilityManagerDto;
	}

	/**
	 * FacilityManagerDtoに格納されているFacilityDtoからFacilityEntityに変換処理
	 * @param facilityManagerDto:FacilityManagerDto
	 * @return FacilityEntity
	 */
	public FacilityEntity getFacilityEntity(FacilityManagerDto facilityManagerDto) {
		FacilityEntity facilityEntity = this.facilityDtoHelper.mapToFacilityEntity(facilityManagerDto.getFacilityDto());

		return facilityEntity;
	}

	/**
	 * resultの値による処理状況の値をFacilityManagerDtoのresultに格納し、返す処理
	 * @param result:int
	 * @return FacilityManagerDto
	 */
	public FacilityManagerDto createFacilityServiceDtoAdd(int result	) {
		FacilityManagerDto facilityManagerDto = FacilityManagerDto.getInstance();

		switch(result) {
		case 1 :
			facilityManagerDto.setResult(ServiceConst.THERE_IS_DATA);
			break;

		case 0 :
			facilityManagerDto.setResult(ServiceConst.NO_DATA);
			break;
		default:
			break;
		}

		return facilityManagerDto;

	}

	/**
	 * FacilityEntityからFacilityDtoに変換処理
	 * @param facilityEntity
	 * @return FacilityDto
	 */
	public FacilityDto getFacilityDto(FacilityEntity facilityEntity) {
		FacilityDto facilityDto = this.facilityDtoHelper.mapToFacilityDto(facilityEntity);

		return facilityDto;
	}

	/**
	 * FacilityEntityをFacilityDtoに変換し、FacilityDtoを格納したFacilityManagerDtoを生成処理
	 * @param facilityEntity:FacilityEntity
	 * @return FacilityManagerDto
	 */
	public FacilityManagerDto createFacilityManagerServiceDto(FacilityEntity facilityEntity) {
		FacilityDto facilityDto = this.facilityDtoHelper.mapToFacilityDto(facilityEntity);
		FacilityManagerDto facilityManagerDto = FacilityManagerDto.getInstance();
		facilityManagerDto.setFacilityDto(facilityDto);

		if(facilityDto == null) {
			facilityManagerDto.setResult(ServiceConst.NO_DATA);
		}

		return facilityManagerDto;
	}

	/**
	 * FacilityEntityリストからFacilityDtoリストに変換処理
	 * @param facilityEntityList
	 * @return
	 */
	public List<FacilityDto> getFacilityList(List<FacilityEntity> facilityEntityList){
		List<FacilityDto> facilityDtoList = this.facilityDtoHelper.mapToFacilityDtoList(facilityEntityList);

		return facilityDtoList;
	}

}

