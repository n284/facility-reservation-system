/**
 *
 */
package jp.co.ginga.web.util.security.domain.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.infra.repository.user.UserRepository;
import jp.co.ginga.infra.repository.userrole.UserRoleRepository;

/**
 * @author yoshi
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		try {

			UserEntity userEntity = userRepository.findByUserId(userId);

			//
			String userName = userEntity.getUserName();

			String password = userEntity.getPassword();

			List<Map<String, Object>> roleNameList = userRoleRepository.findRoleIdByUserId(userId);
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			for (Map<String, Object> map : roleNameList) {
				authorities.add(new SimpleGrantedAuthority((String) map.get("rolename")));
			}

			return new UserDetailsImpl(userId, userName, password, authorities);
		} catch (Exception e) {
			throw new UsernameNotFoundException("user not found.", e);
		}
	}

}
