package jp.co.ginga.web.application.controller.user.add;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAddForm {

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
	 * ユーザー権限情報
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

	/**
	 * UserAddFormインスタンス生成処理
	 * 
	 * @return
	 */
	public static UserAddForm getInstance() {
		return new UserAddForm();

	}
}
