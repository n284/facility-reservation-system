package jp.co.ginga.infra.repository.userrole;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yuzuka ikeda
 *
 */
@Repository
@Mapper
public interface UserRoleRepository {

	/**
	 * ユーザー権限関連情報 全件検索
	 * 
	 * @return List<UserRoleEntity>
	 */
	// findAllなのでリストを返す
	public List<UserRoleEntity> findAll();

	/*
	 * ユーザー権限関連情報 ユーザーID指定検索 連想配列型で返す
	 * 
	 * @param userId
	 * 
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> findRoleIdByUserId(String username); // 型違い
	/*
	 * 検索用の SQL は、<select> タグで宣言する。 id 属性で、その SQL を参照するための識別名を設定する。 resultType
	 * 属性で、検索結果のレコードをどの型にマッピングするかを定義する。 ここでは map と指定しているので、 Map 型に変換される。→
	 * Map<String, Object> 検索結果が複数のレコードを返す場合、SqlSession の selectList() メソッドを使用する。
	 * 戻り値は List 型になる。→ List<Map<String, Object>>
	 */

	/**
	 * ユーザー権限関連情報（ID, ユーザーID, 権限ID） ユーザーID指定検索 リスト型で返す
	 * 
	 * @param userId
	 * @return List<UserRoleEntity>
	 */
	public List<UserRoleEntity> findByUserId(String userId);

	/**
	 * ユーザー権限関連情報 ロールID指定検索
	 * 
	 * @param roleId
	 * @return List<UserRoleEntity>
	 */
	public List<UserRoleEntity> findByRoleId(String roleId);

	/**
	 * ユーザー権限関連情報 登録 ユーザー情報が登録されるとき、同一ユーザーの権限情報も登録する
	 * 
	 * @param userRoleEntity
	 * @return int
	 */
	public boolean insert(UserRoleEntity userRoleEntity);

	/**
	 * ユーザー権限関連情報 更新 ユーザー情報が更新されるとき、同一ユーザーの権限情報も更新する
	 * 
	 * @param userRoleEntity
	 * @return int
	 */
	// public int update(UserRoleEntity userRoleEntity);

	// 静的パターン
	// public int updateUserId(String beforeUserId, String afterUserId);
	// public int updateRoleId(String beforeRoleId, String afterRoleId);

	// 動的パターン
	public int update(@Param("afterEntity") List<UserRoleEntity> afterUserRoleEntity,
			@Param("beforeEntity") List<UserRoleEntity> beforeUserRoleEntity);

	/**
	 * ユーザー権限関連情報 削除 条件 主キーによる限定 ユーザー情報が削除されるとき、同一ユーザーの権限情報も削除する
	 * 
	 * @param userRoleEntity
	 * @return int
	 */
	// 削除処理用デリート文
	public int delete(String userId);

	// 更新処理用デリート文
	public boolean updateDelete(UserRoleEntity userRoleEntity);

}
// もし権限の増減があるなら　論理削除への変更の可能性あり
// ロールID指定で検索したリストサイズが0の時、権限の削除が可能になる
// リストが0じゃない限りは「削除不可」のメッセージを出す　　　ようにすべき？
