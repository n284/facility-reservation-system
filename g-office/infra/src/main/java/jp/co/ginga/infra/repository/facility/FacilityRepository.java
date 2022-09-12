package jp.co.ginga.infra.repository.facility;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FacilityRepository {

	/**
	 * 施設情報 全件検索処理
	 * @return
	 */
	public List<FacilityEntity> findAll();

	/**
	 * 施設情報 主キー検索処理
	 * @param int 施設ID
	 * @return
	 */
	public FacilityEntity findByFacilityId(int facilityId);

	/**
	 * 施設情報 施設種別検索処理
	 * @param int 施設種別ID
	 * @return
	 */
	public List<FacilityEntity> findByFacilityTypeId(int facilityTypeId);

	/**
	 * 施設情報 施設名検索処理
	 * @param String 施設名
	 * @return
	 */
	public List<FacilityEntity> findByName(String name);


	/**
	 * 施設情報 登録処理
	 * @param FacilityEntity 施設エンティティ
	 * @return
	 */
	public int insert(FacilityEntity facilityEntity);

	/**
	 * 施設情報 更新処理
	 * @param FacilityEntity 施設エンティティ
	 * @return
	 */
	public int update(FacilityEntity facilityEntity);

	/**
	 * 施設情報 削除処理
	 * @param int 施設ID
	 * @return
	 */
	public int delete(int facilityId);


}
