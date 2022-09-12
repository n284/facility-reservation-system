package jp.co.ginga.web.domain.service.util.dto.facilityType;

import java.sql.Date;

import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityTypeDto {
	/**
	 * 施設種別ID
	 */
	private int facilityTypeId;

	/**
	 * 施設種別名
	 */
	private String name;

	/**
	 * 施設種別登録日時
	 */
	private Date insertDate;

	private UserDto insertUserDto;

	/**
	 * @return FacilityTypeForm
	 * インスタンス生成処理
	 */
	public static FacilityTypeDto getInstance() {
		return new FacilityTypeDto();
	}

	public FacilityTypeDto(int facilityTypeId, String name) {
		this.facilityTypeId = facilityTypeId;
		this.name = name;
	}
}
