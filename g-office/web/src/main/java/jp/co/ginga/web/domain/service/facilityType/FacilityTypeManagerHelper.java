package jp.co.ginga.web.domain.service.facilityType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import jp.co.ginga.web.domain.service.util.helper.facilityType.FacilityTypeDtoHelper;

@Component
public class FacilityTypeManagerHelper {

	@Autowired
	FacilityTypeDtoHelper facilityTypeDtoHelper;

	/**
	 * FacilityTypeEntityListを格納したFacilityTypeManagerDtoを作成
	 * @param facilityTypeEntity:List<FacilityTypeEntity>
	 * @return FacilityTypeManagerDto
	 */
	public FacilityTypeManagerDto createFacilityTypeServiceDto(List<FacilityTypeEntity> typeEntityList) {
		FacilityTypeManagerDto facilityTypeManagerDto = FacilityTypeManagerDto.getInstance();
		facilityTypeManagerDto.setFacilityTypeDtoList(this.facilityTypeDtoHelper.mapToFacilityTypeDtoList(typeEntityList));

		return facilityTypeManagerDto;
	}


	/**
	 * FacilityTypeEntityを格納したFacilityManagerDtoを作成
	 * @param facilityTypeEntity:FacilityTypeEntity
	 * @return FacilityManagerDto
	 */
	public FacilityTypeManagerDto createFacilityTypeServiceDto(FacilityTypeEntity typeEntity) {
		FacilityTypeManagerDto facilityTypeManagerDto = this.createFacilityTypeServiceDto(typeEntity);
		facilityTypeManagerDto.setFacilityTypeDto(this.facilityTypeDtoHelper.mapToFacilityTypeDto(typeEntity));

		return facilityTypeManagerDto;
	}

	/**
	 * FacilityEntityを格納したFacilityManagerDtoを作成
	 * @param facilityEntity:FacilityEntity
	 * @return FacilityManagerDto
	 */
	public FacilityTypeManagerDto createFacilityTypeServiceDto(FacilityTypeEntity typeEntity, List<FacilityTypeEntity> typeEntityList) {
		FacilityTypeManagerDto facilityTypeManagerDto = this.createFacilityTypeServiceDto(typeEntity);
		facilityTypeManagerDto.setFacilityTypeDtoList(this.facilityTypeDtoHelper.mapToFacilityTypeDtoList(typeEntityList));

		return facilityTypeManagerDto;
	}

	/**
	 * FacilityManagerDtoに格納されているFacilityDtoからFacilityEntityに変換処理
	 * @param facilityManagerDto:FacilityManagerDto
	 * @return FacilityEntity
	 */
	public FacilityTypeEntity getFacilityTypeEntity(FacilityTypeManagerDto typeManagerDto) {
		FacilityTypeEntity facilityTypeEntity = this.facilityTypeDtoHelper.mapToFacilityTypeEntity(typeManagerDto.getFacilityTypeDto());

		return facilityTypeEntity;
	}

	/**
	 * resultの値による処理状況の値をFacilityManagerDtoのresultに格納し、返す処理
	 * @param result:int
	 * @return FacilityManagerDto
	 */
	public FacilityTypeManagerDto createFacilityTypeServiceDtoAdd(int result) {
		FacilityTypeManagerDto facilityTypeManagerDto = FacilityTypeManagerDto.getInstance();

		switch(result) {
		case 1 :
			facilityTypeManagerDto.setResult(ServiceConst.THERE_IS_DATA);
			break;

		case 2 :
			facilityTypeManagerDto.setResult(ServiceConst.NO_DATA);
			break;

		default:
			break;
		}

		return facilityTypeManagerDto;

	}

	/**
	 * FacilityEntityからFacilityDtoに変換処理
	 * @param facilityEntity
	 * @return FacilityDto
	 */
	public FacilityTypeDto getFacilityTypeDto(FacilityTypeEntity facilityTypeEntity) {
		FacilityTypeDto facilityTypeDto = this.facilityTypeDtoHelper.mapToFacilityTypeDto(facilityTypeEntity);

		return facilityTypeDto;
	}

	/**
	 * FacilityEntityをFacilityDtoに変換し、FacilityDtoを格納したFacilityManagerDtoを生成処理
	 * @param facilityEntity:FacilityEntity
	 * @return FacilityManagerDto
	 */
	public FacilityTypeManagerDto createFacilityTypeManagerServiceDto(FacilityTypeEntity resultFacilityTypeEntity) {
		FacilityTypeDto facilityTypeDto = this.getFacilityTypeDto(resultFacilityTypeEntity);
		FacilityTypeManagerDto facilityTypeManagerDto = FacilityTypeManagerDto.getInstance();
		facilityTypeManagerDto.setFacilityTypeDto(facilityTypeDto);

		if(facilityTypeDto == null) {
			facilityTypeManagerDto.setResult(ServiceConst.NO_DATA);
		}

		return facilityTypeManagerDto;
	}

	/**
	 * FacilityEntityリストからFacilityDtoリストに変換処理
	 * @param facilityEntityList
	 * @return
	 */
	public List<FacilityTypeDto> getFacilityTypeList(List<FacilityTypeEntity> facilityTypeEntityList){
		List<FacilityTypeDto> facilityTypeDtoList = this.facilityTypeDtoHelper.mapToFacilityTypeDtoList(facilityTypeEntityList);

		return facilityTypeDtoList;
	}
}
