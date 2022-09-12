package jp.co.ginga.web.application.util.helper.calendar;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.form.calendar.CalendarDayForm;
import jp.co.ginga.web.application.util.form.calendar.CalendarMonthForm;
import jp.co.ginga.web.domain.service.util.dto.calendar.CalendarDayDto;
import jp.co.ginga.web.domain.service.util.dto.calendar.CalendarMonthDto;

@Component
public class CalendarMonthFormHelper {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CalendarDayFormHelper calendarDayDtoHelper;

	public CalendarMonthDto convertCalendarMonthDto(CalendarMonthForm calendarMonthForm) {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
		CalendarMonthDto calendarMonthDto = this.modelMapper.map(calendarMonthForm, CalendarMonthDto.class);
		if(calendarMonthForm.getCalendarDayFormList() != null) {
			List<CalendarDayDto> calendarDayDtoList = this.calendarDayDtoHelper.convertCalendarDayDtoList(calendarMonthForm.getCalendarDayFormList());
			calendarMonthDto.setCalendarDayDtoList(calendarDayDtoList);
		}
		return calendarMonthDto;
	}

	public CalendarMonthForm convertCalendarMonthForm(CalendarMonthDto calendarMonthDto) {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
		CalendarMonthForm calendarMonthForm = this.modelMapper.map(calendarMonthDto, CalendarMonthForm.class);
		if(calendarMonthDto.getCalendarDayDtoList() != null) {
			List<CalendarDayForm> calendarDayFormList = this.calendarDayDtoHelper.convertCalendarDayFormList(calendarMonthDto.getCalendarDayDtoList());
			calendarMonthForm.setCalendarDayFormList(calendarDayFormList);
		}
		return calendarMonthForm;
	}

}
