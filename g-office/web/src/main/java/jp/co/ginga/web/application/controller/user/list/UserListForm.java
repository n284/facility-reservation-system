package jp.co.ginga.web.application.controller.user.list;

import java.util.List;

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
public class UserListForm {

	/**
	 * システムメッセージ
	 */
	private String sysMsg;

	/**
	 * ユーザー情報リスト
	 */
	private List<UserForm> userFormList;

	/**
	 * 種別情報リスト
	 */
	private List<UserRoleForm> userRoleFormList;
}
