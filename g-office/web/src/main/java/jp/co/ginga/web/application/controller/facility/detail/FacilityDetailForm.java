package jp.co.ginga.web.application.controller.facility.detail;

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
public class FacilityDetailForm {
	/**
	 * 入力された値におけるエラーシステムメッセージ
	 */
	private String sysMsg;

	/**
	 * 施設情報リスト
	 */
	@Valid
	private FacilityForm facilityForm;

	/**
	 * 施設種別情報リスト
	 */
	private List<FacilityTypeForm> facilityTypeFormList;

	public FacilityForm getFacilityForm() {
		if(this.facilityForm == null) {
			this.facilityForm = FacilityForm.getInstance();
		}

		return  this.facilityForm;
	}

	public static FacilityDetailForm getInstance() {
		return new FacilityDetailForm();
	}
}
