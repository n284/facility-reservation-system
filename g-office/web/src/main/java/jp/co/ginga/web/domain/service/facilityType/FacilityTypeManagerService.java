package jp.co.ginga.web.domain.service.facilityType;

import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;

public interface FacilityTypeManagerService {

	/**
	 * 種別情報 全件取得処理
	 * @return
	 */
	public FacilityTypeManagerDto getFacilityTypeList();

	/**
	 * 種別情報 主キー検索処理
	 * @param int 種別ID
	 * @return FacilityTypeManagerDto 種別情報マネージャーDto
	 */
	public FacilityTypeManagerDto getFacilityTypeDetailByFacilityTypeId(int facilityTypeId);

	/**
	 * 種別情報 名前検索処理
	 * @param String 種別名
	 * @return FacilityTypeManagerDto 種別情報マネージャーDto
	 */
	public FacilityTypeManagerDto getFacilityTypeByName(String name);

//	/**
//	 * 種別情報 登録処理
//	 */
//	public FacilityTypeManagerDto add(FacilityTypeManagerDto dto);
//
//	/**
//	 * 種別情報 更新処理
//	 */
//	public FacilityTypeManagerDto update(FacilityTypeManagerDto dto);
//
//	/**
//	 * 種別情報 削除処理
//	 */
//	public FacilityTypeManagerDto delete(FacilityTypeManagerDto dto);

	public FacilityTypeDto getFacilityTypeDto(int facilityTypeId);

}
