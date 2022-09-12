package jp.co.ginga.web.application.controller.facility.confirm;

import java.util.List;

import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import jp.co.ginga.web.application.util.form.facilityType.FacilityTypeForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityConfirmForm {
	/**
	 * 入力された値におけるエラーシステムメッセージ
	 */
	private String sysMsg;

	/**
	 * 施設情報リスト
	 */
	private FacilityForm facilityForm;

	/**
	 * 施設種別情報リスト
	 */
	private List<FacilityTypeForm> facilityTypeFormList;

	/**
	 * ボタンの名前
	 */
	private String btnName;

	/**
	 * 押されたボタンの処理名
	 */
	private String btnAction;

	public static FacilityConfirmForm getInstance() {
		return new FacilityConfirmForm();
	}
}
