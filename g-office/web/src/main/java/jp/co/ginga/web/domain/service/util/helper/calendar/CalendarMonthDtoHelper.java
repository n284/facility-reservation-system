package jp.co.ginga.web.domain.service.util.helper.calendar;

import org.springframework.stereotype.Component;

@Component
public class CalendarMonthDtoHelper {
//	@Autowired
//	ModelMapper modelMapper;
//
//	@Autowired
//	CalendarDayDtoHelper calendarDayDtoHelper;
//
//	public CalendarMonthDto mapToCalendarMonthDto(CalendarMonthForm calendarMonthForm) {
//		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
//		CalendarMonthDto calendarMonthDto = this.modelMapper.map(calendarMonthForm, CalendarMonthDto.class);
//		if(calendarMonthForm.getCalendarDayFormList() != null) {
//			List<CalendarDayDto> calendarDayDtoList = this.calendarDayDtoHelper.mapToCalendarDayDtoList(calendarMonthForm.getCalendarDayFormList());
//			calendarMonthDto.setCalendarDayDtoList(calendarDayDtoList);
//		}
//		return calendarMonthDto;
//	}
//
//	public CalendarMonthForm mapToCalendarMonthForm(CalendarMonthDto calendarMonthDto) {
//		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
//		CalendarMonthForm calendarMonthForm = this.modelMapper.map(calendarMonthDto, CalendarMonthForm.class);
//		if(calendarMonthDto.getCalendarDayDtoList() != null) {
//			List<CalendarDayForm> calendarDayFormList = this.calendarDayDtoHelper.mapToCalendarDayFormList(calendarMonthDto.getCalendarDayDtoList());
//			calendarMonthForm.setCalendarDayFormList(calendarDayFormList);
//		}
//		return calendarMonthForm;
//	}

}
