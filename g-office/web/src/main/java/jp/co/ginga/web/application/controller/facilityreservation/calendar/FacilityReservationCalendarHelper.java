package jp.co.ginga.web.application.controller.facilityreservation.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.helper.calendar.CalendarMonthFormHelper;
import jp.co.ginga.web.application.util.helper.facility.FacilityFormHelper;
import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerDto;

@Component
public class FacilityReservationCalendarHelper {
	@Autowired
	CalendarMonthFormHelper calendarMonthFormHelper;

	@Autowired
	FacilityFormHelper facilityFormHelper;

	public FacilityReservationCalendarForm createFacilityReservationCalendarForm(FacilityReservationManagerDto facilityReservationManagerDto) {
		FacilityReservationCalendarForm facilityReservationCalendarForm = FacilityReservationCalendarForm.getInstance();
		facilityReservationCalendarForm.setCalendarMonthForm(this.calendarMonthFormHelper.convertCalendarMonthForm(facilityReservationManagerDto.getCalendarMonthDto()));
		facilityReservationCalendarForm.setFacilityForm(this.facilityFormHelper.convertFacilityForm(facilityReservationManagerDto.getFacilityDto()));

		return facilityReservationCalendarForm;
	}
}
