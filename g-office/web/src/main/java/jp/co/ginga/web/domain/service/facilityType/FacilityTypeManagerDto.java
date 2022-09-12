package jp.co.ginga.web.domain.service.facilityType;

import java.util.List;

import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import jp.co.ginga.web.domain.service.util.dto.role.RoleDto;
import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityTypeManagerDto {

	/**
	 * 施設種別情報の更新追加削除用、楽観的ロック
	 */
	private String optimisticRockValue;

	/**
	 * 処理結果コード
	 */
	private int result;

	/**
	 * 施設種別情報
	 */
	private FacilityTypeDto facilityTypeDto;

	/**
	 * 施設種別情報リスト
	 */
	private List<FacilityTypeDto> facilityTypeDtoList;

	private List<RoleDto> roleDtoList;
	private List<UserRoleDto> userRoleDtoList;

	/**
	 * インスタンス生成処理
	 * @return
	 */
	public static FacilityTypeManagerDto getInstance() {
		return new FacilityTypeManagerDto();
	}

}
