package jp.co.ginga.web.application.util.form.facilityreservation;

import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityReservationForm {
	private int reservationId;
	private FacilityForm facilityForm;
	private String year;
	private String month;
	private String date;
	private String startTimeHour;
	private String startTimeMinute;
	private String endTimeHour;
	private String endTimeMinute;

	public int toInteger(String str) {
		return Integer.parseInt(str);
	}
}
