package jp.co.ginga.web.domain.service.util.dto.user;

import java.sql.Timestamp;
import java.util.List;

import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

	/**
	 * ログインID
	 */
	private String userId;

	/**
	 * ユーザー名
	 */
	private String userName;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * パスワード更新日時
	 */
	private Timestamp passUpdateDate;

	/**
	 * 性別
	 */
	private String gender;

	/**
	 * 生年月日
	 */
	private String birthday;

	/**
	 * 連絡先
	 */
	private String contact;

	/**
	 * メールアドレス
	 */
	private String mailAddress;

	/**
	 * ログイン失敗回数
	 */
	private int loginMissTimes;

	/**
	 * ロック状態
	 */
	private boolean unlock;

	/**
	 * ユーザー有効無効
	 */
	private boolean enabled;

	/**
	 * ユーザー有効期限
	 */
	private Timestamp userDueDate;

	/**
	 * 登録日時
	 */
	private Timestamp insertDate;

	/**
	 * 更新日時
	 */
	private Timestamp updateDate;

	/**
	 * 権限Map
	 */
	//	private Map<Integer, String> mapAuthority;
	private List<UserRoleDto> userRoleList;

	/**
	 *
	 * @return
	 * インスタンス生成処理
	 */
	public static UserDto getInstance() {
		return new UserDto();
	}

}
