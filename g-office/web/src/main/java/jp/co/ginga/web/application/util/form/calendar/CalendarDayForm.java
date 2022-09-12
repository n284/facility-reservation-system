package jp.co.ginga.web.application.util.form.calendar;

import java.util.List;

import jp.co.ginga.web.application.util.form.facilityreservation.FacilityReservationForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalendarDayForm {
	private List<FacilityReservationForm> facilityReservationFormList;
	private String day;
	private int date;

	public static CalendarDayForm getInstance() {
		return new CalendarDayForm();
	}
}
