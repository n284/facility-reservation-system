package jp.co.ginga.web.domain.service.user;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ginga.infra.repository.role.RoleEntity;
import jp.co.ginga.infra.repository.role.RoleRepository;
import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.infra.repository.user.UserRepository;
import jp.co.ginga.infra.repository.userrole.UserRoleEntity;
import jp.co.ginga.infra.repository.userrole.UserRoleRepository;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import jp.co.ginga.web.domain.service.util.helper.role.RoleDtoHelper;
import jp.co.ginga.web.domain.service.util.helper.user.UserDtoHelper;
import jp.co.ginga.web.domain.service.util.helper.userrole.UserRoleDtoHelper;

@Service
@Transactional
public class UserManagerServiceImpl implements UserManagerService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	UserManagerHelper userManagerHelper;

	@Autowired
	UserRoleDtoHelper userRoleHelper;

	@Autowired
	RoleDtoHelper roleHelper;

	@Autowired
	UserDtoHelper userDtoHelper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserManagerDto getUserList() {

		List<UserEntity> userEntityList = userRepository.findAll();
		List<UserRoleEntity> userRoleList = userRoleRepository.findAll();

		UserManagerDto dto = userManagerHelper.createUserManagerDto(userEntityList, userRoleList);
		dto.setUserRoleDtoList(userRoleHelper.mapToUserRoleDtoList(userRoleList));
		return dto;
	}

	@Override
	public UserManagerDto getRoleList() {

		UserManagerDto dto = UserManagerDto.getInstance();

		List<RoleEntity> roleEntityList = roleRepository.findAll();

		dto.setRoleDtoList(roleHelper.mapToRoleDtoList(roleEntityList));

		return dto;

	}

	@Override
	public UserManagerDto getUser(String userId) {

		UserManagerDto dto = UserManagerDto.getInstance();

		UserEntity userEntity = userRepository.findByUserId(userId);
		List<UserRoleEntity> userRoleList = userRoleRepository.findByUserId(userId);
		List<RoleEntity> roleEntityList = roleRepository.findAll();

		dto = userManagerHelper.createUserServiceDto(userEntity, userRoleList, roleEntityList);

		// ユーザー情報楽観ロック情報設定
		dto.setOptimisticRockValue(userEntity.toString());

		return dto;
	}

	@Override
	public UserManagerDto getUserListByUserId(String userId) {

		// 施設情報主キー検索
		List<UserEntity> resultUserEntityList = userRepository.getUserIdByUserId(userId);

		// 処理結果オブジェクト生成
		UserManagerDto userServiceDto = UserManagerDto.getInstance();

		List<UserDto> resultUserDtoList = userDtoHelper.mapToUserDtoList(resultUserEntityList);

		// 検索結果設定
		userServiceDto.setUserDtoList(resultUserDtoList);

		// 処理結果設定
		if (resultUserEntityList.size() == 0) {
			userServiceDto.setResult(ServiceConst.NO_DATA);
		} else {
			userServiceDto.setResult(ServiceConst.THERE_IS_DATA);
		}

		return userServiceDto;

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
	public UserManagerDto add(UserManagerDto umsDto) {
		// 施設情報Entityの取得
		UserEntity entity = userManagerHelper.getUserEntity(umsDto);
		List<UserRoleEntity> usRoleEntList = userRoleHelper.mapToUserRoleEntityList(umsDto.getUserRoleDtoList());

		entity.setPassword(passwordEncoder.encode(entity.getPassword()));

		int result = userRepository.insert(entity);
		boolean resultRole = false;

		for (UserRoleEntity usRoleEnt : usRoleEntList) {
			resultRole = userRoleRepository.insert(usRoleEnt);
		}

		if (resultRole == false) {
			result = 0;
		}

		UserManagerDto userServiceDto = userManagerHelper.createUserServiceDtoAdd(result);

		return userServiceDto;

	}

	@Override
	public UserManagerDto update(UserManagerDto umsDto) {
		UserManagerDto userDto = UserManagerDto.getInstance();

		UserEntity updateUserEntity = userManagerHelper.getUserEntity(umsDto);
		UserEntity userEntity = userRepository.findByUserId(umsDto.getUserDto().getUserId());

		List<UserRoleEntity> updateUsRoleEnt = userRoleHelper.mapToUserRoleEntityList(umsDto.getUserRoleDtoList());
		List<UserRoleEntity> dbRoleEnt = userRoleRepository.findByUserId(umsDto.getUserDto().getUserId());

		boolean resultRole = false;

		if (updateUsRoleEnt.size() == dbRoleEnt.size()) {

			resultRole = true;

		}

		if (updateUsRoleEnt.size() > dbRoleEnt.size()) {

			for (UserRoleEntity usRoleEnt : updateUsRoleEnt) {
				resultRole = userRoleRepository.insert(usRoleEnt);
			}

		}

		if (updateUsRoleEnt.size() < dbRoleEnt.size()) {

			for (UserRoleEntity usRoleEnt : updateUsRoleEnt) {
				resultRole = userRoleRepository.updateDelete(usRoleEnt);
			}

		}

		if (resultRole == false) {
			userDto.setResult(ServiceConst.ERROR);
			return userDto;
		}

		System.out.println(userEntity.toString());
		System.out.println(umsDto.getOptimisticRockValue());

		// 楽観的ロックによるチェック
		if (userEntity.toString().equals(umsDto.getOptimisticRockValue())) {

			// ストレージから取得したエンコードされたパスワードが、エンコードされた後に送信された生のパスワードと一致することを確認します。
			if (!passwordEncoder.matches(updateUserEntity.getPassword(), userEntity.getPassword())) {

				Timestamp timestamp = new Timestamp(System.currentTimeMillis());

				updateUserEntity.setPassUpdateDate(timestamp);

			}

			updateUserEntity.setPassword(passwordEncoder.encode(updateUserEntity.getPassword()));

			// 施設情報更新
			int result = userRepository.update(updateUserEntity);

			if (result != 1) {
				userDto.setResult(ServiceConst.ERROR);
			} else {
				userDto.setResult(ServiceConst.OK);
			}
		} else {
			userDto.setResult(ServiceConst.OPTIMISTIC_ROCK_ERROR);
		}

		return userDto;
	}

	@Override
	public UserManagerDto delete(UserManagerDto umsDto) {

		UserManagerDto dto = UserManagerDto.getInstance();

		int result = userRepository.delete(umsDto.getUserDto().getUserId());
		int resultRole = userRoleRepository.delete(umsDto.getUserDto().getUserId());

		if (result != 0 && resultRole != 0) {
			dto.setResult(ServiceConst.OK);
			return dto;
		}

		dto.setResult(ServiceConst.ERROR);

		return dto;
	}

	@Override
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
