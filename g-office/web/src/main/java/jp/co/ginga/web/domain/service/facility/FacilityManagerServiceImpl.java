package jp.co.ginga.web.domain.service.facility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ginga.infra.repository.facility.FacilityEntity;
import jp.co.ginga.infra.repository.facility.FacilityRepository;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeRepository;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.helper.facility.FacilityDtoHelper;

@Service
@Transactional
public class FacilityManagerServiceImpl implements FacilityManagerService{
	/*
	 * データベースアクセス用
	 */
	@Autowired
	FacilityRepository facilityRepository;

	@Autowired
	FacilityTypeRepository facilityTypeRepository;

	/*
	 * データ変換用
	 */
	@Autowired
	FacilityManagerHelper facilityManagerHelper;

	@Autowired
	FacilityDtoHelper facilityDtoHelper;

	/**
	 * 施設情報 全件検索処理
	 * FacilityRepositoryのfindAllの結果をFacilityManagerDtoに代入して返す
	 * @return FacilityManagerDto
	 */
	@Override
	@Transactional(readOnly = true)
	public FacilityManagerDto getFacilityList() {
		List<FacilityEntity> facilityEntityList = this.facilityRepository.findAll();
		FacilityManagerDto dto = this.facilityManagerHelper.createFacilityServiceDto(facilityEntityList);

		return dto;
	}

	/**
	 * 施設情報 施設ID検索処理
	 * FacilityRepositoryの.findByFacilityIdの結果と
	 * FacilityTypeRepositoryのfindAllの結果をFacilityManagerDtoに代入して返す
	 * 内容が更新されたかを確認するための楽観ロックとして内容一覧を文字列で保持させる
	 * @return FacilityManagerDto
	 */
	@Override
	public FacilityManagerDto getFacilityDetailByFacilityId(int facilityId) {
		FacilityEntity facilityEntity=  this.facilityRepository.findByFacilityId(facilityId);
		List<FacilityTypeEntity> facilityTypeEntityList = this.facilityTypeRepository.findAll();
		FacilityManagerDto dto = this.facilityManagerHelper.createFacilityServiceDto(facilityEntity, facilityTypeEntityList);
		dto.setOptimisticRockValue(facilityEntity.toString());

		return dto;
	}

	/**
	 * 施設情報 施設名検索処理
	 * @return FacilityManagerDto
	 */
	@Override
	public FacilityManagerDto getFacilityByName(String name) {
		List<FacilityEntity> facilityEntityList = this.facilityRepository.findByName(name);
		FacilityManagerDto facilityManagerDto = this.facilityManagerHelper.createFacilityServiceDto(facilityEntityList);
		List<FacilityDto> facilityDtoList = this.facilityManagerHelper.getFacilityList(facilityEntityList);

		facilityManagerDto.setFacilityDtoList(facilityDtoList);

		//条件を一つ減らせる
		//修正してみる
		if(facilityEntityList == null) {
			facilityManagerDto.setResult(ServiceConst.ERROR);
		}
		if(facilityEntityList.size() == 0) {
			facilityManagerDto.setResult(ServiceConst.NO_DATA);
		}else if(facilityEntityList.size() > 0) {
			facilityManagerDto.setResult(ServiceConst.THERE_IS_DATA);
		}else {
			facilityManagerDto.setResult(ServiceConst.ERROR);
		}

		return facilityManagerDto;
	}

	/**
	 * 施設情報 登録処理
	 * @return FacilityManagerDto
	 */
	@Override
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
	public FacilityManagerDto add(FacilityManagerDto facilityManagerDto) {
		FacilityEntity facilityEntity = this.facilityManagerHelper.getFacilityEntity(facilityManagerDto);
		int result = this.facilityRepository.insert(facilityEntity);
		FacilityManagerDto dto = this.facilityManagerHelper.createFacilityServiceDtoAdd(result);

		return dto;
	}

	/**
	 * 施設情報 更新処理
	 * @return FacilityManagerDto
	 */
	@Override
	public FacilityManagerDto update(FacilityManagerDto facilityManagerDto) {
		FacilityManagerDto dto = FacilityManagerDto.getInstance();
		FacilityEntity entity = this.facilityManagerHelper.getFacilityEntity(facilityManagerDto);
		FacilityEntity beforeEntity = this.facilityRepository.findByFacilityId(facilityManagerDto.getFacilityDto().getFacilityId());

		if(beforeEntity.toString().equals(facilityManagerDto.getOptimisticRockValue())) {
			if(this.facilityRepository.update(entity) != 1) {
				dto.setResult(ServiceConst.ERROR);
			} else {
				dto.setResult(ServiceConst.OK);
			}
		}else {
			dto.setResult(ServiceConst.OPTIMISTIC_ROCK_ERROR);
		}

		return dto;
	}

	@Override
	public boolean delete(FacilityManagerDto facilityManagerDto) {
		int result = this.facilityRepository.delete(facilityManagerDto.getFacilityDto().getFacilityId());
		if( result != 1) {
			return false;
		}
		return true;
	}


	@Override
	public void setFacilityRepository(FacilityRepository facilityRepository) {
		this.facilityRepository = facilityRepository;
	}

	@Override
	public void setFacilityTypeRepository(FacilityTypeRepository facilityTypeRepository) {
		this.facilityTypeRepository = facilityTypeRepository;
	}

}
