package jp.co.ginga.web.application.controller.facilityreservation.list;

import java.util.List;

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
public class FacilityReservationListForm {
	private List<FacilityForm> facilityFormList;
	private List<FacilityTypeForm> facilityTypeFormList;

	public static FacilityReservationListForm getInstance() {
		return new FacilityReservationListForm();
	}
}
