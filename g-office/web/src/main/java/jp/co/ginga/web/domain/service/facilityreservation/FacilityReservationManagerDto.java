package jp.co.ginga.web.domain.service.facilityreservation;

import java.util.List;

import jp.co.ginga.web.domain.service.util.dto.calendar.CalendarMonthDto;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.dto.facilityType.FacilityTypeDto;
import jp.co.ginga.web.domain.service.util.dto.facilityreservation.FacilityReservationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityReservationManagerDto {
	private FacilityDto facilityDto;
	private List<FacilityDto> facilityDtoList;

	private List<FacilityTypeDto> facilityTypeDtoList;

	private FacilityReservationDto facilityReservationDto;

	private List<FacilityReservationDto> facilityReservationDtoList;

	private CalendarMonthDto calendarMonthDto;

	private String optimisticRockValue;

	public static FacilityReservationManagerDto getInstance() {
		return new FacilityReservationManagerDto();
	}
}
