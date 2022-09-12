package jp.co.ginga.web.domain.service.util.dto.calendar;

import java.util.ArrayList;
import java.util.List;

import jp.co.ginga.web.domain.service.util.dto.facilityreservation.FacilityReservationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarDayDto {
	private List<FacilityReservationDto> facilityReservationDtoList;
	private String day;
	private int date;

	public List<FacilityReservationDto> getFacilityReservationDtoList(){
		if(this.facilityReservationDtoList == null) {
			this.facilityReservationDtoList = new ArrayList<FacilityReservationDto>();
		}
		return this.facilityReservationDtoList;
	}

	public static CalendarDayDto getInstance() {
		return new CalendarDayDto();
	}
}
