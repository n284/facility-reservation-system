/**
 *
 */
package jp.co.ginga.web.domain.service.util.helper.userrole;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.infra.repository.userrole.UserRoleEntity;
import jp.co.ginga.web.domain.service.util.dto.userrole.UserRoleDto;

/**
 * @author souken
 *
 */
@Component
public class UserRoleDtoHelper {

	@Autowired
	ModelMapper modelMapper;

	/**
	 *
	 * @param entity
	 * @return
	 */
	public UserRoleDto mapToUserRoleDto(UserRoleEntity entity) {

		//マップ先があいまいな場合は無視
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		UserRoleDto dto = modelMapper.map(entity, UserRoleDto.class);

		return dto;
	}

	/**
	 *
	 * @param entityList
	 * @return
	 */
	public List<UserRoleDto> mapToUserRoleDtoList(List<UserRoleEntity> entityList) {

		List<UserRoleDto> dtoList = new ArrayList<UserRoleDto>();
		for(UserRoleEntity entity : entityList) {
			UserRoleDto userRoleDto = mapToUserRoleDto(entity);
			dtoList.add(userRoleDto);
		}
		return dtoList;
	}

	/**
	 *
	 * @param dto
	 * @return
	 */
	public UserRoleEntity mapToUserRoleEntity(UserRoleDto dto) {

		//マップが曖昧な場合は無視
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		UserRoleEntity entity = modelMapper.map(dto, UserRoleEntity.class);
		return entity;
	}

	/**
	 *
	 * @param dtoList
	 * @return
	 */
	public List<UserRoleEntity> mapToUserRoleEntityList(List<UserRoleDto> dtoList) {

		List<UserRoleEntity> entityLiat = new ArrayList<UserRoleEntity>();
		for(UserRoleDto dto :dtoList) {

			UserRoleEntity entity = mapToUserRoleEntity(dto);
			entityLiat.add(entity);
		}
		return entityLiat;
	}

}
