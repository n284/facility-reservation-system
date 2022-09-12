package jp.co.ginga.infra.repository.role;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleRepository {

	/**
	 * 権限情報検索 全件
	 * @return
	 */
	public List<RoleEntity> findAll();

	/**
	 * 権限情報検索 ロールID指定
	 * @param roleId
	 * @return
	 */
	public RoleEntity findByRoleId(String roleId);

	/**
	 * 権限情報 追加
	 * @param roleEntity
	 * @return
	 */
	public int insert(RoleEntity roleEntity);

	/**
	 * 権限情報 更新
	 * @param roleEntity
	 * @return
	 */
	public int update(RoleEntity roleEntity);

	/**
	 * 権限情報 削除
	 * @param roleEntity
	 * @return
	 */
	public int delete(String roleEntity);

}

