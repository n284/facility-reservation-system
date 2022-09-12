package jp.co.ginga.web.application.controller.facilityreservation.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ginga.web.application.util.helper.facility.FacilityFormHelper;
import jp.co.ginga.web.application.util.helper.facilityType.FacilityTypeFormHelper;
import jp.co.ginga.web.domain.service.facilityreservation.FacilityReservationManagerDto;

@Component
public class FacilityReservationListHelper {
	@Autowired
	FacilityFormHelper facilityFormHelper;

	@Autowired
	FacilityTypeFormHelper facilityTypeFormHelper;

	public FacilityReservationListForm createFacilityReservationListForm(FacilityReservationManagerDto facilityReservationManagerDto) {
		FacilityReservationListForm facilityReservationListForm = FacilityReservationListForm.getInstance();

		facilityReservationListForm.setFacilityFormList(
				this.facilityFormHelper.convertFacilityFormList(
						facilityReservationManagerDto.getFacilityDtoList()
				)
		);

		if(facilityReservationManagerDto.getFacilityTypeDtoList() != null) {
			facilityReservationListForm.setFacilityTypeFormList(
					this.facilityTypeFormHelper.convertFacilityTypeFormList(
							facilityReservationManagerDto.getFacilityTypeDtoList()
					)
			);
		}

		return facilityReservationListForm;
	}


}
