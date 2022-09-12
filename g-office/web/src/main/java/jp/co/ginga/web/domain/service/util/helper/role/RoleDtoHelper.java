package jp.co.ginga.web.domain.service.util.helper.role;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.infra.repository.role.RoleEntity;
import jp.co.ginga.web.domain.service.util.dto.role.RoleDto;

@Component
public class RoleDtoHelper {

	@Autowired
	ModelMapper modelMapper;

	public RoleDto mapToRoleDto(RoleEntity entity) {

		//マップ先が曖昧な場合は無視
		modelMapper.getConfiguration().setAmbiguityIgnored(true);

		RoleDto roleDto = modelMapper.map(entity, RoleDto.class);

		return roleDto;

	}

	/**
	* List<RoleEntity>からList<RoleDto>へ変換処理
	* @param roleEntity
	/**
	*
	* @param entityList
	* @return
	*/

	public List<RoleDto> mapToRoleDtoList(List<RoleEntity> entityList) {

		List<RoleDto> dtoList = new ArrayList<RoleDto>();

		for (RoleEntity entity : entityList) {

			RoleDto roleDto = mapToRoleDto(entity);

			dtoList.add(roleDto);

		}

		return dtoList;

	}

	/**
	*
	* @param dto
	* @return
	*/

	public RoleEntity mapToRoleEntity(RoleDto dto) {

		//マップ先が曖昧な場合は無視

		modelMapper.getConfiguration().setAmbiguityIgnored(true);

		RoleEntity roleEntity = modelMapper.map(dto, RoleEntity.class);

		return roleEntity;

	}

	/**
	* List<RoleDto>からList<RoleEntity>へ変換処理
	* @param roleDto
	* @param dtoList
	* @return
	*/

	public List<RoleEntity> mapToRoleEntityList(List<RoleDto> dtoList) {

		List<RoleEntity> entityList = new ArrayList<RoleEntity>();

		for (RoleDto dto : dtoList) {

			RoleEntity roleEntity = mapToRoleEntity(dto);

			entityList.add(roleEntity);

		}

		return entityList;

	}

}
