
package jp.co.ginga.web.application.controller.user.confirm;

import java.util.List;

import jp.co.ginga.web.application.util.form.role.RoleForm;
import jp.co.ginga.web.application.util.form.user.UserForm;
import jp.co.ginga.web.application.util.form.userRole.UserRoleForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserConfirmForm {

	/**
	 * システムメッセージ
	 */
	private String sysMsg;

	/**
	 * 施設情報
	 */
	private UserForm userForm;

	/**
	 * ユーザー権限情報
	 */
	private List<UserRoleForm> userRoleFormList;

	/**
	 * 施設種別情報
	 */
	private List<RoleForm> roleFormList;

	private String btnName;

	private String btnAction;

	public static UserConfirmForm getInstance() {
		return new UserConfirmForm();

	}
}
