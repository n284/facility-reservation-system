package jp.co.ginga.web.application.util.form.facility;

import java.io.Serializable;

import jp.co.ginga.web.application.util.form.facilityType.FacilityTypeForm;
import jp.co.ginga.web.application.validation.facility.FacilityCapacityValidator;
import jp.co.ginga.web.application.validation.facility.FacilityNameValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityForm implements Serializable {
	/**
	 * 施設ID
	 */
	private int facilityId;

	/**
	 * 施設名
	 */
	@FacilityNameValidator
	private String name;

	/**
	 * 施設定員
	 */
	@FacilityCapacityValidator
	private String capacity;

	/**
	 * 施設種別情報
	 */
	private FacilityTypeForm facilityTypeForm;

	public static FacilityForm getInstance() {
		return new FacilityForm();
	}

	public FacilityTypeForm getFacilityTypeForm() {
		if (this.facilityTypeForm == null) {
			this.facilityTypeForm = FacilityTypeForm.getInstance();
		}
		return this.facilityTypeForm;
	}
}
