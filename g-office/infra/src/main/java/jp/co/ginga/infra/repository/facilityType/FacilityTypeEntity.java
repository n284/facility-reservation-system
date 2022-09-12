package jp.co.ginga.infra.repository.facilityType;

import java.sql.Date;

import jp.co.ginga.infra.repository.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityTypeEntity {
	/**
	 * 施設種別ID
	 */
	private int facilityTypeId;

	/**
	 * 施設種別名
	 */
	private String name;

	/**
	 * 施設種別登録日時
	 */
	private Date insertDate;

	/**
	 * 施設種別登録者
	 */
	private UserEntity insertUserEntity;

	/**
	 * 施設種別更新日時
	 */
	private Date updateDate;

	/**
	 * 施設種別更新者
	 */
	private UserEntity updateUserEntity;

	/**
	 * 施設種別削除日時
	 */
	private Date deleteDate;

	/**
	 * 施設種別登録者
	 */
	private UserEntity deleteUserEntity;

	/**
	 * 削除フラグ
	 */
	private int fgDelete;

	/*
	 * 楽観的ロックで使用
	 * 「変更前の値（変更しようとしていた値）」とは違うということをメッセージなどでわかるようにするためのもの
	 */
	public String toString() {
		String str = this.facilityTypeId
				+ "," + this.name
				+ "," + (this.insertDate == null ? "null" : this.insertDate.toString())
				+ "," + this.insertUserEntity
				+ "," + (this.updateDate == null ? "null" : this.updateDate.toString())
				+ "," + this.updateUserEntity
				+ "," + (this.deleteDate == null ? "null" : this.deleteDate.toString())
				+ "," + this.deleteUserEntity
				+ "," + this.fgDelete
				;
		return str;
	}
}
