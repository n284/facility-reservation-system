package jp.co.ginga.web.domain.service.facility;

import jp.co.ginga.infra.repository.facility.FacilityRepository;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeRepository;

public interface FacilityManagerService {
	/**
	 * 施設情報 全件取得処理
	 * @return FacilityManagerDto
	 */
	public FacilityManagerDto getFacilityList();

	/**
	 * 施設詳細情報 施設id指定取得処理
	 * @param facilityId
	 * @return
	 */
	public FacilityManagerDto getFacilityDetailByFacilityId(int facilityId);

	/**
	 * 施設情報 施設名取得処理
	 * @param facilityName
	 * @return
	 */
	public FacilityManagerDto getFacilityByName(String facilityName);

	/**
	 * 施設登録処理
	 * @param FacilityManagerDto
	 * @return FacilityManagerDto
	 */
	public FacilityManagerDto add(FacilityManagerDto dto);

	/**
	 * 施設更新処理
	 * @param FacilityManagerDto
	 * @return FacilityManagerDto
	 */
	public FacilityManagerDto update(FacilityManagerDto dto);


	/**
	 * 施設削除処理
	 * @param FacilityManagerDto
	 * @return FacilityManagerDto
	 */
	public boolean delete(FacilityManagerDto dto);

	// テスト用メソッド
	public void setFacilityRepository(FacilityRepository facilityRepository);

	public void setFacilityTypeRepository(FacilityTypeRepository facilityTypeRepository);



}
