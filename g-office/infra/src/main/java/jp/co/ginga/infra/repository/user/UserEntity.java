package jp.co.ginga.infra.repository.user;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
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
	 * UserEntity toString
	 */
//	toString()で文字列に変換することでデータベースの値を複数の人が変更しているときに誰かが変更した値を上書きしてしまわないようにtoString()で文字列にしておくことで
//	「変更前の値（変更しようとしていた値）」とは違うということをメッセージなどでわかるようにするためのもの
	public String toString() {

		String str = this.userId
				+ "," + userName
				+ "," + password
				+ "," + (passUpdateDate == null ? "null" : passUpdateDate.toString())
				+ "," + gender
				+ "," + birthday
				+ "," + contact
				+ "," + mailAddress
				+ "," + loginMissTimes
				+ "," + (userDueDate == null ? "null" : userDueDate.toString())
				+ "," + (insertDate == null ? "null" : insertDate.toString())
				+ "," + (updateDate == null ? "null" : updateDate.toString())
				;
		return str;
	}

//	public boolean getUnlock() {
//		return unlock;
//	}

}
