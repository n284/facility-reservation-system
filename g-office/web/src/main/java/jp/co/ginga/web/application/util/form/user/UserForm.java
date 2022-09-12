package jp.co.ginga.web.application.util.form.user;

import java.io.Serializable;
import java.util.List;

import jp.co.ginga.web.application.validation.user.UserBirthdayValidator;
import jp.co.ginga.web.application.validation.user.UserContactValidator;
import jp.co.ginga.web.application.validation.user.UserIdValidator;
import jp.co.ginga.web.application.validation.user.UserMailAddressValidator;
import jp.co.ginga.web.application.validation.user.UserNameValidator;
import jp.co.ginga.web.application.validation.user.UserPassWordValidator;
import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm implements Serializable {

	/**
	 * ユーザーID
	 */
	@UserIdValidator
	private String userId;

	/**
	 * ユーザー名
	 */
	@UserNameValidator
	private String userName;

	/**
	 * パスワード
	 */
	@UserPassWordValidator
	private String password;

	/**
	 * 確認用パスワード
	 */
	private String retypePassword;

	/**
	 * 性別
	 */
	private String gender;

	/**
	 * 生年月日
	 */
	@UserBirthdayValidator
	private String birthday;

	/**
	 * 連絡先
	 */
	@UserContactValidator
	private String contact;

	/**
	 * 確認用連絡先
	 */
	private String retypeContact;

	/**
	 * メールアドレス
	 */
	@UserMailAddressValidator
	private String mailAddress;

	/**
	 * 確認用メールアドレス
	 */
	private String retypeMailAddress;

	/**
	 * 権限
	 */
	private List<UserRoleDto> userRoleList;

	public static UserForm getInstance() {
		return new UserForm();
	}

}
