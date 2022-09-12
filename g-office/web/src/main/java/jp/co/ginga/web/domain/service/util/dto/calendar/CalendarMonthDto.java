package jp.co.ginga.web.domain.service.util.dto.calendar;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarMonthDto{
	private List<CalendarDayDto> calendarDayDtoList;

//	public List<CalendarDayDto> getCalendarDayDtoList(){
//		if(this.calendarDayDtoList == null) {
//			this.calendarDayDtoList = new ArrayList<CalendarDayDto>();
//		}
//		return this.calendarDayDtoList;
//	}

	public static CalendarMonthDto getInstance() {
		return new CalendarMonthDto();
	}
}
