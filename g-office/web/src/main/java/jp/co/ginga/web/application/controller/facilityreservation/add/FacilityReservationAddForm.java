package jp.co.ginga.web.application.controller.facilityreservation.add;

import java.util.List;

import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import jp.co.ginga.web.application.util.form.facilityreservation.FacilityReservationForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FacilityReservationAddForm {
	private FacilityForm facilityForm;
	private List<FacilityReservationForm> facilityReservationFormList;

	public static FacilityReservationAddForm getInstance() {
		return new FacilityReservationAddForm();
	}
}
