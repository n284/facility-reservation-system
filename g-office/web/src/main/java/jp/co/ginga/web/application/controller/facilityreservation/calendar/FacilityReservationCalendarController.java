package jp.co.ginga.web.application.controller.facilityreservation.calendar;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.co.ginga.infra.repository.reservation.ReservationRepository;
import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerDto;
import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerService;

@Controller
public class FacilityReservationCalendarController {
	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	FacilityReservationCalendarHelper facilityReservationCalendarHelper;

	@Autowired
	FacilityReservationManagerService facilityReservationManagerService;

	@GetMapping(path="/facilityreservation/calendar/{facilityId}")
	public String getCalenderDetail(ModelMap modelMap, @PathVariable int facilityId) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(2021, 4-1, 6);
		FacilityReservationManagerDto facilityReservationManagerDto = this.facilityReservationManagerService.getFacilityReservationByCalendar(facilityId, calendar);
		FacilityReservationCalendarForm facilityReservationCalendarForm = this.facilityReservationCalendarHelper.createFacilityReservationCalendarForm(facilityReservationManagerDto);
		facilityReservationCalendarForm.setYear(calendar.get(Calendar.YEAR));
		facilityReservationCalendarForm.setMonth(calendar.get(Calendar.MONTH)+1);
		modelMap.addAttribute("facilityReservationCalendarForm", facilityReservationCalendarForm);
		return "facilityreservation/facilityreservation-calendar";
	}

	@GetMapping(path="/facilityreservation/calendar/{facilityId}/{year}/{month}")
	public String getCalenderDetail(ModelMap modelMap, @PathVariable int facilityId, @PathVariable int year, @PathVariable int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, 1);
		FacilityReservationManagerDto facilityReservationManagerDto = this.facilityReservationManagerService.getFacilityReservationByCalendar(facilityId, calendar);
		FacilityReservationCalendarForm facilityReservationCalendarForm = this.facilityReservationCalendarHelper.createFacilityReservationCalendarForm(facilityReservationManagerDto);
		facilityReservationCalendarForm.setYear(calendar.get(Calendar.YEAR));
		facilityReservationCalendarForm.setMonth(calendar.get(Calendar.MONTH) + 1);
		modelMap.addAttribute("facilityReservationCalendarForm", facilityReservationCalendarForm);
		return "facilityreservation/facilityreservation-calendar";
	}

	@GetMapping(path="/facilityreservation/remake/{facilityId}/{year}/{month}")
	public String getCalenderDetailOnAjax(ModelMap modelMap, @PathVariable int facilityId, @PathVariable int year, @PathVariable int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month-1, 1);
		FacilityReservationManagerDto facilityReservationManagerDto = this.facilityReservationManagerService.getFacilityReservationByCalendar(facilityId, calendar);
		FacilityReservationCalendarForm facilityReservationCalendarForm = this.facilityReservationCalendarHelper.createFacilityReservationCalendarForm(facilityReservationManagerDto);
		facilityReservationCalendarForm.setYear(calendar.get(Calendar.YEAR));
		facilityReservationCalendarForm.setMonth(calendar.get(Calendar.MONTH) + 1);
		modelMap.addAttribute("facilityReservationCalendarForm", facilityReservationCalendarForm);
		return "facilityreservation/facilityreservation-calendar";
	}
}
