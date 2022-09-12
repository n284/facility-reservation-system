package jp.co.ginga.web.application.controller.facilityreservation.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.helper.facilityresertvation.FacilityReservationFormHelper;
import jp.co.ginga.web.application.util.session.facilityreservation.FacilityReservationSession;
import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerDto;
import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerHelper;

@Component
public class FacilityReservationDetailHelper {
	@Autowired
	FacilityReservationManagerHelper facilityReservationManagerHelper;

	@Autowired
	FacilityReservationFormHelper facilityReservationFormHelper;

	public FacilityReservationDetailForm createFacilityReservationDetailForm(FacilityReservationSession facilityReservationSession) {
		FacilityReservationDetailForm facilityReservationDetailForm = FacilityReservationDetailForm.getInstance();
		facilityReservationDetailForm.setFacilityReservationForm(facilityReservationSession.getFacilityReservationForm());

		return facilityReservationDetailForm;
	}

	public FacilityReservationDetailForm createFacilityReservationDetailForm(FacilityReservationManagerDto facilityReservationManagerDto) {
		FacilityReservationDetailForm facilityReservationDetailForm = FacilityReservationDetailForm.getInstance();
		facilityReservationDetailForm.setFacilityReservationForm(
				this.facilityReservationFormHelper.convertFacilityReservationForm(facilityReservationManagerDto.getFacilityReservationDto())
				);

		return facilityReservationDetailForm;
	}
}
