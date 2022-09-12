package jp.co.ginga.web.domain.service.util.dto.facility;

import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityDto {
	/**
	 * 施設ID
	 */
	private int facilityId;

	/**
	 * 施設名
	 */
	private String name;

	/**
	 * 施設定員
	 */
	private int capacity;

	/**
	 * 	施設種別情報
	 */
	private FacilityTypeDto facilityTypeDto;

	/**
	 * ユーザー情報
	 */
	private UserDto userDto;

	/**
	 *
	 * @return
	 * インスタンス生成処理
	 */
	public static FacilityDto getInstance() {
		return new FacilityDto();
	}
}
