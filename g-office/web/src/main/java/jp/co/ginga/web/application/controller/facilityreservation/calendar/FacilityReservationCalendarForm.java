package jp.co.ginga.web.application.controller.facilityreservation.calendar;

import jp.co.ginga.web.application.util.form.calendar.CalendarMonthForm;
import jp.co.ginga.web.application.util.form.facility.FacilityForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityReservationCalendarForm {
	private int year;
	private int month;
	private FacilityForm facilityForm;
	private CalendarMonthForm calendarMonthForm;

	public static FacilityReservationCalendarForm getInstance() {
		return new FacilityReservationCalendarForm();
	}
}
