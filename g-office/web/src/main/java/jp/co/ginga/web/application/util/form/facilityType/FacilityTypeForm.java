package jp.co.ginga.web.application.util.form.facilityType;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityTypeForm implements Serializable{

	/**
	 * 施設種別ID
	 */
	private int facilityTypeId;

	/**
	 * 施設種別名
	 */
	private String name;

	/**
	 * @return FacilityTypeForm
	 * インスタンス生成処理
	 */
	public static FacilityTypeForm getInstance() {
		return new FacilityTypeForm();
	}
}
