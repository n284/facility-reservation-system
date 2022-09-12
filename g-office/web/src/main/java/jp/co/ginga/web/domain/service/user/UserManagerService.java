package jp.co.ginga.web.domain.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;

import jp.co.ginga.infra.repository.role.RoleRepository;
import jp.co.ginga.infra.repository.user.UserRepository;
import jp.co.ginga.infra.repository.userrole.UserRoleRepository;

public interface UserManagerService {
	/**
	 * ユーザー情報全件取得処理
	 * 
	 * @return
	 */
	public UserManagerDto getUserList();

	/**
	 * 施設情報全件取得処理
	 * 
	 * @return
	 */
	public UserManagerDto getRoleList();

	/**
	 * ユーザーIDによる、ユーザー1件検索処理
	 * 
	 * @param userId
	 * @return
	 */
	public UserManagerDto getUser(String userId);

	/**
	 * ユーザーIDによる、ユーザー検索処理
	 * 
	 * @param userId
	 * @return
	 */
	public UserManagerDto getUserListByUserId(String userId);

	/**
	 * ユーザー登録処理
	 * 
	 * @param entity
	 * @return
	 */
	public UserManagerDto add(UserManagerDto umsDto);

	/**
	 * ユーザー更新処理
	 * 
	 * @param entity
	 * @return
	 */
	public UserManagerDto update(UserManagerDto umsDto);

	/**
	 * ユーザー削除処理
	 * 
	 * @param entity
	 * @return
	 */
	public UserManagerDto delete(UserManagerDto umsDto);

	// テスト用メソッド
	public void setUserRepository(UserRepository userRepository);

	public void setUserRoleRepository(UserRoleRepository userRoleRepository);

	public void setRoleRepository(RoleRepository roleRepository);
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder);

}
