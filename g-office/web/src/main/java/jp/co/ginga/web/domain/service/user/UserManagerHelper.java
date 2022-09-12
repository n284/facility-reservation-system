package jp.co.ginga.web.domain.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.infra.repository.role.RoleEntity;
import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.infra.repository.userrole.UserRoleEntity;
import jp.co.ginga.web.domain.service.util.constant.ServiceConst;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import jp.co.ginga.web.domain.service.util.helper.role.RoleDtoHelper;
import jp.co.ginga.web.domain.service.util.helper.user.UserDtoHelper;
import jp.co.ginga.web.domain.service.util.helper.userrole.UserRoleDtoHelper;

@Component
public class UserManagerHelper {

	/**
	 * ユーザーDtoヘルパー
	 */
	@Autowired
	UserDtoHelper userDtoHelper;

	/**
	 * ユーザー権限Dtoヘルパー
	 */
	@Autowired
	RoleDtoHelper roleDtoHelper;

	@Autowired
	UserRoleDtoHelper userRoleDtoHelper;

	/**
	 * UserManagerDto生成処理
	 * 
	 * @param entity
	 * @param entityList
	 * @return
	 */
	public UserManagerDto createUserServiceDto(UserEntity userEntity, List<UserRoleEntity> userRoleList,
			List<RoleEntity> roleEntityList) {

		UserManagerDto dto = UserManagerDto.getInstance();

		dto.setUserDto(userDtoHelper.mapToUserDto(userEntity));
		dto.setUserRoleDtoList(userRoleDtoHelper.mapToUserRoleDtoList(userRoleList));
		dto.setRoleDtoList(roleDtoHelper.mapToRoleDtoList(roleEntityList));

		return dto;
	}

	/**
	 * UserEntity生成処理
	 * 
	 * @param dto
	 * @return
	 */
	public UserEntity getUserEntity(UserManagerDto dto) {
		UserEntity entity = userDtoHelper.mapToUserEntity(dto.getUserDto());

		return entity;
	}

	/**
	 * UserManagerDtoAdd生成処理
	 * 
	 * @param userEntity
	 * @return
	 */
	public UserManagerDto createUserServiceDtoAdd(int result) {

		UserManagerDto dto = UserManagerDto.getInstance();

		// 処理結果に伴い
		switch (result) {
		case 1:
			dto.setResult(ServiceConst.THERE_IS_DATA);
			break;
		case 0:
			dto.setResult(ServiceConst.NO_DATA);
			break;
		default:
			dto.setResult(ServiceConst.ERROR);
			break;
		}

		return dto;

	}

	/**
	 * UserManagerDto生成処理
	 * 
	 * @param userEntity
	 * @return
	 */
	public UserManagerDto createUserManagerServiceDto(UserEntity userEntity) {

		// UserManagerDtoインスタンス取得
		UserManagerDto managerDto = UserManagerDto.getInstance();

		UserDto dto = UserDto.getInstance();

		// UserDto取得処理
		if (userEntity != null) {
			dto = userDtoHelper.mapToUserDto(userEntity);
		} else {
			dto = null;
		}
		// UserDto情報セット処理
		managerDto.setUserDto(dto);
		if (dto == null) {
			managerDto.setResult(ServiceConst.NO_DATA);
		}
		return managerDto;

	}

	public UserManagerDto createUserManagerDto(List<UserEntity> userEntityList,
			List<UserRoleEntity> userRoleEntityList) {
		UserManagerDto dto = UserManagerDto.getInstance();

		dto.setUserDtoList(userDtoHelper.mapToUserDtoList(userEntityList));
		dto.setUserRoleDtoList(userRoleDtoHelper.mapToUserRoleDtoList(userRoleEntityList));

		return dto;

//		List<UserDto> userDtoList = new ArrayList<UserDto>();
//
//		for (UserEntity userEntity : userEntityList) {
//			UserDto userDto = userDtoHelper.mapToUserDto(userEntity);
//			List<UserRoleDto> userRoleDtolist = new ArrayList<UserRoleDto>();
//
//			for (UserRoleEntity userRoleEntity : userRoleEntityList) {
//				if (userRoleEntity.getUserId().equals(userEntity.getUserId())) {
//					UserRoleDto userRoleDto = new UserRoleDto();
//					// userRoleDto.setId(userRoleEntity.getId());
//					userRoleDto.setUserId(userRoleEntity.getUserId());
//					userRoleDto.setRoleId(userRoleEntity.getRoleId());
//					userRoleDtolist.add(userRoleDto);
//
//				}
//
//			}
//
//			if (userRoleDtolist.size() == 0) {
//				userRoleDtolist = null;
//			}
//			userDto.setUserRoleList(userRoleDtolist);
//
//			userDtoList.add(userDto);
//		}
//		dto.setUserDtoList(userDtoList);
//
//		return dto;
//	}

	}
}
