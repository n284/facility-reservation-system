package jp.co.ginga.web.domain.service.facilityType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeRepository;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import jp.co.ginga.web.domain.service.util.helper.facilityType.FacilityTypeDtoHelper;

@Service
@Transactional
public class FacilityTypeManagerServiceImpl implements FacilityTypeManagerService{
	/*
	 * レポジトリー
	 */
	@Autowired
	FacilityTypeRepository facilityTypeRepository;

	/*
	 * ヘルパー
	 */
	@Autowired
	FacilityTypeDtoHelper facilityTypeDtoHelper;

	@Autowired
	FacilityTypeManagerHelper facilityTypeManagerHelper;



	/**
	 * 種別情報 全件検索処理
	 */
	@Override
	@Transactional(readOnly = true)
	public FacilityTypeManagerDto getFacilityTypeList() {
		List<FacilityTypeEntity> facilityTypeEntityList = this.facilityTypeRepository.findAll();
		FacilityTypeManagerDto facilityTypeManagerDto = this.facilityTypeManagerHelper.createFacilityTypeServiceDto(facilityTypeEntityList);

		if(facilityTypeEntityList.size() == 0) {
			facilityTypeManagerDto.setResult(ServiceConst.NO_DATA);
		}
		return facilityTypeManagerDto;
	}

	/**
	 * 種別情報 主キー検索処理
	 * @param int 種別ID
	 * @return FacilityTypeManagerDto 種別情報マネージャーDto
	 */
	@Override
	public FacilityTypeManagerDto getFacilityTypeDetailByFacilityTypeId(int facilityTypeId) {
		FacilityTypeEntity facilityTypeEntity = this.facilityTypeRepository.findOneById(facilityTypeId);
		List<FacilityTypeEntity> facilityTypeEntityList = this.facilityTypeRepository.findAll()	;
		FacilityTypeManagerDto facilityTypeManagerDto = this.facilityTypeManagerHelper.createFacilityTypeServiceDto(facilityTypeEntity, facilityTypeEntityList);
		facilityTypeManagerDto.setOptimisticRockValue(facilityTypeEntity.toString());

		return facilityTypeManagerDto;
	}

	/**
	 * 施設情報 施設名検索処理
	 * @param String 名前
	 * @return FacilityTypeManagerDto
	 */
	@Override
	public FacilityTypeManagerDto getFacilityTypeByName(String name) {
		List<FacilityTypeEntity> facilityTypeEntityList = this.facilityTypeRepository.findByTypeName(name);
		FacilityTypeManagerDto facilityTypeManagerDto =FacilityTypeManagerDto.getInstance();
		List<FacilityTypeDto> facilityTypeDtoList = this.facilityTypeManagerHelper.getFacilityTypeList(facilityTypeEntityList);
		facilityTypeManagerDto.setFacilityTypeDtoList(facilityTypeDtoList);

		if(facilityTypeEntityList.size() == 0){
				facilityTypeManagerDto.setResult(ServiceConst.NO_DATA);
		}else if(facilityTypeEntityList.size() > 0) {
				facilityTypeManagerDto.setResult(ServiceConst.THERE_IS_DATA);
		}else {
				facilityTypeManagerDto.setResult(ServiceConst.ERROR);
		}

		return facilityTypeManagerDto;
	}

	@Override
	public FacilityTypeDto getFacilityTypeDto(int facilityTypeId) {
		FacilityTypeEntity facilityTypeEntity = this.facilityTypeRepository.findOneById(facilityTypeId);
		FacilityTypeDto facilityTypeDot = this.facilityTypeDtoHelper.mapToFacilityTypeDto(facilityTypeEntity);

		return facilityTypeDot;
	}




}
