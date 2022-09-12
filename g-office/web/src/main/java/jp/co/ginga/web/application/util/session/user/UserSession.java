package jp.co.ginga.web.application.util.session.user;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.role.RoleForm;
import jp.co.ginga.web.application.util.form.user.UserForm;
import jp.co.ginga.web.application.util.form.userRole.UserRoleForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSession implements Serializable {

	/**
	 * ユーザー情報
	 */
	private UserForm userForm;

	/**
	 * 更新前のユーザー情報
	 */
	private UserForm beforeUserForm;

	/**
	 * ユーザー権限情報リスト
	 */
	private List<UserRoleForm> userRoleFormList;

	/**
	 * 権限情報リスト
	 */
	private List<RoleForm> roleFormList;

	/**
	 * 楽観的ロック
	 */
	private String optimisticRockValue;

	/**
	 *
	 * @return
	 */
	public UserForm getUserForm() {
		if (this.userForm == null) {
			this.userForm = new UserForm();
		}
		return this.userForm;
	}

	/**
	 * セッション 破棄
	 */
	public void clear() {
		System.out.println("session-clear");
		this.userForm = null;
		this.beforeUserForm = null;
		this.userRoleFormList = null;
		this.roleFormList = null;
		this.optimisticRockValue = null;
	}
}
