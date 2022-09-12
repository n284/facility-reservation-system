package jp.co.ginga.web.application.controller.facilityreservation.detail;

import jp.co.ginga.web.application.util.form.facilityreservation.FacilityReservationForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityReservationDetailForm {
	private String sysMsg;
	private FacilityReservationForm facilityReservationForm;

	public static FacilityReservationDetailForm getInstance() {
		return new FacilityReservationDetailForm();
	}
}
