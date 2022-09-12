package jp.co.ginga.web.application.controller.facility.list;

import java.util.List;

import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityListForm {
	/**
	 * 入力された値におけるエラーシステムメッセージ
	 */
	private String sysMsg;

	/**
	 * 施設情報リスト
	 */
	private List<FacilityForm> facilityFormList;

	public static FacilityListForm getInstance() {
		return new FacilityListForm();
	}
}
