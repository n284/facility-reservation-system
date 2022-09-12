package jp.co.ginga.web.domain.service.util.helper.calendar;

import org.springframework.stereotype.Component;

@Component
public class CalendarDayDtoHelper {
//	@Autowired
//	ModelMapper modelMapper;
//
//	@Autowired
//	FacilityReservationFormHelper facilityReservationFormHelper;
//
//	public CalendarDayDto mapToCalendarDayDto(CalendarDayForm calendarDayForm) {
//		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
//		CalendarDayDto calendarDayDto = this.modelMapper.map(calendarDayForm, CalendarDayDto.class);
//
//		if(calendarDayForm.getFacilityReservationForm() != null) {
//			FacilityReservationDto facilityReservationDto = this.modelMapper.map(calendarDayForm.getFacilityReservationForm(), FacilityReservationDto.class);
//			calendarDayDto.setFacilityReservationDto(facilityReservationDto);
//		}
//		return calendarDayDto;
//	}
//
//	public CalendarDayForm mapToCalendarDayForm(CalendarDayDto calendarDayDto) {
//		this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
//		CalendarDayForm calendarDayForm = this.modelMapper.map(calendarDayDto, CalendarDayForm.class);
//
//		if(calendarDayDto.getFacilityReservationDto() != null) {
//			FacilityReservationForm facilityReservationForm = this.modelMapper.map(calendarDayDto.getFacilityReservationDto(), FacilityReservationForm.class);
//			calendarDayForm.setFacilityReservationForm(facilityReservationForm);
//		}
//		return calendarDayForm;
//	}
//
//	public List<CalendarDayDto> mapToCalendarDayDtoList(List<CalendarDayForm> calendarDayFormList){
//		List<CalendarDayDto> calendarDayDtoList = new ArrayList<CalendarDayDto>();
//		for(CalendarDayForm calendarDayForm  : calendarDayFormList) {
//			CalendarDayDto calendarDayDto = this.mapToCalendarDayDto(calendarDayForm);
//			calendarDayDtoList.add(calendarDayDto);
//		}
//		return calendarDayDtoList;
//	}
//
//	public List<CalendarDayForm> mapToCalendarDayFormList(List<CalendarDayDto> calendarDayDtoList){
//		List<CalendarDayForm> calendarDayFormList = new ArrayList<CalendarDayForm>();
//		for(CalendarDayDto calendarDayDto  : calendarDayDtoList) {
//			CalendarDayForm calendarDayForm = this.mapToCalendarDayForm(calendarDayDto);
//			calendarDayFormList.add(calendarDayForm);
//		}
//		return calendarDayFormList;
//	}


}
