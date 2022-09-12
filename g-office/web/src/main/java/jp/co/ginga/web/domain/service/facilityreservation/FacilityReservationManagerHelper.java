package jp.co.ginga.web.domain.service.facilityreservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.infra.repository.facility.FacilityEntity;
import jp.co.ginga.infra.repository.facilityType.FacilityTypeEntity;
import jp.co.ginga.infra.repository.reservation.ReservationEntity;
import jp.co.ginga.web.domain.service.util.helper.facility.FacilityDtoHelper;
import jp.co.ginga.web.domain.service.util.helper.facilityType.FacilityTypeDtoHelper;
import jp.co.ginga.web.domain.service.util.helper.facilityreservation.FacilityReservationDtoHelper;

@Component
public class FacilityReservationManagerHelper {
	@Autowired
	FacilityDtoHelper facilityDtoHelper;

	@Autowired
	FacilityTypeDtoHelper facilityTypeDtoHelper;

	@Autowired
	FacilityReservationDtoHelper facilityReservationDtoHelper;

	public FacilityReservationManagerDto createFacilityReservationServiceDto(List<FacilityEntity> facilityEntityList, List<FacilityTypeEntity> facilityTypeEntityList) {
		FacilityReservationManagerDto facilityReservationManagerDto = FacilityReservationManagerDto.getInstance();
		facilityReservationManagerDto.setFacilityDtoList(this.facilityDtoHelper.mapToFacilityDtoList(facilityEntityList));
		facilityReservationManagerDto.setFacilityTypeDtoList(this.facilityTypeDtoHelper.mapToFacilityTypeDtoList(facilityTypeEntityList));

		return facilityReservationManagerDto;
	}

	public FacilityReservationManagerDto  createFacilityReservationServiceDto  (List<FacilityEntity> facilityEntityList) {
		FacilityReservationManagerDto facilityReservationManagerDto = FacilityReservationManagerDto.getInstance();
		facilityReservationManagerDto.setFacilityDtoList(this.facilityDtoHelper.mapToFacilityDtoList(facilityEntityList));

		return facilityReservationManagerDto;
	}

	public FacilityReservationManagerDto createFacilityReservationServiceDto(List<ReservationEntity> reservationEntityList, FacilityEntity facilityEntity) {
		FacilityReservationManagerDto facilityReservationManagerDto = FacilityReservationManagerDto.getInstance();
		facilityReservationManagerDto.setFacilityReservationDtoList(this.facilityReservationDtoHelper.mapToFacilityReservationDtoList(reservationEntityList));
		facilityReservationManagerDto.setFacilityDto(this.facilityDtoHelper.mapToFacilityDto(facilityEntity));

		return facilityReservationManagerDto;
	}
	public FacilityReservationManagerDto createFacilityReservationServiceDto(ReservationEntity reservationEntity) {
		FacilityReservationManagerDto facilityReservationManagerDto = FacilityReservationManagerDto.getInstance();
		facilityReservationManagerDto.setFacilityReservationDto(this.facilityReservationDtoHelper.mapToFacilityReservationDto(reservationEntity));
		return facilityReservationManagerDto;
	}
}
