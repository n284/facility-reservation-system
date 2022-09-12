package jp.co.ginga.web.domain.service.facility;

import java.util.List;

import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityManagerDto {

	/**
	 * 施設情報の更新追加削除用、楽観的ロック
	 */
	private String optimisticRockValue;

	/**
	 * 処理結果コード
	 */
	private int result;

	/**
	 * 施設情報
	 */
	private FacilityDto facilityDto;

	/**
	 * 施設情報リスト
	 */
	private List<FacilityDto> facilityDtoList;

	/**
	 * 施設種別情報リスト
	 */
	private List<FacilityTypeDto> facilityTypeDtoList;

	private FacilityTypeDto facilityTypeDto;

	/**
	 * インスタンス生成処理
	 * @return FacilityManagerDto
	 */
	public static FacilityManagerDto getInstance() {
		return new FacilityManagerDto();
	}

}
