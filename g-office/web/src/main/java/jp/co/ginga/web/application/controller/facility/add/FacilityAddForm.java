package jp.co.ginga.web.application.controller.facility.add;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import jp.co.ginga.web.application.util.form.facilityType.FacilityTypeForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityAddForm implements Serializable{
	/**
	 * 入力された値におけるエラーシステムメッセージ
	 */
	private String sysMsg;

	/**
	 * 施設情報
	 */
	@Valid
	private FacilityForm facilityForm;

	/**
	 * 施設種別情報リスト
	 */
	private List<FacilityTypeForm> facilityTypeFormList;

	/**
	 * FacilityForm取得処理
	 * @return
	 */
	public FacilityForm getFacilityForm() {
		if(this.facilityForm == null) {
			this.facilityForm = FacilityForm.getInstance();
		}
		return  this.facilityForm;
	}
	/**
	 * インスタンス生成処理
	 * @return
	 */
	public static FacilityAddForm getInstance() {
		return new FacilityAddForm();
	}
}
