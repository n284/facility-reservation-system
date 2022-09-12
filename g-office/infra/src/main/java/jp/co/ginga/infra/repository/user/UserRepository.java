package jp.co.ginga.infra.repository.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepository {

	/**
	 * ユーザー情報 全件件検索
	 * @return
	 */
	public List<UserEntity> findAll();

	/**
	 * ユーザー情報 主キー検索
	 * @param String userId
	 * @return UserEntity
	 */
	public UserEntity findByUserId(String userId);

	/**
	 * ユーザー情報 重複検索
	 * @param String userId
	 * @return UserEntity
	 */
	public List<UserEntity> getUserIdByUserId(String userId);

	/**
	 * ユーザー情報 登録
	 * @param UserEntity entity
	 * @return int
	 */
	public int insert(UserEntity entity);

	/**
	 * ユーザー情報 更新
	 * @param UserEntity entity
	 * @return int
	 */
	public int update(UserEntity entity);

	/**
	 * ユーザー情報 アカウントロックステータス 更新
	 * @param boolean unlock true:ロック解除 false:ロック中
	 * @param String userId
	 * @return int
	 */
	//public int updateUnlock(@Param("unlock") boolean unlock, @Param("userId") String userId);

	/**
	 * ユーザー情報 有効ステータス 更新
	 * @param boolean enabled true:アカウント有効 false:アカウント無効
	 * @param String userId
	 * @return int
	 */
	//public int updateEnabled(@Param("enabled") boolean enabled, @Param("userId") String userId);

	/**
	 * ユーザー情報 削除
	 * @param String userId
	 * @return int
	 */
	public int delete(String userId);

}
