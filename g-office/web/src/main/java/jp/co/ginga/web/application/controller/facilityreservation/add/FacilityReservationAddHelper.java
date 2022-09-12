package jp.co.ginga.web.application.controller.facilityreservation.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.helper.facility.FacilityFormHelper;
import jp.co.ginga.web.application.util.helper.facilityresertvation.FacilityReservationFormHelper;
import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerDto;

@Component
public class FacilityReservationAddHelper {
	@Autowired
	FacilityReservationFormHelper facilityReservatioFormHelper;

	@Autowired
	FacilityFormHelper facilityFormHelper;

	public FacilityReservationAddForm createFacilityReservationAddForm(FacilityReservationManagerDto facilityReservationManagerDto) {
		FacilityReservationAddForm facilityReservationAddForm = FacilityReservationAddForm.getInstance();
		facilityReservationAddForm.setFacilityForm(this.facilityFormHelper.convertFacilityForm(facilityReservationManagerDto.getFacilityDto()));
		facilityReservationAddForm.setFacilityReservationFormList(this.facilityReservatioFormHelper.convertFacilityReservationFormList(facilityReservationManagerDto.getFacilityReservationDtoList()));
		return facilityReservationAddForm;
	}
}
