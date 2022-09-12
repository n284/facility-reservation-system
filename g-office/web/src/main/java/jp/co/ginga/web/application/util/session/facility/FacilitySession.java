package jp.co.ginga.web.application.util.session.facility;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import jp.co.ginga.web.application.util.form.facilityType.FacilityTypeForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilitySession implements Serializable {

	/**
	 * 施設情報フォーム
	 */
	private FacilityForm facilityForm;

	/**
	 * 更新前の施設情報フォーム
	 */
	private FacilityForm beforeFacilityForm;

	/**
	 * 施設情報フォーム
	 */
	private List<FacilityTypeForm> facilityTypeFormList;

	/**
	 * 楽観的ロック
	 */
	private String optimisticRockValue;


	/**
	 * セッション中のFacilityFormを取得
	 * @return FacilityForm
	 */
	public FacilityForm getFacilityForm() {
		if(this.facilityForm == null) {
			this.facilityForm = FacilityForm.getInstance();
		}

		return this.facilityForm;
	}
	/**
	 * セッション破棄
	 */
	public void clear() {
		System.out.println("session-clear");
		this.facilityForm = null;
		this.beforeFacilityForm = null;
		this.facilityTypeFormList = null;
		this.optimisticRockValue = null;
	}


}
