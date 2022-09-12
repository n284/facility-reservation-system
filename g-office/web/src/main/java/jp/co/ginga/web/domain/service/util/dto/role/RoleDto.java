package jp.co.ginga.web.domain.service.util.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDto {

	/**
	 * 権限ID
	 */
	private String roleId;

	/**
	 * 権限名
	 */
	private String roleName;

	/**
	 *
	 * @return
	 * インスタンス生成処理
	 */
	public static RoleDto getInstance() {
		return new RoleDto();
	}
}
