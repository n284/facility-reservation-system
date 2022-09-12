package jp.co.ginga.infra.repository.facilityType;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FacilityTypeRepository {

	/**
	 * 施設種別 全件検索処理
	 * @return
	 */
	public List<FacilityTypeEntity> findAll();

	public List<FacilityTypeEntity> findByFgDelete(int fgDelete);

	/**
	 * 施設種別　主キー検索処理
	 * @param int 施設ID
	 * @return FacilityTypeEntity
	 */
	public FacilityTypeEntity findOneById(int facilityTypeId);

	/**
	 * 施設種別 施設種別名検索処理
	 * @param String name
	 * @return List<FacilityTypeEntity>
	 */
	public List<FacilityTypeEntity> findByTypeName(String name);

//	public int insert(FacilityTypeEntity facilityTypeEntity);
//
//	public int update(FacilityTypeEntity faclityTypeEntity);
//
//	public int delete(int facilityTypeId);
}
