package jp.co.ginga.web.application.controller.user.detail;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import jp.co.ginga.web.application.util.form.role.RoleForm;
import jp.co.ginga.web.application.util.form.user.UserForm;
import jp.co.ginga.web.application.util.form.userRole.UserRoleForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ユーザー詳細フォーム
 * @author takaoaka
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetailForm {
	/**
	 * システムメッセージ
	 */
	private String sysMsg;

	/**
	 * ユーザー情報
	 */
	@Valid
	private UserForm userForm;

	/**
	 * ユーザー権限情報
	 */
	private List<UserRoleForm> userRoleFormList;

	/**
	 * 権限情報リスト
	 */
	private List<RoleForm> roleFormList;

	/**
	 *
	 * @return
	 */
	public UserForm getUserForm() {
		if (userForm == null) {
			userForm = UserForm.getInstance();
		}
		return userForm;
	}

	public List<UserRoleForm> getUserRoleFormList() {
		if (userRoleFormList == null) {
			userRoleFormList = new ArrayList<UserRoleForm>();
		}

		return userRoleFormList;
	}

	public static UserDetailForm getInstance() {
		UserDetailForm userDetailForm = new UserDetailForm();
		return userDetailForm;
	}
}
