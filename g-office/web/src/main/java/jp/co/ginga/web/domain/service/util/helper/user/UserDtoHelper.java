package jp.co.ginga.web.domain.service.util.helper.user;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.infra.repository.user.UserEntity;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;

@Component
public class UserDtoHelper {

	// modelMapper.map(コピー元, コピー先.class（classをつけることでコピー先の生成も行う)
	// entityをUserDtoに変換している

	@Autowired
	ModelMapper modelMapper;

	

	/**
	 * EntityからDtoへ変換処理
	 * 
	 * @param entity
	 * @return
	 */
	public UserDto mapToUserDto(UserEntity entity) {

		// マップ先が曖昧な場合は無視
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		UserDto userDto = modelMapper.map(entity, UserDto.class);
		return userDto;

	}

	/**
	 * List<UserEntity>からList<UserDto>へ変換処理
	 * 
	 * @param userEntity
	 * @param entityList
	 * @return
	 */
	public List<UserDto> mapToUserDtoList(List<UserEntity> entityList) {
		List<UserDto> dtoList = new ArrayList<UserDto>();
		for (UserEntity entity : entityList) {
			UserDto userDto = this.mapToUserDto(entity);
			dtoList.add(userDto);
		}
		return dtoList;

	}

	/**
	*DtoからEntityへ変換処理
	* @param dto
	* @return
	*/

	public UserEntity mapToUserEntity(UserDto dto) {

		//マップ先が曖昧な場合は無視
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		UserEntity userEntity = modelMapper.map(dto, UserEntity.class);
		
		return userEntity;

	}

	/**
	 * List<UserDto>からList<UserEntity>へ変換処理
	 * 
	 * @param userDto
	 * @param dtoList
	 * @return
	 */

	public List<UserEntity> mapToUserEntityList(List<UserDto> dtoList) {

		List<UserEntity> entityList = new ArrayList<UserEntity>();
		for (UserDto dto : dtoList) {
			UserEntity userEntity = mapToUserEntity(dto);
			entityList.add(userEntity);
		}

		return entityList;

	}

}
