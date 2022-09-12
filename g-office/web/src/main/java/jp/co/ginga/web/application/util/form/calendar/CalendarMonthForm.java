package jp.co.ginga.web.application.util.form.calendar;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarMonthForm {
	private List<CalendarDayForm> calendarDayFormList;

	public static CalendarMonthForm getInstance() {
		return new CalendarMonthForm();
	}
}
