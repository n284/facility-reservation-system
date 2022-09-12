package jp.co.ginga.web.application.controller.facility.complete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityCompleteForm {
	/**
	 * 入力された値におけるエラーシステムメッセージ
	 */
	private String sysMsg;

	/**
	 * ボタンの名前
	 */
	private String btnName;

	/**
	 * 押されたボタンの処理名
	 */
	private String btnAction;

	public static FacilityCompleteForm getInstance() {
		return new FacilityCompleteForm();
	}
}
