package jp.co.ginga.infra.repository.facility;

import java.sql.Timestamp;

import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.infra.repository.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityEntity {
	/**
	 * 施設ID
	 */
	private int facilityId;

	/**
	 * 施設名
	 */
	private String name;

	/**
	 * 施設定員
	 */
	private int capacity;

	/**
	 * 施設種別情報
	 */
	private FacilityTypeEntity  facilityTypeEntity;

	/**
	 * 施設登録日時
	 */
	private Timestamp insertDate;

	/**
	 * 施設登録者
	 */
	private UserEntity insertUserEntity;

	/**
	 * 施設更新日時
	 */
	private Timestamp updateDate;

	/**
	 * 施設更新者
	 */
	private UserEntity updateUserEntity;

	/*
	 * 楽観的ロックで使用
	 * 「変更前の値（変更しようとしていた値）」とは違うということをメッセージなどでわかるようにするためのもの
	 */
	public String toString() {
		String str = this.facilityId
				+ "," + this.name
				+ "," + this.capacity
				+ "," + this.facilityTypeEntity
				+ "," + (this.insertDate == null ? "null" : this.insertDate.toString())
				+ "," + this.insertUserEntity
				+ "," + (this.updateDate == null ? "null" : this.updateDate.toString())
				+ "," + this.updateUserEntity
				;
		return str;
	}

}
