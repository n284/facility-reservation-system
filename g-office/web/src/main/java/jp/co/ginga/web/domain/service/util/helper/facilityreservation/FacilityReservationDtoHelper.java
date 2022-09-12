package jp.co.ginga.web.domain.service.util.helper.facilityreservation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.infra.repository.reservation.ReservationEntity;
import jp.co.ginga.web.domain.service.util.dto.facility.FacilityDto;
import jp.co.ginga.web.domain.service.util.dto.facilityreservation.FacilityReservationDto;

@Component
public class FacilityReservationDtoHelper {

	@Autowired
	ModelMapper modelMapper;

	public FacilityReservationDto mapToFacilityReservationDto(ReservationEntity reservationEntity){
		FacilityReservationDto facilityReservationDto = this.modelMapper.map(reservationEntity, FacilityReservationDto.class);
		if(reservationEntity.getFacilityEntity() != null) {
			FacilityDto facilityDto = this.modelMapper.map(reservationEntity.getFacilityEntity(), FacilityDto.class);
			facilityReservationDto.setFacilityDto(facilityDto);
		}
		
		return facilityReservationDto;
	}

	public List<FacilityReservationDto> mapToFacilityReservationDtoList(List<ReservationEntity> reservationEntityList){
		List<FacilityReservationDto> facilityReservationDtoList = new ArrayList<FacilityReservationDto>();
		for(ReservationEntity reservationEntity : reservationEntityList) {
			facilityReservationDtoList.add(this.mapToFacilityReservationDto(reservationEntity));
		}

		return facilityReservationDtoList;
	}
}
