package jp.co.ginga.web.domain.service.util.dto.facilityreservation;

import java.sql.Timestamp;

import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacilityReservationDto {
	private int reservationId;
	private FacilityDto facilityDto;
	private Timestamp startTime;
	private Timestamp endTime;
	private UserDto userDto;

	public static FacilityReservationDto getInstance() {
		return new FacilityReservationDto();
	}
}
