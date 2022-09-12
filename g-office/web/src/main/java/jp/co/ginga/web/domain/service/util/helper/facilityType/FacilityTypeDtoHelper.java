package jp.co.ginga.web.domain.service.util.helper.facilityType;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;

@Component
public class FacilityTypeDtoHelper {

	//ModelMapper.map(コピー元、コピー先.class(classをつけるとコピー先の生成も自動化))

	@Autowired
	ModelMapper modelMapper;

	/**
	 * EntityからDtoに変換処理
	 * @param facilityTypeEntity:FacilityTypeEntity
	 * @return FacilityTypeDto
	 */
	public FacilityTypeDto mapToFacilityTypeDto(FacilityTypeEntity typeEntity) {
		//マップ先が曖昧ならそのフィールドのマップは無視
//		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
		FacilityTypeDto facilityTypeDto = this.modelMapper.map(typeEntity, FacilityTypeDto.class);

		return facilityTypeDto;
	}

	/**
	 * List<FacilityTypeEntity>からList<FacilityTypeDto>への変換処理
	 * @param entityList:List<FacilityTypeEntity>
	 * @return List<FacilityTypeDto>
	 */
	public List<FacilityTypeDto> mapToFacilityTypeDtoList(List<FacilityTypeEntity> typeEntityList){
		List<FacilityTypeDto> dtoList = new ArrayList<FacilityTypeDto>();
		for(FacilityTypeEntity entity : typeEntityList) {
			dtoList.add(this.mapToFacilityTypeDto(entity));
		}

		return dtoList;
	}

	/**
	 * DtoからEntityに変換処理
	 * @param facilityTypeDto:FacilityTypeDto
	 * @return FacilityTypeEntity
	 */
	public FacilityTypeEntity mapToFacilityTypeEntity(FacilityTypeDto typeDto) {
		//マップ先が曖昧ならそのフィールドのマップは無視
//		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
		FacilityTypeEntity facilityTypeEntity = this.modelMapper.map(typeDto, FacilityTypeEntity.class);

		return facilityTypeEntity;
	}

	/**
	 * List<FacilityTypeDto>からList<FacilityTypeEntity>への変換処理
	 * @param dtoList:List<FacilityTypeDto>
	 * @return List<FacilityTypeEntity>
	 */
	public List<FacilityTypeEntity> mapToFacilityTypeEntityList(List<FacilityTypeDto> typeDtoList){
		List<FacilityTypeEntity> entityList = new ArrayList<FacilityTypeEntity>();

		for(FacilityTypeDto dto : typeDtoList) {
			entityList.add(this.mapToFacilityTypeEntity(dto));
		}

		return entityList;
	}


}
