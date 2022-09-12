package jp.co.ginga.web.application.util.form.userRole;

import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRoleForm {

	//private int id;

	/**
	 * ユーザーIｄ
	 */
	private String userId;

	/**
	 * 権限Ｉｄ
	 */
	private String roleId;

	/**
	 * UserRoleDtoのインスタンス生成
	 * @return
	 */
	public static UserRoleDto getInstance() {
		return new UserRoleDto();
	}

}
