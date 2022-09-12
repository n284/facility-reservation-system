/**
 *
 */
package jp.co.ginga.web.util.security.domain.service.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yoshi
 *
 */
@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String userId; // ユーザーID
	private String password; // パスワード
	private Date passUpdateDate; // パスワード更新日付
	private int loginMissTimes; // ログイン失敗回数
	private boolean unlock; // ロック状態フラグ
	private boolean enabled; // 有効・無効フラグ
	private Date userDueDate; // ユーザー有効期限

	// 権限のCollection
	private Collection<? extends GrantedAuthority> authorities;

	// 独自のフィールドがある場合は、以下に追記
	private Date birthday; // ユーザー名
	private String appUserName; // ユーザー名
	private String gender; // 性別
	private String contact; // 連絡先
	private String mailAddress; // メールアドレス
	private int loginMissTime; // ログイン失敗回数
	private Date insertDate; // 登録日時
	private Date updateDate; // 更新日時

	/**
	 * コンストラクタ
	 * 
	 * @param username
	 * @param password
	 * @param authorities
	 */
	public UserDetailsImpl(String userId, String userName, String password, Collection<GrantedAuthority> authorities) {
		this.userId = userId;
		this.appUserName = userName;
		this.password = password;
		this.authorities = authorities;
	}

	/**
	 * 権限リストを返す
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	/**
	 * パスワードを返す
	 */
	@Override
	public String getPassword() {
		return this.password;
	}

	/**
	 * ユーザー名を返す
	 */
	@Override
	public String getUsername() {
		return this.appUserName;
	}

	/**
	 * アカウントの有効期限の判定
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * アカウントのロック状態の判定
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 資格情報の有効期限の判定
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 有効なユーザーであるかの判定
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
