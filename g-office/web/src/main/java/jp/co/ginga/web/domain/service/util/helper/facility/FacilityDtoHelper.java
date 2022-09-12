package jp.co.ginga.web.domain.service.util.helper.facility;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.infra.repository.facility.FacilityEntity;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import jp.co.ginga.web.domain.service.util.helper.user.UserDtoHelper;

@Component
public class FacilityDtoHelper {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserDtoHelper userDtoHelper;

	/**
	 * EntityからDtoへ変換処理
	 * @param entity
	 * @return FacilityDto
	 */
	public FacilityDto mapToFacilityDto(FacilityEntity facilityEntity) {
		/*
		 * マップ先が曖昧な場合は無視
		 * ModelMapperはマッピング先が不明でも自動で適当にマッピングする。
		 * それを防ぐためmap先が不明ならマップせずスキップ
		 */

		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
		FacilityDto facilityDto = this.modelMapper.map(facilityEntity, FacilityDto.class);

		if(facilityEntity.getFacilityTypeEntity() != null) {
			FacilityTypeDto facilityTypeDto = this.modelMapper.map(facilityEntity.getFacilityTypeEntity(), FacilityTypeDto.class);
			facilityDto.setFacilityTypeDto(facilityTypeDto);
		}

		return facilityDto;
	}

	/**
	 * FacilityEntityListをFacilityDtoListに変換
	 * @param entityList
	 * @return
	 */
	public List<FacilityDto> mapToFacilityDtoList(List<FacilityEntity> facilityEntityList){
		List<FacilityDto> dtoList = new ArrayList<FacilityDto>();

		for(FacilityEntity entity : facilityEntityList) {
			dtoList.add(this.mapToFacilityDto(entity));
		}

		return dtoList;
	}

	/**
	 * DtoからEntityへ変換処理
	 * @param dto:FacilityDto
	 * @return FacilityEntity
	 */
	public FacilityEntity mapToFacilityEntity(FacilityDto facilityDto) {
		/*
		 * マップ先が曖昧な場合は無視
		 * ModelMapperはマッピング先が不明でも自動で適当にマッピングする。
		 * それを防ぐためmap先が不明ならマップせずスキップ
		 */

		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
		FacilityEntity facilityEntity = this.modelMapper.map(facilityDto, FacilityEntity.class);

		if(facilityDto.getFacilityTypeDto() != null	) {
			FacilityTypeEntity facilityTypeEntity = this.modelMapper.map(facilityDto.getFacilityTypeDto(), FacilityTypeEntity.class);
			facilityEntity.setFacilityTypeEntity(facilityTypeEntity);
		}
		facilityEntity.setInsertUserEntity(this.userDtoHelper.mapToUserEntity(facilityDto.getUserDto()));
		facilityEntity.setUpdateUserEntity(this.userDtoHelper.mapToUserEntity(facilityDto.getUserDto()));

		return facilityEntity;
	}

	/**
	 * FacilityDtoListをFacilityEntityListに変換
	 * @param dtoList:List<FacilityDto>
	 * @return List<FacilityEntity>
	 */
	public List<FacilityEntity> mapToFacilityEntityList(List<FacilityDto> facilityDtoList){
		List<FacilityEntity> entityList = new ArrayList<FacilityEntity>();

		for(FacilityDto dto : facilityDtoList) {
			entityList.add(this.mapToFacilityEntity(dto));
		}

		return entityList;
	}
}
