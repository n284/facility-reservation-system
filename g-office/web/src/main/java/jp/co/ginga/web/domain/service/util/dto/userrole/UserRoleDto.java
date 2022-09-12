/**
 *
 */
package jp.co.ginga.web.domain.service.util.dto.userrole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author souken
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRoleDto {

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