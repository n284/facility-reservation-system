package jp.co.ginga.web.application.util.helper.calendar;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.calendar.CalendarDayForm;
import jp.co.ginga.web.application.util.form.facilityreservation.FacilityReservationForm;
import jp.co.ginga.web.application.util.helper.facilityresertvation.FacilityReservationFormHelper;
import jp.co.ginga.web.domain.service.util.dto.calendar.CalendarDayDto;
import jp.co.ginga.web.domain.service.util.dto.facilityreservation.FacilityReservationDto;

@Component
public class CalendarDayFormHelper {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	FacilityReservationFormHelper facilityReservationFormHelper;

	public CalendarDayDto convertCalendarDayDto(CalendarDayForm calendarDayForm) {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
		CalendarDayDto calendarDayDto = this.modelMapper.map(calendarDayForm, CalendarDayDto.class);

		if(calendarDayForm.getFacilityReservationFormList() != null) {
			List<FacilityReservationDto> facilityReservationDtoList = this.facilityReservationFormHelper.convertFacilityReservationDtoList(calendarDayForm.getFacilityReservationFormList());
			calendarDayDto.setFacilityReservationDtoList(facilityReservationDtoList);
		}
		return calendarDayDto;
	}

	public CalendarDayForm convertCalendarDayForm(CalendarDayDto calendarDayDto) {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
		CalendarDayForm calendarDayForm = this.modelMapper.map(calendarDayDto, CalendarDayForm.class);

		if(calendarDayDto.getFacilityReservationDtoList() != null) {
			List<FacilityReservationForm> facilityReservationFormList = this.facilityReservationFormHelper.convertFacilityReservationFormList(calendarDayDto.getFacilityReservationDtoList());
			calendarDayForm.setFacilityReservationFormList(facilityReservationFormList);
		}
		return calendarDayForm;
	}

	public List<CalendarDayDto> convertCalendarDayDtoList(List<CalendarDayForm> calendarDayFormList){
		List<CalendarDayDto> calendarDayDtoList = new ArrayList<CalendarDayDto>();
		for(CalendarDayForm calendarDayForm  : calendarDayFormList) {
			CalendarDayDto calendarDayDto = this.convertCalendarDayDto(calendarDayForm);
			calendarDayDtoList.add(calendarDayDto);
		}
		return calendarDayDtoList;
	}

	public List<CalendarDayForm> convertCalendarDayFormList(List<CalendarDayDto> calendarDayDtoList){
		List<CalendarDayForm> calendarDayFormList = new ArrayList<CalendarDayForm>();
		for(CalendarDayDto calendarDayDto  : calendarDayDtoList) {
			CalendarDayForm calendarDayForm = this.convertCalendarDayForm(calendarDayDto);
			calendarDayFormList.add(calendarDayForm);
		}
		return calendarDayFormList;
	}
}
