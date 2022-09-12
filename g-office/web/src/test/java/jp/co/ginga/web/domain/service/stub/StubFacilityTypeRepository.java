package jp.co.ginga.web.domain.service.stub;

import java.util.List;

import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeRepository;

public class StubFacilityTypeRepository implements FacilityTypeRepository{
	public List<FacilityTypeEntity> findAll(){
		return null;
	}

	public List<FacilityTypeEntity> findByFgDelete(int fgDelete){
		return null;
	}

	/**
	 * 施設種別　主キー検索処理
	 * @param int 施設ID
	 * @return FacilityTypeEntity
	 */
	public FacilityTypeEntity findOneById(int facilityTypeId) {
		return null;
	}

	/**
	 * 施設種別 施設種別名検索処理
	 * @param String name
	 * @return List<FacilityTypeEntity>
	 */
	public List<FacilityTypeEntity> findByTypeName(String name){
		return null;
	}

//	public int insert(FacilityTypeEntity facilityTypeEntity);
//
//	public int update(FacilityTypeEntity faclityTypeEntity);
//
//	public int delete(int facilityTypeId);
}
